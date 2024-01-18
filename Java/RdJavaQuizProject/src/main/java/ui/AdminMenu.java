package ui;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminMenu {
	static int max=Integer.MAX_VALUE;
	public void operations() {
		Logger logger = LogManager.getLogger(LoginUI.class);
		int option=0;
		AdminOption adminOption=new AdminOption();
		do
		{
		Scanner sc = new Scanner(System.in);
		logger.info("Choose one of the option");
		logger.info("1.QuestionLibrary\n2.QuizLibrary\n3.Exit");
		try
		{
		option = sc.nextInt();
		adminOption.createAdminRole(option);
		}
		catch(InputMismatchException e)
		{
			logger.info(e+": Invalid Input");
			sc.next();
		}
		}while(option<max);

	}

}
