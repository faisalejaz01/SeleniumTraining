
pipeline {
    agent any
    tools { 
        maven 'Default' 
    }
    stages {

        stage ('Docker-Compose UP') {
            steps {
                
                sh '''
                    docker ps
                    docker-compose up -d
                    '''
            }
        }
        
        stage ('Docker Processes') {
            steps {
                sh 'docker ps'
            }
        }

        stage ('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        
        stage ('Test') {
            steps {
                sh 'mvn test'
            }
        }
          
        stage ('Docker Compose DOWN') {
            steps {
                
                sh '''
                    docker ps
                    docker-compose down 
                    '''
            }
        }
    }
}
