package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("library")
public class LibraryRestController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LibraryService libraryService;
	@Value("${booksUrl}")
	private String booksUrl;
	@Value("${usersUrl}")
	private String usersUrl;

	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		ParameterizedTypeReference<List<BookDto>> reference = new ParameterizedTypeReference<List<BookDto>>() {
		};
		return new ResponseEntity<>(restTemplate.exchange(booksUrl, HttpMethod.GET, null, reference).getBody(), HttpStatus.OK);
	}

	@GetMapping("/books/{bookId}")
	public ResponseEntity<BookDto> getBook(@PathVariable int bookId) {
		return new ResponseEntity<>(restTemplate.exchange(booksUrl + "/" + bookId, HttpMethod.GET, null,BookDto.class).getBody(), HttpStatus.OK);
	}

	@PostMapping("/books")
	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
		HttpEntity<BookDto> requestEntity = new HttpEntity<>(bookDto);
		return new ResponseEntity<>(restTemplate.exchange(booksUrl, HttpMethod.POST, requestEntity,BookDto.class).getBody(), HttpStatus.CREATED);
	}

	@PutMapping("/books/{bookId}")
	public ResponseEntity<BookDto> updateBook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto) {
		HttpEntity<BookDto> requestEntity = new HttpEntity<>(bookDto);
		return new ResponseEntity<>(restTemplate.exchange(booksUrl + "/" + bookId, HttpMethod.PUT, requestEntity,BookDto.class).getBody(), HttpStatus.OK);
	}

	@DeleteMapping("/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBookById(@PathVariable int bookId) {
		libraryService.deleteBookFromLibrary(bookId);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		ParameterizedTypeReference<List<UserDto>> reference = new ParameterizedTypeReference<List<UserDto>>() {
		};
		return new ResponseEntity<>(restTemplate.exchange(usersUrl, HttpMethod.GET, null, reference).getBody(), HttpStatus.OK);
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<LibraryDto> getUser(@PathVariable String username) {
		return new ResponseEntity<>(libraryService.getBooksByUser(username), HttpStatus.OK);
	}

	@PostMapping("users")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
		HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto);
		return new ResponseEntity<>(restTemplate.exchange(usersUrl, HttpMethod.POST, requestEntity,UserDto.class).getBody(), HttpStatus.CREATED);
	}

	@PutMapping("users/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody @Valid UserDto userDto) {
		HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto);
		return new ResponseEntity<>(restTemplate.exchange(usersUrl + "/" + username, HttpMethod.PUT,requestEntity, UserDto.class).getBody(), HttpStatus.OK);
	}

	@DeleteMapping("/users/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String username) {
		libraryService.deleteUserFromLibrary(username);
	}

	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<LibraryDto> issueBookToUser(@PathVariable int bookId, @PathVariable String username) {
		return new ResponseEntity<>(libraryService.lendBookToUser(username, bookId), HttpStatus.OK);
	}

	@DeleteMapping("/users/{username}/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void returnBookToLibrary(@PathVariable int bookId, @PathVariable String username) {
		libraryService.returnBookToLibrary(username, bookId);
	}
}