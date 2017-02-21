### Continuous Deployment

---

### A brief history

- SCM-only systems were already in use 30 years ago
- CI predates DevOps by many years
- CI was born with the Agile Manifesto
- CD = CI + collaboration between Dev and Ops

---

### Continuous Deployment

- CD is the way to go in order to scale 
- Continuous Delivery is CD without the last step, which requires manual approval

---

### CI/CD/CD

- Continuous Integration: integrating, building, and testing code within the development environment
- Continuous Delivery: being able to deploy at any time upon managers request
- Continuous Deployment: automate deployment process so that many deployments can be made every day

---

### Continuous Deployment vs. Continuous Delivery

- Continous Delivery means that you are able to do frequent deployments but may choose not to do it, usually due to businesses preferring a slower rate of development
- Continuous Deployment means that every change goes through the pipeline and automatically gets into production, resulting in many production deployments every day
	- You can leverage feature switches to control when a certain feature is *active*

---

### Blue-green deployment

- Deploy a new cluster with the latest tested version of your software (blue)
- Maintain the old cluster around (green)
- Move the balancer to point to the new cluster (blue)
- Pray

---

### Blue-green deployment

- Problems?  Roll back is trivial, just move the balancer back to the other cluster (green)
- After a safety time with no issues, free the old cluster (green)
- Rinse, and repeat, this time deploy newest version onto the green cluster

---

### Blue-green deployment

- Database schema changes should be separated from app upgrades

---

### Phoenix servers

- Snowflakes are cute, but only when it's Christmas
- Can you ssh into your server?  BANG!  This will come to bite ya in the arse
- Unless the only agent that touches your servers is your CD system, the snowflake effect will grow over time

---

### CI/CD Tools

- Service that monitors your code repository for changes
- When a change is detected, it will compile and test your code
- If something goes awry, it will notify you so measures to fix it can be adopted
- Aditionally it can monitor code quality and automate deployment process (CD)

---

### CI/CD Tools

- Hudson/Jenkins
- BuildBot
- GitLab CI
- Travis CI
- GoCD
- Concourse
- Team Foundation Server

---

### Jenkins CI

- Hudson was developed at Sun Microsystems
- After Oracle buys Sun, devs fork the code into Jenkins
- Hudson has been donated to the Apache Foundation

---

### Jenkins concepts

- SCM: Where we keep track of our work
- Steps: Basic building blocks for projects
- Project: Runnable tasks controlled by Jenkins
- Artifacts: Output objects from projects
- Pipelines: Application lifecycle management
- Jenkins DSL: Pipelines definition, Groovy-based

---

### Jenkins concepts

- Master: Main server running Jenkins
- Agent: Systems running projects for the master
- Executor: Each of the build queues Jenkins can use
- Workspace: Disposable directory used as working dir
- Build stability: Dependes on wether tests are successful after building the project
- Build completeness: Output status for the build process

---

### Jenkins Projects

- Freestyle
- Pipeline
- External Project
- Multi-configuration Project
- Multibranch Pipeline
- Extensible-via-plugins etc

---

### Freestyle Project

- This is the most basic project class in Jenkins
- It is defined via web UI using steps
- Steps types can be added via plugins
- It can be triggered manually or via SCM polling, among others
- Supports adding post-build actions (Publish test reports via JUnit, archive test build results, notificate developers, start another build...)

---

### Pipeline project

- Enable the definition of the application lifecycle
- Enable Jenkins to support Continuous Deployment
- Available since 2.0
- Defined by a Groovy based DSL
- Structured around steps/nodes and stages
- Enables the usage of Jenkins-as-a-Service

---

### Pipeline characteristics

- Durable: must survive restarts of Jenkins service
- Pausable: can be paused in order to enable interaction
- Versatile: support CD requirements (join, fork, loop, parallelization)
- Extensible: support custom extensions to its DSL

---

### Pipeline structure

- Step: Single task that is part of a sequence
- Node: Performs two actions
	- Schedules the steps contained in it to the Jekins build queue
	- Creates a workspace
- Stage: Logically distinct part of the execution of any task
	- Has parameters for locking, ordering and labelling its part of the process
	- Pipeline is formed by a set of stages
	- It can contain one or more steps

---

### Pipeline example

```
stage('hello-world') {
    node {
       sh '''echo hello, world'''
    }
}
```

---

### Distributed Builds

- Jenkins uses a master/agent architecture to manage distributed builds
- An agent is a node set up to offload available projects from the master
- Supports a wide range of OS as agent machines
- Supports using cloud instances as agent machines
- Supports using containers as agent builders
- Critical to achieve a scalable architecture that can adapt to your development needs
- Can be used to delegate specialized tasks

---

### Automated testing

- Jenkins supports integration with several build automation tools
- CI proposes that the build should be verifiable
- Supports unit tests, integration tests, functional and web tests
- While easier to use from pipelines, from freestyle is also available (with extra configuration required)

---

Practice text

---

### Recap

- The role of the CI/CD tool is to just trigger entrypoints (shell scripts)
- These entrypoints encapsulate all the logic that the deployment pipeline needs
- This minimal coupling will allow us to switch the CI/CD tool completely with ease
- The description of the deployment pipeline is just code that is versioned along with the rest of the project
- Since everything is under version control, every build is reproducible

---

### References

- https://en.wikipedia.org/wiki/Continuous_delivery
- https://www.amazon.com/Continuous-Delivery-Deployment-Automation-Addison-Wesley/dp/0321601912
- https://martinfowler.com/bliki/BlueGreenDeployment.html
- https://martinfowler.com/bliki/PhoenixServer.html
- https://www.thoughtworks.com/insights/blog/moving-to-phoenix-server-pattern-introduction
