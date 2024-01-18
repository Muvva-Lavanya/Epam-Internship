package com.epam.builder;

public class BicycleTest {
	public static void main(String[] args) {
		Bicycle bicycle=new BicycleBuilder().setGears(false).setDoubleSeats(true).build();
		System.out.println(bicycle);
	
	}
}
