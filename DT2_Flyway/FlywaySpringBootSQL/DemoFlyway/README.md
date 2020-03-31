# Integrating Spring Boot with Flyway with Persistent MySQL database
A simple application with sql and flywaydb as dependencies, and flyway-maven-plugin as plugin is built, the database corresponding to that application is also built.
Configurations the pom.xml file and application.properties to link to the database can be seen in the source code.

## Database migration
Screenshot of a successful migration after running the application
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/e7ac95dc.png)

Screenshot of the current database

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/1a5f0510.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/dd3d0279.png)

Command line - mvn flyway:info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/11a8b6f3.png)

Running the application after adding a new migration script (V3) and tweaking the previous migration script (V2) will result in error as shown below:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/63e855bf.png)

## Solution - using maven flyway plugin so that we can use the command 'mvn flyway:repair' like the 1. and 2. solution.
*This chain of screenshots will show the steps and commands used to fix the problem
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/6d02c86.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/1cb3ee75.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/1acf8b7f.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/4554f7ad.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/d286807a.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/142a81ee.png)

At last, all migrations are successful.

Screenshot of database

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/b6d19286.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/43bbb374.png)

Screenshot of logs after running the application after successful migrations.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/a900958.png)






