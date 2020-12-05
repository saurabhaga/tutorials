package com.example.subpackage1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {

	@Bean
	public Bean1 getBean1() {
		return new Bean1();
	}
}
