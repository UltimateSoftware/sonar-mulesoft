package com.ultimatesoftware.sonar.plugins.mulesoft.its;

import com.sonar.orchestrator.Orchestrator;
import com.sonar.orchestrator.OrchestratorBuilder;
import com.sonar.orchestrator.build.BuildResult;
import com.sonar.orchestrator.build.SonarScanner;
import com.sonar.orchestrator.locator.FileLocation;
import com.sonar.orchestrator.locator.Location;
import com.sonar.orchestrator.locator.URLLocation;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sonarqube.ws.WsMeasures;
import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;
import org.sonarqube.ws.client.measure.ComponentWsRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class MulesoftTest {
  private final static String PROJECT_KEY = "mulesoft-project";
  private static final String FILE_KEY = "mulesoft-project:src/mule-integration-peopledoc-demographic-information.xml";
  private static final String FILE_WITHOUT_COVERAGE_KEY = "mulesoft-project:src/peopledoc-integration-message-processor.xml";

  @ClassRule
  public static Orchestrator orchestrator;

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  static {
    String defaultRuntimeVersion = "true".equals(System.getenv("SONARSOURCE_QA")) ? null : "7.9";
    OrchestratorBuilder builder = Orchestrator.builderEnv()
      .setOrchestratorProperty("orchestrator.workspaceDir", "build")
      .setSonarVersion(System.getProperty("sonar.runtimeVersion", defaultRuntimeVersion));

    System.out.println(" Hola!!!!" + new File("../build/libs").getAbsolutePath());
    Location pluginLocation = FileLocation.byWildcardMavenFilename(new File("../build/libs"), "sonar-mulesoft-*.jar");
    builder.addPlugin(pluginLocation);
    try {
      builder.addPlugin(URLLocation.create(new URL("https://binaries.sonarsource.com/Distribution/sonar-xml-plugin/sonar-xml-plugin-2.0.1.2020.jar")));
    } catch (MalformedURLException e) {
      throw new IllegalStateException("Failed to download plugin", e);
    }
    orchestrator = builder.build();
  }

  @Test
  public void should_import_coverage() throws IOException {
    SonarScanner build = SonarScanner.create()
      .setProjectKey(PROJECT_KEY)
      .setDebugLogs(true)
      .setSourceDirs("src")
      .setTestDirs("report")
      .setProperty("sonar.coverage.mulesoft.jsonReportPaths", "report/munit-coverage.json")
      .setProperty("sonar.coverage.mulesoft.xmlSourcePaths", "src")
      .setProperty("sonar.java.binaries", ".")
      .setProjectDir(prepareProject("project"));
    orchestrator.executeBuild(build);

    checkCoveredFile();
    checkUncoveredFile();
  }

  @Test
  public void should_give_warning_if_report_doesnt_exist() throws IOException {
    SonarScanner build = SonarScanner.create()
      .setProjectKey(PROJECT_KEY)
      .setDebugLogs(true)
            .setSourceDirs("src")
            .setTestDirs("report")
      .setProperty("sonar.coverage.mulesoft.jsonReportPaths", "invalid.json")
            .setProperty("sonar.coverage.mulesoft.xmlSourcePaths", "src")
      .setProperty("sonar.java.binaries", ".")
      .setProjectDir(prepareProject("project"));
    BuildResult result = orchestrator.executeBuild(build);
    result.getLogs().contains("Report doesn't exist: ");
  }

  @Test
  public void should_import_coverage_if_no_property_given_and_default_is_correct() throws IOException {
    SonarScanner build = SonarScanner.create()
      .setProjectKey(PROJECT_KEY)
      .setDebugLogs(true)
            .setSourceDirs("src")
            .setTestDirs("report")
            .setProperty("sonar.coverage.mulesoft.xmlSourcePaths", "src")
      .setProperty("sonar.java.binaries", ".")
      .setProjectDir(prepareProject("project"));
    orchestrator.executeBuild(build);
    checkCoveredFile();
    checkUncoveredFile();
  }

  @Test
  public void should_not_import_coverage_if_report_contains_files_that_cant_be_found() throws IOException {
    SonarScanner build = SonarScanner.create()
      .setProjectKey(PROJECT_KEY)
      .setDebugLogs(true)
            .setSourceDirs("src")
            .setTestDirs("report")
            .setProperty("sonar.coverage.mulesoft.xmlSourcePaths", "src")
      .setProperty("sonar.coverage.mulesoft.jsonReportPaths", "invalid-sources.json")
      .setProperty("sonar.java.binaries", ".")
      .setProjectDir(prepareProject("project"));
    orchestrator.executeBuild(build);
    checkNoMulesoftCoverage();
  }

  private void checkNoMulesoftCoverage() {
    Map<String, Double> measures = getCoverageMeasures(FILE_KEY);
    assertThat(measures.get("line_coverage")).isNull();
    assertThat(measures.get("lines_to_cover")).isNull();
    assertThat(measures.get("uncovered_lines")).isNull();
    assertThat(measures.get("branch_coverage")).isNull();
    assertThat(measures.get("conditions_to_cover")).isNull();
    assertThat(measures.get("uncovered_conditions")).isNull();
    assertThat(measures.get("coverage")).isNull();
  }

  private void checkUncoveredFile() {
    Map<String, Double> measures = getCoverageMeasures(FILE_WITHOUT_COVERAGE_KEY);
    assertThat(measures.get("line_coverage")).isEqualTo(0.0);
    assertThat(measures.get("lines_to_cover")).isEqualTo(13.0);
    assertThat(measures.get("uncovered_lines")).isEqualTo(13.0);
    assertThat(measures.get("branch_coverage")).isNull();
    assertThat(measures.get("conditions_to_cover")).isNull();
    assertThat(measures.get("uncovered_conditions")).isNull();
    assertThat(measures.get("coverage")).isEqualTo(0.0);
  }

  private void checkCoveredFile() {
    Map<String, Double> measures = getCoverageMeasures(FILE_KEY);
    assertThat(measures.get("line_coverage")).isEqualTo(85.7);
    assertThat(measures.get("lines_to_cover")).isEqualTo(28.0);
    assertThat(measures.get("uncovered_lines")).isEqualTo(4.0);
    assertThat(measures.get("branch_coverage")).isNull();
    assertThat(measures.get("conditions_to_cover")).isNull();
    assertThat(measures.get("uncovered_conditions")).isNull();
    assertThat(measures.get("coverage")).isEqualTo(85.7);
  }

  private Map<String, Double> getCoverageMeasures(String fileKey) {
    List<String> metricKeys = Arrays.asList("line_coverage", "lines_to_cover",
      "uncovered_lines", "branch_coverage",
      "conditions_to_cover", "uncovered_conditions", "coverage");

    return getWsClient().measures().component(new ComponentWsRequest()
      .setComponent(fileKey)
      .setMetricKeys(metricKeys))
      .getComponent().getMeasuresList()
      .stream()
      .collect(Collectors.toMap(WsMeasures.Measure::getMetric, m -> Double.parseDouble(m.getValue())));
  }

  private WsClient getWsClient() {
    return WsClientFactories.getDefault().newClient(HttpConnector.newBuilder()
      .url(orchestrator.getServer().getUrl())
      .build());
  }

  private File prepareProject(String name) throws IOException {
    Path projectRoot = Paths.get("src/test/resources").resolve(name);
    File targetDir = temp.newFolder(name);
    FileUtils.copyDirectory(projectRoot.toFile(), targetDir);
    return targetDir;
  }
}
