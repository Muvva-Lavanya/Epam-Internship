package com.epam.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.customexception.RedundantUserException;
import com.epam.database.UserDao;
import com.epam.entity.User;

@Service
public class SignUpService {
	@Autowired
	@Qualifier(value = "userDaoJpaImpl")
	private UserDao userDaoImpl;

	public User signUpService(User user) throws RedundantUserException {
		if(userDaoImpl.checkUserExists(user.getUsername()).isPresent()) { 
			throw new RedundantUserException();
		}else {
			return userDaoImpl.saveUser(user);
		}
	}
}
