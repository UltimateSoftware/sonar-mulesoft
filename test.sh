#!/bin/bash
./gradlew cleanTest check jacocoTestReport
sonar-scanner -X
.gradlew its:test