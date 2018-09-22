FROM maven:3.5-jdk-8 as builder
WORKDIR /
COPY . /
RUN mvn clean package

FROM openjdk:8-jdk-alpine
LABEL maintainer="j.innerbichler@gmail.com"
COPY --from=builder /target/docker-build-demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]