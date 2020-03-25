## What is TDD?
* it stands for Test Driven Development
* test cases are created before code is written
* purpose: make the code clearer, simple and bug-free
* simple concept:  
    * to write and correct the failed tests before writing new code (before development)
    * instructs developers to write new code only if an automated test has failed
    * this avoids duplication of code as we write a small amount of code at a time in order to pass tests
* is a process of developing and running automated test before actual development of the application. Hence, TDD sometimes also called as Test First Development.
*  ensures that your system actually meets requirements defined for it. It helps to build your confidence about your system.

## How to perform TDD test?
1. Write a test
2. Run all tests and see if any test fails.
3. Write some code.
3. Run tests. Change the code to make it right i.e. Refactor.
4. Repeat process.

## What TDD is NOT
1. TDD is neither about "Testing" nor about "Design".
2. TDD does not mean "write some of the tests, then build a system that passes the tests.
3. TDD does not mean "do lots of Testing."

## TDD vs. Traditional Testing
TDD approach is primarily a specification technique. It ensures that your source code is thoroughly tested at confirmatory level.

| TDD Testing                              | Traditional Testing                      |
|------------------------------------------|------------------------------------------|
| more focused on production code that verifies whether testing will work properly | more focused on test case design, whether the test will show the proper/improper execution of the application in order to fulfill requirements |
| achieve 100% coverage test               | does not achieve 100% coverage test      |

## Levels of TDD 
*there are 2 namely Acceptance TDD (ATDD) and Developer TDD
#### 1. Acceptance TDD (ATDD): 
* With ATDD you write a single acceptance test. 
* This test fulfills the requirement of the specification or satisfies the behavior of the system. 
* After that, write just enough production/functionality code to fulfill that acceptance test. 
* Focuses on the overall behavior of the system. 
* It is aka Behavioral Driven Development (BDD).

#### 2. Developer TDD: 
* With Developer TDD you write single developer test i.e. unit test and then just enough production code to fulfill that test. 
* Focuses on every small functionality of the system. 
* Developer TDD is simply called as TDD.

Main goal of ATDD and TDD is to specify detailed, executable requirements for your solution on a just in time (JIT) basis. JIT means taking only those requirements in consideration that are needed in the system. So increase efficiency.

## TDD vs AMDD (Agile Model Driven Development)
TDD is very good at detailed specification and validation. 
It fails at thinking through bigger issues such as overall design, use of the system, or UI. 
AMDD addresses the Agile scaling issues that TDD does not. But, both support evolutionary development.

| TDD                                      | AMDD                                     |
|------------------------------------------|------------------------------------------|
| shortens the programming feedback loop   | shortens modeling feedback loop          |
| detailed specification                   | works for bigger issues                  |
| promotes the development of high-quality code | promotes high-quality communication with stakeholders and developers |
| speaks to programmer                     | speaks to to business analyst, stakeholders, and data professionals |
| non-visually oriented                    | visually oriented                        |
| has limited scope to software works      | has a broad scope including stakeholders. It involves working towards a common understanding |


## Advantages of TDD
* Early bug notification.
    Developers test their code but in the database world, this often consists of manual tests or one-off scripts. Using TDD you build up, over time, a suite of automated tests that you and any other developer can rerun at will.
* Better Designed, cleaner and more extensible code.
    * It helps to understand how the code will be used and how it interacts with other modules.
    * It results in better design decision and more maintainable code.
    * TDD allows writing smaller code having single responsibility rather than monolithic procedures with multiple responsibilities. This makes the code simpler to understand.
    * TDD also forces to write only production code to pass tests based on user requirements.
* Confidence to Refactor
    * If you refactor code, there can be possibilities of breaks in the code. So having a set of automated tests you can fix those breaks before release. Proper warning will be given if breaks found when automated tests are used.
    * Using TDD, should results in faster, more extensible code with fewer bugs that can be updated with minimal risks.
* Good for teamwork
    * In the absence of any team member, other team members can easily pick up and work on the code. 
    * It also aids knowledge sharing, thereby making the team more effective overall.
* Good for Developers
    * Though developers have to spend more time in writing TDD test cases, it takes a lot less time for debugging and developing new features. You will write cleaner, less complicated code.
    
    
Reference: https://www.guru99.com/test-driven-development.html