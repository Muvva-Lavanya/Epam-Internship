package com.epam.ui;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UserSelection {
	@Autowired
	AdminUI adminUI;
	@Autowired
	UserUI userUI;
	Logger logger = LogManager.getLogger(UserSelection.class);
	private Map<Integer, OptionSelection> userType;

	@PostConstruct
	public void userSelection() {
		userType = new HashMap<>();
		userType.put(1, () -> adminUI.adminService());
		userType.put(2, () -> userUI.userService());
		userType.put(3, () -> System.exit(0));
	}

	public void selectUserOption(int type) { 
		if (userType.containsKey(type)) {
			userType.get(type).selectType();
		} else {
			logger.info("Enter proper option");
		}
	}
}
