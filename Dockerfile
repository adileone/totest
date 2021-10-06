FROM adeo-docker-public.jfrog.io/dockerfiles-collection/openjdk-jar-runner-datadog-agent:11.0.8-jre-0.68.0-slim-buster
COPY ./target/*.jar $USER_HOME/app.jar
