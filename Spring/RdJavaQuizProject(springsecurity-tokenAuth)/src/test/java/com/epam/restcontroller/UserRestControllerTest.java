package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.UserDto;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.restapi.UserRestController;
import com.epam.service.QuestionLibraryServiceImpl;
import com.epam.service.QuizLibraryServiceImpl;
import com.epam.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(UserRestController.class)
@WithMockUser(value = "spring")
class UserRestControllerTest {
	@MockBean
	QuestionLibraryServiceImpl questionLibraryServiceImpl;
	@MockBean
	QuizLibraryServiceImpl quizLibraryServiceImpl;
	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	private MockMvc mockMvc;
	User user, user1;
	UserDto userDto, userDto1;

	@BeforeEach
	public void setUp() {
		Role role=new Role();
		role.setRoleName("USER");
		List<Role> roles=List.of(role);
		user = new User();
		user.setUsername("Admin1");
		user.setPassword("Epam@123");
		user.setRoles(roles);
		userDto = new UserDto(roles, "Admin1", "Epam@123");
	}

	@Test
	void testAdminSignUp() throws Exception {
		Mockito.when(userServiceImpl.signUp(any())).thenReturn(user);
		mockMvc.perform(post("/users/signup").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto)).with(csrf())).andExpect(status().isCreated())
				.andExpect(jsonPath("$.username").value("Admin1"))
				.andReturn();
			
	}
	
	@Test
	void testAdminLogin() throws Exception {
		Mockito.when(userServiceImpl.validate(any())).thenReturn(user);
		mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto)).with(csrf())).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("Admin1"))
				.andReturn();
			
	} 
}