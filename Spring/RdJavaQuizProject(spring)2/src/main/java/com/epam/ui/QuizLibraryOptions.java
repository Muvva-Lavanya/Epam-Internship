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
public class QuizLibraryOptions {
	static QuizLibraryOperations quizLibraryOperations;
	static AdminMenu adminMenu;
	@Autowired
	public void setQuizLibraryOperations(QuizLibraryOperations quizLibraryOperations)
	{
		QuizLibraryOptions.quizLibraryOperations=quizLibraryOperations;
	}
	@Autowired
	public void setAdminMenu(AdminMenu adminMenu)
	{
		QuizLibraryOptions.adminMenu=adminMenu;
	}
	private static final Map<Integer, DefaultMenu> QuizLibraryOptionType;
	final Logger logger = LogManager.getLogger(QuizLibraryOptions.class);
	static {
		final Map<Integer, DefaultMenu> quizLibraryOptionType = new HashMap<>();
	
		
		quizLibraryOptionType.put(1,()-> quizLibraryOperations.create());
		quizLibraryOptionType.put(2,()->quizLibraryOperations.view());
		quizLibraryOptionType.put(3,()->quizLibraryOperations.delete());
		quizLibraryOptionType.put(4,()->quizLibraryOperations.update());
		quizLibraryOptionType.put(5,()->quizLibraryOperations.viewQuizByTitle());
		quizLibraryOptionType.put(6,()->adminMenu.operations());
		QuizLibraryOptionType = Collections.unmodifiableMap(quizLibraryOptionType);
	}
	public void createQuizRole(int option) {
		DefaultMenu command = QuizLibraryOptionType.get(option);
		try {
			if (command == null)  {
				throw new InputMismatchException("Enter proper option");
			}
			else {
				command.selectType();
			}
			}
			catch(InputMismatchException e)
			{
				logger.info(e);
			}
	}
}
