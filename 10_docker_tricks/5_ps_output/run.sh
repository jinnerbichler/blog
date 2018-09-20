#!/usr/bin/env bash

# more info: https://container42.com/2016/03/27/docker-quicktip-7-psformat/
docker run busybox echo "hello from busybox"

docker ps -a --format "table {{.Names}}\\t{{.Image}}\\t{{.Status}}"

