package com.epam.service;

import java.util.List;

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;

public interface BookService {
	BookResponseDto addBook(BookRequestDto bookDto);
	BookResponseDto updateBook(BookRequestDto bookDto,Integer id);
	void deleteBook(Integer id);
	BookResponseDto getBookById(Integer id);
	List<BookResponseDto> getAllBooks();

}
