package com.epam.decorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobileVersion implements Webpage{
	final Logger logger=LogManager.getLogger(MobileVersion.class);
	public int ranking() {
		logger.info("Mobile Version");
		return 5;
	}

}
