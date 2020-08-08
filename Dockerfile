FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

RUN mvn clean install

COPY target/b2w_test-0.0.1-SNAPSHOT.jar $PROJECT_HOME/b2w-test-api.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "./b2w-test-api.jar"]