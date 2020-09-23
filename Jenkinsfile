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
            // steps{ 
            //     script {
            //         openshift.withCluster() {
                        
            //             def builds = openshift.selector("bc", "demo1").related('builds')
            //                   timeout(5) { 
            //                     builds.untilEach(1) {
            //                       return (it.object().status.phase == "Complete")
            //                     }
            //                   }
            //         }
            //     }
            // }
        }
        stage("Deploy to Dev") {

        }
        stage("Approve Build") {
            input {
                message "Do you approve the build on dev?"
                ok "Yes, we should."
                // parameters {
                //     string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                // }
            }
        }
        stage("Deploy to Prod") {

        }
    }
}