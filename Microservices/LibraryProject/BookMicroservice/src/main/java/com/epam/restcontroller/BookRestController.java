package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.service.BookServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("books")
public class BookRestController {
	@Autowired
	BookServiceImpl bookServiceImpl;

	@GetMapping
	public ResponseEntity<List<BookResponseDto>> getAllBooks() {
		log.info("Received get request to view all Books");
		return new ResponseEntity<>(bookServiceImpl.getAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookResponseDto> getQuestionById(@PathVariable int id) {
		log.info("Received get request to view book by id");
		return new ResponseEntity<>(bookServiceImpl.getBookById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BookResponseDto> createQuestion(@Valid @RequestBody BookRequestDto bookDto) {
		log.info("Received post request to create book");
		return new ResponseEntity<>(bookServiceImpl.addBook(bookDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws DataIntegrityViolationException {
		log.info("Received delete request to delete book");
		bookServiceImpl.deleteBook(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookResponseDto> update(@PathVariable Integer id,@Valid @RequestBody BookRequestDto bookDto) {
		log.info("Received put request to update Book");
		return new ResponseEntity<>(bookServiceImpl.updateBook(bookDto,id),HttpStatus.OK);
	}

}
