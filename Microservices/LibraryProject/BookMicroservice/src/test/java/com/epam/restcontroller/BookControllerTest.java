package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.exceptions.BookException;
import com.epam.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookRestController.class)
class BookControllerTest {
	@MockBean
	BookServiceImpl bookServiceImpl;
	@Autowired
	private MockMvc mockMvc;
	BookRequestDto bookRequestDto;
	BookResponseDto bookResponseDto;
	BookResponseDto bookErrorDto;
	@BeforeEach
	public void setUp()
	{
		bookResponseDto= BookResponseDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();	
		bookRequestDto= BookRequestDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();	
		
	}
	
	@Test
	void testAddBook() throws Exception{
		Mockito.when(bookServiceImpl.addBook(any())).thenReturn(bookResponseDto);
		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(bookResponseDto)))
				.andExpect(status().isCreated())
				.andReturn();
		Mockito.verify(bookServiceImpl).addBook(any());
	}
	@Test
	void testAddBookException()throws Exception{
		Mockito.when(bookServiceImpl.addBook(any())).thenThrow(BookException.class);
		mockMvc.perform(post("/books")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(bookResponseDto)))
		.andExpect(status().isOk())
		.andReturn();
		Mockito.verify(bookServiceImpl).addBook(any());
	}
	@Test
	void testGetAllBooks() throws Exception {
	    Mockito.when(bookServiceImpl.getAllBooks()).thenReturn(List.of(bookResponseDto));
	    mockMvc.perform(get("/books"))
	           .andExpect(status().isOk())
	           .andReturn();
	    Mockito.verify(bookServiceImpl).getAllBooks();
	}
	@Test
	void testGetAllBooksException() throws Exception {
	    Mockito.when(bookServiceImpl.getAllBooks()).thenThrow(BookException.class);
	    mockMvc.perform(get("/books"))
	           .andExpect(status().isOk())
	           .andReturn();
	    Mockito.verify(bookServiceImpl).getAllBooks();
	}
	@Test
	 void testGetBookById() throws Exception {
		mockMvc.perform(get("/books/{id}",1)).andExpect(status().isOk())
	            .andReturn();
		Mockito.verify(bookServiceImpl).getBookById(1);
	}
	@Test
	 void testGetBookByIdException() throws Exception {
		Mockito.when(bookServiceImpl.getBookById(1)).thenThrow(BookException.class);
		mockMvc.perform(get("/books/{id}",1)).andExpect(status().isOk())
	            .andReturn();
		Mockito.verify(bookServiceImpl).getBookById(1);
	}
	@Test
	void testDeleteBook() throws  Exception {
		Mockito.doNothing().when(bookServiceImpl).deleteBook(1);
		mockMvc.perform(delete("/books/{id}",1)).andExpect(status().isNoContent())
				.andReturn();
		Mockito.verify(bookServiceImpl).deleteBook(1);
	}
	@Test
	void testUpdateBook() throws Exception {
		Mockito.when(bookServiceImpl.updateBook(any(),eq(1))).thenReturn(bookResponseDto);
		mockMvc.perform(put("/books/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookResponseDto)))
                .andExpect(status().isOk())
                .andReturn();
		Mockito.verify(bookServiceImpl).updateBook(any(),eq(1));
               
	}
	@Test
	void testUpdateBookException() throws Exception {
		Mockito.when(bookServiceImpl.updateBook(any(),eq(1))).thenThrow(BookException.class);
		mockMvc.perform(put("/books/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookResponseDto)))
                .andExpect(status().isOk())
                .andReturn();       
		Mockito.verify(bookServiceImpl).updateBook(any(),eq(1));
	}
}