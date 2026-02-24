pipeline {
    agent any

    options {
        buildDiscarder(
            logRotator(
                numToKeepStr: '3',
                artifactNumToKeepStr: '3'
            )
        )
    }
    
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
                echo 'Packaging application...'
                sh 'mvn clean package'

                sh """
                    cp target/*.jar target/bookmyplan-${BUILD_NUMBER}.jar
                """

                echo "Artifact created: bookmyplan-${BUILD_NUMBER}.jar"
            }
        }

         stage('Build & Tag Docker Image') {
            steps {
                echo 'Building Docker Image and Tagging...'
                sh '''
                    docker build \
                    -t anushkamdevops/bookmyplan:latest \
                    -t bookmyplan:latest .
                '''
                echo 'Docker Image Build Completed!'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubCred', variable: 'DOCKERHUB_PWD')]) {
                        sh '''
                            docker login docker.io -u anushkamdevops -p ${DOCKERHUB_PWD}
                            docker push anushkamdevops/bookmyplan:latest
                        '''
                        echo 'Docker Image Pushed to Docker Hub Successfully!'
                    }
                }
            }
        }

        stage('Push Docker Image to Amazon ECR') {
            steps {
                script {
                    withDockerRegistry(
                        credentialsId: 'ecr:ap-south-1:ecr-credentials',
                        url: "https://973658621146.dkr.ecr.ap-south-1.amazonaws.com"
                    ) {
                        sh '''
                            docker tag bookmyplan:latest 973658621146.dkr.ecr.ap-south-1.amazonaws.com/bookmyplan:latest
                            docker push 973658621146.dkr.ecr.ap-south-1.amazonaws.com/bookmyplan:latest
                        '''
                        echo 'Docker Image Pushed to Amazon ECR Successfully!'
                    }
                }
            }
        }

        stage('Clean Up Local Docker Images') {
            steps {
                echo 'Cleaning Up Local Docker Images...'
                sh '''
                    docker rmi anushkamdevops/bookmyplan:latest || true
                    docker rmi bookmyplan:latest || true
                    docker rmi 973658621146.dkr.ecr.ap-south-1.amazonaws.com/bookmyplan:latest || true
                    docker image prune -f
                '''
                echo 'Local Docker Images Cleaned Up Successfully!'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/bookmyplan-*.jar', fingerprint: true
            }
        }
    }
}
