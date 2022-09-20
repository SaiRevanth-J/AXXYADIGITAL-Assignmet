pipeline {
    agent any 

    stages {
         stage ("git checkout") {
            steps {
                 git 'https://github.com/SaiRevanth-J/AXXYADIGITAL-Assignmet.git'
        }
         }
        stage  ("build") {
            steps
             {
               sh ' sudo docker build -t revanthkumar9/nodeapp .'

             }
        }
        stage ("scan") {
            steps  {
                sh ' trivy revanthkumar9/nodeapp'
            } 
        }

        stage  ("dockerhub") {

            steps
            {
                sh ' sudo  docker login -u revanthkumar9 -p xxxxxxx'
                sh ' sudo docker push revanthkumar9/nodeapp '
            }
        }

        stage ("deploy") {
            steps  {
                sh 'scp -o StrictHostKeyChecking=no nodeappdeploy.yml nodeportservice.yaml  nginx-ingress.yml k8cluster@ipaddress:/tmp '
                sh 'ssh -o StrictHostKeyChecking=no k8cluster@ipaddress '
                sh ' kubectl apply -f ./tmp/nodeappdeploy.yml '
                sh ' kubectl apply -f ./tmp/nodeportservice.yaml  '
                sh ' kubectl apply -f ./tmp/nginx-ingress.yml '

            }
        }

    }


}