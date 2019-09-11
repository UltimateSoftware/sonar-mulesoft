# SonarQube Mulesoft Plugin
https://travis-ci.org/UltimateSoftware/sonar-mulesoft.svg?branch=master

A SonarQube Plugin for MuleSoft workflow projects. The plugin scans MuleSoft reports and provides coverage validation.

## Installation
### Market Place
Coming soon...

### Manual
#### Build the Jar
To build the project use the Gradle task `shadowJar` under `shadow` tasks, this ensures you build a fat jar containing all the dependencies.
```java
./gradlew shadowJar
```

#### Installation
Follow the [guide](https://docs.sonarqube.org/latest/setup/install-plugin/) on SonarQube's official documentation using the jar you just built.

#### Configuration
There are two configuration properties that can be set for the plugin.

MuleSoft Report location (Your munit-coverage.json file location):
```bash
sonar.coverage.mulesoft.jsonReportPaths
```
MuleSoft source location (The location of your source XML files):
```bash
sonar.coverage.mulesoft.xmlSourcePaths
```
