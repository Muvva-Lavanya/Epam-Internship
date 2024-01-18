package com.epam.service1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.database.UserDao;
@Service
public class LoginService {
	@Autowired
	@Qualifier(value = "userDaoJpaImpl")
	UserDao userDaoJpaImpl;
	public boolean validateUser(String userType,String username,String password) {
		return userDaoJpaImpl.validateUser(userType,username,password);
	}
	
	
}
