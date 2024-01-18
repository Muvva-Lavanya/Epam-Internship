package com.epam.ocp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Whatsapp implements Notification{
	final Logger logger=LogManager.getLogger(Whatsapp.class);
	@Override
	public void sendOTP(String medium) {
		// TODO Auto-generated method stub
		if(medium.equals("whatsapp"))
		{
			logger.info("Sent through whatsapp");
		}
		
	}

}
