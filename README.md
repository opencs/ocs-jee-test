# ocs-jee-test

## Description

This repository contains the **OpenCS's Basic JEE Skill Test**. It is a very simple
web application that uses a database, a set of services and a user interface
implemented using JSF.

The actual statement of the test will be delivery to the candidates during the 
application of the test.

## Development environment

This project was written using [**Eclipse IDE for Enterprise Java Developers**](https://www.eclipse.org). It requeres the following extra componets:

* [**Apache Derby 10.14.2.0**](https://db.apache.org/derby/)
* [**Apache Maven 3.6.x or later**](https://maven.apache.org/)
* [**OpenJDK 8 JDK**](https://adoptopenjdk.net/)
* [**TomEE Plume 7.1.x**](http://tomee.apache.org)

### Database installation

To install **Apache Derby** inside **Apache TomEE**, just copy the jar file **derby.jar**
into **TomEE** lib directory.

### Eclipse Workspace

In order to create the development workspace, just execute the following steps:

1. Import all projects of the repository into a new workspace;
1. Create a new **TomEE** server for testing;
1. Set the WAR file as a deployable artifact;

### How to build

The project can be build by running **mvn install** inside the directory
**&lt;repo&gt;/ocs-jee-test**. This will build all modules and create the
**war** package that will be found inside **&lt;repo&gt;/ocs-jee-test-web/target**.

## Implemented System descritpion

This is a very simple JEE 6 Web application that uses only JPA, EJB and JSF. It has
only 5 layers:

1. User interface (JSF xhtml files): The actual user interface;
1. User interface Implementation (JSF Managed Beans): UI logic only;
1. Services (EJB): Actual business logic;
1. Database Repositories (EJB): Database operations;
1. Database Abstraction (JPA): Database entities;

The relational database used by this test is **Apache Derby**. All tables
will be created by the JPA implementation on the first run.

## List of projects

 Project | Description 
 ------- | -----------
 ocs-jee-test | The parent project.
 ocs-jee-test-db-core | The implementation of the JPA entities with unit-tests.
 ocs-jee-test-db | The JPA unit implementation (no classes).
 ocs-jee-test-core | The business logic (EJBs)
 ocs-jee-test-web | The web application (war).

## General Test Instructions

* As a test of skills, this project do contains some errors that are expected to be
  found and fixed during the test;
* If you find an error that cannot be fixed during the expected timeframe, write it
  down and justify your decision;
* Use the standard Java naming conventions and coding styles;
* All the documentation and names must be in English;
* All public and protected classes and methods must be properly documented using
  **JavaDoc** and/or [Doxygen](http://www.doxygen.nl/) syntax;
* Do not add other libraries to the project unless strictly required. If you decide
  to use one anyway, justify its use and take extreme care with the license of the
  library. The license of the end result must be a 3-Clause BSD license as the
  original project;

## License

This project is licensed under 3-Clause BSD license.
