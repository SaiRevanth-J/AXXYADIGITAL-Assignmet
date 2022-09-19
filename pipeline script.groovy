pipeline {
    agent any 

    stages {
        stage {
            steps (build)
             {
               sh ' docker built -t revanthkumar9/nodeapp'

             }
        }
        stage {
            steps (scan) {
                sh ' trivy revanthkumar9/nodeapp'
            } 
        }

        stage {

            steps(dockerhub)
            {
                sh ' docker login -u revanthkumar9 -p xxxxxxx'
                sh ' docker push revanthkumar9/nodeapp '
            }
        }

        stage {
            steps (deploy) {
                sh 'scp -o StrictHostKeyChecking=no nodeapp-pod.yaml  k8cluster@ipaddress:/tmp
                sh ' kubectl apply -f ./tmp/nodeapp-pod.yaml '
            }
        }

    }


}