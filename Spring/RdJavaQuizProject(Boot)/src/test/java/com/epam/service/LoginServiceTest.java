package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.UserDao;
import com.epam.entity.User;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
	@Mock
	private UserDao userDaoJpaImpl;
	@InjectMocks
	private LoginService loginService;
	@Test
	void adminValidLoginTest() {
		User user = new User("admin","Admin1", "Epam@123");
		
		when(userDaoJpaImpl.checkUser(user)).thenReturn(true);
		userDaoJpaImpl.checkUser(user);
		boolean result=loginService.validateUser(user);
		//Mockito.verify(userDaoJpaImpl).checkUser(user);
		assertEquals(true,result); 
	}	
	@Test
	void adminInValidLoginTest() {
		User user = new User("admin","sai", "Epam@123");
		when(userDaoJpaImpl.checkUser(user)).thenReturn(false);
		userDaoJpaImpl.checkUser(user);
		boolean result=loginService.validateUser(new  User("admin","1","123"));
		Mockito.verify(userDaoJpaImpl).checkUser(user);
		assertEquals(false,result);
	}	

	
}
