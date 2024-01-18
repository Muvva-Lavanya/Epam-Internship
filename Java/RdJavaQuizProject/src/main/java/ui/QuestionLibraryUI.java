package ui;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuestionLibraryUI {
	static int max=Integer.MAX_VALUE;
	public void operations() {
		QuestionLibraryUI questionLibraryUI = new QuestionLibraryUI();
		final Logger logger = LogManager.getLogger(QuestionLibraryUI.class);
		Scanner sc = new Scanner(System.in);
		QuestionLibraryOptions questionLibraryOptions=new QuestionLibraryOptions();
		int option=0;
		do
		{
		logger.info("Select any of the following operations");
		logger.info("1. Create a question \n2. View questions \n3. Update a question \n4. Delete a question \n5. Exit");
		try
		{
		option=sc.nextInt();
		questionLibraryOptions.createQuestionRole(option);
		}
		catch(Exception e)
		{
			logger.info(e+" Invalid input");
			sc.next();
		}
		}while(option<max);
	}

}
