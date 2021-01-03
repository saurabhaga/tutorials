---
layout: default
---

# Cloud Config Server with Spring Boot

### This article will explain the need and implemenation of cloud config server in microservices with Spring Boot.

We all know that no one would like to do the hardcoding of configurations like database details, port etc so Cloud config server solves this problem where the configurtions can be managed 
seperately outside code base in git repository (other options are also available like using database).  At first, it looks very complicated but Spring took away all the complexiy and we just need to add couple of dependencies along with some annotations and we are all set with our cloud config server.

## Key points to understand.
- Spring Cloud config server is being used to configure the properties. 
- Properties can be stored in git which can be secured.
- We can configure the git branch, profile from where to read the data.
- One of the finest example of cloud config is server port. It is not advisable to hardcode the path in boot app itself as we may need to run app on different ports in different lifecycles

**There are 2 parts –**

1. Cloud config server 
2. Any service which is using cloud server to read its configuration at runtime.


# Cloud Config Server using Git Repository

**Step1 – Lets set up git directory**

- Create a git repo where the configurations will be saved. 
- You can create files at root location of git (means separate repo for git) or can be inside any folder of git. 
- If you create files under folder of git repo, you need to define the search path property in cloud config server.
- Default branch is master.

In this example. lets assume there is a Git repo with name "spring-cloud" and there is a folder inside it with name `config-server-git`. Lets create two files under config-server-git folder in both master and develop branch
 
```
person-service-dev.properties
person-service-prod.properties
```
> Name of file will be in the format of  {service name of consuming service}-{active profile}.properties.

### Step 2 Create Config Server Application

Create a spring boot project and add "Config Server" dependency by adding `implementation 'org.springframework.cloud:spring-cloud-config-server'` under dependencies section of build.gradle file.

Configure below in `application.properties` 

```
server.port= 8082
spring.application.name=Cloud Config Server

#git repo .This is the url we used to clone 
spring.cloud.config.server.git.uri= https://github.com/saurabhaga/spring-cloud.git
# if any error it will throw on server start else on first request
spring.cloud.config.server.git.clone-on-start=true

# git repo credentials if git repo is private 
spring.cloud.config.server.git.username= saurabhaga
spring.cloud.config.server.git.password= *********

# skil ssl verification
spring.cloud.config.server.git.skip-ssl-validation=true

# This is required if your files under folder. In this example, it is not at root of git repo so add the folder name in search path.
spring.cloud.config.server.git.search-paths=config-server-git
```

- Open main class and add `@EnableConfigServer` annotation.
- Run the main program. Application will run on 8082 as we configured it in application.properties
### Verification
- We can check that server is up and can see the files using 
- http://localhost:8081/person-service/dev/master
-  http://localhost:8081/[[name of spring app whose files are]]/[[active priofile]]/[[optional branch]]
- If branch is not provided, default is master
- Any changes done in git is reflected in real time on config server

Sample Output 
![sample](images/cloud-config-1.png)


[Download source code](https://github.com/saurabhaga/tutorials/tree/main/code/Autowiring) |[Back to Home Page](../)
