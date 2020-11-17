pipeline{

    agent {
        kubernetes { yamlFile 'container.yaml' }
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
