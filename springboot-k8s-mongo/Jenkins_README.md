# springboot-k8s-mongo

Pre-requisites:
--------
    - Install Git
    - Install Maven
    - Install Docker
    - Install Jenkins
    - EKS Cluster
Give permission for service which is running under same namespace by using rolebinding
----------------------
    kubectl create rolebinding default-view \
      --clusterrole=view \
      --serviceaccount=default:default \
      --namespace=default
      
Clone code from github:
-------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/springboot-k8s-mongo/

Add Jenkins to Docker Group:
------
    usermod -aG docker jenkins
    service jenkins restart
Copy kubectl file to /usr/bin path
-------
    cp kubectl /usr/bin

Add plugins to jenkins:
-----
Goto Jenkins dashboard --> Manage Jenkins --> Manage Plugins
Click on Available
Select below plugins and click on Install without restart

    - 	Maven Integration
    -	Kubernetes
    -	Kubernetes Continuous Deploy
    -	Kubernetes Credentials Provider
    
Create Docker credentials:
-------
Goto Jenkins dashboard --> Credentials
![1](https://user-images.githubusercontent.com/63221837/82425154-c396ad00-9aa3-11ea-8cd5-258b3b485cc9.png)
Click on jenkins
![2](https://user-images.githubusercontent.com/63221837/82425155-c396ad00-9aa3-11ea-85ec-22c5583e057d.png)
Click on Global credentials (unrestricted)
![3](https://user-images.githubusercontent.com/63221837/82425149-c1cce980-9aa3-11ea-991e-09bfc6263069.png)
Click on Add Credentials
![4](https://user-images.githubusercontent.com/63221837/82425153-c2fe1680-9aa3-11ea-9c86-372a5d9c6828.png)
Click on OK

Create AWS Credentials:
-------
Goto Jenkins dashboard --> Credentials
![1](https://user-images.githubusercontent.com/63221837/82425154-c396ad00-9aa3-11ea-8cd5-258b3b485cc9.png)
Click on jenkins
![2](https://user-images.githubusercontent.com/63221837/82425155-c396ad00-9aa3-11ea-85ec-22c5583e057d.png)
Click on Global credentials (unrestricted)
![3](https://user-images.githubusercontent.com/63221837/82425616-618a7780-9aa4-11ea-9522-e4699300eb5d.png)
Click on Add Credentials
![4](https://user-images.githubusercontent.com/63221837/82426046-f7be9d80-9aa4-11ea-844d-a2f6d0a1585a.png)
Click on OK

Create kubernetes_credentials
------
![1](https://user-images.githubusercontent.com/63221837/82426452-7fa4a780-9aa5-11ea-9835-7308198b595d.png)
Click on jenkins
![2](https://user-images.githubusercontent.com/63221837/82426445-7d424d80-9aa5-11ea-97dd-aeac305d295d.png)
Click on Global credentials (unrestricted)
![3](https://user-images.githubusercontent.com/63221837/82426449-7e737a80-9aa5-11ea-903e-6899eb8a5396.png)
Click on Add Credentials
![4](https://user-images.githubusercontent.com/63221837/82426451-7f0c1100-9aa5-11ea-9f7f-da6e65415004.png)
Click on OK

Encode USERNAME and PASSWORD of Postgres using following commands:
--------
    echo -n "mongoadmin" | base64
    echo -n "admin123" | base64
    
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
 
Now we can cleanup:
--------
    
