### Microservices

Microservices are small, autonomous services that work together.

---

#### Small, and Focused on Doing One Thing Well

* Cohesion—the drive to have related code grouped together—is an important concept when we think about microservices.
* Single responsibility principle.
* Fit in your head.
* The smaller the service, the more you maximize the benefits and downsides of microservice architecture.

---

#### Autonomous

* Every microservice is a separate entity.
* All communication between the services themselves are via network calls.
* The service exposes an application programming interface (API), and collaborating services communicate with us via those APIs.
* The golden rule: can you make a change to a service and deploy it by itself without changing anything else.

---

### Benefits

---

#### Strong Module Boundaries

Microservices reinforce modular structure, which is particularly important for larger teams.

#### Independent Deployment

Simple services are easier to deploy, and since they are autonomous, are less likely to cause system failures when they go wrong.

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

![](./images/microservices.productivity.png)

---

### Microservice charateristics

#### High cohesion

* All the related behavior is grouped together inside of the same microservice.
* When change occurs we want to be able to change it in one place.

#### Low coupling

* A change to one service should not require a change to another.
* A loosely coupled service knows as little as it needs to about the services with which it collaborates.
* Chatty communication can lead to tight coupling.
* A microservice can deploy in production independently.

---

### Domain Driven Design

---

DDD is about trying to make your software a model of a real-world system or process.
In using DDD, you are meant to work closely with a domain expert who can explain how the real-world system works.

---

#### Ubiquitous language

Is the the practice of building up a common, rigorous language between developers and bussiness experts.
This language should be based on the Domain Model used in the software - hence the need for it to be rigorous,
since software doesn't cope well with ambiguity.

---

#### Bounded Contexts

Multiple models are in play on any large project.

* When code based on distinct models is combined:
  * Software becomes buggy.
  * Unreliable.
  * Difficult to understand.
  * Communication among team members becomes confused.

---

To solve this:

* Explicitly define the context within which a model applies.
* Explicitly set boundaries in terms of:
  * Team organization.
  * Usage within specific parts of the application.
  * Physical manifestations such as code bases and database schemas.

Keep the model strictly consistent within these bounds.

---

Within each Bounded Context, you will have a coherent dialect of the Ubiquitous language.

---

#### Practice 1

---

#### Detect bounded contexts

```


```

---

### Why split the monolith?

* Performance

#### Conway law

Take care of the organization

---

### Strategies to split a monolith?

---

### Practice 2 (30')

Split the monolith. Using the code in the previous exercises split our monolith in
microservices.

---

### Lessons learned

* Start small
  * Don't create as many microservices as you can.
  * Start with small team with the correct culture.
  * Don't use all the existing databases.
  * Don't use all the existing programming languages.
---

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
