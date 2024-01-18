package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.UserDao;
import com.epam.entity.User;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
	@Mock
	private UserDao userDaoJpaImpl;
	@InjectMocks
	private LoginService loginService;
	private User user=new User();
	@BeforeEach
	public void setUp()
	{
	        user.setUserType("admin");
	        user.setUsername("john");
	        user.setPassword("password");
	}
	@Test
	void adminValidLoginTest() {
		when(userDaoJpaImpl.validateUser("admin","john","password")).thenReturn(true);
		boolean result=loginService.validateUser("admin","john","password");
		assertEquals(true,result);  
	}	
	@Test
	void adminInValidLoginTest() {
		when(userDaoJpaImpl.validateUser("admin","john","password")).thenReturn(false);
		userDaoJpaImpl.validateUser("admin","john","password");
		boolean result=loginService.validateUser("admin","john1","password");
		assertEquals(false,result);
	}	

	
}
