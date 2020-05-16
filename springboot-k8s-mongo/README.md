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
    
Give permission for service which is running under same namespace by using rolebinding
----------------------
    kubectl create rolebinding default-view \
      --clusterrole=view \
      --serviceaccount=default:default \
      --namespace=default

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

POST Method you can check in POSTMAN App:
--------------
    af43efdd8377b4896841fbc4ca8da55f-1519337998.us-west-2.elb.amazonaws.com:8080/addProduct
    {
	"id":"100",
	"productId":"100",
	"description":"my prodcut",
	"price":"100.23"
    }
![1](https://user-images.githubusercontent.com/63221837/82110586-3aa70b00-975d-11ea-8f63-c6fb231e6dbf.png)

Get Methods you can check in web UI:
----------------
    af43efdd8377b4896841fbc4ca8da55f-1519337998.us-west-2.elb.amazonaws.com:8080/findAllProducts
    af43efdd8377b4896841fbc4ca8da55f-1519337998.us-west-2.elb.amazonaws.com:8080/findProduct/100
    af43efdd8377b4896841fbc4ca8da55f-1519337998.us-west-2.elb.amazonaws.com:8080/deleteProduct/100
 
Now we can cleanup by using below commands:
--------
    kubectl delete deploy mongo spring-mongo-service
    kubectl delete svc mongodb-service spring-mongo-service
    kubectl delete pvc mongo-pv-claim
    kubectl delete pv mongo-pv-volume
    kubectl delete configmaps mongo-conf
    kubectl delete secrets mongo-secret
