## Continuous Deployment

### A brief history

- SCM-only systems were already in use 30 years ago
- CI comes from an age where DevOps did not exist
- It is born from the Agile Manifesto
- CD = CI + collaboration between Dev and Ops
- CD is the way to go in order to be able to scale
- Continuous Delivery is CD without the last step, which requires manual approval

---

### CI/CD/CD

- Continuous Integration: integrating, building, and testing code within the development environment
- Continuous Delivery: being able to deploy at any time upon managers request
- Continuous Deployment: automate deployment process so many deployments are made every day

---

### CD/CD

- Continous Delivery means that you are able to do frequent deployments but may choose not to do it, usually due to businesses preferring a slower rate of development
- Continuous Deployment means that every change goes through the pipeline and automatically gets put into production, resulting in many production deployments every day
- The most desirable target is Continuous Deployment in part because a human cannot make thousands of deployments per day

---

### CI/CD Tools

- Service that monitors your code repository for changes
- When one change is detected, it will compile and test your code
- If something goes awry, it must notify you so measures can be adopted
- Aditionally it can monitor code quality and automate deployment process (CD)

---

### CI/CD Tools

- Hudson/Jenkins
- BuildBot
- GitLab CI
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


