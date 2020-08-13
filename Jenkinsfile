pipeline{
    agent {
        docker {image 'gradle'}
    }

    stages{
        stage('Build'){
            steps{
                sh 'gradle clean build'
            }
        }
        stage('Docker'){
            environment {
                DOCKER_BUILDKIT = "1"
            }
            steps{
                sh 'docker build .'
            }
        }
    }
}