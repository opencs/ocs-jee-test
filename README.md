# ocs-jee-test
OpenCS's basic JEE skill test

## Description

TODO

As a test of skills, this project contains some errors that are expected to be found
and fixed during the test.

## Development environment

This project was written using [**Eclipse IDE for Enterprise Java Developers**](https://www.eclipse.org), a Java 8 JDK and [**TomEE Plume 7.1.x**](http://tomee.apache.org) as
the application server.

The workspace must be created by importing all projects inside the repository.

## How to build

Just build the project **ocs-jee-test** using **Apache Maven**. This should compile all projects and generate the final WAR file that should be deployed on the application
server.

## Implemented System descritpion

This is a very simple JEE Web application that uses JPA, EJB and JSF.

It uses the **Apache Derby** as its database. For the sake of simplicity, all tables
are created by JPA using the metadata inside the the entities.

## List of projects

 Project | Description 
 ------- | -----------
 ocs-jee-test | The parent project.
 ocs-jee-test-db-core | The implementation of the JPA entities with unit-tests.
 ocs-jee-test-db | The JPA unit implementation (no classes).
 ocs-jee-test-core | The business logic (EJBs)
 ocs-jee-test-web | The web application (war).
 

