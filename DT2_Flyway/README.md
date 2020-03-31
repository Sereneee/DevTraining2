# Flyway 
1. Learn how to configure Flyway and perform migration to your database
2. Simulate a scenario where you have migration up to V3, then you go to edit your V2 script and re-run the migration. See what happen and figure out how to resolve if issue happens.


## 1. Flyway migration with H2 in-memory database and Java based migration - checkout 'flywayjdbc' directory for source code
Ref: 
i. https://flywaydb.org/getstarted/firststeps/maven
ii. https://flywaydb.org/getstarted/java

#### SQL Migration
This is the first migration, command used to execute migration 'mvn flyway:migrate'

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/9ff48ec6.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/c27a3315.png)

Second migration 

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/ef5d7761.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/d3147297.png)

Third migration

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/aab49281.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/e3c88106.png)

Fourth migration 

*Added the column 'AGE' to the PERSON table in V1__Create_person_table.sql
*Added V4__Add_more_people.sql 
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/f1e4e63e.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/ffea45da.png)
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/26/99344b2f.png)

#### Solution to error
As seen above, there is error. We can view the schema information by using the command 'mvn flyway:info'
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/acac2ec6.png)
*It is noted that the 4th version state is 'Pending'

Use the command 'mvn flyway:repair' to fix the error
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/45601aac.png)
*the line 'Repairing Schema History table for version 1 (Description: Create person table, Type: SQL, Checksum: -204023461)' shows that the schema is being repaired

Check the schema information to see if all database migration is a success
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/8629a140.png)
*Looks like the 4th database migration is still pending, so we will execute the migrate command again

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/841e4f2a.png)
*the lines 'Current version of schema "PUBLIC": 3
[INFO] Migrating schema "PUBLIC" to version 4 - Add more people
[INFO] Successfully applied 1 migration to schema "PUBLIC" (execution time 00:00.065s)' tells us that the 4th migration is successful.

Checking the schema info to ensure all migration was successful.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/47e9d625.png)

#### Creating a java based migration
Ref: https://flywaydb.org/getstarted/java
Things to do beforehand
* adding the flyway-core dependency to our pom.xml
* configuring the Java compiler for Java 8
* configuring Flyway to scan the Java classpath for migrations

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/94d6eab9.png)

Then,
* Create the migration directory src/main/java/db/migration.
* Followed by a first migration called src/main/java/db/migration/V5__Anonymize.java

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/fdfa74c2.png)


Compile the project using 'mvn compile' command
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/dde47a32.png)

Check the schema info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/a819a7b7.png)
*we can see that the new migration uses type JDBC

Execute the migration command
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/c3848871.png)

Check the schema info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/613c30b9.png)


## 2. Integrating Spring Boot with Flyway with HSQLDB database - check out 'flyway' directory for source code
Ref: https://dzone.com/articles/database-versioning-with-flyway-and-java
This method requires to add the following to the pom file
```xml
<dependency>
   <groupId>org.flywaydb</groupId>
   <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
   <groupId>org.hsqldb</groupId>
   <artifactId>hsqldb</artifactId>
   <version>2.3.3</version>
</dependency>
```

And the following to the application.properties file
```powershell
# defining location for HSQLDB's data
spring.datasource.url=jdbc:hsqldb:file:data/app
# disabling Hibernate's auto schema generation
spring.jpa.hibernate.ddl-auto=none
```

#### Database migration
This is a RESTful application that uses flyway to create the database and update its database
So it has the typical migration script in the src/main/resources/db/migration/ folder, ie:
V1__customers.sql
```sql
create table customer (
  id int identity primary key,
  name varchar(255) not null,
  contact_name varchar (255) not null,
  email varchar (255) not null,
  phone varchar (255) not null
);
insert into customer (name, contact_name, email, phone) values
  ('Coca Cola', 'John Doe', 'john.doe@cocacola.com', '202-555-0143'),
  ('Dell', 'Bob Frapples', 'bob.frapples@dell.com', '202-555-0180'),
  ('Apple', 'Barb Ackue', 'barb.ackue@apple.com', '202-555-0128'),
  ('Google', 'Sue Vaneer', 'sue.vaneer@google.com', '202-555-0174'),
  ('FedEx', 'Robin Banks', 'robin.banks@fedex.com', '202-555-0146'),
  ('Salesforce', 'Zack Lee', 'zack.lee@salesforce.com', '202-555-0122');
```

Results in postman
sending request: http://localhost:8080/customers/ gets all the database records that was created within the application itself
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/25/641730b1.png)

sending request: http://localhost:8080/customers/1/contacts/ - gets 
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/25/12d8edfc.png)


Running the application automatically triggers the flyway migration, the following screenshot is an example of a successful migration.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/d86b92af.png)

The following is an example of screenshot after running the application where the migration script has been altered.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/5569b58b.png)

#### Solution to error - using maven flyway plugin so that we can use the command 'mvn flyway:repair' like the 1. solution.
Add the following to pom.xml
```xml
<plugin>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-maven-plugin</artifactId>
	<version>6.3.2</version>
	<configuration>
		<url>jdbc:hsqldb:file:data/app</url>
		<user>sa</user>
	</configuration>
</plugin>
```

Using the command 'mvn flyway:migrate' will trigger the migration and show and error.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/c8d7757e.png)

Use the command 'mvn flyway:repair' to fix the problem
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/de4a0c58.png)

We can safely run the application on intellij now
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/f6245320.png)

## 3. Integrating Spring Boot with Flyway with Persistent MySQL database - Check out the directory 'FlywaySpringBootSQL/DemoFlyway'
A simple application with sql and flywaydb as dependencies, and flyway-maven-plugin as plugin is built, the database corresponding to that application is also built.
Configurations the pom.xml file and application.properties to link to the database can be seen in the source code.

#### Database migration
Screenshot of a successful migration after running the application
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/e7ac95dc.png)

Screenshot of the current database

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/1a5f0510.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/dd3d0279.png)

Command line - mvn flyway:info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/11a8b6f3.png)

Running the application after adding a new migration script (V3) and tweaking the previous migration script (V2) will result in error as shown below:
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/63e855bf.png)

#### Solution - using maven flyway plugin so that we can use the command 'mvn flyway:repair' like the 1. and 2. solution.
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


Fun fact: I realized that changes in previous migration script does not change the existing data in the database. Eg: Lets say v2 is about inserting data to employee table, it is successfully migrated. Then we have v3 which is about inserting more data to employee table, we then change some data in v2, lets say a name was changed from 'aaa' to 'bbb'. Although it is being repaired and does not show any error when running, the data in database still remains as 'aaa'.



