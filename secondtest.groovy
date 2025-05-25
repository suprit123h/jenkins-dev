pipeline {
    agent any
    
    parameters{
    	string(name: 'pipeline value', description: 'just checking the parameters option', defaultValue: 'NA')
        string(name: 'opetions value', description: 'just checking the options', defaultValue: 'NA')
	choice(name: 'pipeline choices', choices: ['first pipeline', 'second pipeline'], description: 'nothing to describe here' )
    }

    environment{
    	GCP_PROJECT_ID		= 'gcp'
    	// nothing to mention as of now
    }


    stages {
        stage('Hello This is the git passkey token name: "Surpass" ') {
            steps {
	    	dir("infraGCP"){
                script {
                    println('Hello This is the git passkey token name: "Surpass"')

		    sh "git clone https://github.com/suprit123h/gcp-ssh-2025-wisdom-dev.git"

		    sh "$(cd pwd)/infraGCP/terraform init"

		    sh "$(cd pwd)/infraGCP/terraform plan"

		    //sh "gcloud version"
		   }

                }
            }
        }
    }
}
