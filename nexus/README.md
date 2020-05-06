# Deployment of Nexus in Kubernetes

Step1: 
-----
Create Namespace for nexus

    kubectl create namespace nexus
    
Step2:
-----
Execute deployment file

    kubectl apply -f nexus-deployment.yml
    
Step3:
------
Execute Service file

     kubectl apply -f nexus-service.yml
     
Step4:
------
Check pods for nexus deployment

     kubectl get pods -n nexus
     
Step5:
------
Check nexus services

    kubectl get svc -n nexus
    
Step6:
------
Copy Loadbalancer DNS and check in UI

    af65ad71e7a4e427884f1cbb73aa8764-1029717853.us-west-2.elb.amazonaws.com

Step7:
------
For Nexus password we need to connect pods and check in file

    kubectl exec -it nexus-85f9fcf65c-cjc9g -n nexus -- bash
    cat /nexus-data/admin.password
  
Step8:
------
SignIn into Nexus by clicking signIn button

    UserName: admin
    Password: <get password from file /nexus-data/admin.password>
    
Step9:
------
Destroy namespace, deployment and service of nexus

     kubectl delete deploy nexus -n nexus
     kubectl delete svc nexus-service -n nexus
     kubectl delete namespace nexus
