
# Spring Microservices - Build Spring Microservices and Dockerize
1. Service Discovery
2. Config Server
3. Authentication: https://developer.okta.com/blog/2019/02/28/spring-microservices-docker
4. Additional link for microservices: https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud

Send get request `http://localhost:8081` after running school-service application
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/8cda0743.png)


Send get request `http://localhost:8080` after running school-ui application
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/ecdd8.png)

Build and run the discovery server
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/3946af54.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/8023a75e.png)

Configure both application school-service and school-ui to register with Eureka, then refresh the Eureka webpage and we'll see the 2 application there
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/d20b6b8d.png)

Add a configuration server, configure it, send request http://localhost:8888/school-ui.properties, see if it returns the correct configurations

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/e3762639.png)

Put school-ui behind an authorization layer (okta configuration - create a new Web Application in Okta’s dashboard) and Change School UI to Use Spring Cloud Config and OAuth 2.0

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/d1296ed9.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/02/4e95dd22.png)

Use Docker to Package Spring Apps
*technology that allows creating system images similar to Virtual Machines but that shares the same Kernel of the host operating system. This feature increases system performance and startup time. Also, Docker provided an ingenious built system that guarantees once an image is created; it won’t be changed, ever. In other words: no more “it works on my machine!”

mvn clean install
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/fa81e515.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/d5e4b1d4.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/9ff6af4.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/31e5b7a5.png)

docker-compose up -d

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/2481b844.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/8277afe9.png)

accessing http://localhost:8080/

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/fe2588b1.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/17bcee51.png)

command to stop docker `docker-compose stop`, important for when we wanna gracefully stop running the images 
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/594937bb.png)

Using spring profiles to Modify Microservices’ Configuration
*Create another school-ui-production.properties, put new okta application client id and secret, terminate and restart docker, check the container logs for school-ui and we'll see that the profiles have been set to 'production' as seen in the screenshots below

Get the container id of school-ui first using command 'docker ps'
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/64d7df6f.png)

Get the logs by using command 'docker logs [container id]'
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/9dc3ad2d.png)

Search for the word 'production'
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/6e2a9f90.png)

Another test to see if it really is using production profile is to deactivate the previous web application on okta, access http://localhost:8080/, it will still function as normal

Deactivated applications

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/50baaced.png)
Active applications

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/d43b1329.png)

Accessing http://localhost:8080/

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/507ed063.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/06/78d9f29b.png)


