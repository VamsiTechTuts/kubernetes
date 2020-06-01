# Upgrade Application with Helm3

Pre-requisites:
------
    - Install Git
    - Install npm
    - Install Docker
    - EKS-Cluster
    - Install helm

Install Git:
-------
    yum install git -y

Install npm:
-------
    sudo yum install -y gcc-c++ make
    curl -sL https://rpm.nodesource.com/setup_13.x | sudo -E bash -
    sudo yum install -y nodejs

Install Docker:
------
    yum install docker -y
    service docker start
    
Install helm:
-------
    curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3
    chmod 700 get_helm.sh
Now edit get_helm.sh file
   
    vi get_helm.sh
    
![1](https://user-images.githubusercontent.com/63221837/83378838-9fb65e00-a3f7-11ea-9abf-aa042368341c.png)

    ./get_helm.sh

Clone code from github:
-------
    git clone https://github.com/Naresh240/docker.git
    cd docker/employee-jdbc-docker   
Build Maven Artifact:
-------
    npm install
Build Docker image for Springboot Application
--------------
    docker build -t naresh240/nodejs-k8s:v1 .
Docker login
-------------
    docker login
Push docker image to dockerhub
-----------
    docker push naresh240/nodejs-k8s:v1
