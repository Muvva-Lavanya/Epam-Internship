package com.epam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.customexception.QuizException;
import com.epam.entity.Quiz;
import com.epam.repository.QuizRepository;

@Component
public class UserService {
	@Autowired
	private QuizRepository quizRepository;

	public long countMarks(int id, List<String> answers) throws QuizException{
		Optional<Quiz> quiz = quizRepository.findById(id); 
		return quiz.map(q->{
			List<String> actualAnswers = q.getQuestion().stream().map(e -> e.getAnswer()).toList(); 
			return IntStream.range(0, actualAnswers.size()).filter(i -> (answers.get(i).equals(actualAnswers.get(i))))
					.count()*q.getMarks(); 
		}).orElseThrow(()->new QuizException("Quiz with id doesnt exist. Please try with proper id"));

	}
}
