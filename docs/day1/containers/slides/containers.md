###  Containers

A container is an isolated and limited context, that contain an app and all its dependencies to be executed.
- It generates a new namespace in the OS kernel.
- It has a host OS shared kernel.
- It has own resources of cpu, memory, network y storage.
- It executes only one app, ideally.
- It contains all needed (basic os and dependencies) to execute the app.
- It would die once the principal app get stopped.

---

####  Containers vs VMs

- Container = context (shared kernel) vs VM = virtualitation (hypervisor)
- Container has near native performance. Almost NO overhead.
- Containers have all software requirement and dependencies needed to run the app.
- Containers are portable, versionable and immutable.

---

---

####  Container runtime
Principal container runtime in the market:

<img src="day1/containers/slides/images/containers.jpg", style="height:40vh; background-color:white; float:center;"/>
 
---

### Docker
Docker is a container platform used to develop, deploy and execute dockerized app's. Ecosystem:

- Docker Engine: Server and client to work with docker.
- Docker Trusted Registry: Docker repository where save, push and pull docker images.
- Docker Hub: Docker registry official service. Could code autobuilds from github y bitbucket.
- Docker Machine: Tool to deploy hosts in distinct providers.
- Docker Compose: Tool to deploy multi docker services.

---

#### First steeps

- Docker service can run in every linux distro (debian, ubuntu, coreos, centos, rancheros,...)
- Docker daemon listen by default in a unix socket /var/run/docker.sock. Also, it's possible to listen in a network port 2375 y 2376 (ssl).
- Local connection: Executing the command in a machine running the daemon, connect through unix socket. docker <command>
- Remote: Executing the command in a remote machine not running the daemon. We need to specify -H param or the env variable DOCKER_HOST. 

```
export DOCKER_HOST=tcp://<host>:<port>; docker <command> || docker -H tcp://<host>:<port> <command>
```

---

#### Commands

- Life cycle : create, rename, run, rm, update, images, import, build, commit, rmi, load, save......
- State : start, stop, restart, pause, unpause, wait, kill, attach...
- Information : ps, logs, inspect, events, port, top, stats, diff, tag....
- Import/Export : cp, export...
- Execution : exec...

---

#### Dockerfiles

- A Dockerfile is the script to build a docker image. 
- Docker images are cumulative and incremental. 
- Every Dockerfile has a FROM section, to specify what’s the "base" image it comes from. 
- Many distributions and applications, already have docker images published in dockerhub. 
- We could use them as FROM to add or customize for our use. 

---

#### Images

Image workflow:

Dockefile -> docker build -> docker tag -> docker push -> docker rmi

- Building a Dockerfile, generates a docker image. 
- Every build, generates a new and unique docker image. 
- Every image should be tagged and published in a docker registry (public or private), to be used. 

---

#### Dockers

docker workflow:

docker pull -> docker run -> docker stop -> docker rm 

- A docker container is an deployed instance of a docker image. 
- Pulling an image from a docker registry, download the docker image to your host.
- Running an image, start an instance of your docker image.
- Every single deployed docker is unique and has state (started, stopped, created, error,...)

---

### Examples

---

#### Docker as client app

- A user wants to use curl in his computer.
- Curl package for his specific OS doesn’t exist.
- The user could execute dockers.

We could build a docker that executes curl as a client. 

---

- Generate a Dockerfile

```
FROM docker.io/alpine:3.5 		# Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl curl && \ 	 # Install curl and some basic packages, and remove packages cache.
    rm -rf /var/cache/apk/* 	

ENTRYPOINT [“/usr/bin/curl”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

---

- Build and tag the docker image. Generate a unique, portable and reproducible version.

```
docker build  -f Dockerfile .   # It generates an image with a uuid
docker tag <IMAGE_UUID> <MY_DOCKERHUB_USER>/curlApp:<VERSION>
```

Or in one command

```
docker build -t <MY_DOCKERHUB_USER>/curlApp:<VERSION> -f Dockerfile-curlApp .
```

---

- Login to docker hub with your user/pass if needed.

```
docker login -u <MY_DOCKERHUB_USER> -p <MY_DOCKERHUB_PASS>
```

- Push the image to a docker registry to make it available.

```
docker push <MY_DOCKERHUB_USER>/curlApp:<VERSION>
```

---

- Launch an instace of our dockerized curl app. $CMD would be passed as curl args.

```
docker run -it --rm <MY_DOCKERHUB_USER>/curlApp:<VERSION> -L www.google.com
```

- Making it nicer: Set an alias in the user computer and he/she could launch regular curl.
```
alias curl='docker run -it --rm <MY_DOCKERHUB_USER>/curlApp:<VERSION> '

