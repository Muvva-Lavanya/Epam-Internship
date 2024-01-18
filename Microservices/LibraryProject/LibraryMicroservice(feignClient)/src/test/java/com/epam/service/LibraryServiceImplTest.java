package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.dto.LibraryDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.entity.Library;
import com.epam.exceptions.LibraryException;
import com.epam.repository.LibraryRepository;
import com.epam.restcontroller.BookFeignClient;
import com.epam.restcontroller.UserFeignClient;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {
	@Mock
	BookFeignClient bookFeignClient;
	@Mock
	UserFeignClient userFeignClient;
	@Mock
	LibraryRepository libraryRepository;
	@Mock
	ModelMapper mapper;
	@InjectMocks
	LibraryServiceImpl libraryService;
	@Value("${books}")
	String booksUrl;
	@Value("${users}")
	String usersUrl;

	UserResponseDto userDto;
	Library library;
	LibraryDto libraryResponseDto;
	BookResponseDto bookDto;

	@BeforeEach
	void setup() {
		bookDto = BookResponseDto.builder().name("Ikigai").author("lavanya").publisher("abcPublishers").build();
		userDto = UserResponseDto.builder().name("Lavanya12").email("abc@gmail.com").name("Lavanya").build();
		library = new Library(0, "Lavanya12", 1);
		libraryResponseDto = LibraryDto.builder().userDto(userDto).bookdtos(List.of(bookDto)).build();
	}

	@Test
	void testDeleteBook() {
		Mockito.doNothing().when(libraryRepository).deleteByBookId(1);
		Mockito.doNothing().when(bookFeignClient).deleteBookById(1);
		libraryService.deleteBookFromLibrary(1);
		Mockito.verify(libraryRepository).deleteByBookId(1);
		Mockito.verify(bookFeignClient).deleteBookById(1);
	}

	@Test
	void testDeleteUser() {
		Mockito.doNothing().when(libraryRepository).deleteByUsername("Lavanya12");
		Mockito.doNothing().when(userFeignClient).deleteBookByUsername("Lavanya12");
		libraryService.deleteUserFromLibrary("Lavanya12");
		Mockito.verify(libraryRepository).deleteByUsername("Lavanya12");
		Mockito.verify(userFeignClient).deleteBookByUsername("Lavanya12");
	}

	@Test
	void testGetUser() {
		Mockito.when(userFeignClient.getUser("Lavanya12")).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(libraryRepository.findAllByUsername("Lavanya12")).thenReturn(List.of(library));
		Mockito.when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		assertEquals(libraryResponseDto, libraryService.getBooksByUser("Lavanya12"));
		Mockito.verify(userFeignClient).getUser("Lavanya12");
		Mockito.verify(libraryRepository).findAllByUsername("Lavanya12");
		Mockito.verify(bookFeignClient).getBook(1);
	}

	@Test
	void testIssueBookToUserSuccess() {
		Mockito.when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		Mockito.when(userFeignClient.getUser("Lavanya12")).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(libraryRepository.countByUsername("Lavanya12")).thenReturn(1L);
		Mockito.when(libraryRepository.save(library)).thenReturn(library);
		assertEquals(libraryResponseDto, libraryService.lendBookToUser("Lavanya12", 1));
		Mockito.verify(userFeignClient).getUser("Lavanya12");
		Mockito.verify(bookFeignClient).getBook(1);
		Mockito.verify(libraryRepository).countByUsername("Lavanya12");
		Mockito.verify(libraryRepository).save(library);

	}

	@Test
	void testIssueBookToUserFail() {
		Mockito.when(userFeignClient.getUser("Lavanya12")).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(bookFeignClient.getBook(1)).thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		Mockito.when(libraryRepository.countByUsername("Lavanya12")).thenReturn(5L);
		assertThrows(LibraryException.class, () -> libraryService.lendBookToUser("Lavanya12", 1));
		Mockito.verify(userFeignClient).getUser("Lavanya12");
		Mockito.verify(bookFeignClient).getBook(1);
		Mockito.verify(libraryRepository).countByUsername("Lavanya12");
	}

	@Test
	void testIssueBookToUserFailCaseTwo() {
		ResponseEntity<UserResponseDto> userEntity = ResponseEntity.status(HttpStatus.OK).body(userDto);
		ResponseEntity<BookResponseDto> bookEntity = ResponseEntity.status(HttpStatus.OK).body(bookDto);
		userDto.setHttpStatus(HttpStatus.NO_CONTENT);
		bookDto.setHttpStatus(HttpStatus.NO_CONTENT);
		Mockito.when(userFeignClient.getUser("Lavanya12")).thenReturn(userEntity);
		Mockito.when(bookFeignClient.getBook(1)).thenReturn(bookEntity);
		assertThrows(LibraryException.class, () -> libraryService.lendBookToUser("Lavanya12", 1));
	}

	@Test
	void testReturnBookToLibrary() {
		Mockito.doNothing().when(libraryRepository).deleteByUsernameAndBookId("Lavanya12", 1);
		libraryService.returnBookToLibrary("Lavanya12", 1);
		Mockito.verify(libraryRepository).deleteByUsernameAndBookId("Lavanya12", 1);
	}
}