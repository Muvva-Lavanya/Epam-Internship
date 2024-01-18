package com.epam.srp;

public class InsuranceInfoGetter {
	private TravelInsuranceClient medium;
	public InsuranceInfoGetter(TravelInsuranceClient medium){
		this.medium=medium;
	}
	
	public void getInsuranceInfo(int ticketId) {
		medium.getTravelInsuranceInfo(ticketId);
	}

}
