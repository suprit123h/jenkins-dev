pipeline {
    agent any

    environment{
    	GCP_PROJECT_ID		= 'ssh-devops1-dev-2025'
    	// nothing to mention as of now
    }


    stages {
	    
        stage('Hello This is the git passkey token name: "Surpass" ') {
	steps {
		dir("infraGCP") {
			script {
				withCredentials([
					file(
						credentialsId: 'ssh-devops1-dev-2025',
						variable: 'GOOGLE_APPLICATION_CREDENTIALS'
					),
          usernamePassword(
            credentialsId: '81dead19-04f7-4a8a-91c2-84adc1e8b1f1',
            usernameVariable: 'git_user', passwordVariable: 'git_pass'
            )
				]) {
					echo 'Hello This is the git passkey token name: "Surpass"'
					// sh "cat $GOOGLE_APPLICATION_CREDENTIALS"

					// Optionally, ensure the repo is checked out before pulling
					checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/suprit123h/gcp-ssh-2025-wisdom-dev.git']]])
         // checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/suprit123h/jenkins-dev.git']]])

					// git pull "https://github.com/suprit123h/gcp-ssh-2025-wisdom-dev.git"

					sh '''
						git pull origin master
						pwd
						ls
                                                mv ${GOOGLE_APPLICATION_CREDENTIALS} ssh-devops1-dev-2025.json
                                                rm -rf *.tfstate
						terraform init
                                                echo "=================================================="
						terraform plan
                                                echo "=================================================="
                                                terraform apply -auto-approve
	                                        echo "=================================================="
					        sleep 30
	                                        echo "=================================================="
						terraform plan -destroy
                                                echo "=================================================="
						terraform destroy -auto-approve -no-color
					'''
				}
			}
		}
	}
        }
    }
}
