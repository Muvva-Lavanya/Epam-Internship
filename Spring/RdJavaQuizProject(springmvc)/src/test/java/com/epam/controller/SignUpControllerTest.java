package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.customexception.RedundantUserException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.service.SignUpService;

@ExtendWith(MockitoExtension.class)
class SignUpControllerTest {
	@Mock
	SignUpService signUpService;
	@InjectMocks
	SignupController signupController;
	private MockMvc mockMvc;
	User user;
	UserDto userDto;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(signupController).build();
		user = new User("admin", "Admin1", "Epam@123");
		userDto = new UserDto("admin", "Admin1", "Epam@123");
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
		Mockito.when(signUpService.signUpService(any())).thenThrow(new RedundantUserException());
		mockMvc.perform(post("/signup").param("userDto", "userDto")).andExpect(status().isOk())
				.andExpect(view().name("signupFailure.jsp")).andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "User already exists")).andReturn();
	}
}
