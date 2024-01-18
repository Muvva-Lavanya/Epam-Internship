package com.epam.builder;
public class Bicycle
{
	boolean gears;
	boolean doubleStands;
	boolean doubleSeats;
	boolean carriers;
	public Bicycle(boolean gears, boolean doubleStands, boolean doubleSeats, boolean carriers) {
		super();
		this.gears = gears;
		this.doubleStands = doubleStands;
		this.doubleSeats = doubleSeats;
		this.carriers = carriers;
	}
	@Override
	public String toString() {
		return "Bicycle [gears=" + gears + ", doubleStands=" + doubleStands + ", doubleSeats=" + doubleSeats
				+ ", carriers=" + carriers + "]";
	}
	
}