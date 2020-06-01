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
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/nodejs-k8s
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

Create helm chart:
------
    helm create nodejs
Edit values.yml file

    vi nodejs/values.yaml
Edit image as shown below:

![2](https://user-images.githubusercontent.com/63221837/83379255-b5785300-a3f8-11ea-969b-99a66f6a4b83.png)

Edit Service and port as show below:

![3](https://user-images.githubusercontent.com/63221837/83379258-b610e980-a3f8-11ea-884e-583bc347c02c.png)

Edit deployment.yml file as shown below
![4](https://user-images.githubusercontent.com/63221837/83379426-1acc4400-a3f9-11ea-8b2a-b5314fff2bb1.png)
Deploy nodejs Application using helm:
-------------
    helm install nodejs nodejs
Check pods and services:
--------
    kubectl get pods
    kubectl get svc
Goto Web UI and check whether we are getting output or not:
![2](https://user-images.githubusercontent.com/63221837/83380697-2c631b00-a3fc-11ea-8a67-2eb2eb1a3cbe.png)

Check history of nodejs Application:
-------
![5](https://user-images.githubusercontent.com/63221837/83379808-0f2d4d00-a3fa-11ea-91b0-d5b178a9fa6a.png)

Upgrading for nodejs Application:
-------------
Edit our our application and Build docker image with new tag:
    
    docker build -t naresh240/nodejs-k8s:v2 .

Push Docker image to docker hub with tag v2:

    docker push naresh240/nodejs-k8s:v2

Edit values.yml file with new tag v2:

![6](https://user-images.githubusercontent.com/63221837/83380233-1012ae80-a3fb-11ea-8a4d-31bbd4208303.png)

upgrade nodejs application with tag v2:

    helm upgrade nodejs ./nodejs

Check history of nodejs Application:
    
    helm history nodejs
![7](https://user-images.githubusercontent.com/63221837/83380376-697add80-a3fb-11ea-8ad2-f2717b55a38f.png)

Goto Web UI and check whether we are getting upgraded output or not:
![8](https://user-images.githubusercontent.com/63221837/83380500-b494f080-a3fb-11ea-98c2-0730286077e7.png)

How to Rollback using helm:
----------
    helm rollback nodejs 1
![1](https://user-images.githubusercontent.com/63221837/83380642-076ea800-a3fc-11ea-9365-8b55999b94fb.png)

Goto Web UI and check whether we are getting rollback output or not:
![2](https://user-images.githubusercontent.com/63221837/83380697-2c631b00-a3fc-11ea-8a67-2eb2eb1a3cbe.png)



