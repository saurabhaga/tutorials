package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

	@Autowired @Qualifier("maruti") private Car car;
	@Autowired private Honda hondaCar;
	@Autowired private Maruti marutiCar;
	
	@Autowired private Car honda;
	@Autowired private Car maruti;
	
	public void print() {
		System.out.println("Car car=" + car);
		System.out.println("Honda hondaCar=" + hondaCar);
		System.out.println("Maruti MarutiCar=" + marutiCar);
		System.out.println("Car honda=" + honda);
		System.out.println("Car maruti=" + maruti);
		
	}
	
}
