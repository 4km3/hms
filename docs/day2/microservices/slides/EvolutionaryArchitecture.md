
The Only Thing That Is Constant Is Change

― Heraclitus

---

### Evolutionary architecture

An evolutionary architecture designs for incremental change in an architecture as first principle.

---

### Characteristics

---

#### Modularity and Coupling

The ability to separate components along well defined boundaries allows to developers to make non breaking changes.
Inappropriate coupling inhibits evolution by propagate changes in a difficult to predict ways.

---

#### Organized Around Business Capabilities

Modularity at the domain level is increasingly done using Domain-Driven Design.

---

#### Experimentation

Changes to an application becomes trivial allowing for A/B testing and Canary releases, among others.
This allows for experimentation and ultimately enables usage of Hypothesis-Driven Development.

---

### Principles

---

#### Last Responsible Moment

In an evolutionary architecture, we wait for the last responsible moment to make decisions.
The benefit of delaying a decision is the additional information available to make the decision.

---

#### Bring the Pain Forward

When something on a project has the potential to cause pain, force yourself to do it more often and earlier,
which in turn encourages you to automate the pain away and identify issues early

---

#### Fitness Functions

We distinguish between emergent and evolutionary architecture, and this distinction is an important one.
In evolutionary architecture a fitness function specifies what our target architecture looks like.
Examples include levels of uptime, throughput, availability and security needed.

---

### Change the metaphor

* The architect's metaphor bring to our minds this ideas:
  * The idea of someone who draws up detailed plans for others to interpret.
  * Diagram after diagram created with a view to inform the construction of the perfect system.
  * Someone utterly devoid of any understanding as to how hard it will be to implement.

---

* In contrast the town planner metaphor:
  * The town planner does his best to anticipate the changes, but accepts
    that trying to exert direct control over all aspects of what happens is futile.
  * Town planners likened a city to a living creature.
  * The city changes over time. It shifts and evolves as its occupants use it in different ways,
    or as external forces shape it.

---

### Lesson learned

* Evolutionary architecture is hard.
* Start small, deploy in production fast.
* Try the simplest thing that work.
* You Ain't Gonna Need It.

---

* Use an hypothesis driven development. Small experiments tested against reality.
* The architect MUST program.
* The change is your friend, don't fight against it, embrace it.
* Test frequently your fitness function, realize what are your system fragilities.
* It's impossible predict the future, seriously.

---

### References

### Talks

* https://www.infoq.com/presentations/evolutionary-architecture-microservices-cd

### Links

* https://www.thoughtworks.com/insights/blog/microservices-evolutionary-architecture
* https://martinfowler.com/bliki/SacrificialArchitecture.html
* https://martinfowler.com/bliki/MonolithFirst.html