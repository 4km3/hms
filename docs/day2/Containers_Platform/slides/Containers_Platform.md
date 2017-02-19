## Containers platform (2:30h)
- Intro and presentation 
- Market offering
- Rancher platform
- Practices

---

###  Intro and presentation

- Microservices architecture requires new infrastructure components. 
- Massive dynamism, portability and extensibility are required. 
- Current cloud platforms are not portable neither extensible.


---

Principal components
- Scheduler/orchestrator: How and where you container will be deployed.
- Network: Network layer to communicate containers between them avoiding port conflict. 
- Storage: Storage service to be able to share and persist the services data.
- Service discovery: Information about what and where services are running in the system.
- Service management: Manage access to the services in a HA and load balance scenarios.

---

Functionality is the most important...
<img src="https://raw.githubusercontent.com/cncf/landscape/master/landscape/CloudNativeLandscape_v0.9.3.jpg", style="height:600; width:auto; background-color:white; float:center;"/>


---

####  Scheduler/orchestrator

A container scheduler is a piece of software that manages the server resources and deployment into them. It knows all the servers that provide resources (cpu and memory), how much they offer and how much resource are free. Based on the work load of every server, the scheduler will deploy the containers in the most free server, in order to manage the global load of the system.

Also, the scheduler is responsible to move or to rebalance the services if a server enter in maintenance mode or goes down, and to assure that the services are running in the scale that you define for them.

---

Principal scheduler/orchestrator in the market

<img src="day2/Containers_Platform/slides/images/scheduler.jpg", style="height:auto; width:900; background-color:white; float:center;"/>
 

---

#### Network

Network overlay is a service that provide the communication between containers independent from the hosts. It would generate a flat overlay network that provide an ip to every single container wide all hosts.

It helps to avoid networking collision and/or overlap between all the services exposing services in your system. 

---

Principal network services in the market

<img src="day2/Containers_Platform/slides/images/network.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

####  Storage

Storage service makes the tasks to manage and provide persintence to your services. It's specially important in statefull and/or db services that has to persist and share data.

Basically, you could choose between block storage, fs storage or object storage. 

---

Principal storage services in the market

<img src="day2/Containers_Platform/slides/images/storage.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Service discovery

A system discovery is a distributed key/value service that knows all the information about our services. When you add a new service, it will be added to the system discovery, when you scale an already deployed service a new backend will be added or removed to the system discovery. 

Users or services could talk with service discovery in order to know how to communicate with a service, for examples which are the backends of a services??

System discovery is dependent of the scheduler you are using.

---

Principal Service discovery in the market

<img src="day2/Containers_Platform/slides/images/service_discovery.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Service management

A service management is a platform service that provides access to the services, providing a load balancing and high availability features. 

The service management provides an indirection layer to access the services. It could manages a service with dynamic backends that provide it or the access to different versions of the service. 

Load balancer could be external or internal, depending the use of them.


---

Principal service management in the market

<img src="day2/Containers_Platform/slides/images/service_management.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

### Rancher platform

- Rancher is an opensource containers platform, designed to be open and avoid vendor locks. 
- It provides a unified platform to manage from hosts (multicloud) to microservices. 
- It's a multi environment platform.
- It provides and manages principal market scheduler/orchestrator.
- It provides a full API and CLI to facilitate the automation and the integration with other pieces. 
- It provides some useful services as user auth, registry management, ssl certs management...

---

#### Overview

<img src="day2/Containers_Platform/slides/images/rancher-overview.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Platform Schema
Manage multiple isolated environments
<img src="day2/Containers_Platform/slides/images/platform.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Environment
Environments by functionality
<img src="day2/Containers_Platform/slides/images/rancher-environment.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Hosts
Provide host from multiple cloud providers 
<img src="day2/Containers_Platform/slides/images/rancher-host.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Schedulers
Choose orchetrator by environment
<img src="day2/Containers_Platform/slides/images/rancher-schedulers.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Network
Choose the network management
<img src="day2/Containers_Platform/slides/images/rancher-network.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Storage
Choose storage backend
<img src="day2/Containers_Platform/slides/images/rancher-storage.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Service Discovery
Built in service discovery
<img src="day2/Containers_Platform/slides/images/rancher-SDiscovery.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Service Management
HAproxy, traefik or external load balancers
<img src="day2/Containers_Platform/slides/images/rancher-SManagement.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Catalog
One click deploy already integrated services
<img src="day2/Containers_Platform/slides/images/rancher-catalog.jpg", style="height:550; width:auto; background-color:white; float:center;"/>

---

#### API
Full featured API 
<img src="day2/Containers_Platform/slides/images/rancher-api.jpg", style="height:auto; width:900; background-color:white; float:center;"/>

---

#### Overview

- Admin
- Infrastructure
- Environments
- Stack
- Catalog
- API

---

#### Anatomy of a stack

- A stack could have one or many service inside it. 
- Every service is independent in terms of deployment or scale.
- Definition by standard docker-compose.yml and rancher-compose.yml. 
- External services could be added. 
- Built in LoadBalancers (haproxy).
- LoadBalancer could work on layer 4 or on layer 7.

---

### Example

Create a hms service with a loadbalancer and access slides to view the concept.

UI and rancher CLI command line

---

docker-compose.yml
```
version: '2'
services:
  web:
    image: rawmind/hms
    stdin_open: true
    tty: true
    labels:
      io.rancher.container.pull_image: always
  lb:
    image: rancher/lb-service-haproxy:v0.4.9
    links:
    - web:web
    ports:
    - 8080:8080/tcp
    labels:
      io.rancher.container.agent.role: environmentAdmin
      io.rancher.container.create_agent: 'true'
```

---

rancher-compose.yml
```
version: '2'
services:
  web:
    scale: 1
    start_on_create: true
    health_check:
      response_timeout: 2000
      healthy_threshold: 2
      port: 8080
      unhealthy_threshold: 3
      initializing_timeout: 60000
      interval: 2000
      strategy: recreate
      request_line: GET "/" "HTTP/1.0"
      reinitializing_timeout: 60000
  lb:
    scale: 1
    start_on_create: true
    lb_config:
      certs: []
      port_rules:
      - priority: 1
        protocol: http
        service: web
        source_port: 8080
        target_port: 8080
    health_check:
      healthy_threshold: 2
      response_timeout: 2000
      port: 42
      unhealthy_threshold: 3
      interval: 2000
      strategy: recreate

```

---

#### Rancher CLI

```
rancher up -d -u -s HMS   # Upgrade HMS stack

rancher up -d -c -s HMS   # Confirm HMS upgrade or
rancher up -d -r -s HMS   # Rollback HMS upgrade
```

---

### Practices

---

#### Practice 1

Get the app dockers created in the previous stages, deploy an stack/services into the platform and access them.

---

### Practice 2

Make some change in one of the services and upgrade the already deployed stack/services.

---

### References

---

#### Web
- Cloud Native Landscape - https://github.com/cncf/landscape
- Rancher Site - http://rancher.com/
- Rancher Documentation - https://docs.rancher.com/rancher/v1.4/en/

---

#### Training & videos
- Rancher free monthly training sessions - https://attendee.gotowebinar.com/rt/2905734731496917249
- Rancher youtube channel - https://www.youtube.com/channel/UCh5Xtp82q8wjijP8npkVTBA


