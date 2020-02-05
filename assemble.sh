#!/bin/bash
set -o errexit
set -o nounset

build_api(){
    echo "Fetching changes"
    git pull origin master;
    echo "Building"
    mvn clean package -DskipTests;
    echo "Finish build_api"
}

build_api

exit $?


