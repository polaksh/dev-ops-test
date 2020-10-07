pipeline{
    agent {
      kubernetes {
        cloud 'kubernetes'
        inheritFrom 'default'
        namespace 'default'
        yaml '''
apiVersion: v1
kind: Pod
metadata:
  labels:
    tes1: some-label-value1
spec:
  containers:
  - name: gradle
    image: gradle
    command:
    - cat
    tty: true'''
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
