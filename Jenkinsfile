def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    stages {
        stage('Build') {
            sleep {
                    time 5
                    unit SECONDS
            }
            steps {
                
            }
        }
        stage("Deploy to Dev") {
            steps {
                echo "Deploy to Dev Step"
            }
        }
        stage("Approve Build") {
            input {
                    message "Do you approve the build on dev?"
                    ok "Yes, we should."
                    // parameters {
                    //     string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            }
            steps {
                echo "Approve Build Step"
            }
        }
        stage("Deploy to Prod") {
            steps {
                echo "Deploy to Prod Step"
            }
        }
    }
}