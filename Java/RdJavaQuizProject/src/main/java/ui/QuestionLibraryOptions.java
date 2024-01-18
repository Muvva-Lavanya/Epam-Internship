package ui;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuestionLibraryOptions {
	private static final Map<Integer, DefaultMenu> QuestionLibraryOptionType;
	static {
		final Map<Integer, DefaultMenu> questionLibraryOptionType = new HashMap<>();
		final Logger logger = LogManager.getLogger(QuestionLibraryOptions.class);
		QuestionLibraryOperations questionLibraryOperations=new QuestionLibraryOperations();
		questionLibraryOptionType.put(1, new DefaultMenu() {
			@Override
			public void selectType() {
				questionLibraryOperations.create();
			}
		});
		questionLibraryOptionType.put(2, new DefaultMenu() {
			@Override
			public void selectType() {
				questionLibraryOperations.view();
			}
		});
		questionLibraryOptionType.put(3, new DefaultMenu() {
			@Override
			public void selectType() {
				questionLibraryOperations.update();
			}
		});
		questionLibraryOptionType.put(4, new DefaultMenu() {
			@Override
			public void selectType() {
				questionLibraryOperations.delete();
			}
		});
		questionLibraryOptionType.put(5, new DefaultMenu() {
			@Override
			public void selectType() {
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.operations();
			}
		});

		QuestionLibraryOptionType = Collections.unmodifiableMap(questionLibraryOptionType);
	}

	public void createQuestionRole(int option) {
		DefaultMenu command = QuestionLibraryOptionType.get(option);
		if (command == null) {
			System.out.println("Enter proper option");
		}
		try {
			command.selectType();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
