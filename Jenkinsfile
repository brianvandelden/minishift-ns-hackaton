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
                    string(name: 'TAG', defaultValue: '', description: 'Prefix die je aan de build-tag wilt geven')
                }
            }
            steps {
                script {
                    echo "${TAG}_${stringtijd}"
                    def now = new Date()
                    def stringtijd = now.format("yyMMddHHmm", TimeZone.getTimeZone('UTC'))
                    openshiftTag alias: 'false', apiURL: '', authToken: '', destStream: 'demo1', destTag: "${TAG}_${stringtijd}", destinationAuthToken: '', destinationNamespace: 'myproject', namespace: 'myproject', srcStream: 'demo1', srcTag: 'latest', verbose: 'true'
                }
            }
        }
        
        stage("Trigger deploy to Prod") {
            steps {
                echo "Deploy to Prod Step"
                openshiftDeploy apiURL: '', authToken: '', depCfg: 'demo1', namespace: 'prod', verbose: 'false', waitTime: '', waitUnit: 'sec'            }
        }
    }
}