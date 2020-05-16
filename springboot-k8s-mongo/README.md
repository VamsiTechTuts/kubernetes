# springboot-k8s-mongo

Pre-requisites:
--------
    - Install Git
    - Install Maven
    - Install Docker
    - EKS Cluster
    
Clone code from github:
-------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/springboot-k8s-mongo/
    
Build Maven Artifact:
-------
    mvn clean install -DskipTests=true
 
Build Docker image for Springboot Application
--------------
    docker build -t vamsitechtuts/springboot-k8s-mongo .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push vamsitechtuts/springboot-k8s-mongo

Encode USERNAME and PASSWORD of Postgres using following commands:
--------
    echo -n "mongoadmin" | base64
    echo -n "admin123" | base64
Create the Secret using kubectl apply:
-------
    kubectl apply -f mongo-secret.yml

Create PV and PVC for Mongo using yaml file:
-----
    kubectl apply -f mongo-pv.yml
    kubectl apply -f mongo-pvc.yml
    
Deploying Mongo with kubectl apply:
-----------
    kubectl apply -f mongo-deployment.yml
    kubectl apply -f mongo-service.yml

Create configmaps for URL which we use in Springboot:
-------
    kubectl apply -f mongo-config.yml
Deploy Springboot Application:
-------------
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
    
Now Goto Loadbalancer and check whether service comes Inservice or not, If it comes Inservice copy DNS Name of Loadbalancer 
Check in POSTMAN App:
-----

check in web UI:
----
 
Now we can cleanup by using below commands:
--------
    kubectl delete deploy postgres sonarqube
    kubectl delete svc postgres sonarqube
    kubectl delete pvc postgres-pv-claim sonar-data sonar-extensions
    kubectl delete pv postgres-pv-volume sonar-pv-data sonar-pv-extensions
    kubectl delete configmaps sonar-config
    kubectl delete secrets postgres-secrets
