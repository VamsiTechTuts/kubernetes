# nodejs-kubernetes

Pre-requisites:
------
    - Install Git
    - Install npm
    - Install Docker
    - EKS Cluster
   
Install npm:
----
  sudo yum install -y gcc-c++ make
  curl -sL https://rpm.nodesource.com/setup_13.x | sudo -E bash -
  sudo yum install -y nodejs
	
Clone code from github:
-------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/nodejs-k8s/
Build Artifact:
-------
    npm install
Build Docker image for nodejs Application
--------------
    docker build -t vamsitechtuts/nodejs-k8s .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push vamsitechtuts/nodejs-k8s:latest
Create helm chart:
---------
    helm create chart
Edit values.yml file:
-----------
    vi chart/values.yaml
Replace image, and service details like as shown in below figure
![1](https://user-images.githubusercontent.com/63221837/82455777-f99e5600-9ad0-11ea-8954-32c1cc33df4c.png)
![2](https://user-images.githubusercontent.com/63221837/82455779-facf8300-9ad0-11ea-9db9-dbeae978efc0.png)
Edit deployment.yml file:
------------
    vi chart/templates/deployment.yaml
Replace container port number like as shown in below figure
![3](https://user-images.githubusercontent.com/63221837/82455783-facf8300-9ad0-11ea-9699-c8b09cf471cd.png)
Deploy nodejs application using helm charts:
-------------
    helm install nodejs-k8s chart
Check Pods:
--------
    kubectl get pods
Check Services:
--------
    kubectl get svc
Check whether the service comes Inservice or not:
----------
Goto EC2 Service --> Loadbalancers
![1](https://user-images.githubusercontent.com/63221837/82456547-feafd500-9ad1-11ea-946e-9a9ea88cf065.png)
Copy DNS name and check in WebUI along with portnumber:
![2](https://user-images.githubusercontent.com/63221837/82456762-46cef780-9ad2-11ea-9cce-2ea29dabe637.png)

CleanUP Nodejs application:
------------
    helm delete nodejs-k8s
