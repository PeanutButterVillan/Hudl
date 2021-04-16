#!/usr/bin/env bash
platform=$1
demoUrl=$2
appUrl=$3

echo "Download Maven in Silent Mode"
TEST=$(mvn dependency:go-offline >gooffline.txt 2>&1; echo $?)
if [ "$TEST" != "0" ]; then
    cat gooffline.txt | tail -10
    exit 1
fi
rm gooffline.txt
echo "Finished Download Maven in Silent Mode"
mvn clean verify -Pgrid -Dspring.profiles.active=Regression \
-Dcucumber.options="--tags @devops" \
-Dplatform=$platform \
-Didev.demo.url=$demoUrl \
-Didev.app.url=$appUrl
