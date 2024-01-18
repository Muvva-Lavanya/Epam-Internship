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
public class AdminOption {
	static QuestionLibraryUI questionLibraryUI;
	static QuizLibraryUI quizLibraryUI;
	static Main main;
	@Autowired
	public void setQuestionLibraryUI(QuestionLibraryUI questionLibraryUI)
	{
		AdminOption.questionLibraryUI=questionLibraryUI;
	}
	@Autowired
	public void setQuizLibraryUI(QuizLibraryUI quizLibraryUI)
	{
		AdminOption.quizLibraryUI=quizLibraryUI;
	}
	@Autowired
	public void setMain(Main main)
	{
		AdminOption.main=main;
	}
	private static final Map<Integer, DefaultMenu> AdminOptionType;
	Logger logger=LogManager.getLogger(AdminOption.class);
	static {
		final Map<Integer,DefaultMenu> adminOptionType = new HashMap<>();
		adminOptionType.put(1,()->questionLibraryUI.operations());
		adminOptionType.put(2,()->quizLibraryUI.operations());
		adminOptionType.put(3,()->main.startApplication());
		AdminOptionType = Collections.unmodifiableMap(adminOptionType);
	}
	
	public void createAdminRole(int type){
		DefaultMenu command = AdminOptionType.get(type);
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
