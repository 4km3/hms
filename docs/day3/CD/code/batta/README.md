##Batta

Batta means grasshopper in japanese, the reason of the name is the Aesop's Fable 'The Ant and the Grasshopper'.

Is a microservice splitted from Ari, contain the part of the service that keeps the money. Is the first step of
a complete split. The service don't have its own acceptance test, use the ari tests to test if it's fulfills the
contract.

###Prerequisites

* Java 8
* Maven 3.0

####Build

mvn clean package

#### Test

mvn clean test

#### Run

* For run in local environment Add in /etc/hosts
  * 127.0.0.1 batta

java -jar target/batta-0.1.0.jar


