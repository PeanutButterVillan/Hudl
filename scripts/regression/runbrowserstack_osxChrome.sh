#!/usr/bin/env bash
#2 arguments passed in when running through VStack
localid=$1
browserstackurl=$2
current_date_time="`date +%Y%m%d%H%M%S`";

mvn clean verify -Pbstack \
-Dbrowser=osxChrome \
-Dbrowserstackurl=$browserstackurl \
-Dlocalidentifier=$localid \
-Dspring.profiles.active=Regression \
-Dbuild=osxChrome$current_date_time