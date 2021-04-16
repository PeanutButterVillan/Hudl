#!/usr/bin/env bash

mvn clean verify -Pgrid -Dplatform=sandpit_latest -Dspring.profiles.active=Integration -Dcucumber.options="--tags @Sandpit_Latest"
