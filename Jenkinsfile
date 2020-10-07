pipeline{

    agent none
    stages{
        stage('Build'){
        agent {
            kubernetes {
                cloud 'kubernetes'
                yaml '''
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
  - name: gradle
    image: gradle:latest
    command:
    - cat
    tty: true'''
            }
        }


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
            agent {
            kubernetes {
                cloud 'kubernetes'
                yaml '''
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: some-label-value
spec:
  containers:
  - name: docker-builder
    env:
    - name: DOCKER_HOST
      value: tcp://localhost:2375
    command:
    - cat
    tty: true'''
                }
            }
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
