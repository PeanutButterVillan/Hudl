#!/usr/bin/env bash

mvn clean verify -Pgrid -Dplatform=test -Dspring.profiles.active=Integration -Dcucumber.options="--tags @Exports"
