pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/aninda08/kixx-backend']])
                echo 'Building the Spring Boot application...'
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './gradlew test'
            }
        }

        stage('Containerize') {
            steps {
                echo 'Building Docker image...'
                sh 'docker --version'
                sh 'docker build -t kixx-backend .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                sh 'docker run -d --name kixx-backend -p 8081:8081 kixx-backend:latest'
            }
        }
    }
}