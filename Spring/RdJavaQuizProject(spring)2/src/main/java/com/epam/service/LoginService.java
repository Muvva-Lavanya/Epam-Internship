package com.epam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.database.UserDaoJpaImpl;
import com.epam.entity.User;
@Service
public class LoginService {
	@Autowired
	UserDaoJpaImpl userDaoJpaImpl;
	public boolean validateUser(User user) {
		boolean result=false;  
		
		if(userDaoJpaImpl.checkUser(user))
		{
			result=true;
		}
		return result;
	}
	
	
}
