package com.epam.ui;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UpdateQuestionOptions {
	@Autowired
	UpdateQuestionOperations updateQuestionOperations;
	@Autowired
	QuestionLibraryUI questionLibraryUI;
	private Map<Integer, UpdateSelection> updateQuestionOptionType;
	Logger logger = LogManager.getLogger(UpdateQuestionOptions.class);

	@PostConstruct
	public void questionLibraryOptions() {
		updateQuestionOptionType = new HashMap<>();
		updateQuestionOptionType.put(1, questionId -> updateQuestionOperations.updateTitle(questionId));
		updateQuestionOptionType.put(2, questionId -> updateQuestionOperations.updateOption(questionId));
		updateQuestionOptionType.put(3, questionId -> updateQuestionOperations.updateDifficultyLevel(questionId));
		updateQuestionOptionType.put(4, questionId -> updateQuestionOperations.updateTag(questionId));
		updateQuestionOptionType.put(5, questionId -> updateQuestionOperations.updateAnswer(questionId));
		updateQuestionOptionType.put(6, questionId -> questionLibraryUI.operations());
	}

	public void selectUserOption(int option, int questionId) {
		if (updateQuestionOptionType.containsKey(option)) {
			updateQuestionOptionType.get(option).selectOption(questionId);
		} else {
			logger.info("Enter proper option");
		}

	}
}
