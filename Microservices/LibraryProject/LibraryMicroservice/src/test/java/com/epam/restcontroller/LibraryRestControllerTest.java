package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.entity.Library;
import com.epam.service.LibraryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = LibraryRestController.class)
class LibraryRestControllerTest {
	@MockBean
	RestTemplate restTemplate;
	@MockBean
	LibraryServiceImpl service;
	@Autowired
	MockMvc mockMvc;
	BookDto bookDto;
	Library library;
	LibraryDto libraryDto;
	UserDto userDto;

	@BeforeEach
	public void setUp() {
		bookDto = new BookDto("Ikigai", "Lavanya", "abcPublishers", 5);
		userDto = new UserDto();
		userDto.setUsername("Lavanya12");
		userDto.setName("LavanyaMuvva");
		userDto.setEmail("lavanyamuvva@gmail.com");
		userDto.setPassword("Lavanya@123");
		library = new Library(0, "Lavanya12", 1);
		libraryDto = LibraryDto.builder().userDto(userDto).bookdtos(List.of(bookDto)).build();
	}

	@Test
	void testGetAllBooks() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<ParameterizedTypeReference<List<BookDto>>>any()))
				.thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
		mockMvc.perform(get("/library/books")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetBookById() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(BookDto.class)))
				.thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.OK));
		mockMvc.perform(get("/library/books/1")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testAddBook() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(BookDto.class)))
				.thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpectAll(status().isCreated())
				.andReturn();
	}

	@Test
	void testUpdateBook() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(BookDto.class)))
				.thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.OK));
		mockMvc.perform(put("/library/books/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookDto))).andExpectAll(status().isOk()).andReturn();
	}

	@Test
	void deleteBook() throws Exception {
		Mockito.doNothing().when(service).deleteBookFromLibrary(1);
		mockMvc.perform(delete("/library/books/1")).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	void testAddUser() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(UserDto.class)))
				.thenReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpectAll(status().isCreated())
				.andReturn();
	}

	@Test
	void testUpdateUser() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(UserDto.class)))
				.thenReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.OK));
		mockMvc.perform(put("/library/users/Lavanya12").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpectAll(status().isOk()).andReturn();
	}

	@Test
	void deleteUser() throws Exception {
		Mockito.doNothing().when(service).deleteUserFromLibrary("Lavanya12");
		mockMvc.perform(delete("/library/users/Lavanya12")).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	void testGetAllUsers() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<ParameterizedTypeReference<List<UserDto>>>any()))
				.thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
		mockMvc.perform(get("/library/users")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetUserByUserName() throws Exception {
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), eq(UserDto.class)))
				.thenReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED));
		Mockito.when(service.getBooksByUser("Lavanya12")).thenReturn(libraryDto);
		mockMvc.perform(get("/library/users/priyanka2861")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testIssueBookToUser() throws Exception {
		Mockito.when(service.lendBookToUser("Lavanya12", 1)).thenReturn(libraryDto);
		mockMvc.perform(post("/library/users/Lavanya12/books/1")).andExpectAll(status().isOk()).andReturn();
	}

	@Test
	void testReturnBookToLibrary() throws Exception {
		Mockito.doNothing().when(service).returnBookToLibrary("Lavanya12", 1);
		mockMvc.perform(delete("/library/users/Lavanya12/books/1")).andExpectAll(status().isNoContent()).andReturn();
	}
}