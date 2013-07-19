#!/usr/bin/env bash
if [ -z "$CLOUDFOUNDRY_PASSWORD" ]; then
echo "CLOUDFOUNDRY_PASSWORD must be set "
    echo "==== Your current environment====="
    env
    exit 1
fi
ORG="cloudguru"
SPACE="development"
PATH_TO_WAR=$1

gem install cf
cf --script login webmaster@crowdguru.org --password $CLOUDFOUNDRY_PASSWORD -o $ORG -s $SPACE
cf --script push --reset --name crowdguru --path $1

