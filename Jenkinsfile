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
<<<<<<< HEAD
}
=======
}
>>>>>>> bcbd628a0e42a14c6c5fb5790ddb29099ea62827
