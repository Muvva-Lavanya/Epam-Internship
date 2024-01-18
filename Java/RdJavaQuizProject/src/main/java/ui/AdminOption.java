package ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AdminOption {
	private static final Map<Integer, DefaultMenu> AdminOptionType;
	static {
		final Map<Integer,DefaultMenu> adminOptionType = new HashMap<>();
		adminOptionType.put(1, new DefaultMenu() {
		@Override
		public void selectType() 
		    {
			QuestionLibraryUI questionLibraryUI = new QuestionLibraryUI();
			questionLibraryUI.operations();
			}
		});
		adminOptionType.put(2, new DefaultMenu() {
		@Override
		public void selectType()
	        {
			QuizLibraryUI quizLibraryUI = new QuizLibraryUI();
			quizLibraryUI.operations();
			}});
		adminOptionType.put(3, new DefaultMenu()
	{
		@Override
		public void selectType()
		{
			Main main=new Main();
			String args[]=new String[1];
			main.main(args);
			}
		}
	);
		AdminOptionType = Collections.unmodifiableMap(adminOptionType);
	}
	public void createAdminRole(int adminOptionType) {
		DefaultMenu command = AdminOptionType.get(adminOptionType);
		if (command == null) {
			System.out.println("Enter proper option");
		}
		try
		{
		command.selectType();
		}
		catch(NullPointerException e)
		{
			System.out.println(e);
		}
	}
}
