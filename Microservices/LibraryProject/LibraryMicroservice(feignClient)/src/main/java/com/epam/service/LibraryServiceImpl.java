package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epam.dto.LibraryDto;
import com.epam.dto.response.BookResponseDto;
import com.epam.dto.response.UserResponseDto;
import com.epam.entity.Library;
import com.epam.exceptions.LibraryException;
import com.epam.repository.LibraryRepository;
import com.epam.restcontroller.BookFeignClient;
import com.epam.restcontroller.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	LibraryRepository libraryRepository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	BookFeignClient bookFeignClient;
	@Autowired
	UserFeignClient userFeignClient;
	@Value("${booksUrl}")
	String booksUrl;
	@Value("${usersUrl}")
	String usersUrl;

	public LibraryDto lendBookToUser(String username, int bookId) {
		log.info("Entered into lendBookToUser {} {}", username, bookId);
		BookResponseDto bookDto = bookFeignClient.getBook(bookId).getBody();
		UserResponseDto userDto = userFeignClient.getUser(username).getBody();
		if(bookDto.getHttpStatus()==HttpStatus.NO_CONTENT || userDto.getHttpStatus()==HttpStatus.NO_CONTENT)
		{
			throw new LibraryException("User with username doesnt exist / Book with id doesnt exist");
		}
		log.info("Checking the  number of books associated with user {} ", username);
		if (libraryRepository.countByUsername(username) >= 3) {
			throw new LibraryException("User can take atmost 3 books");
		}
		libraryRepository.save(Library.builder().bookId(bookId).username(username).build());
		log.info("Book with id {} issued successfully to the user {}", bookId, username);
		return LibraryDto.builder().userDto(userDto).bookdtos(List.of(bookDto)).build();
	}

	public void returnBookToLibrary(String username, int bookId) {
		log.info("Eneterd into returnBookToLibrary {} {}", username, bookId);
		libraryRepository.deleteByUsernameAndBookId(username, bookId);
	}

	public LibraryDto getBooksByUser(String username) {
		log.info("Entered into getBooksByUser with username {} ", username);
		UserResponseDto userDto = userFeignClient.getUser(username).getBody();
		List<Integer> bookIds = libraryRepository.findAllByUsername(username).stream().map(Library::getBookId).toList();
		List<BookResponseDto> bookDtoList = bookIds.stream().map(id -> bookFeignClient.getBook(id).getBody()).toList();
		log.info("Books {} retrived successfully of user {} ", bookDtoList, userDto);
		return LibraryDto.builder().userDto(userDto).bookdtos(bookDtoList).build();
	}

	public void deleteBookFromLibrary(int bookId) {
		log.info("Entered into deleteBookFromLibrary of id {} ", bookId);
		log.info("Deleting book from library of id {} ", bookId);
		libraryRepository.deleteByBookId(bookId);
		log.info("Deleteing book from book database of id {} ", bookId);
		bookFeignClient.deleteBookById(bookId);
	}

	public void deleteUserFromLibrary(String userName) {
		log.info("Entered into deleteUserFromLibrary of username {} ", userName);
		log.info("Deleting user from library of username {} ", userName);
		libraryRepository.deleteByUsername(userName);
		log.info("Deleting user from user database of username {} ", userName);
		userFeignClient.deleteBookByUsername(userName);
	}
}