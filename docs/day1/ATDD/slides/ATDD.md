# ATDD

---

### Why does it matter?

* It's a new way to communicate between business and developers.
* Allows evolutionary architecture.
* Defines what the system do, not how.
* Creates living documentation.
* Generate a set of regression tests.
* Acceptance tests ensure you build the right thing.
* They are executable specifications.
* They become the definitive source of truth as to what the system does.

---

## What is a user story?

### Definition

A user story describes functionality that will be valuable to either a user or purchaser of a system or software.

---

### Characteristics

* Card: A written description of the story used for planning and as a reminder.
* Conversations: About the story that serve to flesh out the details of the story.
* Tests that convey and document details and that can be used to determine when a story is complete.

---

### Characteristics of a good user story

* Independent: Should be self-contained, in a way that there is no inherent dependency on another story.
* Negotiable: Stories are not explicit contracts and should leave space for discussion.
* Valuable: A story must deliver value to the stakeholders.
* Estimable: You must always be able to estimate the size of a story.
* Small: Stories should not be so big as to become impossible to plan/task/prioritize.
* Testable: The story or its related description must provide the necessary information to make test development possible.

---

## Practice 1
    
### Find the user stories in the code

* Open the code in your IDE.
  * Browse to yourProjectDirectory/docs/day1/ATDD/code/acceptance-test/src/test/resources/features/customers.
  * Open createCustomer.feature.

---

### A simple scenario

```
Background:
    Given a customers with the following data:
      | id      | identityCard | name   | lastName  | email                           | phone     | avatar  |
      | abcdefg | 79986535X    | Alfred | Hitchcock | alfred.hitchcock@indiscreet.com | 636561241 | potatoe |

  Scenario: Register a new person successfully
    When a person is registered in the system with the following data:
      | id                  | name | lastName | idCard    | email         | termsAndConditions | mobilePhone |
      | 43r34t-rgertwe-rw43 | Bart | Simpson  | 50861048K | lmb@gmail.com | true               | 636589713   |
    Then the response must be "CREATED"
    And the response has links to
      | link | api        | href   |
      | self | ari-read | api/me |
    And therefore, it exists a customer, identified by "43r34t-rgertwe-rw43" with the following data:
      | name | lastName | idCard    | email         |
      | Bart | Simpson  | 50861048K | lmb@gmail.com |
    And the customer "50861048K" has the following limits into its bucks:
      | rechargeThisPeriod | rechargeLast | rechargeRemaining | rechargeMax |
      | 0.0 EUR            | 0.0 EUR      | 2500.0 EUR        | 2500.0 EUR  |

```

---

## Cucumber



---

### Gherkin

Gherkin is plain-text English (or one of 60+ other languages) with a little extra structure.

Gherkin is designed to be easy to learn by non-programmers, yet structured enough to allow concise
description of examples to illustrate business rules in most real-world domains.

---

### Practice 2

### Execute acceptance test

* Open a terminal console.
* Execute:
  * cd yourProjectDirectory/docs/day1/ATDD/code
  * mvn clean package
  * java -jar target/ari-0.1.0.jar

---

```
...

2017-02-12 23:02:36.884  INFO 24082 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-02-12 23:02:36.895  INFO 24082 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2017-02-12 23:02:37.039  INFO 24082 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-02-12 23:02:37.045  INFO 24082 --- [           main] io.ari.Application                       : Started Application in 4.499 seconds (JVM running for 4.926)

```

---

* Open other terminal console
* Execute:
  * cd yourProjectDirectory/docs/day1/ATDD/code/acceptance-test
  * mvn clean test

---

```
...

29 Scenarios (29 passed)
101 Steps (101 passed)
0m2.776s

Tests run: 130, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.367 sec

Results :

Tests run: 130, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.347 s
[INFO] Finished at: 2017-02-12T23:04:47+01:00
[INFO] Final Memory: 21M/309M
[INFO] ------------------------------------------------------------------------

```

---

### Feature

Is supposed to describe a single feature of the system, or a particular aspect of a feature.
It's just a way to provide a high-level description of a software feature, and to group related scenarios.

A feature has three basic elements:

* The Feature: keyword.
* A name (on the same line).
* An optional (but highly recommended) description that can span multiple lines.

---

### Scenario

A scenario is a concrete example that illustrates a business rule. It consists of a list of steps.

