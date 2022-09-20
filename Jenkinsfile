pipeline {
    agent any 

    stages {
         stage{
            steps("git checkout") {
                 git 'https://github.com/SaiRevanth-J/AXXYADIGITAL-Assignmet.git'
        }
         }
        stage {
            steps ("build")
             {
               sh ' docker build -t revanthkumar9/nodeapp .'

             }
        }
        stage {
            steps ("scan") {
                sh ' trivy revanthkumar9/nodeapp'
            } 
        }

        stage {

            steps("dockerhub")
            {
                sh ' docker login -u revanthkumar9 -p xxxxxxx'
                sh ' docker push revanthkumar9/nodeapp '
            }
        }

        stage {
            steps ("deploy") {
                sh 'scp -o StrictHostKeyChecking=no nodeappdeploy.yml nodeportservice.yaml  nginx-ingress.yml k8cluster@ipaddress:/tmp '
                sh 'ssh -o StrictHostKeyChecking=no k8cluster@ipaddress '
                sh ' kubectl apply -f ./tmp/nodeappdeploy.yml '
                sh ' kubectl apply -f ./tmp/nodeportservice.yaml  '
                sh ' kubectl apply -f ./tmp/nginx-ingress.yml '

            }
        }

    }


}