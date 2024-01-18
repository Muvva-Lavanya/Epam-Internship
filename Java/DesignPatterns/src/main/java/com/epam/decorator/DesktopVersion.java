package com.epam.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesktopVersion implements Webpage{
	final Logger logger=LogManager.getLogger(DesktopVersion.class);
	public int ranking() {
		logger.info("Desktop Version");
		return 10;
	}

}
