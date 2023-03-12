#!/bin/bash -xe
APP=$1
DEPLOY_TAG=$2

if [ $# -lt 2 ]; then
  exit
fi

RUNNING_CONTAINERS=$(docker ps -qf "name=${APP}")
RUNNING_CONTAINERS_SCALE=$(echo $RUNNING_CONTAINERS | wc -w | xargs)

DEPLOY_TAG_SCALE=$(($RUNNING_CONTAINERS_SCALE * 2))
if [[ $RUNNING_CONTAINERS_SCALE == 0 ]]; then
    DEPLOY_TAG_SCALE=1
fi

TAG=$DEPLOY_TAG docker compose up -d $APP --scale $APP=$DEPLOY_TAG_SCALE --no-recreate --no-build

until [ "`docker ps -q -f "name=${APP}" -f "health=healthy" | wc -l | xargs`" == $DEPLOY_TAG_SCALE ]; do
    sleep 0.1;
done;

if [[ $RUNNING_CONTAINERS_SCALE > 0 ]]; then
    docker rm $RUNNING_CONTAINERS --force
fi
