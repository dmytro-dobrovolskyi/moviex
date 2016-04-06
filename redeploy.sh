#!/usr/bin/env bash

git -C ~/moviex remote update

LOCAL=$(git -C ~/moviex rev-parse @)
REMOTE=$(git -C ~/moviex rev-parse @{u})

echo $LOCAL
echo $REMOTE

if [ $LOCAL = $REMOTE ]; then
    echo "Already up-to-date"
else
    echo "Needs redeployment. Starting..."
    sudo git -C ~/moviex fetch origin master
    sudo git -C ~/moviex reset origin/master --hard
    sudo chmod -R 777 ~/moviex
    /var/lib/apache-maven/bin/mvn -f ~/moviex/pom.xml -P prod clean install
    sudo /var/lib/tomcat/bin/catalina.sh stop
    sudo rm -fr /var/lib/tomcat/webapps/moviex && rm -f sudo /var/lib/tomcat/webapps/moviex.war
    sudo cp -uf ~/moviex/moviex-server/web/target/moviex.war /var/lib/tomcat/webapps/
    sudo /var/lib/tomcat/bin/catalina.sh start

    echo "Successfully redeployed"
fi
