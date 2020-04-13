# Dev Training II Curicculum 

## Part 1
1.	Spring Bean & @Autowired - read up and try to understand about it
    a.	Link: https://www.baeldung.com/spring-bean-scopes
2.	Flyway 
    a.	Learn how to configure Flyway and perform migration to your database 
    b.	Simulate a scenario where you have migration up to V3, then you go to edit your V2 script and re-run the migration. See what happen and figure out how to resolve if issue happens.
3.	Spring Microservices - Build Spring Microservices and Dockerize
    a.	Service Discovery 
    b.	Config Server
    c.	Authentication: https://developer.okta.com/blog/2019/02/28/spring-microservices-docker
    d.	Additional link for microservices: https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud

## Part 2: Extra topics to explore after completing the above topics 
1.	Caching (using different ways: EhCache, Redis, Apache Geode) 
2.	Event-driven Microservices with Kafka (using Spring Cloud Stream) 
3.	Streaming Data using Spring Cloud Stream with KStream (Kafka Stream) 
4.	Spring Cloud Data Flow 
5.	Reactive Programming using Spring WebFlux 
6.	Spring Boot with RSocket 
7.	Spring Boot with Kotlin 
8.	Spring Security - create an OAuth2 service to secure API calls 
9.	Spring Cloud Circuit Breaker (using different ways: resilience4j, Hystrix) 
10.	Apply Feature Flag to Spring Boot (e.g. ff4j or unleash)

## Side quest
1. Checkpoint #1 : You should be able to compile and run your applications only using javac and java. 
Don't use spring boot or anything.

    Objective : Write a simple command line program that accepts input as command line arguments and does some basic CRUD operations on a sqllite DB
    
    Condition : You cannot start off using Maven

2. Checkpoint #2 : Dust off the grand daddy of java build tools..Ant...and use that to compile your application into a jar and run it.

3. Checkpoint #3: Move on from Ant to maven

4. Final Boss battle : Come and explain to me differences you observed as you progressed

