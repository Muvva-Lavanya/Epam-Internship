package com.epam.service1;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.Quiz;

@Component
public class UserService {
	@Autowired
	private QuizLibraryServiceImpl quizLibraryServiceImpl;

	public int countMarks(int id, List<String> answers) {
		Quiz quiz = quizLibraryServiceImpl.viewQuizById(id);
		List<String> actualAnswers = quiz.getQuestion().stream().map(e -> e.getAnswer()).toList();
		long count = IntStream.range(0, actualAnswers.size()).filter(i -> (answers.get(i).equals(actualAnswers.get(i))))
				.count();
		return (int) (count * quiz.getMarks());
	}
}
