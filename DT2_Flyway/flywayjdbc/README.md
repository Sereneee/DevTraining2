## Flyway migration with H2 in-memory database and Java based migration
Ref: 
i. https://flywaydb.org/getstarted/firststeps/maven
ii. https://flywaydb.org/getstarted/java

#### SQL Migration
This is the first migration, command used to execute migration `mvn flyway:migrate`

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
As seen above, there is error. We can view the schema information by using the command `mvn flyway:info`
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/acac2ec6.png)
*It is noted that the 4th version state is 'Pending'

Use the command `mvn flyway:repair` to fix the error
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


Compile the project using `mvn compile` command
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/dde47a32.png)

Check the schema info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/a819a7b7.png)
*we can see that the new migration uses type JDBC

Execute the migration command
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/c3848871.png)

Check the schema info
![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/03/30/613c30b9.png)

