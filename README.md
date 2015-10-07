# FMC - Finding My Car
Finnding my car is a command line (CLI) based role play game whose story line is like, Players car has been stolen by a Car Mafia, Now player need to first find his Car and fight with Mafia to get it back.
 
### Features
Following are the features FMC currently provide.

* **Character Creation**
  *  When Game start, It prompts player to create a character by providing some basic information.
  *  This character will be saved for subsequent Games.
* **Exploration** 
  *  Going thru with different stages according to the story line and fight.
* **Experience**
  *  Per completion  of the Game users Experience will be increased (indifferent of loses or wins)
* **Save and Resume Game**
  *  While fighting, if Player opts to exit, It prompts user to Save the Game, which could be resumed later.
* **Statistics**
  *  While fighting, Player can keep an eye on Statistics like health of self and opponents.

### Player Instructions
* Make sure you have Java 8.
* Download FMCRPGChallenge.jar from fmc folder.
* Opern command prompt and execute "java -jar FMCRPGChallenge.jar". and enjoy...
* _Make sure you have write permission on the folder you are running the Game from, it generates some files._   


## Developers Area of Interest

### Technical Specifications 
* Java 8.
* Command line interface based (CLI).
* External library has been used only for testing and build.
* Maven has been used as build tool
* Maven assembly plugin has been used to package the app.
* Cobertura has been used for code coverage.
* Mockito has been used as mocking framework with JUnit.  


### Development Story, 
Doubts, Issues and Learnings
 
* **Big while Loop**
  *  In the very beginning of the development I got an Idea that the simplest approach to develop it, could be a BIG while loop, but it has several basic issues like, Maintainability, Extensibility, Testability and fast changes adaption.
* **MVC**
  *  Since I am from J2EE background, the first solution came to my mind was MVC. Which has been used in many of the JEE frameworks, its a very good solution where I can easily segregate my Modal, Views and Controllers. and increase  maintainability of my Game. But again...I had some doubts like..
  *  Is MVC a right choice for desktop application?
The Answer to this questions was "YES". As a proof, Swing internally uses MVC :) So I convinced myself and decided to use MVC with front controller.
* **FAT controller**
  *  The next issue was FAT controller, If I use MVC with Front Controller, My controller will grow as and when my Game will grow....and eventually it will be unmaintainable. Hmm...what should I do now?...No Problem Reflection is there to save me :)
* **SOLID Principles**
  *  So far so good, but...What about Inversion of Control principle? It is one of the most important principle, I cannot compromise on it...How would I make my high level modules independent of my low level modules? How my classes will be decoupled with each other? Hmm... thats a problem...Off course, can I use Factory patter for IoC? Yeah..but Dependency Injection is magic...I wish I could use Spring but I can't. Knock...Knock...Who is there? An Idea.....What Idea? Implement your own DI container? Really ?? "YES", it does not need to be as powerful as Spring DI but at least it can serve the purpose. Hmm....Let's do it...

###  Architecture
The Game is developed as Maven multi-module project, it has three modules
  1. Api - Holds all the interfaces and DTO's.
  2. Framework -
    1.  DI engine
    2.  MVC capability with front controller design pattern.
    3.  Supports Annotations based configuration, Annotations names and functionality intentionally kept as is of Spring annotations so that developers familiar with Spring can easily understand it.    
  3. service - It is a real game implementation utilizing the capability framework provide. 

### Framework Flow
![alt tag](https://github.com/akhileshkshatriya/fmc/blob/master/architecture.png)

  *  The Service module loads the ApplicationContext.
  *  On loading ApplicationContext, 
    *   read all the classes annotated with @Beans, @Controller, @Views and @Service from the component scan package specified in config.properties.
    *  creates Singleton objects of all the annotated classes.
    *  Injects dependencies
    *  create mappings for methods marked as @RequestMapping in controllers.
    *  create mappings for methods marked as @ViewMapping in Views.
    *  Injects RequestMappings and ViewMappings(ViewResolver) in FrontController Dispatcher.
  *  After loading ApplicationContext MainClass makes a request to FrontController with "home" as request mapping key.
  *  FrontController reads the @RequestMapping and delegate the request to the corresponding method of a controller.
  *  Controller interacts with Service Interfaces and do the desired operation and returns the control back to FrontController with the view name to be displayed as redirectTo parameter.
  *  Now FrontController reads ViewMapping from view resolver and pass on the control to desired View.       
  *  All the desired data is passed in Model across the layers.

	
### Developer Instructions
* To generate an executable Jar please run "mvn clean compile assembly:single" on service module.
* To generate code coverage report run "mvn clean cobertura:cobertura"

	
