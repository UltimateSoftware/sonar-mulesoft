#!/bin/bash
./gradlew shadowjar
./gradlew check jacocoTestReport
