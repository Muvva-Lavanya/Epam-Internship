package com.epam.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSelection {
	static AdminUI adminUI;
	static UserUI userUI;
	@Autowired
	public void setAdminUI(AdminUI adminUI)
	{
		UserSelection.adminUI=adminUI;
	}
	@Autowired
	public void setUserUI(UserUI userUI)
	{
		UserSelection.userUI= userUI;
	}
	private static final Map<Integer, DefaultMenu> UserType;
	Logger logger = LogManager.getLogger(UserSelection.class);
	static {
		final Map<Integer, DefaultMenu> userType = new HashMap<>();
		userType.put(1, ()->adminUI.adminService());
		userType.put(2,()->userUI.userService());
		userType.put(3, ()-> System.exit(0));
		UserType = Collections.unmodifiableMap(userType);
		
	}
	public void createUser(int type) {
		DefaultMenu command = UserType.get(type);
		try {
			if (command == null) {
				throw new InputMismatchException("Enter proper option");
			} else {
				command.selectType();
			}
		} catch (InputMismatchException e) {
			logger.info(e);
		}
	}
}
