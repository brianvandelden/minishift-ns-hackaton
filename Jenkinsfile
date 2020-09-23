def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    stages {
        stage('Build') {
            steps {
                echo "Starting build"
                openshiftBuild apiURL: '', authToken: '', bldCfg: 'demo1', buildName: '', checkForTriggeredDeployments: 'false', commitID: '', namespace: 'myproject', showBuildLogs: 'true', verbose: 'false', waitTime: '1', waitUnit: 'min'            }
            }
        stage("Trigger deploy to Dev") {
            steps {
                    echo "Deploy to Dev Step"
                    openshiftDeploy apiURL: '', authToken: '', depCfg: 'demo1', namespace: 'myproject', verbose: 'false', waitTime: '', waitUnit: 'sec'            
                }
        }
        stage("Approve Build") {
            input {
                message "Do you approve the build on dev?"
                ok "Yes, I do."
                parameters {
                    string(name: 'TAG', defaultValue: 'prod-latest', description: 'Tag die je aan de build wilt geven')
                }
            }
            steps {
                echo "Approved the Build. Tagging with ${TAG}"
                openshiftTag alias: 'false', apiURL: '', authToken: '', destStream: 'dev', destTag: '$(TAG)', destinationAuthToken: '', destinationNamespace: 'dev', namespace: 'myproject', srcStream: 'demo1', srcTag: 'latest', verbose: 'true'
            }
        }
        
        stage("Trigger deploy to Prod") {
            steps {
                echo "Deploy to Prod Step"
                openshiftDeploy apiURL: '', authToken: '', depCfg: 'demo1', namespace: 'prod', verbose: 'false', waitTime: '', waitUnit: 'sec'            }
        }
    }
}