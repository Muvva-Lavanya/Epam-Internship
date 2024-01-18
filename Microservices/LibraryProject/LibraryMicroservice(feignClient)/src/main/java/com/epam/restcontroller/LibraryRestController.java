package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.epam.dto.LibraryDto;
import com.epam.dto.request.BookRequestDto;
import com.epam.dto.request.UserRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.service.LibraryServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("library")
@Slf4j
public class LibraryRestController {
	@Autowired
	BookFeignClient bookFeignClient;
	
	@Autowired
	UserFeignClient userFeignClient;
	
	@Autowired
	LibraryServiceImpl libraryServiceImpl;

	@GetMapping("/books")
	public ResponseEntity<List<BookResponseDto>> getAllBooks() {
		log.info("Entered into library/books: getAllBooks");
		return bookFeignClient.getAllBooks();
	}
	
	@GetMapping("/books/{bookId}")
	public ResponseEntity<BookResponseDto> getBook(@PathVariable int bookId) {
		log.info("Entered into library/books/bookId: getBook");
		return bookFeignClient.getBook(bookId);
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookResponseDto> addBook(@RequestBody @Valid BookRequestDto bookDto) {
		log.info("Entered into library/books: addBook");
		return bookFeignClient.addBook(bookDto); 
	}
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<BookResponseDto> updateBook(@PathVariable int bookId, @RequestBody @Valid BookRequestDto bookDto) {
		log.info("Entered into library/books: updateBook");
		return bookFeignClient.updateBook(bookId, bookDto);
	} 
	
	@DeleteMapping("/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBookById(@PathVariable int bookId) {
		log.info("Entered into library/books: deleteBookByID");
		libraryServiceImpl.deleteBookFromLibrary(bookId);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		log.info("Entered into library/users: getAllUsers");
		return userFeignClient.getAllUsers();
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<LibraryDto> getUser(@PathVariable String username) {
		log.info("Entered into library/users/username: getUser");
		return new ResponseEntity<>(libraryServiceImpl.getBooksByUser(username),HttpStatus.OK);
	}

	@PostMapping("users")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userDto) {
		log.info("Entered into library/users: createUser");
		return userFeignClient.addUser(userDto);
	}

	@PutMapping("users/{username}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable String username, @RequestBody @Valid UserRequestDto userDto) {
		log.info("Entered into library/users: updateUser");
		return userFeignClient.updateUser(username, userDto);
	}

	@DeleteMapping("/users/{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String username) {
		log.info("Entered into library/users: deleteUser");
		libraryServiceImpl.deleteUserFromLibrary(username);
	}
	
	@PostMapping("/users/{username}/books/{bookId}")
	public ResponseEntity<LibraryDto> issueBookToUser(@PathVariable int bookId, @PathVariable String username) {
		log.info("Entered into library/users/books: issueBookToUser");
		return new ResponseEntity<>(libraryServiceImpl.lendBookToUser(username, bookId), HttpStatus.OK);
	}

	@DeleteMapping("/users/{username}/books/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void returnBookToLibrary(@PathVariable int bookId, @PathVariable String username) {
		log.info("Entered into library/users/books: returnBookToLibrary");
		libraryServiceImpl.returnBookToLibrary(username, bookId);
	}
	
	

	
}