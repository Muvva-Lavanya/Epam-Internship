package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.http.HttpStatus;

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.entity.Book;
import com.epam.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	
	@Mock
	BookRepository bookRepository;
	@Mock
	ModelMapper modelMapper;
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	Book book;
	BookRequestDto bookRequestDto;
	BookResponseDto bookResponseDto;
	BookResponseDto bookErrorDto;
	@BeforeEach
	public void setUp()
	{
		book=new Book(1,"Ikigai","lavanya","abcPublishers");
		bookResponseDto= BookResponseDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();	
		bookRequestDto= BookRequestDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();	
		bookErrorDto= BookResponseDto.builder().timestamp(new Date().toString()).developerMessage("Book with id " + 100 + " doesnt exists. Try with different Id").httpStatus(HttpStatus.NO_CONTENT).build();
		
	}
	
	@Test
	void testAddBook()
	{
		Mockito.when(modelMapper.map(bookRequestDto, Book.class)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		BookResponseDto savedBook=bookServiceImpl.addBook(bookRequestDto);
		Mockito.verify(bookRepository).save(book);
		assertEquals(savedBook,bookResponseDto);
	}
	
	@Test
	void testUpdateBook()
	{
		Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		BookResponseDto updatedBook=bookServiceImpl.updateBook(bookRequestDto,1);
		Mockito.verify(bookRepository).findById(1);
		assertEquals(updatedBook,bookResponseDto);
	}
	
	@Test
	void testUpdateBookWithIdNotPresent()
	{
		Mockito.when(bookRepository.findById(100)).thenReturn(Optional.empty());
		BookResponseDto retrivedBookDto=bookServiceImpl.updateBook(bookRequestDto,100);
		Mockito.verify(bookRepository).findById(100);
		assertEquals(bookErrorDto,retrivedBookDto); 
	}
	
	@Test
	void testGetBookById()
	{
		Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		BookResponseDto retrivedBook=bookServiceImpl.getBookById(1);
		Mockito.verify(bookRepository).findById(1);
		assertEquals(retrivedBook,bookResponseDto);
		
	}
	
	@Test
	void testGetBookByIdNotPresent()
	{
		Mockito.when(bookRepository.findById(100)).thenReturn(Optional.empty());
		assertEquals(bookErrorDto,bookServiceImpl.getBookById(100));
		Mockito.verify(bookRepository).findById(100);
	}
	
	@Test
	void testDeleteBook()
	{
		Mockito.doNothing().when(bookRepository).deleteById(1);
		bookServiceImpl.deleteBook(1);
		Mockito.verify(bookRepository).deleteById(1);
	}
	
	@Test
	void testGetAllBooks()
	{
		Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));
		List<BookResponseDto> retrivedBooks=bookServiceImpl.getAllBooks();
		Mockito.verify(bookRepository).findAll();
		assertEquals(retrivedBooks,List.of(bookResponseDto));
	}
	
	

}
