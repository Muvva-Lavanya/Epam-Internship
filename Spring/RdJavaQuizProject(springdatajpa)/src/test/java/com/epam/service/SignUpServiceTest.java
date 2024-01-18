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
import org.modelmapper.ModelMapper;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;
@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	ModelMapper modelMapper;
	@InjectMocks
	SignUpService signUpservice;
	UserDto userDto;
	User user;
	@BeforeEach
	public void setUp()
	{
		userDto=new UserDto("user","Sai2","Sai@123");
		user=new User("user","sai","Sai@123");
	}
	@Test
	void testSignUpSuccess() throws UserException
	{
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		Mockito.when(userRepository.findByUsername("sai")).thenReturn(Optional.empty());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User savedUser=signUpservice.signUpService(userDto);
		assertEquals(user,savedUser);
		Mockito.verify(userRepository).findByUsername("sai");
	}
	
	@Test
	void testSignUpFailure() throws UserException
	{
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		Mockito.when(userRepository.findByUsername("sai")).thenReturn(Optional.ofNullable(user));
		assertThrows(UserException.class,()->signUpservice.signUpService(userDto));
		Mockito.verify(userRepository).findByUsername("sai");
	}
	
	

}
