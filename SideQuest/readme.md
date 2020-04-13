# Side quest (Java -> ANT -> Maven)
## Checkpoint #1 
### Mission Statement:
***You should be able to compile and run your applications only using javac and java. 
Don't use spring boot or anything.***

***Objective*** : Write a simple command line program that accepts input as command line arguments and does some basic CRUD operations on a sqllite DB
    
***Condition*** : You cannot start off using Maven

### Outputs:
Note: The application created is not exactly command line, rather, it is menu driven, just thought it'll be slightly more user friendly...

**Compiling the application** - `javac PetApp.java` 

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/1b3e9170.png)

**Running the application** - `java -classpath ".;sqlite-jdbc-3.30.1.jar" net2.package2.PetApp`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/d69257b5.png)

**Performing some CRUD operations.**

Inserting:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/d4b077a8.png)

Reading: 

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/46fb88fe.png)

Updating:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/7aece195.png)

Deleting:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/37b782b5.png)

File path:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/688f85b3.png)

## Checkpoint #2 
### Mission Statement:
***Objective*** : Dust off the grand daddy of java build tools..Ant...and use that to compile your application into a jar and run it.

### Outputs:
#### Pre making of JAR file:
**Compiling:** `javac -sourcepath src -d build\classes src\example\PetApp.java`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/3fef82cd.png)

**Run:** `java -classpath ".;sqlite-jdbc-3.30.1.jar" example.PetApp`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/a4e1221a.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/e6d6963a.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/729ff49e.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/39e214b7.png)

File path:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/bedbf6b6.png)

Now we can see that everything works fine. Let's move on.

#### Making of Jar File

**Making Jar File:** `jar cfm build\jar\PetApp.jar myManifest -C build\classes .`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/09/e1a5c5dc.png)

File path: 

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/47393f72.png)

#### Running the Jar File
*since the application requires sqlite jdbc driver, make sure the sqlite-jdbc-3.30.1.jar file is in the same directory as the PetApp.jar, this is because we are going to specify in the build.xml to look for that file in that directory.

##### Method 1 (Using command `ant compile jar run`)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/2a5e5da1.png)

*Make sure the file path you're at has the build.xml file and the build.xml file specifies the classpath to point to the sqlite-jdbc-3.30.1.jar file.

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/c9dfd525.png)

##### Method 2 (Using a bat file / use the command `java -jar PetApp.jar`)
Create a bat file with `java -jar PetApp.jar` in the file. Make sure the file is in the same directory where the PetApp.jar is. You can use cmd as shown below or even right in the file explorer.

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/daa87fd8.png)

**Run the application** - either by using command line, typing the bat file name in it, in this case `run.bat` or double clicking on the actual file or just type in `java -jar PetApp.jar` in cmd.

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/2086a328.png)

File path so far:

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/12/ffe14dfd.png)

## Checkpoint #3
### Mission Statement:
Move on from Ant to maven

### Outputs:
#### Method 1
*this application was created using spring initialzr at https://start.spring.io/ the same java file used in the previous checkpoints was used in place of the main file of the project, with some modification to better suit the project of course. The sqlite jdbc driver was added as a dependency in the pom.xml file.

The application is executed by running the command `mvn spring-boot:run`
Maven took care of all the file path, where the compiled file should go, where to take the java file from, etc.

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/3e5c403d.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/672b450b.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/66d888c7.png)

#### Method 2 - Using command line only
**Project generation - use the scaffolding functionality of Maven to create a Java project** 

Command: `mvn archetype:generate -DgroupId=com.sidequest -DartifactId=petapp  -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/19709eb9.png)

**Compile**
Command: `mvn compile`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/889efee9.png)

**Create jar file**
Command: `mvn clean package` - can use this command to create jar file because it was stated in pom.xml

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/3dbbdb58.png)

Command: `mvn clean install` - frames a dependency tree based on the project configuration pom.xml on all the sub projects under the super pom.xml (the root POM) and downloads/compiles all the needed components in a directory called .m2 under the user's folder. These dependencies will have to be resolved for the project to be built without any errors, and mvn install is one utility that could download most of the dependencies.

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/ae4839cf.png)

**Execute**

Command: `mvn exec:java`

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/4d5a9edf.png)

![](https://devtraining2.blob.core.windows.net/devtraining2-images/2020/04/13/95d5160b.png)

*extra command (to run the jar file) - `java -cp target/petapp-1.0-SNAPSHOT.jar com.sidequest.App`

## Checkpoint #4: Final Boss battle 
### Mission Statement: Come and explain differences you observed as you progressed

#### Using JAVAC and JAVA only
- creates a .class file only
- have to manually place the sqlite jdbc jar file to the file path that the compile command would be executed at
- must specify the file name and class path of sqlite jdbc driver in command to execute application


#### Using ANT
- make use of build.xml to navigate the original .java file build the .class and .jar file specifying the location, specifies where the sqlite jdbc jar file is so that the application can access it
- build .class file based on the .java file specified, builds a .jar file based on the .class file built
- TL;DR: Automates the process of compiling project, building jar files, etc. Customize-able in terms of what jar files you want to run, which directory to take from or placed at.


#### Using MAVEN
- creates a .class file, and .jar file (if specified)
- makes use of pom.xml to get sqlite jdbc driver

**Difference between ANT and MAVEN**

Ref: 
- https://javarevisited.blogspot.com/2015/01/difference-between-maven-ant-jenkins-and-hudson.html
- https://examples.javacodegeeks.com/enterprise-java/maven/difference-ant-maven/

|                       | MAVEN                                    | ANT                                      |
|-----------------------|------------------------------------------|------------------------------------------|
| Project structure     | Maven has a project conventions and has several archetypes for predefined projects, so maven is easier because it knows where things are if you follow the project convention | ANT does not have defined project conventions, you can put things in any place, and later instruct ant for know where the things are |
| Execution method      | Maven is a declarative tool, you only have to declare your project object model (pom) and put your source code and resources in the correct folder, maven will take care of the rest for you. | Ant is a procedural tool, you have to tell it when, what and how it has to do all the things: compile, then copy, then package, then deploy, etc. |
| Lifecycle management  | Maven has a lifecycle management.<br>More info: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html | Ant does not have a lifecycle management, you have to declare some target/goals and define which of those goals run first, what run later and so on manually. |
| Dependency management | Maven has a mechanism to manage dependencies, you have a local repository that acts like a cache and you can define some remote repositories in order to download more dependencies, you only have to define all your dependencies needed and maven will download it for you. | Ant does not have any mechanism to manage dependencies, you have to manage it manually: donwload all dependencies, place the dependencies in a folder and later copy it to the packaged artifact |
| Naming convention     | Enforce a standard naming convention for artifacts defined using groupId, artifactId and version. | ANT allows you to name as you please.    |

