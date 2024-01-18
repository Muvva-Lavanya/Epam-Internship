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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.entity.Library;
import com.epam.exceptions.LibraryException;
import com.epam.repository.LibraryRepository;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {
	@Mock
	RestTemplate restTemplate;
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
	BookDto bookDto;
	UserDto userDto;
	Library library;
	LibraryDto libraryResponseDto;

	@BeforeEach
	void setup() {
		bookDto = new BookDto("Ikigai", "Lavanya", "abcPublishers",5);
		userDto = new UserDto();
		userDto.setUsername("Lavanya12");
		userDto.setName("LavanyaMuvva");
		userDto.setEmail("lavanyamuvva.com");
		userDto.setPassword("Lavanya@123");
		library = new Library(0, "Lavanya12", 1);
		libraryResponseDto = LibraryDto.builder().userDto(userDto).bookdtos(List.of(bookDto)).build();
	}

	@Test
	void testDeleteBook() {
		Mockito.doNothing().when(libraryRepository).deleteByBookId(1);
		Mockito.doNothing().when(restTemplate).delete(booksUrl +"/"+1);
		libraryService.deleteBookFromLibrary(1);
		Mockito.verify(libraryRepository).deleteByBookId(1);
	}

	@Test
	void testDeleteUser() {
		Mockito.doNothing().when(libraryRepository).deleteByUsername("Lavanya12");
		Mockito.doNothing().when(restTemplate).delete(usersUrl+"/"+"Lavanya12");
		libraryService.deleteUserFromLibrary("Lavanya12");
		Mockito.verify(libraryRepository).deleteByUsername("Lavanya12");
	}

	@Test
	void testGetUser() {
		Mockito.when(
				restTemplate.exchange(usersUrl+"/"+"Lavanya12", HttpMethod.GET, null, UserDto.class))
				.thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(libraryRepository.findAllByUsername("Lavanya12")).thenReturn(List.of(library));
		Mockito.when(restTemplate.exchange(booksUrl +"/"+ 1, HttpMethod.GET, null, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		assertEquals(libraryResponseDto, libraryService.getBooksByUser("Lavanya12"));
		Mockito.verify(restTemplate).exchange(usersUrl+"/"+"Lavanya12", HttpMethod.GET, null, UserDto.class);
		Mockito.verify(libraryRepository).findAllByUsername("Lavanya12");
		Mockito.verify(restTemplate).exchange(booksUrl +"/"+ 1, HttpMethod.GET, null, BookDto.class);
	}

	@Test
	void testIssueBookToUserSuccess() {
		Mockito.when(restTemplate.exchange(booksUrl + "/" + 1, HttpMethod.GET, null, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		Mockito.when(restTemplate.exchange(usersUrl + "/" + "Lavanya12", HttpMethod.GET, null, UserDto.class))
		.thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(libraryRepository.countByUsername("Lavanya12")).thenReturn(1L);
		Mockito.when(libraryRepository.existsByUsernameAndBookId("Lavanya12", 1)).thenReturn(false);
		Mockito.when(libraryRepository.save(library)).thenReturn(library);
		assertEquals(libraryResponseDto, libraryService.lendBookToUser("Lavanya12", 1));
		Mockito.verify(restTemplate).exchange(booksUrl +"/"+ 1, HttpMethod.GET, null, BookDto.class);
		Mockito.verify(restTemplate).exchange(usersUrl+"/"+"Lavanya12", HttpMethod.GET, null, UserDto.class);
		Mockito.verify(libraryRepository).countByUsername("Lavanya12");
		Mockito.verify(libraryRepository).existsByUsernameAndBookId("Lavanya12", 1);
		Mockito.verify(libraryRepository).save(library);
		
	}

	@Test
	void testIssueBookToUserFail() {
		Mockito.when(restTemplate.exchange(usersUrl + "/" + "Lavanya12", HttpMethod.GET, null, UserDto.class))
				.thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(restTemplate.exchange(booksUrl + "/" + 1, HttpMethod.GET, null, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		Mockito.when(libraryRepository.countByUsername("Lavanya12")).thenReturn(5L);
		assertThrows(LibraryException.class, () -> libraryService.lendBookToUser("Lavanya12", 1));
		Mockito.verify(restTemplate).exchange(usersUrl+"/"+"Lavanya12", HttpMethod.GET, null, UserDto.class);
		Mockito.verify(restTemplate).exchange(booksUrl +"/"+ 1, HttpMethod.GET, null, BookDto.class);
		Mockito.verify(libraryRepository).countByUsername("Lavanya12");
	}

	@Test
	void testIssueBookToUserFailCaseTwo() {
		Mockito.when(restTemplate.exchange(usersUrl + "/" + "Lavanya12", HttpMethod.GET, null, UserDto.class))
				.thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));
		Mockito.when(restTemplate.exchange(booksUrl + "/" + 1, HttpMethod.GET, null, BookDto.class))
				.thenReturn(new ResponseEntity<>(bookDto, HttpStatus.OK));
		Mockito.when(libraryRepository.existsByUsernameAndBookId("Lavanya12", 1)).thenReturn(true);
		assertThrows(LibraryException.class, () -> libraryService.lendBookToUser("Lavanya12", 1));
		Mockito.verify(restTemplate).exchange(usersUrl+"/"+"Lavanya12", HttpMethod.GET, null, UserDto.class);
		Mockito.verify(restTemplate).exchange(booksUrl +"/"+ 1, HttpMethod.GET, null, BookDto.class);
		Mockito.verify(libraryRepository).existsByUsernameAndBookId("Lavanya12",1);
	}

	@Test
	void testReturnBookToLibrary() {
		Mockito.doNothing().when(libraryRepository).deleteByUsernameAndBookId("Lavanya12", 1);
		libraryService.returnBookToLibrary("Lavanya12", 1);
		Mockito.verify(libraryRepository).deleteByUsernameAndBookId("Lavanya12", 1);
	}
}