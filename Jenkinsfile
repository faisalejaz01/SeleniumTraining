pipeline {
    agent any
    tools { 
        maven 'Default' 
    }
    stages {
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
