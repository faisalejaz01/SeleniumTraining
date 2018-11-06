pipeline {
    agent any
    tools { 
        maven 'Default' 
    }
    stages {
        stage ('Run Docker Containers') {
            steps {
                sh 'docker-compose up'
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
    }
}
