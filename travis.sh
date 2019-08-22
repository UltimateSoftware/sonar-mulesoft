#!/usr/bin/env bash
set -euo pipefail

function configureTravis {
  mkdir -p ~/.local
  curl -sSL https://github.com/SonarSource/travis-utils/tarball/v49 | tar zx --strip-components 1 -C ~/.local
  source ~/.local/bin/install
}

configureTravis

./gradlew test
