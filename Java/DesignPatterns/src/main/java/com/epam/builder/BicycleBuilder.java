package com.epam.builder;

public class BicycleBuilder {
	boolean gears;
	boolean doubleStands;
	boolean doubleSeats;
	boolean carriers;
	public BicycleBuilder setGears(boolean gears) {
		this.gears = gears;
		return this;
	}
	public BicycleBuilder setDoubleStands(boolean doubleStands) {
		this.doubleStands = doubleStands;
		return this;
	}
	public BicycleBuilder setDoubleSeats(boolean doubleSeats) {
		this.doubleSeats = doubleSeats;
		return this;
	}
	public BicycleBuilder setCarriers(boolean carriers) {
		this.carriers = carriers;
		return null;
	}
	public Bicycle build()
	{
		
		return new Bicycle(gears, doubleStands, doubleSeats, carriers);
	}
}
