# spring-boot-hello

Pre-requisites:
-----
  - Install Java
  - Install GIT
  - Install Maven
  - Install Docker
  - EKS Cluster setup
  
Clone code from github:
-------------
    https://github.com/VamsiTechTuts/kubernetes.git
    kubernetes/spring-boot-hello
Build Maven Artifact:
------------
    mvn clean install
Build Docker image for Springboot Application:
------------
    docker build -t vamsitechtuts/spring-boot-hello:latest .
Docker login:
-------
    docker login
Push docker image to dockerhub:
--------
    docker push vamsitechtuts/spring-boot-hello:latest
Deploy Springboot Application on EKS-Cluster:
------------
    kubectl apply -f deployement.yml
Expose Springboot Application with LoadBalancer service:
-----------
    kubectl apply -f loadbalancer-service.yml
Expose Springboot Application with LoadBalancer service:
-----------
    kubectl apply -f nodeport-service.yml
Check Deployments:
--------
    kubectl get deployments
Check pods:
--------
    kubectl get pods
Check Services:
--------
    kubectl get svc
![1](https://user-images.githubusercontent.com/63221837/82745663-9ba99100-9da4-11ea-8eb3-7f61b960e1d5.png)
Open EC2 Service of AWS:
------------
![2](https://user-images.githubusercontent.com/63221837/82745664-9ba99100-9da4-11ea-927a-e5f3c5181a51.png)
Click on security group of Node and allow NoadPort:
------------
![3](https://user-images.githubusercontent.com/63221837/82745665-9c422780-9da4-11ea-8f5b-bd211e41da68.png)
Check Nodeport service with web UI:
----------
    http://3.219.240.202:30001/
![4](https://user-images.githubusercontent.com/63221837/82745666-9cdabe00-9da4-11ea-9f1a-3e1e62a3117f.png)
Check wether LoadBalancer came Inservice or not:
Goto EC2 serice and click on Load balancer
![5](https://user-images.githubusercontent.com/63221837/82745659-99dfcd80-9da4-11ea-9984-9df224e7d338.png)
Check Load balancer service with web UI:
--------------
    http://a9e4ed685a62e47fab8f7f2b1c36078d-461031710.us-east-1.elb.amazonaws.com:8080/
![6](https://user-images.githubusercontent.com/63221837/82745662-9b10fa80-9da4-11ea-847f-3a1eeef251be.png)
Clean UP:
------
    kubectl delete svc spring-boot-hello-loadbalancer spring-boot-hello-nodeport
    kubectl delete deployments spring-boot-hello
