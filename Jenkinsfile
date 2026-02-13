pipeline {
    agent any

    options {
        buildDiscarder(
            logRotator(numToKeepStr: '3', artifactNumToKeepStr: '3')
        )
    }

    tools {
        maven 'mvn-3.9.12'
    }

    stages {

        stage('Branch Info') {
            steps {
                echo "Building branch: ${env.BRANCH_NAME}"
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
                sh """
                cp target/*.jar target/bookmyplan-${BRANCH_NAME}-${BUILD_NUMBER}.jar
                """
            }
        }

    }
}
