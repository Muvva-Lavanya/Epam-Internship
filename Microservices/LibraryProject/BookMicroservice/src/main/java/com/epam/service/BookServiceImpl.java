package com.epam.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dto.request.BookRequestDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.entity.Book;
import com.epam.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public BookResponseDto addBook(BookRequestDto bookDto) {
		log.info("Entered into Book : addBook method");
		Book book = modelMapper.map(bookDto, Book.class);
		book = bookRepository.save(book);
		log.info("Book {} created successfully", book);
		return BookResponseDto.builder().name(book.getName()).author(book.getAuthor()).publisher(book.getPublisher()).build();
	}

	@Override
	@Transactional
	public BookResponseDto updateBook(BookRequestDto bookDto, Integer id) {
		log.info("Entered into Book : updateBook method");
		return bookRepository.findById(id).map(book -> {
			book.setAuthor(bookDto.getAuthor());
			book.setName(bookDto.getName());
			book.setPublisher(bookDto.getPublisher());
			log.info("Book {} updated suceessfully",book);
			return BookResponseDto.builder().name(book.getName()).author(book.getAuthor()).publisher(book.getPublisher()).build();
		}).orElseGet(() -> {
			log.info("Book with id {} doesnt exists", id);
			return BookResponseDto.builder().timestamp(new Date().toString())
					.developerMessage("Book with id " + id + " doesnt exists. Try with different Id")
					.httpStatus(HttpStatus.NO_CONTENT).build();
		}); 

	}
	

	@Override
	public void deleteBook(Integer id) {
		log.info("Entered into Book : deleteBook method");
		bookRepository.deleteById(id);
	}

	@Override
	public BookResponseDto getBookById(Integer id) {
		log.info("Entered into Book : getBookById method");
		return bookRepository.findById(id).map(book -> {
			log.info("Book {} retrived successfully", book);
			return BookResponseDto.builder().name(book.getName()).author(book.getAuthor()).publisher(book.getPublisher())
					.build();
		}).orElseGet(() -> {
			log.info("Book with id {} doesnt exists", id);
			return BookResponseDto.builder().timestamp(new Date().toString())
					.developerMessage("Book with id " + id + " doesnt exists. Try with different Id")
					.httpStatus(HttpStatus.NO_CONTENT).build();
		});
	}

	@Override
	public List<BookResponseDto> getAllBooks() {
		log.info("Entered into Book : getAllBooks method");
		return bookRepository.findAll().stream().map(book->(BookResponseDto.builder().name(book.getName()).author(book.getAuthor()).publisher(book.getPublisher())
					.build())).toList(); 

	}

}
