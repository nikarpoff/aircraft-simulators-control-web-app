# Dockerfile
FROM eclipse-temurin:17

RUN mkdir -p /usr/src/myapp
COPY target/Simulator-1.0-SNAPSHOT.jar /usr/src/myapp

RUN mkdir -p /usr/src/myapp/target

WORKDIR /usr/src/myapp
ENTRYPOINT ["java", "-jar", "Simulator-1.0-SNAPSHOT.jar"]