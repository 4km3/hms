### TDD

Test-driven development (TDD) is an evolutionary approach to development which combines test-first
development where you write a test before you write just enough production code to fulfill that test and refactoring.

---

### Why matters?

* Allows emergent design, refactor is impossible without unit tests.
* Is a key technique in continuous integration/delivery.
* Fast feedback. Fail fast.
* Creates a detailed specification. Documents the functionality using executable examples.

---

* Improves quality and reduces bugs.
* Simplification.
* The test is the first client to the class or function.
* Generate a set of regression tests.
* Have a test set allows make changes in the system fast.

---

### What is unit testing?

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

* Red. Create a unit tests that fails.
* Green. Write production code that makes that test pass.
* Refactor. Clean up the mess you just made.

Make it work. Make it right. Make it fast.

---

### My test strategy

---

#### Test first

Write the test before write the code

* Save a lot of debugging effort.
* Design the code for testability.
* Makes the design more simple.
* YAGNI.
* You must think about the better interface for your code.

---

#### Test in Isolation

Test the class separate and apart from the application, and all other units.

* When a test fail you know exactly with class it fails.
* Allows you reasoning about what are the dependencies with other classes or functions.
* You must use test doubles.

---

#### Outside-in development

Designing the software from the outside inward implies that we think first about black-box customer tests (also known as storytests)
 for the entire system and then think about unit tests for each piece of software we design

* Hollywood principle.
* Inversion of Control.
* The system grows organically from user requirement.
* YAGNI

---

### Anatomy of a test

* Fixture
  * Set the stage to the test, setup the system in the initial state to test it.
  * Fresh fixture. Every test not share any data with other tests.
  * Test wars. When two or more tests share the fixture and don´t clean up properly.
* Experimentation
  * The concrete experiment you'll be made.
* Expectations
  * What is the expected results of the test.
  * Assert per test.
  * Assert first.

---

### Types of unit tests

---

#### Black box

* The test doesn't know about the internal structure of the class or function.
* Only know about inputs and outputs.
* Test and implementation are not coupled.
* Easy to refactor.
* Easy to implements in pure functions or systems without side effects.

---

#### White box/Glass box

* The test knows about the internal structure of the class.
* There is a degree of coupling between test and implementation.
* This kind of test are used to test side effects.
* It makes the refactor more difficult.

---

Prefer black box test over glass box tests

---    

### Test doubles

* Mocks
* Fakes
* Stubs
* Dummies

---

### Practice 1

Add new test to existing method.

---

* Open other terminal console
* Execute:
  * cd yourProjectDirectory/tdd (in the tdd branch)
  * mvn clean test

---

```
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.089 sec - in io.ari.schemaValidations.SchemasContainerTest

Results :

Tests run: 63, Failures: 0, Errors: 0, Skipped: 1

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.097 s
[INFO] Finished at: 2017-02-13T15:51:44+01:00
[INFO] Final Memory: 27M/322M
[INFO] ------------------------------------------------------------------------

```

---

* In your IDE
  * Open the java test class.
  * yourProjectDirectory/tdd/src/test/java/io/ari/customers/domain/factories/CustomersFactoryTest.java (in the tdd branch)

---

The factory can't create an user with the same id card of other user.

* Tips
  * Is the same schema of shouldThrowExceptionIfCustomerWithIdExists

---

```
@Test(expected = CustomerIdCardExists.class)
public void shouldThrowExceptionIfCustomerIdCardExists() throws CustomerExists {
    when(customersRepository.findByIdCard(ID_CARD)).thenReturn(Optional.of(customer));
    customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);
}

```

---

* Make the code compiles
  * The exception doesn't exists create it.
  * Create the method in the repository. CustomersRepository.java

```
public Optional<Customer> findByIdCard(String idCard) {
    return Optional.empty();
}

```

---

* Run the test. Red

```
java.lang.AssertionError: Expected exception: io.ari.customers.domain.exceptions.CustomerIdCardExists

	at org.junit.internal.runners.statements.ExpectException.evaluate(ExpectException.java:32)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
```

---

Add this method to the factory

```
private void verifyNonExistingIdCard(String idCard) throws CustomerIdCardExists {
    if (customersRepository.findByIdCard(idCard).isPresent()) {
        throw new CustomerIdCardExists(idCard);
    }
}
```

---

Complete the verification method

```
private void verifyNonExistingCustomer(String customerId, String idCard,String mobilePhone) throws CustomerExists {
    verifyNonExistingCustomerId(customerId);
    verifyNonExistingIdCard(idCard);
    verifyNonExistingMobilePhone(mobilePhone);
}
```

---

* Run the test. What's the hell. All test are Red!

```
java.lang.NullPointerException
	at io.ari.customers.domain.factories.CustomersFactory.verifyNonExistingIdCard(CustomersFactory.java:59)
	at io.ari.customers.domain.factories.CustomersFactory.verifyNonExistingCustomer(CustomersFactory.java:49)
	at io.ari.customers.domain.factories.CustomersFactory.createCustomer(CustomersFactory.java:23)

```

---

```
Record the default CustomersRepository stub

@Before
public void prepareCustomerRepository(){
    when(customersRepository.exists(ID)).thenReturn(false);
    when(customersRepository.findByMobilePhone(MOBILE_PHONE)).thenReturn(Optional.empty());
    when(customersRepository.findByIdCard(ID_CARD)).thenReturn(Optional.empty());
}
```

Run the test again

Green!!!!!!!

---

### Practice 2

Implements the method in CustomersRepository.java to detect if the card is duplicated using TDD.
Run acceptance test after and discover that code fulfills the ATDD lesson scenario.

---

### References

---

### Books

* xUnit Test Patterns: Refactoring Test Code. Gerard Meszaros.
* Test Driven Development. Kent Beck.
* Refactoring: Improving the Design of Existing Code . Martin Fowler.
* Clean code. Robert C. Martin.

---

### Links

* http://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html
* http://junit.org/junit4/
* http://www.jmock.org/oopsla2004.pdf
* https://martinfowler.com/articles/mocksArentStubs.html