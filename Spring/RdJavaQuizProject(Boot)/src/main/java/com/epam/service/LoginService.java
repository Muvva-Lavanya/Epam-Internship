package com.epam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.database.UserDao;
import com.epam.entity.User;
@Service
public class LoginService {
	@Autowired
	@Qualifier(value = "userDaoJpaImpl")
	UserDao userDaoJpaImpl;
	public boolean validateUser(User user) {
		return userDaoJpaImpl.validateUser(user);
	}
	
	
}
