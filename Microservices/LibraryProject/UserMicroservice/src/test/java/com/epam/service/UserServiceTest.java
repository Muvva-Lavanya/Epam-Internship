package com.epam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.entity.User;
import com.epam.repository.UserRepository;



@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock 
	UserRepository userRepo;
	
	@Mock 
	ModelMapper mapper; 

	@InjectMocks
	UserServiceImpl userServiceImpl;
	User user;
	UserRequestDto userRequestDto;
	UserResponseDto userResponseDto;
	UserResponseDto userErrorDto;

	@BeforeEach
	public void questionServiceObject() {		
		user=new User();
		user.setUsername("Lavanya12");
		user.setEmail("lavanyamuvva@gmail.com");
		user.setName("Lavanya Muvva");
		user.setPassword("Epam@123");
		userRequestDto=UserRequestDto.builder().username("Lavanya12").email("lavanyamuvva@gmail.com").name("Lavanya Muvva").password("Epam@123").build();
		userResponseDto =UserResponseDto.builder().username("Lavanya12").email("lavanyamuvva@gmail.com").name("Lavanya Muvva").build();
		userErrorDto=UserResponseDto.builder().timestamp(new Date().toString()).developerMessage("User with username " + "Lavanya12" + " doesnt exists. Try with different username").httpStatus(HttpStatus.NO_CONTENT).build();
	}
	
	@Test 
	void testaddUserSuccess()
	{
		Mockito.when(mapper.map(userRequestDto, User.class)).thenReturn(user);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		assertEquals(userResponseDto,userServiceImpl.addUser(userRequestDto));
		Mockito.verify(userRepo).save(user);
	}
	@Test 
	void testaddUserFail()
	{
		Mockito.when(mapper.map(userRequestDto, User.class)).thenReturn(user);
		Mockito.when(userRepo.save(user)).thenThrow(new DataIntegrityViolationException("user already exists"));
		assertThrows(DataIntegrityViolationException.class,()->userServiceImpl.addUser(userRequestDto));
		Mockito.verify(mapper).map(userRequestDto,User.class);
	}
	@Test 
	void testDeleteUser()
	{
		Mockito.doNothing().when(userRepo).deleteByUsername("Lavanya12");;
		userServiceImpl.deleteUser("Lavanya12");
		Mockito.verify(userRepo).deleteByUsername("Lavanya12");
	}
	@Test 
	void testUpdateUser()
	{
		Mockito.when(userRepo.findByUsername("Lavanya12")).thenReturn(Optional.of(user));
		assertEquals(userResponseDto,userServiceImpl.updateUser(userRequestDto,"Lavanya12"));
		Mockito.verify(userRepo).findByUsername("Lavanya12");
	}
	@Test 
	void updateUserFailCaseTwo()
	{
		Mockito.when(userRepo.findByUsername("Lavanya12")).thenReturn(Optional.empty());
		assertEquals(userErrorDto,userServiceImpl.updateUser(userRequestDto,"Lavanya12"));
		Mockito.verify(userRepo).findByUsername("Lavanya12");
		
	}
	@Test 
	void getUserByUsername()
	{
		Mockito.when(userRepo.findByUsername("Lavanya12")).thenReturn(Optional.of(user));
		assertEquals(userResponseDto,userServiceImpl.getUserByUsername("Lavanya12"));
		Mockito.verify(userRepo).findByUsername("Lavanya12");
	}
	@Test 
	void getUserFail()
	{
		Mockito.when(userRepo.findByUsername("Lavanya12")).thenReturn(Optional.empty());
		assertEquals(userErrorDto,userServiceImpl.getUserByUsername("Lavanya12"));
		Mockito.verify(userRepo).findByUsername("Lavanya12");
	}
	
	@Test 
	void getAllUsers()
	{
		Mockito.when(userRepo.findAll()).thenReturn(List.of(user));
		assertEquals(List.of(userResponseDto),userServiceImpl.getAllUsers());
		Mockito.verify(userRepo).findAll();
	}

}