# spring-boot-hello

Pre-requisites:
-----
  - Install Java
  - Install GIT
  - EKS Cluster setup
  - Create Hosted zone with domain name with in Route53
  - Need to have cerified DNS with primary.key & certificate.crt
  - Create CNAME record set for DNS name with in Hosted Zone

Check Hosted zone with domain name with in ROute53:
-----------

![image](https://user-images.githubusercontent.com/63221837/87849166-fcb49780-c903-11ea-82e3-223ac37d408d.png)

I purchased doamin from Godaddy site and added Nameservers here:
---

![image](https://user-images.githubusercontent.com/63221837/87849183-279eeb80-c904-11ea-9a5d-0f6688c42293.png)

Cretificate issue from https://www.sslforfree.com
----

![image](https://user-images.githubusercontent.com/63221837/87849220-7187d180-c904-11ea-9329-3b168331d2f0.png)

Clone code from github:
-------------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd externaldns-jenkins

Create secret by using kubectl command:
-----------
    kubectl create secret tls jenkins-tls \
      --key private.key \
      --cert certificate.crt
      
This secrect we need to give in "ingress.yml" file
Deploy Nginx-Ingress-Controller using below commands:
-----------
Add NGINX Helm repository:

    cd nginx-ingress-controller
    kubectl apply -f .

Note:
----
Create "A" record set with nginx ingress controler loadbalancer

Deploy Springboot Application on EKS-Cluster:
------------
    kubectl apply -f jenkins-deploy.yaml
Expose Springboot Application with LoadBalancer service:
-----------
    kubectl apply -f jenkins-svc.yaml
Execute ingress on EKS-Cluster:
---------
    kubectl apply -f jenkins-ingress.yml
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
    kubectl delete ingress jenkins-ingress
    kubectl delete svc jenkins-master-svc
    kubectl delete deployments jenkins-master
