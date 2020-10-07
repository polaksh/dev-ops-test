pipeline{

    agent {
        kubernetes {
        yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
  - name: gradle
    image: gradle
    command:
    - cat
    tty: true
"""
        }
    }
    stages{
        stage('Build'){
            steps{
                container('gradle'){
                    echo '>>> building Code'
                    sh 'gradle clean build'
                }
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
                container('docker'){
                    echo '>>> building Docker'
                    echo "sh 'docker build .'"
                }
            }
        }
    }
}
