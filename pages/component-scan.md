---
layout: default
---

# Component Scanning

### This article demonstrates the Spring Component Scanning with example.

a) By default Spring boot scans **@Component, @Configuration, @Service, @Repository** annotated classes defined under package or sub 
packages of main Class.

b) In order to scan other classes annotated with these annotation defined under different package, annotate the class with **@ComponentScan** annotation.

c) If you add @ComponentScan, it overrides the default and will scan only packages defined in @ComponentScan so to keep scanning default packages, add the package under which main class is defined also.

**Note: It is not necessary that you add @ComponentScan on main class. You can add it on @Configuration or @Component classes. The moment those classes loads, it sees that @ComponantScan is defined so it will load classes from those packages also.**

All above points are demonstrated in the code.

a) **_@ComponentScan(basePackages = {"com.outerpackage2","com.example"})_** on Main class loads the classes defined under `com.example, com.example.subpackage1 and com.outerpackage2.`

b) Above will load Component2.java which is again annotated with **_@ComponentScan("com.outerpackage3")_** so this 
will load all the classes under `com.outerpackage3` also.

c) Point b will load  Config3.java which is again annotated with **_@ComponentScan("com.outerpackage4")_** so this 
will load all the classes under `com.outerpackage4` also.

**Important Points**

-   **@SpringBootApplicaiton** - This is equivalent to adding all three @ComponentScan , @Configuration , @EnableAutoConfiguration

 
-   If we do not add package in  `@ComponentScan`  annotation, it by defaults loads classes under same or child package where it is added.

-   When I say all classes means classes annotated with stereo type including **@Configuration**

-   **Adding `@ComponentScan or @SpringBootApplication` on default package will scan all the stereo classes  in all jar files so avoid applying it on default package**

-   `@Configuration` is equivalent to Application context which means if we have multiple classes annotated with @Configuration, there are multiple ApplicationContext and they do not share the beans. (similar to xml files).
-   Bean defined in class annotated with @Configuration will not be available/autowired in bean defined under diff class with @Configuration Annotation


## Creating Your Own Auto-configuration
If you work in a company that develops shared libraries, or if you work on an open-source or commercial library, you might want to develop your own auto-configuration. Auto-configuration classes can be bundled in external jars and still be picked-up by Spring Boot.


Under the hood, auto-configuration is implemented with standard @Configuration classes. Additional @Conditional annotations are used to constrain when the auto-configuration should apply. Usually, auto-configuration classes use @ConditionalOnClass and @ConditionalOnMissingBean annotations. This ensures that auto-configuration applies only when relevant classes are found and when you have not declared your own @Configuration.

**Locating Auto-configuration Candidates**
Spring Boot checks for the presence of a `resources/META-INF/spring.factories` file within your published jar. The file should list your configuration classes under the EnableAutoConfiguration key, as shown in the following example:

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.test.CustomAutoconfiguration
```


**Details**

a) Create spring.factories file and define `EnableAutoConfiguration` field.

b) Create a class `CustomAutoconfiguration` under `com.test` package. Define one bean in it which is there in package `com.test2`

**Note:** 
> If we define any class as EnableAutoConfiguration, that class will be loaded automatically no matter which package it is in.
> Note: This feature applies to any class annotated with stereo type annotation. In this example TestBean class is annotated with @Component 


## Disable Auto Configuration

Another possibility is that autoconfiguraion is enabled in jar file but in a given service we dont need it. We can disable it via annotaion or yml file 

**yml file example**

```
spring:
  autoconfigure:
    exclude:
    - com.test.CustomAutoconfiguration
```

**Annotation Example**

```
@SpringBootApplication(
  exclude = { com.test.CustomAutoconfiguration.class })
```


[Download source code](https://github.com/saurabhaga/tutorials/tree/main/code/ComponentScan) 
[Back to Home Page](../)
