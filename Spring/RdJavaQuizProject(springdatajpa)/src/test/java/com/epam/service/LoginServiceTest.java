package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
class LoginServiceTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private LoginService loginService;
	private User user;
	private UserDto userDto;
	@BeforeEach
	public void setUp()
	{
		userDto=new UserDto("admin","Admin1","Epam@123");
	    user=new User("admin","Admin1","Epam@123");
	}
	@Test
	void adminValidLoginTest() throws UserException {
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		when(userRepository.findByUsernameAndPassword("Admin1","Epam@123")).thenReturn(Optional.ofNullable(user));
		User result=loginService.validateUser(userDto);
		Mockito.verify(modelMapper).map(userDto,User.class);
		Mockito.verify(userRepository).findByUsernameAndPassword("Admin1","Epam@123");
		assertEquals(user,result);  
	}	
	@Test
	void adminInValidLoginTest() {
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		when(userRepository.findByUsernameAndPassword("Admin1","Epam@123")).thenReturn(Optional.empty());
		assertThrows(UserException.class,()->loginService.validateUser(userDto));
		Mockito.verify(modelMapper).map(userDto,User.class);
		Mockito.verify(userRepository).findByUsernameAndPassword("Admin1","Epam@123");
	}
	
}
