pipeline {
    agent any

    tools {
        maven 'mvn_3.9.12'
    }

    stages {

        stage('Code Compilation') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Code QA Execution') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Code Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
