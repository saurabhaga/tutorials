---
layout: default
---

# Java Memory 

### This article is about the Java memory arguments.


**Initial heap size**
- Can be overridden with -Xms {value} {unit in k , m , g}
- Default value if not speified is larger of 1/64th of the machine's physical memory 


**Maximum heap size**
- Can be overridden with -Xmx {value} {unit in k , m , g}
- Default value if not speified Smaller of 1/4th of the physical memory or 1GB

  
**How to Determine the Default values**
  
In order to find default values, run ```java -XX:+PrintFlagsFinal -version | findstr HeapSize``` on machine . The outout of the command will be something like below (on 4GB ram machine) .All the values are in bytes
  
  ```
  uintx ErgoHeapSizeLimit                         = 0                                   {product}
  uintx HeapSizePerGCThread                       = 87241520                            {product}
  uintx InitialHeapSize                          := 67108864                            {product}
  uintx LargePageHeapSizeThreshold                = 134217728                           {product}
  uintx MaxHeapSize                              := 1059061760                          {product}

  ```
 
Let's analyze above output

- InitialHeapSize value is 67108864 bytes. As the machine is 4 GB so as per rules, (4/64) gb which means (1024 * 1024 * 1024 * 4 )/ 64 bytes =  67108864

- MaxHeapSize value is 1059061760 bytes. As the machine is 4 GB RAM so as per rule (4/4) gb  which means (1024 * 1024 * 1024 * 4 )/ 4 bytes = 1,07,37,41,824 close to what was in the o/p


  

  
  
  
  
  
  
  
