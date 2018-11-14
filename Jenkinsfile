
pipeline {
    agent any
    stages {
        stage ('Check Docker') {
            steps {
                bat 'echo %DOCKER_HOST%'
                bat 'docker ps'
            }
        }

        stage ('Start Docker Stack') {
            steps {
                bat '''
                docker stack deploy --compose-file stack1.yml grid
                '''
            }
        }
        stage ('Test') {
            steps {
                bat '''
                docker ps
                mvn clean install
                '''
            }
        }
        stage ('Stop Docker Stack') {
            steps {
                bat 'docker ps'
                bat 'docker stack rm grid'
            }
        }
    }
}
