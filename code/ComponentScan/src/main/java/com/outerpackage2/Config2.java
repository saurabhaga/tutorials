package com.outerpackage2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.outerpackage5.Config5;

@Configuration
@Import(Config5.class)
public class Config2 {

	@Bean
	public Bean2 getBean() {
		return new Bean2();
	}
}
