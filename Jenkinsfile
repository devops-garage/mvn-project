pipeline {
    agent any

    tools {
        jdk 'JDK25'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                    echo "Java Version:"
                    java -version

                    echo "Maven Version:"
                    mvn -version

                    echo "JAVA_HOME:"
                    echo $JAVA_HOME
                '''
            }
        }

        stage('Validate') {
            steps {
                sh 'mvn -B validate'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }

        success {
            echo 'Maven build completed successfully!'
        }

        failure {
            echo 'Maven build failed. Check the console output.'
        }
    }
}
