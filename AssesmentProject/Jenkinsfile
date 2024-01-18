pipeline {
    agent any
    environment {
            SONAR_TOKEN = credentials('SONAR_TOKEN')
            }

    stages {
        stage('Build, Test, and Generate Coverage') {
            steps {
                bat "mvn clean verify"
                bat "mvn jacoco:prepare-agent"
            }
        }
    stage('SonarQube Analysis') {
            steps {
                bat "mvn sonar:sonar -Dsonar.projectKey=jenkins-task -Dsonar.projectName='jenkins-task' -Dsonar.host.url=http://localhost:9000 -Dsonar.login=%SONAR_TOKEN% -Dsonar.java.coveragePlugin=jacoco"
            }

            }

        stage('Build Docker Image') {
                    steps {
                        // Build the Docker image using the Dockerfile
                        bat "docker build -t assesment/project3 ."
                    }
                }
        stage('Push to Docker Hub') {
                    steps {
                        // Log in to Docker Hub and push the image
                        withCredentials([string(credentialsId: 'docker_password', variable: 'docker_credentials')]) {
                            bat "docker login -u lavanyamuvva1 -p ${docker_credentials}"
                            bat "docker push assesment/project"
                        }

                    }
                }

        }
}


