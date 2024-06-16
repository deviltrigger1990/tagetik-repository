# Tagetik-Repository

The code published in the Tagetik-Repository repository contains the Java implementation of the three exercises provided by Tagetik.

# Project Setup Instructions

To start the project, execute the following commands:

1. Change the directory:
   `cd tagetik-repository\TagetikAppsProject`
2. Install all project dependencies with this maven command:
   `mvn clean install`
3. Each project has an application class that starts an instance of Spring Boot. You can launch the Tomcat server, where Spring Boot runs, using the following command:
   `mvn spring-boot:run`

# Project Database

The web application uses two types of databases: PostgreSQL to faithfully replicate a real production scenario, and an in-memory H2 database used during tests.
The configurations for these databases are found in the application.properties file

## Exercises 1 and 2

Exercises 1 and 2 can be tested using the `ExerciseTest1` and `ExerciseTest2` classes located in the `/src/main/test` folder within the Maven modules `TagetikFirstProject` and `TagetikSecondProject`. The business logic for Exercise 1 is encapsulated in the documented interface `CategoryAllDescendantsRetriever`, and for Exercise 2 in the documented interface `CategoryTotalAmountCalculator`.

## Exercise 3

Exercise 3 exposes REST services running in a Spring Boot application. To launch the Spring Boot server, navigate to the `TagetikThirdProject` folder and start the Spring Boot server with the Maven command 

`mvn spring-boot:run`

For this project, you can also run tests found in the `Exercise3Test` class located in the `/src/main/test` folder of the `TagetikThirdProject`. The test class `ProductServiceTest` and `ValidatorServiceTest`
test the validation service and the business logic of the service class `ProductService`. 

## Testing Setup
Each test starts a dedicated Spring Boot context specifically for the testing environment. This allows me to test and directly inject all the interfaces I have developed to implement the business logic of the exercise.

Exercise 3 also uses a database to store data and verify its correct retrieval.