### TDD

Test-driven development (TDD) is an evolutionary approach to development which combines test-first
development where you write a test before you write just enough production code to fulfill that test and refactoring.

---

### Why matters?

* Allows emergent design, refactor is impossible without unit tests.
* Is a key technique in continuous integration/delivery.
* Fast feedback. Fail fast.
* Creates a detailed specification. Documents the functionality using executable examples.
* Improves quality and reduces bugs.
* Simplification.
* The test is the first client to the class or feature.
* Generate a set of regression tests.

---

### What is a unit testing?

Unit testing refers to the practice of testing certain functions and areas – or units – of our code.
This gives us the ability to verify that our functions work as expected.

---

### The three laws of TDD

#### First Law
You may not write production code until you have written a failing unit test.

#### Second Law
You may not write more of a unit test than is sufficient to fail, and not compiling is failing.

#### Third Law
You may not write more production code than is sufficient to pass the currently failing test.

---

### TDD cycle

* Red
* Green
* Refactor
* Repeat
  * Baby steps

---

### My test strategy

#### Test first

Write the test before write the code

* Save a lot of debugging effort.
* Design the code for testability.
* YAGNI.
* Makes the design more simple.

#### Test in Isolation

*

---

### Anatomy of a test

* Fixture
  * Set the stage to the test, setup the system in the
  * Fresh fixture. Every test not share any data with other tests.
  * Test wars. When two or more tests share the fixture and don´t clean up properly.
* Experimentation
  *
* Expectations
  * Assert per test
  * Assert first

---

### Types of unit tests

* White box/Glass box
* Black box

---    

### Test doubles
* Mocks
* Fakes
* Stubs
* Dummies

---

#Exercise

* Add new test to existing method

### References

### Books

* Menzaros
* Kent beck
* refactor. Martin fowler
* Clean code


###Links

* El link a JUnit