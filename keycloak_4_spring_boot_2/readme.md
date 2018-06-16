# Keycloak 4 with Spring Boot 2

This project shows a simple integration of Keycloak 4 in Spring Boot 2 applications.

The following versions are used:

* __Docker__: 18.03.1-ce
* __Docker-compose__: 1.21.1
* __Apache Maven__: 3.5.3
* __Spring Boot__: 2.0.3.RELEASE
* __Keycloak__: 4.0.0.Final

## Keycloak Setup

First Keycloak and the database needs to be started via:

```
docker-compose -f keycloak/docker-compose.yml up
```

It imports a demo realm with an example user and sets the admin credentials to `admin:password`. You can visit the administration console at [http://localhost:8081/auth/](http://localhost:8081/auth/).

## Spring Boot Application

After Keycloak started successfully, you can start the Spring Boot application in a separate terminal via

```
mvn package spring-boot:run
```

It is accessible on [http://localhost:8080](http://localhost:8080). After clicking on `All Persons`, the login page from Keycloak shows up. The credentials of the demo user are `username:password`. Signing in with that user should redirect you back to the application, where the restricted list of persons is shown.
