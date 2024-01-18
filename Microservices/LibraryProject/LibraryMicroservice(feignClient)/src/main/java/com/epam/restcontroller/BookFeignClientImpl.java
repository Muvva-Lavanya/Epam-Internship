package com.epam.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;

import jakarta.validation.Valid;
@Service
public class BookFeignClientImpl implements BookFeignClient {
	BookResponseDto bookResponseDto;
	@Override
	public ResponseEntity<List<BookResponseDto>> getAllBooks() {
		bookResponseDto=BookResponseDto.builder().author("author").name("name").publisher("publisher").build();
		return ResponseEntity.ok(List.of(bookResponseDto));
	}

	@Override
	public ResponseEntity<BookResponseDto> getBook(int bookId) {
		bookResponseDto=BookResponseDto.builder().author("Author").name("Name").publisher("Publisher").build();
		return ResponseEntity.ok(bookResponseDto);
	}

	@Override
	public ResponseEntity<BookResponseDto> addBook(@Valid BookRequestDto bookDto) {
		bookResponseDto=BookResponseDto.builder().author("author").name("name").publisher("publisher").build();
		return ResponseEntity.ok(bookResponseDto);
	}

	@Override
	public ResponseEntity<BookResponseDto> updateBook(int bookId, @Valid BookRequestDto bookDto) {
		bookResponseDto=BookResponseDto.builder().author("Author").name("Name").publisher("Publisher").build();
		return ResponseEntity.ok(bookResponseDto);
	}

	@Override
	public void deleteBookById(int bookId) {
		//delete book by id
	}

}
