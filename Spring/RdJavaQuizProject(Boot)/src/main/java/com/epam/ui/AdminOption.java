package com.epam.ui;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AdminOption {
	@Autowired
	QuestionLibraryUI questionLibraryUI;
	@Autowired
	QuizLibraryUI quizLibraryUI;
	@Autowired
	HomePage main;
	Logger logger = LogManager.getLogger(AdminOption.class);
	private Map<Integer, OptionSelection> adminOptionType;
	@PostConstruct
	public void adminOption() { 
		adminOptionType = new HashMap<>();
		adminOptionType.put(1, () -> questionLibraryUI.operations());
		adminOptionType.put(2, () -> quizLibraryUI.operations());
		adminOptionType.put(3, () -> main.startApplication());
	}

	public void selectUserOption(int type) {
		if (adminOptionType.containsKey(type)) {
			adminOptionType.get(type).selectType();
		} else {
			logger.info("Enter proper option");
		}
	}

}
