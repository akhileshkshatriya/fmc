# FMC - Finding My Car
Finnding my car is a command line (CLI) based role play game whose story line is like, Players car has been stolen by a Car Mafia, Now player need to first find his Car and fight with Mafia to get it back.
 
### Features
Following are the features FMC currently provide.

* **Character Creation**
  *  When Game start, It prompts player to create a character by providing some basic information.
  *  This character will be saved and in the subsequent play, user will not be asked to create character again.
* **Exploration** 
  *  Going thru with different stages according to the story line and fight.
* **Experience **
  *  Per completion  of the Game users Experience will be increased (indifferent of loses or wins)
* **Save and Resume Game**
  *  While on fighting if user opts to exit, the games prompts for Save  	 
* **Statistics**
  *  While on fighting user can keep an eye on Statistics like health of himself and opponents.

### Player Instructions
* Make sure you have Java 8.
* Download FMCRPGChallenge.jar from fmc folder.
* Opern command prompt and execute "java -jar FMCRPGChallenge.jar". and enjoy...
* _Make sure you have write permission on the folder you are running the Game, it generates some files._   


## Developers Area of Interest

### Technical Specifications 
* Java 8.
* Command line interface based (CLI).
* External library used only for testing and build.
* Maven has been used for as build tool
* Maven assembly plugin has been used to package the app.
* Cobertura has been used for code coverage.
* Mockito has been used as mocking framework with JUnit.  


### Development Story, 
Doubts, Issues and Learnings
 
* **Big while Loop**
  *  In the very beginning of the development I got an Idea that the simplest approach to develop this CLI based Game could be a BIG while loop, but wait a minute... I have several question.... would I be able to maintain it? Would I be able to adapt to changes fast? Would I be able to test it? The answer was NO.
* **MVC**
  *  Since I am from J2EE background, the first solution came to my mind was MVC. Which is a very good solution where I can easily segregate my Modal, Views and Controllers. and increase the maintainability of my Game. But again...
  *  Is MVC a right choice for desktop application?
  *  Do people use MVC for Desktop applications? 
The Answer to these questions was "YES". As a proof, Swing internally uses MVC :) So I convinced myself to use MVC and made a decision to use MVC with front controller.
* **FAT controller**
  *  The next issue was FAT controller problem, My controller will grow as and when my Game will grow....Hmm...what should I do now?...No Problem Reflection is there to save me :)
* **SOLID Principles**
  *  So far so good, but...What about Inversion of Control principle? It is one of the most important principle, I cannot compromise on it...How would I make my high level modules independent of my low level modules? Hmm... thats a problem...Off course, I can use Factory patter for IoC but Dependency Injection is magic...I wish I could use Spring but I can't. Idea..... Can I implement my own little Dependency Injection engine? ya ya... not as powerful as Spring DI but at least can serve the purpose. Let's do it...

###  Architecture
As stated above, The Game is developed as Maven multi-module project, it has three parts
1. Api - Holds all the interfaces and DTO's.
2. Framework -
  1.  DI engine
  2.  MVC capability with front controller design pattern.
  3.  Supports Annotations based configuration, Annotations names and functionality intentionally kept as is of Spring annotations so that developers familiar with Spring can easily understand it.    
3. service - It is a real game implementation utilizing the capability framework provide. 

### Framework Flow
![alt tag](https://github.com/akhileshkshatriya/fmc/blob/master/architecture.png)
From the MainClass loading the ApplicationContext, which eventually read all the classes annotated with @Beans, @Controller, @Views and @Service from the component scan package specified in config.properties.
On loading ApplicationContext first it creates Singleton objects of all the annotated classes, Injects dependencies for them and create mappings for methods marked as @RequestMapping and @ViewMapping. and Injects in FrontController and Dispatcher.    
After loading ApplicationContext first it makes a request to "home" which passes lands to FrontController. FrontController reads the @RequestMapping and delegate the request to the corresponding method of a controller.
Model holds the required data. 
Controller interacts with Service Interfaces and do the desired operation and returns the control back to FrontController with the view name to be displayed.
Now FrontController reads ViewMapping from view resolver and pass on the control to desired View.       
  

	
### Developer Instructions
* To generate an executable Jar please run "mvn clean compile assembly:single" on service module.
* To generate code coverage report run "mvn clean cobertura:cobertura"

	
