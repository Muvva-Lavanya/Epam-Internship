package com.epam.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.LoginService;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
	@Mock
	private LoginService loginService;
	
	@InjectMocks
	private LoginController loginController;
	private MockMvc mockMvc;
	User user=new User();
	UserDto userDto=new UserDto();
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
		userDto=new UserDto("admin","Admin1","Epam@123");
		user = new User(userDto);
			}
	 @Test
	     void testLoginSuccessForAdmin() throws Exception {
	        when(loginService.validateUser("admin","Admin1","Epam@123")).thenReturn(true);
	        mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
			.andExpect(view().name("adminMenu.jsp"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("message","Welcome admin....")).andReturn();
		 
	    }
	 @Test
     void testLoginSuccessForUser() throws Exception {
        when(loginService.validateUser("user","Admin1","Epam@123")).thenReturn(true);
        mockMvc.perform(post("/login").param("userType","user").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("userMenu.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Welcome user")).andReturn();
	 
    }
	 @Test
     void testFailureForLogin() throws Exception {
        when(loginService.validateUser("admin","Admin1","Epam@123")).thenReturn(false);
        mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login Unsuccessfull")).andReturn();
	 
    }
	 @Test
     void testUserTypeFailureForLogin() throws Exception {
        when(loginService.validateUser("admin1","Admin1","Epam@123")).thenReturn(true);
        mockMvc.perform(post("/login").param("userType","admin1").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login Unsuccessfull")).andReturn();
	 
    }
}
