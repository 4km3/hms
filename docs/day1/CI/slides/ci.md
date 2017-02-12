## Continuous Integration

### Brief introduction

- CI is the process of automating software build
- Traditional code development involves multiple steps
- Often manual, those steps might introduce new bugs
- CI enables faster and safer pipelines by automating these steps 
- CI also enables developer to catch bugs early on development cycle

---

### Process Overview

- Developers commit source code to a SCM
- A CI tool checks out the changes uploaded to the SCM
- It then runs a batch of tests over the new code
- If all tests are passed, the artifacts are stored
- The information on the build process is made available to the developer

---

### Jenkins CI

- Jenkins is an open source extensible automation server
- Supports popular SCM tools 
- Built around pipelines
- Pipelines enable software lifecycle management

---

### Jenkins CI

- Supports ant, maven, gradle for building automation
- Unit and acceptance testing are supported 
- Jenkins supports developer notifications via e-mail, info radiator, etc
- Jenkins has plugin support to extend its capabilities

---

## Exercise

- We will automate the deployment of the code generated in the previous exercise
- For that purpose, we will use Jenkins as our CI tool deployed inside your Rancher
- This Jenkins instance will check over your GitHub repo and then run the tests included in the code

