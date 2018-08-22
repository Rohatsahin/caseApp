pipeline {
    agent  { 
    	label 'gradle' 
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                sh 'chmod 755 gradlew'
                sh './gradlew build assemble'
                archiveArtifacts artifacts: '**/trendyol*.jar', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'chmod 755 gradlew'
                sh './gradlew clean test --info'
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                echo 'Deploying...'
            }
        }
    }
}