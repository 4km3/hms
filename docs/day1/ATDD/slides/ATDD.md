# ATDD

## Why does it matter?

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
* Small: Stories should not be so big as to become impossible to plan/task/prioritize with a certain level of certainty.
* Testable: The story or its related description must provide the necessary information to make test development possible.

---

## Exercise 1
    
### Find the user stories in the code

* Open the code in your IDE.
* Go to acceptance-test/src/test/resources/features/customers.
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

## Gherkin

Gherkin is plain-text English (or one of 60+ other languages) with a little extra structure.

Gherkin is designed to be easy to learn by non-programmers, yet structured enough to allow concise
description of examples to illustrate business rules in most real-world domains.

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

## Exercise 2

### Execute acceptance test

*
*
*

---

# Lesson learned

* All the team must write the features together.
* Test must be independent. Avoid test wars.
* Keep the test DRY.
* Try to keep the feature declarative.
* Asynchronous interfaces are hard to test.
* Don't access directly to the database front test.

---

# Exercise 
    
## Add new step to existing scenario

*
*
*

---

## References

### Books

* User Stories Applied.For Agile Software Development. Mike Cohn
* Specification by Example: How Successful Teams Deliver the Right Software. Gojko Adzic
* The Cucumber Book: Behaviour-Driven Development for Testers and Developers. Matt Wynne, Aslak Hellesoy

### Links

* https://cucumber.io/
