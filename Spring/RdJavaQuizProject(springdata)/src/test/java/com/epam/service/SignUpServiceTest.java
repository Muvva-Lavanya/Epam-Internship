package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexception.RedundantUserException;
import com.epam.database.UserDao;
import com.epam.dto.UserDto;
import com.epam.entity.User;
@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

	@Mock    
	UserDao userDaoJpaImpl;
	
	@InjectMocks
	SignUpService signUpservice;
	UserDto userDto=new UserDto();
	User user=new User();
	@BeforeEach
	public void setUp()
	{
		userDto=new UserDto("user","Sai2","Sai@123");
		user=new User(userDto);
	}
	@Test
	 void testExisitngUserSignUp() throws RedundantUserException
	{
		Mockito.when(userDaoJpaImpl.checkUserExists("Sai2")).thenReturn(Optional.ofNullable(user));
		assertThrows(RedundantUserException.class,()->signUpservice.signUpService(user));
		
	}	  
	@Test
	void testUserSignUp() throws RedundantUserException
	{
		Mockito.when(userDaoJpaImpl.checkUserExists("Sai2")).thenReturn(Optional.empty());
		Mockito.when(userDaoJpaImpl.saveUser(user)).thenReturn(user);
		User savedUser=signUpservice.signUpService(user);
		Mockito.verify(userDaoJpaImpl).checkUserExists("Sai2");
		Mockito.verify(userDaoJpaImpl).saveUser(user);
		assertEquals(user,savedUser);
	}
	
	

}
