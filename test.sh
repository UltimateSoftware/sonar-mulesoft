#!/bin/bash
.gradlew shadowjar
ls build
./gradlew check jacocoTestReport
ls build