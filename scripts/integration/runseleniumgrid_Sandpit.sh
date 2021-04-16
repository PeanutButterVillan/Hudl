#!/usr/bin/env bash

mvn clean verify -Pgrid -Dplatform=sandpit -Dspring.profiles.active=Integration -Dcucumber.options="--tags @Sandpit"