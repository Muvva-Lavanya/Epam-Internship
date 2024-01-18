package ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuizLibraryOptions {
	private static final Map<Integer, DefaultMenu> QuizLibraryOptionType;

	static {
		final Map<Integer, DefaultMenu> quizLibraryOptionType = new HashMap<>();
		final Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
		QuizLibraryOperations quizLibraryOperations = new QuizLibraryOperations();
		Scanner sc = new Scanner(System.in);
		quizLibraryOptionType.put(1, new DefaultMenu() {
			@Override
			public void selectType() {
				quizLibraryOperations.create();
			}
		});
		quizLibraryOptionType.put(2, new DefaultMenu() {
			@Override
			public void selectType() {
				quizLibraryOperations.view();
			}
		});
		quizLibraryOptionType.put(3, new DefaultMenu() {
			@Override
			public void selectType() {
				quizLibraryOperations.delete();
			}
		});
		quizLibraryOptionType.put(4, new DefaultMenu() {
			@Override
			public void selectType() {
				quizLibraryOperations.update();
			}
		});
		quizLibraryOptionType.put(5, new DefaultMenu() {
			@Override
			public void selectType() {
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.operations();
			}
		});
		QuizLibraryOptionType = Collections.unmodifiableMap(quizLibraryOptionType);
	}

	public void createQuizRole(int option) {
		DefaultMenu command = QuizLibraryOptionType.get(option);
		if (command == null) {
			System.out.println("Enter proper option");
		}
		try {
			command.selectType();
		} catch (NullPointerException e) {
			System.out.println(e);
		}
	}
}
