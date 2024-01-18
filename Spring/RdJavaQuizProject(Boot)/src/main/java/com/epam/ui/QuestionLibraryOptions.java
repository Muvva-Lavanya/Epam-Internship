package com.epam.ui;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
@Component
public class QuestionLibraryOptions {
		@Autowired
		QuestionLibraryOperations questionLibraryOperations;
		@Autowired
		AdminMenu adminMenu;
		private Map<Integer, OptionSelection> questionLibraryOptionType;
		Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
		@PostConstruct
		public void questionLibraryOptions() {
			questionLibraryOptionType=new HashMap<>();
			questionLibraryOptionType.put(1,()-> questionLibraryOperations.create()); 
			questionLibraryOptionType.put(2, ()->questionLibraryOperations.view()); 
			questionLibraryOptionType.put(3,()->questionLibraryOperations.update());
			questionLibraryOptionType.put(4,()->questionLibraryOperations.delete()); 
			questionLibraryOptionType.put(5,()->adminMenu.operations());
		}
	public void selectUserOption(int option) {
		if (questionLibraryOptionType.containsKey(option)) {
			questionLibraryOptionType.get(option).selectType();
		} else {
			logger.info("Enter proper option");
		}
	}

}
