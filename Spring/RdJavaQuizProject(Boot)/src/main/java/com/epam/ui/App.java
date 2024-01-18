package com.epam.ui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.epam.Configuration;

public class App {
	public static void main(String[] args) {
		Logger logger=LogManager.getLogger(App.class);
		ApplicationContext context=SpringApplication.run(Configuration.class, args);
		HomePage homePage=context.getBean(HomePage.class);
		logger.info("Welcome to Console based Quiz Application");
		homePage.startApplication();
    	
	} 
}
