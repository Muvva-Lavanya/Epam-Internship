package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer>{
	public boolean existsByTitle(String title);

}
