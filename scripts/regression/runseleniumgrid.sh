#!/usr/bin/env bash


mvn clean verify -Pgrid -Dspring.profiles.active=Regression -Dcucumber.options="--tags @devops"