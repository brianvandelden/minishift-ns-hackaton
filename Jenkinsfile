def bcName = 'demo1'
pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    stages {
        stage('Build') {
            steps{ 
                script {
                    openshift.withCluster() {
                        
                        def builds = openshift.selector("bc", "demo1").related('builds')
                              timeout(5) { 
                                builds.untilEach(1) {
                                  return (it.object().status.phase == "Complete")
                                }
                              }
                        // openshift.selector("bc", "demo1").startBuild('--namespace=myproject')
                    }
                }
            }
        }
    }
}