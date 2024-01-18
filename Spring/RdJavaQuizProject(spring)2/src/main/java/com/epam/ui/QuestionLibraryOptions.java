package com.epam.ui;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class QuestionLibraryOptions {
	static QuestionLibraryOperations questionLibraryOperations;
	static AdminMenu adminMenu;
	Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
	@Autowired
	public void setQuestionLibraryOperations(QuestionLibraryOperations questionLibraryOperations)
	{
		QuestionLibraryOptions.questionLibraryOperations=questionLibraryOperations;
	}
	@Autowired
	public void setAdminMenu(AdminMenu adminMenu)
	{
		QuestionLibraryOptions.adminMenu=adminMenu;
	}
	private static final Map<Integer, DefaultMenu> QuestionLibraryOptionType;
	static {
		final Map<Integer, DefaultMenu> questionLibraryOptionType = new HashMap<>();
		questionLibraryOptionType.put(1,()-> questionLibraryOperations.create());
		questionLibraryOptionType.put(2, ()->questionLibraryOperations.view()); 
		questionLibraryOptionType.put(3,()->questionLibraryOperations.update());
		questionLibraryOptionType.put(4,()->questionLibraryOperations.delete());
		questionLibraryOptionType.put(5,()->adminMenu.operations());
		QuestionLibraryOptionType = Collections.unmodifiableMap(questionLibraryOptionType);
	}
	public void createQuestionRole(int option) {
		DefaultMenu command = QuestionLibraryOptionType.get(option);
		try {
			if (command == null) {
				throw new InputMismatchException("Enter proper option");
			} else {
				command.selectType(); 
			}
		} catch (InputMismatchException e) {
			logger.info(e);
		}
	}

}
