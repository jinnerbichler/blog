#!/usr/bin/env bash

docker build -t my-onbuild-app .
docker run -it --rm --name my-running-app my-onbuild-app