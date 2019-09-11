#!/bin/bash
.gradlew shadowjar
ls
./gradlew check jacocoTestReport
ls