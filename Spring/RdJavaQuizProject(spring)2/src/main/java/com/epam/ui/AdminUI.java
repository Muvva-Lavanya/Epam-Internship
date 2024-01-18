package com.epam.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminUI {
	@Autowired
	LoginUI login;
	public void adminService() {
		login.loginService("admin"); 
	}

}
