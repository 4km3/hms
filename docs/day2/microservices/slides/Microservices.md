### Microservices (15')

Microservices are small, autonomous services that work together.

---

#### Small, and Focused on Doing One Thing Well

#### Autonomous

---



---

### Benefits

---

#### Strong Module Boundaries

Microservices reinforce modular structure, which is particularly important for larger teams.

#### Independent Deployment

Simple services are easier to deploy, and since they are autonomous,
are less likely to cause system failures when they go wrong.

#### Technology Diversity

With microservices you can mix multiple languages, development frameworks and data-storage technologies.

---

### Costs

---

#### Distribution
Distributed systems are harder to program, since remote calls are slow and are always at risk of failure.

#### Eventual Consistency

Maintaining strong consistency is extremely difficult for a distributed system,
which means everyone has to manage eventual consistency.

#### Operational Complexity

You need a mature operations team to manage lots of services, which are being redeployed regularly.

---

### When use microservices?

Don't even consider microservices unless you have a system that's too complex to manage as a monolith

![](./images/microservices.productivity.png?raw=true)

---


### Microservice charateristics

#### High cohesion

#### Low coupling

---

### How big is a microservices? (15')

#### Domain Driven Design

* Ubiquitous language
* Bounded Contexts

#### Conway law

Take care of the organization

---

Break (15')

---

Detect bounded contexts (15')

Create a real problem with multiple bounded contests (Gemar)

---


--- (30')

### Why split the monolith?

* Performance

---

### Strategies to split a monolith?

---

### Practice 2 (30')

Split the monolith. Using the code in the previous exercises split our monolith in
microservices.

---

### Lessons learned (15')

* Start small
  * Don't create as many microservices as you can
  * Start with small team with the correct culture
  * Don't use all the existing databases
  * Don't use all the existing programming languages
* Take care about the architecture
  *
  * If
* Remember Conway Law
*

---
### References

### Books

* Building Microservices: Designing Fine-Grained Systems. Sam Newman
* Release It!: Design and Deploy Production-Ready Software (Pragmatic Programmers). Michael T. Nygard
* Domain driven design. Eric Evans

---

### Links

* https://martinfowler.com/microservices/
* https://martinfowler.com/articles/microservices.html
* https://martinfowler.com/bliki/SacrificialArchitecture.html
* http://www.reactivemanifesto.org/
* https://martinfowler.com/articles/microservice-trade-offs.html
* https://martinfowler.com/articles/microservice-testing/
* https://martinfowler.com/bliki/MicroservicePremium.html

---

### Talks

* https://www.infoq.com/presentations/microservices-replaceability-consistency
* https://www.infoq.com/presentations/evolutionary-architecture-microservices-cd
