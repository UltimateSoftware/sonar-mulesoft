#!/bin/bash
./gradlew shadowjar
./gradlew its:integrationTest
