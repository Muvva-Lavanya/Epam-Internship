package com.epam.lsp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class AppleIpod implements Ipod{
	final Logger logger=LogManager.getLogger(AppleIpod.class);
	@Override
	public void playMusic(String fileName) {
		// TODO Auto-generated method stub
		logger.info("Playing music "+fileName);
		
	}

}
