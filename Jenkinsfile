pipeline {
    agent any

    tools {
        maven 'mvn-3.9.12'
    }

    stages {

        stage('Code Compilation') {
            steps {
                echo 'Starting Code Compilation...'
                sh 'mvn clean compile'
                echo 'Code Compilation Completed Successfully!'
            }
        }

        stage('Code QA Execution') {
            steps {
                echo 'Running JUnit Test Cases...'
                sh 'mvn test'
                echo 'JUnit Test Cases Completed Successfully!'
            }
        }

        stage('Code Package') {
            steps {
                echo 'Creating JAR Artifact...'
                sh 'mvn package'

                sh """
                cp target/*.jar \
                target/bookmyplan-1.1.${BUILD_NUMBER}.jar
                """

                echo "Artifact created: bookmyplan-1.1.${BUILD_NUMBER}.jar"
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/bookmyplan-*.jar', fingerprint: true
            }
        }
    }
}
