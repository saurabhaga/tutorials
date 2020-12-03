package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutowiringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AutowiringApplication.class, args);

		// default bean name is the class name with first letter in small case
		TestComponent comp = (TestComponent) ctx.getBean("testComponent");
		System.out.println(comp);

		comp.print();
	}

}
