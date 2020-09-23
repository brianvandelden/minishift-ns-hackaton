def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    stages {
        stage('Pre-build variabelen zetten') {
            steps {
                script {
                    script {
                        def now = new Date()
                        def stringtijd = now.format("yyyyMMddHHmm", TimeZone.getTimeZone('UTC'))
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
                    // openshiftDeploy apiURL: '', authToken: '', depCfg: 'demo1', namespace: 'prod', verbose: 'false', waitTime: '', waitUnit: 'sec'            }
                script {
                    openshiftCreateResource apiURL: '', authToken: '', jsonyaml: '''apiVersion: apps.openshift.io/v1
kind: DeploymentConfig
metadata:
  annotations:
    openshift.io/generated-by: Niek Arends
  creationTimestamp: 2020-09-23T13:59:36Z
  generation: 2
  labels:
    app: demo1-3
  name: demo1-3
  namespace: prod
  resourceVersion: "240980"
  selfLink: /apis/apps.openshift.io/v1/namespaces/prod/deploymentconfigs/demo1-3
  uid: 03e239f2-fda5-11ea-81c3-0800271795d0
spec:
  replicas: 1
  selector:
    app: demo1-3
    deploymentconfig: demo1-3
  strategy:
    activeDeadlineSeconds: 21600
    resources: {}
    rollingParams:
      intervalSeconds: 1
      maxSurge: 25%
      maxUnavailable: 25%
      timeoutSeconds: 600
      updatePeriodSeconds: 1
    type: Rolling
  template:
    metadata:
      annotations:
        openshift.io/generated-by: OpenShiftWebConsole
      creationTimestamp: null
      labels:
        app: demo1-3
        deploymentconfig: demo1-3
    spec:
      containers:
      - image: 172.30.1.1:5000/myproject/demo1@sha256:0fd058d869019046bdafaa3147084aee505d16d614738609a90905105e3bc28a
        imagePullPolicy: Always
        name: demo1-3
        ports:
        - containerPort: 8080
          protocol: TCP
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
  test: false
  triggers:
  - type: ConfigChange
  - imageChangeParams:
      automatic: true
      containerNames:
      - demo1-3
      from:
        kind: ImageStreamTag
        name: demo1:${stringtijd}
        namespace: myproject
    type: ImageChange''', namespace: '', verbose: 'false'
                }
            }
        }
    }
}