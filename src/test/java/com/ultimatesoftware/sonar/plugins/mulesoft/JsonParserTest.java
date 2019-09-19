/*
 * SonarQube JaCoCo Plugin
 * Copyright (C) 2018-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.ultimatesoftware.sonar.plugins.mulesoft;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParserTest {
  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  private Path load(String name) throws URISyntaxException {
    return Paths.get(this.getClass().getClassLoader().getResource(name).toURI());
  }

  @Test
  public void should_parse_all_items_in_report() throws URISyntaxException, IOException {
    Path sample = load("munit-coverage.json");
    JsonParser report = new JsonParser(sample);
    List<JsonParser.SourceFile> sourceFiles = report.parse();

    assertThat(sourceFiles).hasSize(2);
    assertThat(sourceFiles.stream().mapToInt(sf -> sf.lines().size()).sum()).isEqualTo(12);
  }

  @Test
  public void should_parse_all_attributes() throws URISyntaxException, IOException {
    Path sample = load("munit-coverage.json");
    JsonParser report = new JsonParser(sample);
    List<JsonParser.SourceFile> sourceFiles = report.parse();

    assertThat(sourceFiles).hasSize(2);
    assertThat(sourceFiles.stream().mapToInt(sf -> sf.lines().size()).sum()).isEqualTo(12);
    assertThat(sourceFiles.get(0).name()).isEqualTo("test-upload-file.xml");
    assertThat(sourceFiles.get(0).packageName()).isEqualTo("");
    assertThat(sourceFiles.get(0).lines().size()).isEqualTo(7);
    assertThat(sourceFiles.get(0).lines().get(0).missedInstrs()).isEqualTo(0);
    assertThat(sourceFiles.get(0).lines().get(0).coveredInstrs()).isEqualTo(1);
    assertThat(sourceFiles.get(0).lines().get(0).missedBranches()).isEqualTo(0);
    assertThat(sourceFiles.get(0).lines().get(0).coveredBranches()).isEqualTo(0);
  }

  @Test
  public void should_fail_if_report_is_not_json() throws IOException {
    Path filePath = temp.newFile("munit-coverage-not-json.txt").toPath();
    JsonParser report = new JsonParser(filePath);

    exception.expect(JsonMappingException.class);
    report.parse();
  }
}
