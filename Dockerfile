FROM java:8-alpine

RUN mkdir -p /app
WORKDIR /app
COPY target/gitter2slack.jar /app/

CMD ["java", "-jar", "gitter2slack.jar"]