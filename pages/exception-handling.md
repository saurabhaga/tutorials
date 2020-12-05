---
layout: default
---
 
 
 # Spring REST Exception Handling

If any exception is thrown in REST, we can control and can handle it at a 2 levels.

 1. At a **Controller** level  ( define methods in controller with `@ExceptionHandler` annotation)
 2. At a **Global** level (define class with `@RestControllerAdvice` annotation and define methods with `@ExceptionHandler` annotation)

**Controller Level**

Lets define Controller and two method which will throw `IllegalArgumentException` and `NullPointerException`

    @RequestMapping("/hello")
    public String hello(){
      throw new IllegalArgumentException("This is illegal exception");
    }
    
    
    @RequestMapping("/hi")
    public String hi(){
      throw new NullPointerException("This is null exception");
    }

Accessing above two end points  will show something like below on browser

    {
      "timestamp": 1495304728406,
      "status": 500,
      "error": "Internal Server Error",
      "exception": "java.lang.NullPointerException",
      "message": "This is null exception",
      "path": "/hi"
    }

To handle exceptions, we can define methods in controller annotated with `@ExceptionHandler` and passing exception class to handle

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorBean handleIllegalArgumentException(IllegalArgumentException e)
    {
        ErrorBean bean = new ErrorBean();
        bean.setMessage(e.getMessage());
        return  bean;
    }

Above method will handle only `IllegalArgumentException`. 
> We can have comma separated values.

With this we can achieve and display relevant information to the end user. Its worth noting that above method will set **HTTP** status as 200. So in case we want to set status as well we can use `@ResponseStatus` annotation

Now below handler will set response header status as 400 which is bad request

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ErrorBean handleIllegalArgumentException(IllegalArgumentException e)
    {
      ErrorBean bean = new ErrorBean();
      bean.setMessage(e.getMessage());
      return  bean;
    }

**Global Level**

Limitation with above  approach is that it is applied at controller level . To apply this at global level. we need to use `@RestControllerAdvice`  annotation on a separate class and add `@ExpceptionHandler` method in it

    package com.example.demo.spring.exception.handling;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    
    @RestControllerAdvice
    public class GlobalExceptionHandling {
       @ExceptionHandler(IllegalArgumentException.class)
       @ResponseStatus(code=HttpStatus.CONFLICT)
       public ErrorBean handleIllegalArgumentException(IllegalArgumentException e)
       {
          ErrorBean bean = new ErrorBean();
          bean.setMessage(e.getMessage());
          return  bean;
       }
    
      @ExceptionHandler(NullPointerException.class)
      @ResponseStatus(code=HttpStatus.BAD_REQUEST)
      public ErrorBean handleNullPointerException(NullPointerException e)
      {
        ErrorBean bean = new ErrorBean();
        bean.setMessage(e.getMessage());
        return  bean;
      }
    }

**Note-**

>a) If defined at both controller and global level, controller will take preference.

>b)We can use fields of `@RestControllerAdvice`  annotation to limit the class it needs to handle.

>c) Similarly we can use `basepackage` property to apply only at package level


**Spring Specific Exceptions**

We can handle our exceptions with above approach but how can we handle spring specific exceptions? It is hard to identify the Spring MVC specific exceptions to implement a common error response handling strategy for them. One way of overcoming this problem is to extend the `ResponseEntityExceptionHandler` class and override the methods we are interested in. Refer below example 

```
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
        List<String> supportedUnsupportedMediaType= new ArrayList<String>();
        supportedUnsupportedMediaType.add(supported);
        supportedUnsupportedMediaType.add(unsupported);
        return new ResponseEntity(supportedUnsupportedMediaType, headers, status);
    }
}

```


[Back to Home Page](../)

