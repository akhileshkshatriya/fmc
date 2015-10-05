# FMC - Finding My Car
Its a role play game developed using MVC pattern for Command line based interface.


### Requirement
Create simple CLI based role play game in Java. 

### Constraints
In development, No external Framework or Libraries could be used except for testing and application build. 

### Issue
* Big while Loop - Many a times, people tend to write BIG while loops when it comes to CLI based role play games which is un-maintainable, hard to adapt change and impossible to test.

* FAT controller

### Solution
For the BIG loop problem described above one of the possible solution is MVC, which is really a nice pattern to segregate the responsibilities.
Several of the very popular JEE frameworks provide their implementation on top of MVC like Spring MVC and Struts. Of course it has been used in Swing as well.
Here, MVC is used for CLI application.

It has two parts
* Framework
* Application developed on top of the implemented framework.
	

### Framework Explained
![alt tag](https://github.com/akhileshkshatriya/fmc/blob/master/architecture.png)


###Framework Highlights
* MVC Design Pattern to segregate the Model, Views and Controller.
Fron Controller design pattern
*	Dependency Injection - since there was a contraint to not to use frameworks or libraries, Implemented dependency injection in itself to keep code SOLID compliant.
*	Creaeted several Annotation  - Intentionally,  kept Annotation names and functionality similar to Spring annotations, so that the developers from Spring background can easy understand it.   
	
### Application
*Its a very simple CLI based highly extensible program, Simply create new Controller and Views, Annotate it and add new levels.
*	JUnit and Mockito used to write unit test cases.
*	Maven multi module project.
*	Maven Assembly plugin used for packaging.
Prerequisite

	
### Instructions
* Program need Java 8
* To generate an executable Jar please run "mvn clean compile assembly:single" on service module.
* Execute "java -jar FMCRPGChallenge.jar" from command line.
	
