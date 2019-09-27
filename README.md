# SonarQube Mulesoft Plugin

[![Build Status](https://travis-ci.org/UltimateSoftware/sonar-mulesoft.svg?branch=master)](https://travis-ci.org/UltimateSoftware/sonar-mulesoft) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=UltimateSoftware_sonar-mulesoft&metric=alert_status)](https://sonarcloud.io/dashboard?id=UltimateSoftware_sonar-mulesoft)

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
## Changelog
### Release 0.5.1
* The release Jar is now bundled with all dependencies.
### Release 0.5.0
* This is the initial release.
* Basic support for counting the flow coverage. The total number of processors for all flows is considered in the coverage weighting.