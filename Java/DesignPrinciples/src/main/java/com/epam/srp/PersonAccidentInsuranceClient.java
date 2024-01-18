package com.epam.srp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PersonAccidentInsuranceClient implements TravelInsuranceClient {
	final Logger logger=LogManager.getLogger(PersonAccidentInsuranceClient.class); 
	@Override
	public void getTravelInsuranceInfo(int ticketId) {
		// TODO Auto-generated method stub
		logger.info("Insurance is under process");
	}

}
