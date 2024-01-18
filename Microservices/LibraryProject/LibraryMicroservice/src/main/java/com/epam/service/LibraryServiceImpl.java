package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.entity.Library;
import com.epam.exceptions.LibraryException;
import com.epam.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	LibraryRepository libraryRepository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	RestTemplate restTemplate;
	@Value("${booksUrl}")
	String booksUrl;
	@Value("${usersUrl}")
	String usersUrl;
	public LibraryDto lendBookToUser(String username, int bookId) {
		BookDto bookDto = restTemplate.exchange(booksUrl +"/"+ bookId, HttpMethod.GET, null, BookDto.class).getBody();
		UserDto userDto = restTemplate.exchange(usersUrl +"/"+ username, HttpMethod.GET, null, UserDto.class).getBody();
		if (libraryRepository.countByUsername(username) >= 3) {
			throw new LibraryException("User can take atmost 3 books");
		}
		if (libraryRepository.existsByUsernameAndBookId(username, bookId)) {
			throw new LibraryException("User has taken that book already");
		}
		libraryRepository.save(Library.builder().bookId(bookId).username(username).build());
		return LibraryDto.builder().userDto(userDto).bookdtos(List.of(bookDto)).build();
	}

	public void returnBookToLibrary(String username, int bookId) {
		libraryRepository.deleteByUsernameAndBookId(username, bookId);
	}

	public LibraryDto getBooksByUser(String username) {
		UserDto userDto = restTemplate.exchange(usersUrl +"/"+ username, HttpMethod.GET, null, UserDto.class).getBody();
		List<Integer> bookIds = libraryRepository.findAllByUsername(username).stream().map(Library::getBookId).toList();
		List<BookDto> bookDtoList = bookIds.stream().map(id -> restTemplate.exchange(booksUrl +"/"+ id, HttpMethod.GET, null, BookDto.class).getBody()).toList();
		return LibraryDto.builder().userDto(userDto).bookdtos(bookDtoList).build();
	}

	public void deleteBookFromLibrary(int bookId) {
		libraryRepository.deleteByBookId(bookId);
		restTemplate.delete(booksUrl +"/"+ bookId);
	}

	public void deleteUserFromLibrary(String userName) {
		libraryRepository.deleteByUsername(userName);
		restTemplate.delete(usersUrl +"/"+ userName);
	}
}