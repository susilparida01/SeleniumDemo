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
                    // Run the container and execute Maven tests
                    // The .inside() method ensures the workspace is mounted and reports are saved back to Jenkins
                    docker.image("${IMAGE_NAME}:${env.BUILD_ID}").inside("--shm-size=2g") {
                        sh 'mvn test -Dheadless=true'
                    }
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
