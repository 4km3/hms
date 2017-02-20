### Continuous Integration

---

- CI is a practice that aims to prevent integration problems, aka “integration hell”
- In order to build but the most trivial pieces of software, multiple steps are often required
- These steps, collectively, can be understood as “build pipelines”
- CI techniques allows these build pipelines to be fully automated
	- Automation reduces the build time
	- Also minimizes the chance of human error
- TDD is essential, otherwise essential feedback won't be availablee (did it fail? did it not? I'll ask my dog, then)
- A consecuence of CI is that the code can be rolled into production with little extra effort

---

### Safer Pipelines

- CI allows to detect:
	- Bugs in the commited code early on the development cycle
	- Issues when integrating with other developers' code
- The integration process must be frequent, potentially tens of times a day
- Safety depends on the test suite in use
- Enables visual feedback on the build status
- Test environment must be as close as possible to production

---

### Faster Pipelines

- Automation in the process makes it seamless
- Also eliminates the human-induced bugs
- Faster Pipelines, not necessarily equal to faster builds
- Every minute counts, so please try to speed up build time
- The automated build should include every needed step, not shortcuts

---

### Process Overview

- Developers commit source code to a SCM
- A CI tool checks out the changes uploaded to the SCM
- It then runs a batch of tests over the new code
- If all tests are passed, the artifacts are versioned and stored
- The feedback of the build process is made available to the developers

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

## Practice


- Automate the integration of the code available in your forked repo from hms-code
- For that purpose, we will use Jenkins as our CI tool
- Jenkins will check over your GitHub repo and then run the tests included in the code
- After the tests have been run it will show the tests tendency graphs and provide the resulting artifacts

---

## Step 1

- Configure GitHub Service
	- Inside your repo settings, 'Integrations & Services' block, select 'Add Service'
	- Search for Jenkins, and select 'Jenkins (Git Plugin)'
	- Fill in your specific Jenkins URL (http://jenkins.hms.rawmind.net/)

---

## Step 2

- Setup a new freestyle project
	- Activate the github project option, pointing to your repo
	- Activate Poll SCM with no further options
	- Add a build step, class 'Invoke top-level Maven targets' with Maven version mvn and target verify

---

## Step 3

- Add two postbuild actions:
	- First 'Archive the artifacts' with value '**/target/*.jar'
	- Second 'Publish JUnit test result report' with value '**/target/surefire-reports/TEST-*.xml'

- Now try make some changes to your repo locally and then uploading them to your repo.
- Check what happens in your Jenkins web view

---

### References

### Links

- https://en.wikipedia.org/wiki/Continuous_integration
- https://www.martinfowler.com/articles/continuousIntegration.html
