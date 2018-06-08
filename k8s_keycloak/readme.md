## Setup Prerequisites

This project shows a simple Spring Boot application with Keycloak. Both, the application and Keycloak (including the database), will be deployed to a Kubernetes cluster.

The Kubernetes cluster is created via Minikube, which provides tools for creating clusters on a local machine easily. Keycloak, the database, and the Spring Boot application are deployed to the newly created cluster. 
The following versions are used throughout the example:

* __Minikube__: v0.25.0
* __Docker__: 18.03.1-ce
* __Kubectl__: v1.10.1
* __Kubernetes__: v1.9.0 
* __Helm__: v2.8.2
* __Spring Boot__: 1.5.3.RELEASE
* __Keycloak__: 3.4.3.Final

More details on the setup and the example application can be found in the corresponding [blog article](https://medium.com/@jinnerbichler/securing-spring-boot-applications-with-keycloak-on-kubernetes-76cdb6b8d674).