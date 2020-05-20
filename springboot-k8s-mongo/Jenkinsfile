pipeline{
    agent any
    parameters {
	    choice(name: 'action', choices: 'create\nrollback', description: 'Create/rollback of the deployment')
    }
    tools { 
        maven 'maven3' 
    }
    stages{
        stage('git checkout'){
            when {
				expression { params.action == 'create' }
			}
            steps{
                git 'https://github.com/VamsiTechTuts/kubernetes.git'
            }
        }
        stage('Build Maven'){
            when {
				expression { params.action == 'create' }
			}
            steps{
	           dir("springboot-k8s-mongo"){
	                sh 'mvn clean install -DskipTests=true'
                }
            }
        }
        stage("Docker Build") {
            when {
				expression { params.action == 'create' }
			}
	        steps {
	            dir("springboot-k8s-mongo") {
	                sh 'docker build -t vamsitechtuts/springboot-k8s-mongo .'
	            }
	        }
        }
        stage("Docker Push") {
            when {
				expression { params.action == 'create' }
			}
	        steps {
               withCredentials([usernamePassword(credentialsId: 'docker_credentials', passwordVariable: 'docker_password', usernameVariable: 'docker_username')]) {
                   sh 'docker login -u ${docker_username} -p ${docker_password}'
               }
               sh 'docker push vamsitechtuts/springboot-k8s-mongo'
           }
        }
        stage('Application Deployment'){
            when {
				expression { params.action == 'create' }
			}
            steps{
	           dir("springboot-k8s-mongo"){
	               withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'AWS_Credentials', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
	                   withCredentials([kubeconfigFile(credentialsId: 'kubernetes_credentials', variable: 'KUBECONFIG')]) {
	                   sh """
	                   kubectl apply -f mongo-secret.yml
	                   kubectl apply -f mongo-pv.yml
	                   kubectl apply -f mongo-pvc.yml
	                   kubectl apply -f mongo-deployment.yml
	                   kubectl apply -f mongo-service.yml
	                   kubectl apply -f mongo-config.yml
	                   kubectl apply -f springboot-deployment.yml
	                   kubectl apply -f springboot-service.yml
	                   """
	                   }
	               }
	           }
            }
        }
        stage('CleanUP'){
            when {
				expression { params.action == 'rollback' }
			}
            steps{
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'AWS_Credentials', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    withCredentials([kubeconfigFile(credentialsId: 'kubernetes_credentials', variable: 'KUBECONFIG')]) {
                    sh """
                    kubectl delete deploy mongo spring-mongo-service
                    kubectl delete svc mongodb-service spring-mongo-service
                    kubectl delete pvc mongo-pv-claim
                    kubectl delete pv mongo-pv-volume
                    kubectl delete configmaps mongo-conf
                    kubectl delete secrets mongo-secret
                    """
	               }
	           }
            }
        }
    }
}
