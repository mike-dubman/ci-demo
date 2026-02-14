#!/usr/bin/env bash
# Structural smoke test for buildImage optimization (PR #123).
# Verifies imageExistsInRegistry and the if/else-if/else chain exist in Matrix.groovy.
set -e
MATRIX=src/com/mellanox/cicd/Matrix.groovy
for pattern in \
  'imageExistsInRegistry' \
  'else if (changed_files.contains(filename))' \
  'else if (image.deps)' \
  'imageExistsInRegistry(img, config)'; do
  if ! grep -q "$pattern" "$MATRIX"; then
    echo "Smoke test failed: pattern not found: $pattern" >&2
    exit 1
  fi
done
echo "Smoke test passed: buildImage optimization structure present in Matrix.groovy"
