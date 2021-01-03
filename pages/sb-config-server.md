# Spring Boot –Cloud Config Server

- Spring Cloud config is being used to configure the properties. 
- Properties can be stored in git which can be secured
- We can configure the git branch, profile from where to read the data.
- One of the finest example of cloud config is server port. It is not advisable to hardcode the path in boot app itself as we may need to run app on different ports in different lifecycles

**There are 2 parts –**

1. Cloud config server 
2. Any service which is using cloud server to read the data.

**Step1 – Lets set up git directory**

- Create a git repo where the configurations will be saved. 
- You can create files at root location of git (means separate repo for git) or can be inside any folder of git. 
- If you create files under folder of git repo, you need to define the search path property in cloud config server
- Default branch is master.
- Lets create two files under config-server-git folder in both master and develop branch
 
```
person-service-dev.properties
person-service-prod.properties
```
> Name of file is <<service name consuming config>>-<<active profile>>.properties.


### Step 2 Create Config Server Application

Create a spring boot project and add "Config Server" dependency 
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

# This is required if your files under folder. In my case it is not in root of git repo.
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
![sample](images/response.png)
