pipeline {
    agent none 
    stages {
        stage('Compile Stage') {
            steps {
                withMaven(maven : Default){
				sh 'mvn clean compile'
				}
            }
        }
		        stage('Testing Stage') {
            steps {
                withMaven(maven : Default){
				sh 'mvn test'
				}
            }
        }
		

        }
    }
}