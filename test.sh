#!/bin/bash
.gradlew shadowjar
./gradlew cleanTest check jacocoTestReport