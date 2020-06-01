# Deploy Jenkins with in kubernetes

Pre-requisites:
-------
    - Install git
    - EKS Cluster

Attach below policy to Node Instance Role
-----------

	{
	    "Version": "2012-10-17",
	    "Statement": [
		{
		    "Effect": "Allow",
		    "Action": [
			"route53:ListHostedZones",
			"route53:ListResourceRecordSets"
		    ],
		    "Resource": [
			"*"
		    ]
		},
		{
		    "Effect": "Allow",
		    "Action": [
			"route53:ChangeResourceRecordSets"
		    ],
		    "Resource": [
			"*"
		    ]
		}
	    ]
	}
Create Hosted zone with in Route53:
-------------
Goto Route53 and Create Hosted Zone with the name of our DNS Name. Here my DNS name is "vamsitechtuts.tk"
![1](https://user-images.githubusercontent.com/63221837/83413349-4bcc6900-a439-11ea-9633-88c28e6a9935.png)

Change name servers where we purchased our DNS. Here I purchased in Freenom website
![2](https://user-images.githubusercontent.com/63221837/83413584-b4b3e100-a439-11ea-816d-9da4231da45a.png)

Get Source Code from github:
---------------
    git clone https://github.com/VamsiTechTuts/kubernetes.git
    cd kubernetes/jenkins
    
Deploy Jenkins with on kubernetes:
--------
    kubectl apply -f jenkins-deploy.yaml
Expose jenkins as LoadBalancer Service:
---------
    kubectl apply -f jenkins-svc.yaml
Deploying mandatory files:
----------
	  kubectl apply -f mandatory.yaml
	  kubectl apply -f patch-configmap-l4.yaml
Create Certificates:
-------------
Create Certificates for our external-dns using AWS Certificate Manager
Goto AWS Certificate Manager service with in AWS
![1](https://user-images.githubusercontent.com/63221837/83411808-98627500-a436-11ea-8711-4f87575af76e.png)
Click on Get started

![2](https://user-images.githubusercontent.com/63221837/83411817-9993a200-a436-11ea-839b-964f0584c57b.png)
Click on Request a certificate

![3](https://user-images.githubusercontent.com/63221837/83411819-9a2c3880-a436-11ea-9c15-e0a7e1092101.png)
Click on Next

![4](https://user-images.githubusercontent.com/63221837/83411821-9a2c3880-a436-11ea-93c1-d252515837b5.png)
Click on Next

![5](https://user-images.githubusercontent.com/63221837/83411823-9ac4cf00-a436-11ea-9b6b-27c7a75381c8.png)
Click on Review

![6](https://user-images.githubusercontent.com/63221837/83411825-9b5d6580-a436-11ea-8057-2c97a7bb08aa.png)
Click on Confirm and Request

![7](https://user-images.githubusercontent.com/63221837/83411827-9b5d6580-a436-11ea-997a-4778e9242fed.png)
Click on Create Record in Route53

![8](https://user-images.githubusercontent.com/63221837/83411828-9bf5fc00-a436-11ea-82f4-7e6e1d167f9b.png)
Click on Create and then Click on Continue

Now we can Certificate Issued
![2](https://user-images.githubusercontent.com/63221837/83412383-99e06d00-a437-11ea-9f74-3477701f3968.png)

Goto Route53 and check whether CNAME Record created or not:
![1](https://user-images.githubusercontent.com/63221837/83412379-9947d680-a437-11ea-892e-ca6971108837.png)

Deploying externaldns, service and ingress:
----------
change our external dns in external-dns.yaml and also edit certificate arn in service-l4.yaml

    kubectl apply -f external-dns.yaml
    kubectl apply -f service-l4.yaml
    kubectl apply -f ingress.yml
Goto Route53 and check wether Records sets are created or not:
![1](https://user-images.githubusercontent.com/63221837/83412807-618d5e80-a438-11ea-9dda-b82cb41b06e8.png)

Check output with External-dns with API:
----------------
Goto web UI and give "jenkins.vamsitechtuts.tk"
![2](https://user-images.githubusercontent.com/63221837/83412969-a9ac8100-a438-11ea-8af5-c3d7dc726012.png)
