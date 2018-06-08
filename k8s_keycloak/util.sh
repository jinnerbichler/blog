#!/usr/bin/env bash


set -e    # Exits immediately if a command exits with a non-zero status.

if [[ "$1" = "keycloak-db" ]]; then

    helm install --name keycloak-db \
        --set postgresUser=admin \
        --set postgresPassword=password \
        --set postgresDatabase=keycloak-db \
        stable/postgresql

elif [ "$1" == "keycloak-start" ]; then

    kubectl apply -f ./kubernetes/keycloak-deployment.yml

elif [ "$1" == "keycloak-open" ]; then

    minikube service keycloak

elif [ "$1" == "keycloak-url" ]; then

    minikube service keycloak --url

elif [ "$1" == "keycloak-logs" ]; then

	POD_NAME=$(kubectl get pods -l name=keycloak -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
	kubectl logs -f ${POD_NAME}

elif [ "$1" == "app-open" ]; then

    minikube service person-app

elif [ "$1" == "app-url" ]; then

    minikube service person-app --url

elif [ "$1" == "app-logs" ]; then

	POD_NAME=$(kubectl get pods -l name=person-app -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
	kubectl logs -f ${POD_NAME}

fi

fi