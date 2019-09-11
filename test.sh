#!/bin/bash
./gradlew cleanTest check jacocoTestReport
sonar-scanner
.gradlew its:test