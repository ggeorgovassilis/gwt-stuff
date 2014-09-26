=== gwt-maven-project-skeleton ===

# Overview

A basic GWT and Maven 3 project which can serve as a base for quickly bootstrapping enterprise applications. 

Features:

* Full Maven 3 lifecycle 
* GWT 2.6
* Spring 3.2
* Hello world client application
* Purposefully committed Eclipse project settings to demonstrate useful deployment assembly

Roadmap:

* Integration tests with embedded servlet container
* Fully automated frontend tests
* A proper frontend application
* Expand on the documentation

# Methodology and tools

The instructions refer to GWT 2.6.1

### What do I do?

```mvn clean install```

### How do I get started with super dev mode?

There's an initial setup. You do this only once for every project.

1. Deploy the WAR to a servlet container. We'll assume it runs under http://localhost:8080/gwt-maven-project-skeleton/
2. run ```mvn gwt:run-codeserver```
3. Visit http://localhost:8888/
4. Bookmark the two buttons following the instructions
5. Visit http://localhost:8080/gwt-maven-project-skeleton/
6. Click on Dev Mode on
7. Bookmark the Compile button

That's it. Now, the next day when you start your computer you only have to do these steps:

1. Start the servlet container
2. run ```mvn gwt:run-codeserver```
3. http://localhost:8080/gwt-maven-project-skeleton/
4. Any time you make a change to client code, just click on the bookmarked Compile button and the browser will reload the application with changes