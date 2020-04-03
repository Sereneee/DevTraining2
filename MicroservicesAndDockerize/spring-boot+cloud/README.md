# Spring Boot + Cloud
Spring Cloud is a framework for building robust cloud applications. The framework facilitates the development of applications by providing solutions to many of the common problems faced when moving to a distributed environment.

Applications that run with microservices architecture aim to simplify development, deployment, and maintenance. The decomposed nature of the application allows developers to focus on one problem at a time. Improvements can be introduced without impacting other parts of a system.

## Some screenshots:
### Eureka Server
A way for all of our servers to be able to find each other. We will solve this problem by setting the Eureka discovery server up. Since our applications could be running on any ip/port combination we need a central address registry that can serve as an application address lookup.

When a new server is provisioned it will communicate with the discovery server and register its address so that others can communicate with it. This way other applications can consume this information as they make requests.

Run discovery-service application - accessing the eureka server
Sending get request http://localhost:8761/
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/faec275.png)


Run api-gateway application, then run the car-service application
* uses okta authentication - OAuth 2.0

Sending http://localhost:8080/cool-cars
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/def94a9b.png)

Making use of Hystrix - allows any fallbacks or feign logic to work
ie: if service isn't up, at least smtg gets return, even if it is an empty list
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/df179688.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/1b278cea.png)


Zuul in action
*Zuul is a gateway service that provides dynamic routing, monitoring, resiliency, and more

http://localhost:8080/home

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/758fae74.png)

http://localhost:8080/cars - we can see all the cars endpoint
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/01/e364be7a.png)