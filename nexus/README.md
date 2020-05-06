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

    kubectl exec -it  -- bash
    cat /
  
Step7:
------
UserName: admin
Password: <get password from file>
