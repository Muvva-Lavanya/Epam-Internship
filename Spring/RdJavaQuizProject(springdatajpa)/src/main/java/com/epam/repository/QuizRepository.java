package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz,Integer>{
	public Optional<Quiz> findByTitle(String title);
}
