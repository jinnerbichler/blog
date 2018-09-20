#!/usr/bin/env bash

docker build -t my-app .
docker run -it --rm --name my-running-app my-app
