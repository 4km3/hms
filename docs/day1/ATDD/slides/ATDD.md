# Why does it matter?

* It's a new way to communicate between business and developers.
* Allows evolutionary architecture.
* Defines what the system do, not how.
* Creates living documentation.
* Generate a set of regression tests.

---

# What is a user story?

 * It's a meeting point
 * Card
 * Conversation
 * Collaboration
 
---

# Characteristics of a good user story 

* Independent
* Negotiable
* Valuable 
* Estimable
* Small
* Testable

---

# Exercise 1
    
## Get the code and find the features

* Clone the course report in your laptop.
* Open the code in your IDE.
* In the acceptance test directory find the features.  

---

# Why specification by example?

* Concrete

---

# Which tools?

  * Cucumber  
  * Gherkin

---


# A simple example
  
```
Feature: Customer registration

  As a customer,
  I order to 
  I want to register on the application to create my own account
    
  Scenario: Register a new person successfully
    Given a customers with the following data:
          | id      | identityCard | name   | lastName  | email                           | phone     | avatar  |
          | abcdefg | 79986535X    | Alfred | Hitchcock | alfred.hitchcock@indiscreet.com | 636561241 | potatoe |
    When a person is registered in the system with the following data: (Reescribir)
      | id                  | name | lastName | idCard    | email         | termsAndConditions | mobilePhone |
      | 43r34t-rgertwe-rw43 | Bart | Simpson  | 50861048K | lmb@gmail.com | true               | 636589713   |
    Then therefore, it exists a customer, identified by "43r34t-rgertwe-rw43" with the following data:
      | name | lastName | idCard    | email         |
      | Bart | Simpson  | 50861048K | lmb@gmail.com |

``` 
  

# Anatomy of a feature

* Feature
* Scenario:
* Given: Describe  
* When:
* Then:
  
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

* Clone the course report in your laptop.
* Follow the steps defined in the README file.       
* The step is implemented but is in red because is not implemented in system.
 
---

# It cool, but how start to do this?

---
# References

## Books

* Specification by Example: How Successful Teams Deliver the Right Software. Gojko Adzic
* The Cucumber Book: Behaviour-Driven Development for Testers and Developers. Matt Wynne, Aslak Hellesoy
* Libro de mike cohn

## Links

* https://cucumber.io/
