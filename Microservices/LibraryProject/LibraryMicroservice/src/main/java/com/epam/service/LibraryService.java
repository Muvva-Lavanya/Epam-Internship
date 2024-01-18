package com.epam.service;

import com.epam.dto.LibraryDto;

public interface LibraryService {
	
	 LibraryDto lendBookToUser(String username, int bookId);
	 void returnBookToLibrary(String username, int bookId);
	 LibraryDto getBooksByUser(String username);
	 void deleteBookFromLibrary(int bookId);
	 void deleteUserFromLibrary(String userName);

}
