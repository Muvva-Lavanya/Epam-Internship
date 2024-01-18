package com.epam.ocp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Email implements Notification{
	final Logger logger=LogManager.getLogger(Email.class);
	@Override
	public void sendOTP(String medium) {
		// TODO Auto-generated method stub
		if(medium.equals("email"))
		{
			logger.info("Sent through email");
		}
		
	}

}
