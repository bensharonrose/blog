Blog
====

- As an admin user I want to be able to see all user information with their related posts and comments so that I can track in one place what users are posting to the site.

---

## Background information

For this assignment we are pretending we are working on an existing Blog application. This application has users that can post content online. This Blog application already has some APIs created that we can re-use to complete the requirement.
The APIs are:
- GET - /users: Lists all users in the system with their personal information. The API can be accessed on: https://jsonplaceholder.typicode.com/users
- GET - /posts: Lists all posts in the system. The API can be accessed on: https://jsonplaceholder.typicode.com/posts
- GET - /posts: Lists all comments on the posts. The API can be accessed on: https://jsonplaceholder.typicode.com/comments
---

## Pre-requisites

To install Toy Robot Simulator project below are the pre-requisites

1. Openjdk11
2. Apache Maven
3. Git bash
4. Node v12.6.3
5. Npm 6.14.4

---

## Important step prior to running the application

We are using maven frontend plugin(refer pom.xml) to run react within spring boot app from maven and hence, please re-point the installDirectory variable to the root of the nodejs folder(ex. in this case my actual nodejs directory is D:\node\node.exe) and so, I have mentioned the installDirectory value as D:\.

Alternatively, you can uncomment "install node and npm" & "npm install" executions under maven frontend plugin and comment out installDirectory variable. Then using "mvn clean package" command compile the project and ./run.sh to run the project.

```
<groupId>com.github.eirslett</groupId>
<artifactId>frontend-maven-plugin</artifactId>
<version>${frontend-maven-plugin.version}</version>
<configuration>
  <workingDirectory>app</workingDirectory>
  <!-- Place the nodejs executable in this directory -->
  <installDirectory>D:\</installDirectory>
</configuration>
<executions>
```

## How to run this project

Go to the project root folder and run the below steps to achieve its corresponding end result.

### 1. Run the unit test cases

    ./test.sh

### 2. Run the project

    ./run.sh

---

## Demo run project screenshot

![alternativetext](app/src/images/BlogDemoAppScreenshot.JPG)