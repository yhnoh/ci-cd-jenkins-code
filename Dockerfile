FROM openjdk:8-jdk

ARG JAR_FILE=build/libs/ci-cd-jenkins-code-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.js

ENTRYPOINT ["java", "-jar", "/app.js"]