* You can have as many steps as you like, but we recommend you keep the number at 3-5 per scenario.
* If they become longer than that they lose their expressive power as specification and documentation.

---

### Step definitions

* Given: Are used to describe the initial context of the system.
* When: Are used to describe an event, or an action.
* Then: steps are used to describe an expected outcome, or result.

---

## Practice 3

### Add new scenario to existing feature

* Open the code in your IDE.
  * Browse to yourProjectDirectory/docs/day1/acceptance-test/src/test/resources/features/customers.
  * Open createCustomer.feature.
  * Copy the scenario into createCustomer.feature

---

```

Scenario: Conflict when a customer exists with the given idCard
    When a person is registered in the system with the following data:
      | id         | name | lastName | idCard    | email         | termsAndConditions |
      | lahdsfalsd | Bart | Simpson  | 79986535X | lmb@gmail.com | true               |
    Then the response must be "CONFLICT"
    And the response has the following data
      | code           | description                                                       |
      | existingIdCard | Attempting to create a customer with an existing idCard:79986535X |

```

---

* Open other terminal console
* Execute:
  * cd yourProjectDirectory/docs/day1/ATDD/code/acceptance-test
  * mvn clean test

---

```

Failed scenarios:
features/customers/createCustomer.feature:37 # Scenario: Conflict when a customer exists with the given idCard

30 Scenarios (1 failed, 29 passed)
105 Steps (1 failed, 1 skipped, 103 passed)
0m1.722s

java.lang.AssertionError: The response status must be the expected expected:<409> but was:<201>
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.failNotEquals(Assert.java:834)
	at org.junit.Assert.assertEquals(Assert.java:645)
	at stepdefinitions.response.ThenTheResponseMustBe.the_response_must_be(ThenTheResponseMustBe.java:20)
	at ✽.Then the response must be "CONFLICT"(features/customers/createCustomer.feature:41)

Tests run: 135, Failures: 2, Errors: 0, Skipped: 1, Time elapsed: 3.515 sec <<< FAILURE!
Then the response must be "CONFLICT"(Scenario: Conflict when a customer exists with the given idCard)  Time elapsed: 0.003 sec  <<< FAILURE!
java.lang.AssertionError: The response status must be the expected expected:<409> but was:<201>
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.failNotEquals(Assert.java:834)
	at org.junit.Assert.assertEquals(Assert.java:645)
	at stepdefinitions.response.ThenTheResponseMustBe.the_response_must_be(ThenTheResponseMustBe.java:20)
	at ✽.Then the response must be "CONFLICT"(features/customers/createCustomer.feature:41)

Scenario: Conflict when a customer exists with the given idCard  Time elapsed: 0.004 sec  <<< FAILURE!
java.lang.AssertionError: The response status must be the expected expected:<409> but was:<201>
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.failNotEquals(Assert.java:834)
	at org.junit.Assert.assertEquals(Assert.java:645)
	at stepdefinitions.response.ThenTheResponseMustBe.the_response_must_be(ThenTheResponseMustBe.java:20)
	at ✽.Then the response must be "CONFLICT"(features/customers/createCustomer.feature:41)

Feb 12, 2017 11:13:27 PM org.springframework.context.support.GenericApplicationContext doClose
INFO: Closing org.springframework.context.support.GenericApplicationContext@2d710f1a: startup date [Sun Feb 12 23:13:24 CET 2017]; root of context hierarchy

Results :

Failed tests:   Then the response must be "CONFLICT"(Scenario: Conflict when a customer exists with the given idCard): The response status must be the expected expected:<409> but was:<201>
  Scenario: Conflict when a customer exists with the given idCard: The response status must be the expected expected:<409> but was:<201>

Tests run: 135, Failures: 2, Errors: 0, Skipped: 1

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.428 s
[INFO] Finished at: 2017-02-12T23:13:27+01:00
[INFO] Final Memory: 21M/309M

```

---

# Lesson learned

* All the team must write the features together.
* Test must be independent. Avoid test wars.
* Keep the test DRY.
* Try to keep the feature declarative.
* Asynchronous interfaces are hard to test.
* Don't access directly to the database front test.

---

## References

### Books

* User Stories Applied.For Agile Software Development. Mike Cohn
* Specification by Example: How Successful Teams Deliver the Right Software. Gojko Adzic
* The Cucumber Book: Behaviour-Driven Development for Testers and Developers. Matt Wynne, Aslak Hellesoy

---

### Links

* https://cucumber.io/