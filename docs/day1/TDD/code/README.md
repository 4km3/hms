##Ari

Ari means ant in japanese, the reason of the name is the Aesop's Fable 'The Ant and the Grasshopper'.


###Prerequisites

* Java 8
* Maven 3.0

####Build

mvn clean package

####Test

mvn clean test

####Run

java -jar target/ari-0.1.0.jar

###Acceptance Testo

* For run in local environment Add in /etc/hostsoo
  * 127.0.0.1  ari
* cd acceptance-test
* mvn test