curl -L www.google.com
```

---

#### Docker as server 

- A user wants a local web server to publish static files.
- The user don't know about install and configure it.
- The user could execute dockers.

- We could build a docker that 
  - executes an nginx server.
  - copy the static files and serve them. 

---

- Option 1: Build only one docker with nginx and the files to serve.

```
FROM docker.io/alpine:3.5 		  # Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl nginx && \
    rm -rf /var/cache/apk/*     # Install nginx and some basic packages, and remove packages cache.

ADD <html_files> /var/www/html 	# Copy files into the image

EXPOSE 80		                    # Expose the service by a network port

# App is executed in foreground, to retain the docker started. The docker will be stoped once the primary command 
# will close its stdin, may because it’s executed as daemon, may be because it finish the execution.

ENTRYPOINT ["nginx", "-g", "daemon off;”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

---

- Option 2: Build two docker, to take profit of docker hierarchy

  - Dockerfile for myNginx. Could be used for any other web service.
```
FROM docker.io/alpine:3.5 		# Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl nginx && \
    rm -rf /var/cache/apk/* 	# Install nginx and some basic packages, and remove packages cache.

EXPOSE 80		                  # Expose the service by a network port

# App is executed in foreground, to retain the docker started. The docker will be stoped once the primary command 
# will close its stdin, may because it’s executed as daemon, may be because it finish the execution.

ENTRYPOINT ["nginx", "-g", "daemon off;”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

---

  - Dockerfile for myServer

```
FROM <MY_DOCKERHUB_USER>/myNginx:<VERSION>  # Dockers hierarchy. Comes from myNginx

ADD <html_files> /var/www/html  # Copy files into the image
```

---

- Build and tag the docker images. Generate a unique, portable and reproducible version.
  - Option 1
```
docker build -t <MY_DOCKERHUB_USER>/myServer:<VERSION> -f Dockerfile-myServer1 .
```

  - Option 2: You should build and publish myNginx image before.
```
docker build -t <MY_DOCKERHUB_USER>/myNginx:<VERSION> -f Dockerfile-myNginx .
docker login -u <MY_DOCKERHUB_USER> -p <MY_DOCKERHUB_PASS>
docker push <MY_DOCKERHUB_USER>/myNginx:<VERSION>
docker build -t <MY_DOCKERHUB_USER>/myServer:<VERSION> -f Dockerfile-myServer2 .
```

---

- Login to docker hub with your user/pass if needed.

```
docker login -u <MY_DOCKERHUB_USER> -p <MY_DOCKERHUB_PASS>
```

- Push the image to a docker registry to make it available.

```
docker push <MY_DOCKERHUB_USER>/myServer:<VERSION>
```

---

- Launch an instace of our dockerized web server.
```
docker run -td <MY_DOCKERHUB_USER>/myServer:<VERSION>
```

- Making it nicer: Set an alias in user computer and he/she could launch his local server.
```
alias MyServer='docker run -td <MY_DOCKERHUB_USER>/myServer:<VERSION>'

MyServer 
```

---

### Practices

---

#### HMS docker hierarchy proposition for the workshop

<img src="day1/containers/slides/images/containers.jpg", style="height:40vh; background-color:white; float:center;"/>

---

- JRE8

Based on hms-base, create a docker with oracle jre8 installed to be the ari service execution.

- Maven

Based on hms-jdk8, create a docker with maven to be to be the ari service builder.

---

### References

---

#### Web
- Official Documentation - https://docs.docker.com/
- Docker 101 Tutorial - https://blog.docker.com/tag/docker-101/
- Official Docker Training - https://training.docker.com/
- Microservices architecture : http://microservices.io/index.html
- An introduction to microservices : https://opensource.com/resources/what-are-microservices
- Neal Ford slices: http://nealford.com/abstracts.html

---

#### Books
- Docker:  Up & Running - Karl Matthias y Sean P.Kane (O’Reilly)
- Docker in Action - Jeff Nickolofff (Manning)
- Building microservices - Sam Newman (O’Reilly)





