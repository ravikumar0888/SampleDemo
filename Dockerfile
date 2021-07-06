FROM openjdk:8-jre-slim

WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/selenium-docker.jar selenium-docker.jar
ADD  target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD  target/libs libs

# Add the suite xmls
ADD testng.xml testng.xml

# Command line to execute the test
# Expects below environment variables
# BROWSER = chrome / firefox
# MODULE  = testng-module
# GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dsurefire.useFile=false -Djavax.net.debug=all -Ddeployment.security.SSLv2Hello=false -Ddeployment.security.SSLv3=false -Ddeployment.security.TLSv1=false -Ddeployment.security.TLSv1.1=true -Ddeployment.security.TLSv1.2=true -Dtestng.dtd.http=true -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE
