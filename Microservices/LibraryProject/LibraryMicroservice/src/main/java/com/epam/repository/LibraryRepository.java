package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.entity.Library;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {
	long countByUsername(String username);
	boolean existsByUsernameAndBookId(String username, int bookId);
	void deleteByUsernameAndBookId(String username, int bookId);
	List<Library> findAllByUsername(String username);
	void deleteByBookId(int bookId);
	void deleteByUsername(String username);
}