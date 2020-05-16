# spring-boot-mongo

Pre-requisites:
--------
    - Install Git
    - Install Maven
    - Install Docker
    - EKS Cluster
    
Clone code from github:
-------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/spring-boot-mongo/
    
Build Maven Artifact:
-------
    mvn clean install -DskipTests=true
 
Build Docker image for Springboot Application
--------------
    docker build -t vamsitechtuts/spring-boot-mongo .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push vamsitechtuts/spring-boot-mongo
    
Deploying Mongo with kubectl apply:
-----------
    kubectl apply -f mongo-deployment.yml
    kubectl apply -f mongo-service.yml
    
Deploy Spring Application:
--------
    kubectl apply -f spring-deployment.yml
    kubectl apply -f spring-service.yml
    
Check deployments, pods and services:
-------
    kubectl get deploy
    kubectl get pods
    kubectl get svc
    
Now Goto Loadbalancer and check whether service comes Inservice or not, If it comes Inservice copy DNS Name of Loadbalancer and Give in WebUI
