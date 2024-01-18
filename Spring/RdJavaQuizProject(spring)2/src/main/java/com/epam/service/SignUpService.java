package com.epam.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.database.UserDao;
import com.epam.entity.User;
@Service
public class SignUpService {
	@Autowired
	private UserDao userDaoImpl;
	public boolean signUpService(User user) {
		boolean result=false;
			
			List<User> userList=userDaoImpl.loadAll().stream().filter(e->e.getUserType().equals(user.getUserType())).toList(); 
			long countUsers=userList.stream().filter(e->e.getUsername().equals(user.getUsername())).count();
			if(countUsers==0)
			{
				userDaoImpl.save(user);
				result=true;
			}
		return result; 

	}



}
