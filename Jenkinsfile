pipeline{

    agent none
    stages{
        stage('Build'){
            agent {
                kubernetes {
                    containerTemplate {
                        name 'gradle'
                        image 'gradle:latest'
                        ttyEnabled true
                        command 'cat'
                    }
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
                    containerTemplate {
                        name 'docker'
                        ttyEnabled true
                        command 'cat'
                        privileged true
                        envVars: [ 
                            envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')
                        ]
                    }
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
