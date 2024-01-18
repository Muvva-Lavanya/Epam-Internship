package com.epam.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.LibraryDto;
import com.epam.dto.request.BookRequestDto;
import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.entity.Library;
import com.epam.service.LibraryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = LibraryRestController.class)
class LibraryRestControllerTest {
	@MockBean
	BookFeignClient bookFeignClient;
	@MockBean
	UserFeignClient userFeignClient;
	@MockBean
	LibraryServiceImpl service;
	@Autowired
	MockMvc mockMvc;
	Library library;
	LibraryDto libraryDto;
	UserResponseDto userResponseDto;
	UserRequestDto userRequestDto;
	BookResponseDto bookResponseDto;
	BookRequestDto bookRequestDto;

	@BeforeEach
	public void setUp() {
		userRequestDto=UserRequestDto.builder().username("Lavanya12").email("abc@gmail.com").name("Lavanya").password("Epam@123").build();
		userResponseDto=UserResponseDto.builder().username("Lavanya12").email("abc@gmail.com").name("Lavanya").build();
		bookResponseDto= BookResponseDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();
		bookRequestDto= BookRequestDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();
		library = new Library(0, "Lavanya12", 1);
		libraryDto = LibraryDto.builder().userDto(userResponseDto).bookdtos(List.of(bookResponseDto)).build();
	}

	@Test
	void testGetAllBooks() throws Exception {
		Mockito.when(bookFeignClient.getAllBooks())
				.thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
		mockMvc.perform(get("/library/books")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetBookById() throws Exception {
		Mockito.when(bookFeignClient.getBook(1))
				.thenReturn(new ResponseEntity<BookResponseDto>(bookResponseDto, HttpStatus.OK));
		mockMvc.perform(get("/library/books/1")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testAddBook() throws Exception {
		Mockito.when(bookFeignClient.addBook(bookRequestDto))
				.thenReturn(new ResponseEntity<BookResponseDto>(bookResponseDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookResponseDto))).andExpectAll(status().isCreated())
				.andReturn();
	}

	@Test
	void testUpdateBook() throws Exception {
		Mockito.when(bookFeignClient.updateBook(1, bookRequestDto))
				.thenReturn(new ResponseEntity<BookResponseDto>(bookResponseDto, HttpStatus.OK));
		mockMvc.perform(put("/library/books/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookResponseDto))).andExpectAll(status().isOk()).andReturn();
	}

	@Test
	void deleteBook() throws Exception {
		Mockito.doNothing().when(service).deleteBookFromLibrary(1);
		mockMvc.perform(delete("/library/books/1")).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	void testAddUser() throws Exception {
		Mockito.when(userFeignClient.addUser(userRequestDto))
				.thenReturn(new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED));
		mockMvc.perform(post("/library/users").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequestDto))).andExpectAll(status().isCreated())
				.andReturn();
	}

	@Test
	void testUpdateUser() throws Exception {
		Mockito.when(userFeignClient.updateUser("Lavanya12", userRequestDto))
				.thenReturn(new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK));
		mockMvc.perform(put("/library/users/Lavanya12").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userRequestDto))).andExpectAll(status().isOk()).andReturn();
	}

	@Test
	void deleteUser() throws Exception {
		Mockito.doNothing().when(service).deleteUserFromLibrary("Lavanya12");
		mockMvc.perform(delete("/library/users/Lavanya12")).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	void testGetAllUsers() throws Exception {
		Mockito.when(userFeignClient.getAllUsers())
				.thenReturn(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
		mockMvc.perform(get("/library/users")).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetUserByUserName() throws Exception {
		Mockito.when(userFeignClient.getUser("Lavanya12"))
				.thenReturn(new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED));
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