package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Integer>{
	public boolean existsByTitle(String title);
}
