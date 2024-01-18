package com.epam.lsp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartPhone implements Mobile{
	final Logger logger=LogManager.getLogger(AppleIpod.class);
	@Override
	public void sendSMS() {
		// TODO Auto-generated method stub
		logger.info("sending sms");
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		logger.info("calling");
	}

	@Override
	public void playMusic(String fileName) {
		// TODO Auto-generated method stub
		logger.info("Playing music "+fileName);
	}

	@Override
	public void playVideo(String videoFileName) {
		// TODO Auto-generated method stub
		logger.info("playing video");
	}

}
