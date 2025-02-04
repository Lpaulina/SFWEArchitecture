#All
mvn test
#One class
mvn -Dtest=edu.baylor.cs.junit.hooks.Ex1StandardTests test
#One class
mvn -Dtest=Ex1StandardTests test
#One method
mvn -Dtest=Ex1StandardTests#succeedingTest test
mvn -Dtest=Ex6_2Suite test
mvn -Dtest=Ex6_2Suite2 test
