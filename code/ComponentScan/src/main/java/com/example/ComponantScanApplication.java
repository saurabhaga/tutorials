package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.subpackage1.Bean1;
import com.example.subpackage1.Component1;
import com.example.subpackage1.Config1;
import com.outerpackage2.Bean2;
import com.outerpackage2.Component2;
import com.outerpackage2.Config2;
import com.outerpackage3.Bean3;
import com.outerpackage3.Component3;
import com.outerpackage3.Config3;
import com.outerpackage4.Component4;
import com.outerpackage5.Bean5;
import com.outerpackage5.Config5;
import com.test.CustomAutoconfiguration;
import com.test2.TestBean;

@SpringBootApplication
@ComponentScan(basePackages = {"com.outerpackage2","com.example"})
public class ComponantScanApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ComponantScanApplication.class, args);
		
		System.out.println(ctx.getBean(Component1.class));
		System.out.println(ctx.getBean(Config1.class));
		System.out.println(ctx.getBean(Bean1.class));
		
		System.out.println(ctx.getBean(Component2.class));
		System.out.println(ctx.getBean(Config2.class));
		System.out.println(ctx.getBean(Bean2.class));
	
		System.out.println(ctx.getBean(Component3.class));
		System.out.println(ctx.getBean(Config3.class));
		System.out.println(ctx.getBean(Bean3.class));
	
		System.out.println(ctx.getBean(Component4.class));
		
		System.out.println(ctx.getBean(Config5.class));
		System.out.println(ctx.getBean(Bean5.class));
		
		System.out.println("======Custom Auto Configure Test============");
		
		System.out.println(ctx.getBean(CustomAutoconfiguration.class));
		System.out.println(ctx.getBean(TestBean.class));
		

		
	}
	
	 

}
