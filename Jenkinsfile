def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    stages {
        stage('Build') {
            steps {
                sleep 5
                openshiftBuild apiURL: '', authToken: '', bldCfg: 'demo1', buildName: '', checkForTriggeredDeployments: 'false', commitID: '', namespace: 'myproject', showBuildLogs: 'true', verbose: 'false', waitTime: '1', waitUnit: 'min'            }
        }
        stage("Deploy to Dev") {
            steps {
                echo "Deploy to Dev Step"
                sleep 1
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
                echo "Approved the Build"
            }
        }
        
        stage("Deploy to Prod") {
            steps {
                echo "Deploy to Prod Step"
                sleep 1
            }
        }
    }
}