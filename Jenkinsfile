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
  name: agent
spec:
  containers:
    - name: gradle-agent
      image: gradle
  imagePullSecrets:
    - name: myregistrykey
'''
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
