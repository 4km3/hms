### Practice 1

#### Get the code

In this practice we obtain the code for the course and check your laptop configuration.

* Clone the course report in your laptop.
  * git clone https://github.com/4km3/hms.git
* Open a terminal console.
* cd yourProjectDirectory/docs/day1/ATDD/code

---

#### Execute: mvn -version

```
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T17:41:47+01:00)
Maven home: /Applications/apache-maven-3.3.9
Java version: 1.8.0_65, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.2", arch: "x86_64", family: "mac"

```

---

#### Execute: mvn clean test

```
[INFO] Surefire report directory: /Users/raimundoalegriallorente/Documents/workspace/hms/docs/day1/ATDD/code/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------

...

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.576 s
[INFO] Finished at: 2017-02-10T07:53:25+01:00
[INFO] Final Memory: 28M/313M
[INFO] ------------------------------------------------------------------------

```

---

### Microservices

---

Is an approach to developing a single application as a suite of small services,
each running in its own process and communicating with lightweight mechanisms,
often an HTTP resource API.

---

### Characteristics

---

Componentization via Services

---

Organized around Business Capabilities

---

Products not Projects

---

Smart endpoints and dumb pipes

---

Decentralized Governance

---

Decentralized Data Management

---

Infrastructure Automation

---

Design for failure

---

Evolutionary Design

---

## References

* https://martinfowler.com/microservices/
* https://martinfowler.com/articles/microservices.html