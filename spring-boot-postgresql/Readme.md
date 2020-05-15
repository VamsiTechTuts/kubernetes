## Spring Boot, PostgreSQL, JPA, Hibernate REST API Demo

Pre-requisites:
--------
    - Install Git
    - Install Maven
    - Install Docker
    - EKS Cluster
    
Clone code from github:
-------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/spring-boot-postgresql/
    
Build Maven Artifact:
-------
    mvn clean install -DskipTests=true
 
Build Docker image for Springboot Application
--------------
    docker build -t vamsitechtuts/springbootpostgresrestapidemo .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push vamsitechtuts/springbootpostgresrestapidemo

Encode USERNAME and PASSWORD of Postgres using following commands:
--------
    echo -n "postgresadmin" | base64
    echo -n "admin123" | base64
Create the Secret using kubectl apply:
-------
    kubectl apply -f postgres-secrets.yml

Create PV and PVC for Postgres using yaml file:
-----
    kubectl apply -f postgres-storage.yaml

Deploying Postgres with kubectl apply:
-----------
    kubectl apply -f postgres-deployment.yaml
    kubectl apply -f postgres-service.yaml

Create a config map with the hostname of Postgres
-------------
    kubectl create configmap hostname-config --from-literal=postgres_host=$(kubectl get svc postgres -o jsonpath="{.spec.clusterIP}")
    
Deploy Spring Application:
--------
    kubectl apply -f springboot-deployment.yml
    kubectl apply -f springboot-service.yml
    
Check secrets:
-------
    kubectl get secrets
    kubectl get configmaps
    kubectl get pv
    kubectl get pvc
    kubectl get deploy
    kubectl get pods
    kubectl get svc
    
Now Goto Loadbalancer and check whether service comes Inservice or not, If it comes Inservice copy DNS Name of Loadbalancer and Give in POSTMAN App

Use POST Method:
--------
    http://a64d28d4626484b2687437bb81935d71-65005852.us-west-2.elb.amazonaws.com:8080/questions
    select raw and Json format with in the Body section
        {
	          "title": "Which country you belongs too?",
	          "description": "I am from India..."
        }
Use GET Method:
-------
    http://a64d28d4626484b2687437bb81935d71-65005852.us-west-2.elb.amazonaws.com:8080/questions
    
Use POST Method:
--------
http://<LoadBalancer-DNS>:8080/questions/{questionId}/answers
    
    http://a64d28d4626484b2687437bb81935d71-65005852.us-west-2.elb.amazonaws.com:8080/questions/1000/answers
    select raw and Json format with in the Body section
        {
	          "text": "I am an Indian"
        }
  
Use GET Method:
-------
    http://a64d28d4626484b2687437bb81935d71-65005852.us-west-2.elb.amazonaws.com:8080/questions/1000/answers

You can Test other API also.........

Cluean UP process:
-------
    kubectl delete deploy spring-boot-postgres-sample postgres
    kubectl delete svc spring-boot-postgres-sample postgres
    kubectl delete pvc postgres-pv-claim
    kubectl delete pv postgres-pv-volume
    kubectl delete secrets postgres-secrets
    kubectl delete configmaps hostname-config
