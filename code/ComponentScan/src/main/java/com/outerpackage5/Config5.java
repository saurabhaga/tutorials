package com.outerpackage5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config5 {

	@Bean
	public Bean5 getBean5() {
		return new Bean5();
	}
}
