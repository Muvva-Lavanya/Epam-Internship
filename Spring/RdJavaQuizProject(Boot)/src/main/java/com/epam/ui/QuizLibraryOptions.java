package com.epam.ui;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class QuizLibraryOptions {
	Logger logger = LogManager.getLogger(QuizLibraryOptions.class);
	@Autowired
	QuizLibraryOperations quizLibraryOperations;
	@Autowired
	AdminMenu adminMenu;
	private Map<Integer, OptionSelection> quizLibraryOptionType;
	@PostConstruct
	public void quizLibraryOptions() {
		quizLibraryOptionType = new HashMap<>();
		quizLibraryOptionType.put(1, () -> quizLibraryOperations.create());
		quizLibraryOptionType.put(2, () -> quizLibraryOperations.view());
		quizLibraryOptionType.put(3, () -> quizLibraryOperations.delete());
		quizLibraryOptionType.put(4, () -> quizLibraryOperations.update());
		quizLibraryOptionType.put(5, () -> quizLibraryOperations.viewQuizById());
		quizLibraryOptionType.put(6, () -> adminMenu.operations());
	}
 
	public void selectUserOption(int option) {
		if (quizLibraryOptionType.containsKey(option)) {
			quizLibraryOptionType.get(option).selectType();
		} else {
			logger.info("Enter proper option");
		}
	}
}
