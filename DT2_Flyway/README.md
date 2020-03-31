# Flyway 
1. Learn how to configure Flyway and perform migration to your database
2. Simulate a scenario where you have migration up to V3, then you go to edit your V2 script and re-run the migration. See what happen and figure out how to resolve if issue happens.


## 1. Flyway migration with H2 in-memory database and Java based migration - checkout 'flywayjdbc' directory for source code

## 2. Integrating Spring Boot with Flyway with HSQLDB database - check out 'flyway' directory for source code

## 3. Integrating Spring Boot with Flyway with Persistent MySQL database - Check out the directory 'FlywaySpringBootSQL/DemoFlyway'

Fun fact about `mvn flyway:repair`: I realized that changes in previous migration script does not change the existing data in the database. Eg: Lets say v2 is about inserting data to employee table, it is successfully migrated. Then we have v3 which is about inserting more data to employee table, we then change some data in v2, lets say a name was changed from 'aaa' to 'bbb'. Although it is being repaired and does not show any error when running, the data in database still remains as 'aaa'.