package com.test;

import org.springframework.context.annotation.Bean;

import com.test2.TestBean;

public class CustomAutoconfiguration {

	@Bean
	public TestBean getTestBean() {
		return new TestBean();
	}
	
}
