def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    environment {
        def stringtijd = 'unknown'
    }
    stages {
        stage('Pre-build variabelen zetten') {
            steps {
                script {
                    script {
                        def now = new Date()
                        stringtijd = now.format("yyyyMMddHHmm", TimeZone.getTimeZone('UTC'))
                    }
                }
            }
        }
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
                // parameters {
                //     string(name: 'TAG', defaultValue: '', description: 'Prefix die je aan de build-tag wilt geven')
                // }
            }
            steps {
                script {
                    echo "Tagging with ${stringtijd}"
                    openshiftTag alias: 'false', apiURL: '', authToken: '', destStream: 'demo1', destTag: "${stringtijd}", destinationAuthToken: '', destinationNamespace: 'myproject', namespace: 'myproject', srcStream: 'demo1', srcTag: 'latest', verbose: 'false'
                }
            }
        }
        
        stage("Trigger deploy to Prod") {
            steps {
                echo "Deploy to Prod Step"
                // openshiftDeploy apiURL: '', authToken: '', depCfg: 'demo1', namespace: 'prod', verbose: 'false', waitTime: '', waitUnit: 'sec'           
                openshiftCreateResource apiURL: '', authToken: '', jsonyaml: '''apiVersion: v1
kind: DeploymentConfig
metadata:
  name: demo1
spec:
  replicas: 5
  selector:
    name: demo1
  triggers:
  - type: ConfigChange 
  - imageChangeParams:
      automatic: true
      containerNames:
      - demo1
      from:
        kind: ImageStreamTag
        name: demo1:${stringtijd}
    type: ImageChange  
  strategy:
    type: Rolling''', namespace: 'prod', verbose: 'false'
            }
        }
    }
}