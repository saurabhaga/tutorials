package com.outerpackage3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.outerpackage4")
public class Config3 {

	@Bean
	public Bean3 getBean3() {
		return new Bean3();
	}
}
