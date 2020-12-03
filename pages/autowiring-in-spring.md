
---
layout: default
---

## Spring Autowiring

### This article demonstrates the Spring Autowiring with example.

 - **Spring Autowiring is “byType” followed by “ byName”**
 - **"byType"** - Determine beans by Type variables. 
 - **"byName"** - Determine beans by variables name. 
 - **By default name of class with first letter small case will be the bean name**
- **We can  also define the name of Beans inside @Component("bean name") or @Bean("bean name")**

Lets say – we have a interface Car with two implementation of Maruti and Honda

    public interface Car {
    }

Car interface has two implementations 
**Honda implementation**-   will have default name as "honda"


    @Component
    public class Honda implements Car {
    }

**Maruti implementation**-   will have default name as "maruti"

    @Component
    public class Maruti implements Car {
    }

One Test Component class 

    @Component
    public class TestComponent {
    
    @Autowired @Qualifier("maruti") private Car car; //1
    @Autowired private Honda hondaCar; //2
    @Autowired private Maruti marutiCar; //3
    @Autowired private Car honda;//4
    @Autowired private Car maruti;//5
    
    //  test method
    public void print() {
        System.out.println("Car car=" + car);
        System.out.println("Honda hondaCar=" + hondaCar);
        System.out.println("Maruti MarutiCar=" + marutiCar);
        System.out.println("Car honda=" + honda);
        System.out.println("Car maruti=" + maruti);
        }
    }

**Output**

    Car car=com.example.Maruti@62577d6
    Honda hondaCar=com.example.Honda@49bd54f7
    Maruti MarutiCar=com.example.Maruti@62577d6
    Car honda=com.example.Honda@49bd54f7
    Car maruti=com.example.Maruti@62577d6

**Now in above –**

 1. If we don’t add @Qualifier on line 1 then it will throw error as there are 2 implementations.
 
2. By default name of class with fist letter small case will be the bean name.

3. For line number 2 and 3 there are no issues as the types are concrete  implementations like Honda or Maruti.
4. For line number 4 and 5 - **even though the type is car** , spring tries to use **“byname”** strategy because if finds 2 implementations as second strategy. With this it founds a beans with name “honda” and “maruti” which is same as the variable names used in line number 4 and  5
5. For example, if we define `@Autowired private Car maruti1;`  -this will throw error as "byType" there are 2 choices and with "byName" there is no bean with name with maruti1 so an error.
    





