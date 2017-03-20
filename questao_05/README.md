To run this project needs to run follow commands on the respective projects root files:

Lets go create the Dockers images ...:

questao_05_docker:
docker build --tag:node-docker:1.0.0 .

questao_05_node0:
docker build --tag:node-0:1.0.0 .

questao_05_node1:
docker build --tag:node-1:1.0.0 .

questao_05_node2:
docker build --tag:node-2:1.0.0 .

Lets go now run the Docker images ...:

//This node is responsable to create a sum.txt and diff.txt files
docker run -v /tmp:/opt/app/shared --name pod-docker -t pod-docker:1.0.0

//This node is responsable to resolve 1 times a sum operation, to resolve again execute "docker restart pod-node0"
docker run -v /tmp:/opt/app/shared --name pod-node1 -d -t pod-node1:1.0.0

//This node is responsable to resolve 1 times a diff operation, to resolve again execute "docker restart pod-node1"
docker run -v /tmp:/opt/app/shared --name pod-node2 -d -t pod-node2:1.0.0

//This node is responsable to recive the input parameters to write in the sum.txt or diff.txt,
//Note that this node is run forever for more easly tests, to stop this execute "docker rm -f pod-node0
docker run -v /tmp:/opt/app/shared --name pod-node0 -i -t pod-node0:1.0.0
