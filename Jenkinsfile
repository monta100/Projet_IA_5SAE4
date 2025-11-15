pipeline {
    agent any

    tools {
        maven 'M2-HOME'
    }

    options {
        skipDefaultCheckout(true)   // évite le checkout auto de Jenkins
        timeout(time: 2, unit: 'MINUTES')
    }

    environment {
        APP_ENV = "DEV"
    }

    stages {

        stage('Code Checkout') {
            steps {
                git branch: 'Gestion_Recette',
                    url: 'https://github.com/monta100/Projet_IA_5SAE4.git'
            }
        }

        stage('Code Build') {
            steps {
                sh 'mvn install -Dmaven.test.skip=true'
            }
        }
    }

    post {
        always {
            echo "====== always ======"
        }

        success {
            echo "===== pipeline executed successfully ====="
        }

        failure {
            echo "====== pipeline execution failed ======"
        }
    }
}
