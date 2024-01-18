package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.LoginService;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
	@MockBean
	private LoginService loginService;
	
	@Autowired
	private MockMvc mockMvc;
	User user,user1;
	UserDto userDto,userDto1;
	@BeforeEach
	public void setUp() {
		user=new User("admin","Admin1","Epam@123");
		userDto=new UserDto("admin","Admin1","Epam@123");
		user1=new User("user","Admin1","Epam@123");
		userDto1=new UserDto("user","Admin1","Epam@123");
			}
	 @Test
	     void testLoginSuccessForAdmin() throws Exception {
	        when(loginService.validateUser(any())).thenReturn(user);
	        mockMvc.perform(post("/login").param("userType","admin").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
			.andExpect(view().name("adminMenu.jsp"))
			.andExpect(model().attributeExists("message"))
			.andExpect(model().attribute("message","Welcome admin..")).andReturn();
		 
	    }
	 @Test
     void testLoginFailureForAdmin() throws Exception {
        when(loginService.validateUser(any())).thenThrow(new UserException("Login unSuccessfull"));
        mockMvc.perform(post("/login").param("userDto","userDto")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login unSuccessfull")).andReturn();
	 
    }
	 @Test
     void testLoginSuccessForUser() throws Exception {
        when(loginService.validateUser(any())).thenReturn(user1);
        mockMvc.perform(post("/login").param("userType","user").param("username","Admin1").param("password","Epam@123")).andExpect(status().isOk())
		.andExpect(view().name("userMenu.jsp")) 
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Welcome user..")).andReturn();
	 
    }
	 @Test
     void testUserTypeFailureForLogin() throws Exception {
        when(loginService.validateUser(any())).thenThrow(new UserException("Login unSuccessfull"));
        mockMvc.perform(post("/login").param("userDto","userDto")).andExpect(status().isOk())
		.andExpect(view().name("loginFailure.jsp"))
		.andExpect(model().attributeExists("message"))
		.andExpect(model().attribute("message","Login unSuccessfull")).andReturn();
	 
    }
}
