package com.epam.srp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailOTPClient implements OTPClient{
	
	final Logger logger=LogManager.getLogger(EmailOTPClient.class);
	@Override
	public void sendOTP() {
		// TODO Auto-generated method stub
		System.out.println("");
		logger.info("OTP sent to your email");
		
	}

}
