package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.database.UserDao;
import com.epam.entity.User;
@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

	@Mock    
	UserDao userDaoJpaImpl;
	
	@InjectMocks
	SignUpService signUpservice;
	List<User> users;
	@BeforeEach
	public void setUp()
	{
		users=Arrays.asList(new User("user","Sai","Sai@123"));
	}
	@Test
	 void testUserSignUp()
	{
		User user=new User("user","Sai2","Sai@123");
		Mockito.when(userDaoJpaImpl.saveUser(user)).thenReturn(user);
		boolean savedUser=signUpservice.signUpService(user);
		Mockito.verify(userDaoJpaImpl).saveUser(user);
		assertEquals(true,savedUser); 
		
	}	  
	@Test
	void testExistingUserSignUp()
	{
		Mockito.when(userDaoJpaImpl.loadAll()).thenReturn(users);
		boolean savedUser=signUpservice.signUpService(users.get(0));
		Mockito.verify(userDaoJpaImpl).loadAll();
		assertEquals(false, savedUser);
	}
	
	

}
