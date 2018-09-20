#!/usr/bin/env bash

sample_job=$(docker run -d busybox /bin/sh -c "while true; do echo Docker; sleep 1; done")
docker logs -f $sample_job
