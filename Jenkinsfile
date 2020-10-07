pipeline{
    agent {
      kubernetes {
        cloud 'kubernetes'
        inheritFrom 'default'
        namespace 'default'
          containerTemplate {
            name 'gradle-agent'
            image 'gradle:latest'
            ttyEnabled true
            command 'cat'
          }
      }
    }

    stages{
        stage('Build'){
            steps{
                echo '>>> building Code'
                sh 'gradle clean build'
            }
            post{
                always{
                    junit allowEmptyResults: true, testResults: '**/test-results/**/TEST*.xml' 
                }
            }
        }
        stage('Docker'){
            environment {
                DOCKER_BUILDKIT = "1"
            }
            steps{
                echo '>>> building Docker'
                sh 'docker build .'
            }
        }
    }
}
