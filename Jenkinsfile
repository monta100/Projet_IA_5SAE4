pipeline {
    agent any
    // Si tu veux utiliser un node spécifique, remplace par :
    // agent { label 'build' }

    tools {
        maven 'M2-HOME'
    }

    options {
        // Timeout counter starts after agent is allocated
        timeout(time: 2, unit: 'MINUTES')
    }

    environment {
        APP_ENV = "DEV"
    }

    stages {

        stage('Code Checkout') {
            steps {
                git branch: 'Gestion_Recette',
                    url: 'https://github.com/monta100/Projet_IA_5SAE4.git',
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
