FROM eclipse-temurin:21-jdk-alpine
MAINTAINER bnpf
COPY target/bookstore-0.0.1-SNAPSHOT.jar bookstore-v0.jar
ENTRYPOINT ["java","-jar","/bookstore-v0.jar"]