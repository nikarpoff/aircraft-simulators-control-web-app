# Dockerfile
FROM eclipse-temurin:17

RUN mkdir -p /usr/src/myapp && mkdir -p /usr/src/myapp/target

COPY target/WebServer-1.0-SNAPSHOT.jar /usr/src/myapp
COPY keystore.jks /usr/src/myapp/target
COPY server.cer /usr/src/myapp/target

WORKDIR /usr/src/myapp
ENTRYPOINT ["java", "-jar","WebServer-1.0-SNAPSHOT.jar"]