pipeline {
    agent any

    environment {
        // Define the image name
        IMAGE_NAME = "selenium-demo-test"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from the repository
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile in the root
                    docker.build("${IMAGE_NAME}:${env.BUILD_ID}", ".")
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    // On Windows, the .inside() method often fails because it tries to pass a Windows path (C:\...)
                    // as the container's working directory. Using a manual 'docker run' avoids this.
                    // We convert the Windows path to use forward slashes for Docker compatibility.
                    def workspacePath = env.WORKSPACE.replace('\\', '/')
                    bat "docker run --rm --shm-size=2g -v \"${workspacePath}:/app\" -w /app ${IMAGE_NAME}:${env.BUILD_ID} mvn test -Dheadless=true"
                }
            }
        }
    }

    post {
        always {
            // Publish TestNG Results
            junit testResults: '**/target/surefire-reports/TEST-*.xml', allowEmptyResults: true

            // Archive Extent Reports and Screenshots for viewing in Jenkins
            archiveArtifacts artifacts: 'target/extent/*.html, target/screenshots/*.png', allowEmptyArchive: true
        }
    }
}
