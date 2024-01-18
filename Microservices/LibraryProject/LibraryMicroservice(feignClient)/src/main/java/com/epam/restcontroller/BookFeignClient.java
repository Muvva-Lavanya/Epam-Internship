package com.epam.restcontroller;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;

import jakarta.validation.Valid;

@FeignClient(name = "books",fallback = BookFeignClientImpl.class)
@LoadBalancerClient(name="books",configuration = BookFeignClientImpl.class)
public interface BookFeignClient {
	
	@GetMapping("books")
	public ResponseEntity<List<BookResponseDto>> getAllBooks();
	
	@GetMapping("books/{bookId}")
	public ResponseEntity<BookResponseDto> getBook(@PathVariable int bookId);
	
	@PostMapping("books")
	public ResponseEntity<BookResponseDto> addBook(@RequestBody @Valid BookRequestDto bookDto);
	
	@PutMapping("books/{bookId}")
	public ResponseEntity<BookResponseDto> updateBook(@PathVariable int bookId, @RequestBody @Valid BookRequestDto bookDto);
		
	@DeleteMapping("books/{bookId}")
	public void deleteBookById(@PathVariable int bookId); 

}
