## Integrating Spring Boot with Flyway with HSQLDB database
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

#### Solution to error - using maven flyway plugin so that we can use the command `mvn flyway:repair` like the 1. solution.
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

Using the command `mvn flyway:migrate` will trigger the migration and show and error.
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/c8d7757e.png)

Use the command `mvn flyway:repair` to fix the problem
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/de4a0c58.png)

We can safely run the application on intellij now
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/31/f6245320.png)

