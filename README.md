# Docker in Practice
>Java-User-Group Saxony 31.03.2017

## Introduction

This repository contains code-examples how to use [docker](www.docker.com) in combination with jenkins, maven and selenium.

## Run a Jenkins-Maven-Container

You need the ci-system jenkins with a full stack of plugins quickly?
So you can use docker to automate the difficult installation-process of a complex ci-system.
You only need these two commands to start your own jenkins:

```
docker build . -t myjenkins
docker run --name jenkins -p 8080:8080 myjenkins
```

## Details

This docker image builds upon the vanilla Jenkins image (https://hub.docker.com/_/jenkins/)

As well as the vanilla jenkins images, Maven3 is pre-installed.

It also ensures that the following plugins (along with dependencies) are pre-installed:

* Git v2.4.1 (https://wiki.jenkins-ci.org/display/JENKINS/Git+Plugin)
* Build pipeline v1.4.9 (https://wiki.jenkins-ci.org/display/JENKINS/Build+Pipeline+Plugin)
* Cloudbees Docker Build and Publish v1.1 (https://wiki.jenkins-ci.org/display/JENKINS/CloudBees+Docker+Build+and+Publish+plugin)

Finally it pre-configures the Jenkins instance with knowledge of the Maven installation directory and Docker executable.

## Web-Driver-Tests with Selenium?

If you want to test your web-application professionally you should use [selenium](http://docs.seleniumhq.org/docs/07_selenium_grid.jsp ).

Selenium automates browser-testing, this is important to save time in development.
The only problem with selenium is the time you need to install and configure the service.

Docker solves this problem too. 
Install easily a stack of services: your jenkins, selenium-hub, two nodes and a mysql db with the given docker-compose.yml file and these commands:

```
docker-compose up
```
then configure a maven-jenkins-job on `localhost:8080` with:
```
mvn package -t 4
```
As target you can use this repository, because there are simple selenium test-cases given (title check from google).

