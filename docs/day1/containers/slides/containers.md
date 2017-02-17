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

### Docker
Docker is a container platform used to develop, deploy and execute dockerized app's. Ecosystem:

- Docker Engine: Server and client to work with docker.
- Docker Trusted Registry: Docker repository where save, push and pull docker images.
- Docker Hub: Docker registry official service. Could code autobuilds from github y bitbucket.
- Docker Machine: Tool to deploy hosts in distinct providers.
- Docker Compose: Tool to deploy multi docker services.

---

#### First steeps

To access docker service, we need a minimal linux instalation (debian, ubuntu, coreos,...) with docker installed and running. Docker daemon listen by default in a unix socket /var/run/docker.sock. Also, it's possible to listen in a network port 2375 y 2376 (ssl).
Connection could be:
- Local: Executing the command in a machine running the daemon, connect through unix socket. docker <command>
- Remote: Executing the command in a remote machine not running the daemon. We need the parameter -H or specify the environment variable DOCKER_HOST. 
docker -H tcp://<host>:<port> <command>
export DOCKER_HOST=tcp://<host>:<port>; docker <command>

---

#### Commands

For containers:
- Life cycle : create, rename, run, rm, update, images, import, build, commit, rmi, load, save......
- State : start, stop, restart, pause, unpause, wait, kill, attach...
- Information : ps, logs, inspect, events, port, top, stats, diff, tag....
- Import/Export : cp, export...
- Execution : exec...

---

#### Dockerfiles

Dockerfile is the script to build a docker image. Docker images are cumulative and incremental. Every Dockerfile has a FROM section, i mean, what’s the "base" image form it comes. Many distributions and applications, already have docker images published in dockerhub. We could use them as FROM to add or customize for our use. 

---

#### Images

Image workflow:

Dockefile -> docker build -> docker tag -> docker push -> docker rmi

When you generate a dockerfile and build a docker, you are generating a docker image. Every build, generates a new and unique docker image that coulbe tagged and published in whichever docker registry (public or private). 

---

#### Dockers

docker workflow:

docker pull -> docker run -> docker stop -> docker rm 

A docker container is an deployed instance of a docker image. To instance a docker in a host, we only need docker engine installed in our hosts (single dependency) and access to the image we want to launch. Every single docker is unique and has state, started, stopped, created, error,...

---

#### Examples

- Docker as client: 
A user wants to use curl in his computer, but a package for his specific OS doesn’t exist, but he can execute dockers.

We could build a docker that executes curl. 

We generate a Dockerfile
```
FROM docker.io/alpine:3.5 		# Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl curl && \ 	 # When we build a docker, we can execute command inside the image.
    rm -rf /var/cache/apk/* 	# We install curl and some basic packages, and remove packages cache.

ENTRYPOINT [“/usr/bin/curl”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

---

Build: Generate and publish a unique, portable and reproducible version.
```
docker build -t curlApp:0.0.1 -f Dockerfile .
docker push curlApp:0.0.1
```

Execute: Launch an instace of the docker. $CMD would be passed as curl args.
```
docker run -it --rm curlApp:0.0.1 -L www.google.com
```

Making it nice: Set an alias in user computer and he could launch curl.
```
alias curl='docker run -it --rm curlApp:0.0.1 '

curl -L www.google.com
```

---

- Docker as server: 

A user wants a web server to publish static files. 

We could build a docker that copy the static files and executes an nginx server configured with them. 

---

- Option 1: Build only one docker.

Generate a Dockerfile
```
FROM docker.io/alpine:3.5 		# Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl nginx && \
    rm -rf /var/cache/apk/* 	# We install nginx and some basic packages, and remove packages cache.

ADD <html_files> /var/www/html 	# We copy files into the image

EXPOSE 80		# We expose the service by a network port

# App is executed in foreground, to retain the docker started. The docker will be stoped once the primary command 
# will close its stdin, may because it’s executed as daemon, may be because it finish the execution.

ENTRYPOINT ["nginx", "-g", "daemon off;”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

Build: Generate and publish a unique, portable and reproducible version, web server + files.
```
docker build -t MyServer:0.0.1 -f Dockerfile .
```

---

- Option 2: Build two docker, one for the web server and other server + files

Generate a Dockerfile only to nginx server
```
FROM docker.io/alpine:3.5 		# Dockers hierarchy. Where it comes from.

RUN apk add --update bash libressl nginx && \
    rm -rf /var/cache/apk/* 	# We install nginx and some basic packages, and remove packages cache.

EXPOSE 80		# We expose the service by a network port

# App is executed in foreground, to retain the docker started. The docker will be stoped once the primary command 
# will close its stdin, may because it’s executed as daemon, may be because it finish the execution.

ENTRYPOINT ["nginx", "-g", "daemon off;”]	# Default command to execute when we run the docker: $ENTRYPOINT + $CMD
```

Build: Generate a unique, portable and reproducible version, web server. (reusable by other services)
```
docker build -t MyNginx:0.0.1 -f Dockerfile .
```

---

Publish:
Docker push will publish your docker version into a docker registry.
```
docker push MyNginx:0.0.1
```

Generate a Dockerfile from our nginx server and copy files
```
FROM MyNginx:0.0.1 			# Dockers hierarchy. Where it comes from.

ADD <html_files> /var/www/html 	# We copy files into the image
```

Build: Generate a unique, portable and reproducible version, web server + files.
```
docker build -t MyServer:0.0.1 -f Dockerfile .
```

Publish: Publish your docker version into a docker registry.
```
docker push MyServer:0.0.1
```

Execute: Launch an instace of the docker and expose the service.
```
docker run -td MyServer:0.0.1 
```
Making it nice: Set an alias in user computer and he could launch his server.
```
alias MyServer='docker run -td MyServer:0.0.1'

MyServer 
```

---

#### Practices

- JVM
Create a docker with jvm to be a base of our microservices execution.

- JDK + maven
Create a docker with jdk and maven to be a base of our microservices compilation.






