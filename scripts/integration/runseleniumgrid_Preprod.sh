#!/usr/bin/env bash

mvn clean verify -Pgrid -Dplatform=preprod -Dspring.profiles.active=Integration -Dcucumber.options="--tags @Preprod"
