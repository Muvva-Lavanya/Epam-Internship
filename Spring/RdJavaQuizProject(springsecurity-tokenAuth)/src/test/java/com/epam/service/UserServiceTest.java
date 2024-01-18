package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.test.context.support.WithMockUser;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@WithMockUser(value = "spring")
class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	private User user;
	private UserDto userDto;
	@BeforeEach
	public void setUp()
	{
		Role role=new Role();
		role.setRoleName("USER");
		List<Role> roles=List.of(role);
		userDto=new UserDto(roles,"Admin1","Epam@123");
		user=new User();
		user.setUsername("Admin1");
		user.setPassword("Epam@123");
		user.setRoles(roles);
	}
	@Test
	void adminValidLoginTest() throws UserException {
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		when(userRepository.findByUsernameAndPassword("Admin1","Epam@123")).thenReturn(Optional.ofNullable(user));
		User result=userServiceImpl.validate(userDto);
		Mockito.verify(modelMapper).map(userDto,User.class);
		Mockito.verify(userRepository).findByUsernameAndPassword("Admin1","Epam@123");
		assertEquals(user,result);  
	}	
	@Test
	void adminInValidLoginTest() {
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		when(userRepository.findByUsernameAndPassword("Admin1","Epam@123")).thenReturn(Optional.empty());
		assertThrows(UserException.class,()->userServiceImpl.validate(userDto));
		Mockito.verify(modelMapper).map(userDto,User.class);
		Mockito.verify(userRepository).findByUsernameAndPassword("Admin1","Epam@123");
	}
	
	@Test
	void testSignUpSuccess() throws UserException
	{
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		Mockito.when(userRepository.existsByUsername("Admin1")).thenReturn(false);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User savedUser=userServiceImpl.signUp(userDto);
		assertEquals(user,savedUser);
		Mockito.verify(userRepository).existsByUsername("Admin1");
	}
	
	@Test
	void testSignUpFailure() throws UserException
	{
		Mockito.when(modelMapper.map(userDto,User.class)).thenReturn(user);
		Mockito.when(userRepository.existsByUsername("Admin1")).thenReturn(true);
		assertThrows(UserException.class,()->userServiceImpl.signUp(userDto));
		Mockito.verify(userRepository).existsByUsername("Admin1");
	}
	
}
