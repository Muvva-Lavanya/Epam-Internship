package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.customexception.UserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.SignUpService;
@WebMvcTest(SignupController.class)
class SignUpControllerTest {
	@MockBean
	SignUpService signUpService;
	@Autowired
	private MockMvc mockMvc;
	User user;
	UserDto userDto;

	@BeforeEach
	public void setUp() {
		userDto = new UserDto("admin", "Admin1", "Epam@123");
		user=new User("admin","Admin1","Epam@123");
	}

	@Test
	void testSignUp() throws Exception {
		Mockito.when(signUpService.signUpService(any())).thenReturn(user);
		mockMvc.perform(post("/signup").param("userDto", "userDto")).andExpect(status().isOk())
				.andExpect(view().name("userMenu.jsp")).andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "Welcome Admin1")).andReturn();
	}

	@Test
	void testExistingUserSignUp() throws Exception {
		Mockito.when(signUpService.signUpService(any())).thenThrow(new UserException("User already exists"));
		mockMvc.perform(post("/signup").param("userDto", "userDto")).andExpect(status().isOk())
				.andExpect(view().name("signupFailure.jsp")).andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "User already exists")).andReturn();
	}
}
