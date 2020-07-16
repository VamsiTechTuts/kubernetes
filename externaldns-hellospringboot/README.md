# spring-boot-hello

Pre-requisites:
-----
  - Install Java
  - Install GIT
  - Install Maven
  - Install Docker
  - EKS Cluster setup
  - Install Helm3
  - Create Hosted zone with domain name with in Route53
  - Need to have cerified DNS with primary.key & certificate.crt
  - Create CNAME record set for DNS name with in Hosted Zone
  
Clone code from github:
-------------
    git clone https://github.com/Naresh240/spring-boot-hello.git
    cd pring-boot-hello
Build Maven Artifact:
------------
    mvn clean install
Build Docker image for Springboot Application:
------------
    docker build -t naresh240/spring-boot-hello:latest .
Docker login:
-------
    docker login
Push docker image to dockerhub:
--------
    docker push vamsitechtuts/spring-boot-hello:latest

Create secret by using kubectl command:
-----------
    kubectl create secret tls spring-tls \
      --key springboot-tls.key \
      --cert springboot-tls.crt
This secrect we need to give in "ingress.yml" file
Deploy Nginx-Ingress-Controller using below commands:
-----------
Add NGINX Helm repository:

    helm repo add nginx-stable https://helm.nginx.com/stable
    helm repo update

To install the chart with the release name my-release (my-release is the name that you choose):

    helm install my-release nginx-stable/nginx-ingress

Note:
----
Create "A" record set with nginx-control loadbalancer

Deploy Springboot Application on EKS-Cluster:
------------
    kubectl apply -f deployement.yml
Expose Springboot Application with LoadBalancer service:
-----------
    kubectl apply -f service.yml
Execute ingress on EKS-Cluster:
---------
    kubectl apply -f ingress.yaml
Check Deployments:
--------
    kubectl get deployments
Check pods:
--------
    kubectl get pods
Check Services:
--------
    kubectl get svc
Check Ingress:
---------
    kubectl get ingress
Check output for the application using dns name:
--------
    curl https://dnsname
Check in browser:
-----
    https://dnsname

Clean UP:
------
    kubectl delete ingress springboot-ingress
    helm delete my-release
    kubectl delete svc spring-boot-hello-loadbalancer
    kubectl delete deployments spring-boot-hello
