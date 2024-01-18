package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.exceptions.UserException;
import com.epam.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserRestController.class)
class UserRestControllerTest {
	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	private MockMvc mockMvc;

	UserRequestDto userRequestDto;
	UserResponseDto userResponseDto;

	@BeforeEach
	public void questionServiceObject() {
		userRequestDto = UserRequestDto.builder().username("Lavanya12").password("Epam@123")
				.email("lavanyamuvva@epam.com").name("Lavanya Muvva").build();
		userRequestDto = UserRequestDto.builder().username("Lavanya12").email("lavanyamuvva@gmail.com")
				.name("Lavanya Muvva").password("Saibaba@2002").build();
		userResponseDto = UserResponseDto.builder().username("Lavanya12").email("lavanyamuvva@gmail.com")
				.name("Lavanya Muvva").build();
	}

	@Test
	void testGetAllUsers() throws Exception {
		Mockito.when(userServiceImpl.getAllUsers()).thenReturn(List.of(userResponseDto));
		mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].username").value("Lavanya12")).andReturn();
		Mockito.verify(userServiceImpl).getAllUsers();
	}

	@Test
	void testGetUserById() throws Exception {
		Mockito.when(userServiceImpl.getUserByUsername("Lavanya12")).thenReturn(userResponseDto);
		mockMvc.perform(get("/users/{username}", "Lavanya12")).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("Lavanya12")).andReturn();
		Mockito.verify(userServiceImpl).getUserByUsername("Lavanya12");
	}

	@Test
	void testGetUserByIdWithException() throws Exception {
		Mockito.when(userServiceImpl.getUserByUsername("Lavanya12")).thenThrow(UserException.class);
		mockMvc.perform(get("/users/{username}", "Lavanya12")).andExpect(status().isOk()).andReturn();
		Mockito.verify(userServiceImpl).getUserByUsername("Lavanya12");
	}

	@Test
	void testAddUser() throws Exception {
		Mockito.when(userServiceImpl.addUser(userRequestDto)).thenReturn(userResponseDto);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequestDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.username").value("Lavanya12"));
		Mockito.verify(userServiceImpl).addUser(userRequestDto);
	}

	@Test
	void testDeleteUser() throws Exception {
		Mockito.doNothing().when(userServiceImpl).deleteUser("Lavanya12");
		mockMvc.perform(delete("/users/{username}", "Lavanya12")).andExpect(status().isNoContent());
	}

	void testModifyUser() throws Exception {
		Mockito.when(userServiceImpl.updateUser(userRequestDto, "Lavanya12")).thenReturn(userResponseDto);
		mockMvc.perform(put("/users/{username}", "Sumi123").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userResponseDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("Lavanya12"));
		Mockito.verify(userServiceImpl).updateUser(userRequestDto, "Lavanya12");
	}

}
