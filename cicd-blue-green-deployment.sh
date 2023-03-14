#!/bin/bash -xe
APP=$1
GREEN_CONTAINER_DEPLOY_TAG=$2

if [ $# -lt 2 ]; then
  exit
fi

BLUE_CONTAINERS=$(docker ps -qf "name=${APP}")
BLUE_CONTAINERS_SCALE=$(echo "$BLUE_CONTAINERS" | wc -w | xargs)

GREEN_CONTAINERS_SCALE=$((BLUE_CONTAINERS_SCALE * 2))
if [[ $BLUE_CONTAINERS_SCALE == 0 ]]; then
    GREEN_CONTAINERS_SCALE=1
fi

TAG=$GREEN_CONTAINER_DEPLOY_TAG docker compose up -d "$APP" --scale "$APP=$GREEN_CONTAINERS_SCALE" --no-recreate --no-build

until [ "$(docker ps -q -f "name=${APP}" -f "health=healthy" | wc -l | xargs)" == $GREEN_CONTAINERS_SCALE ]; do
    sleep 0.1;
done;

if [[ $BLUE_CONTAINERS_SCALE -gt 0 ]]; then
    docker kill --signal=SIGTERM "$BLUE_CONTAINERS"
fi
