#!/usr/bin/env sh
gradle=./gradlew
if [ ! -e "$gradle" ]; then
    gradle="gradle"
fi
echo "Using gradle path as $gradle"

${gradle} bootJar