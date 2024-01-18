package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Question;

public interface QuestionRepository extends CrudRepository<Question,Integer>{
	public Optional<Question> findByTitle(String title);

}
