package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
	static {
		Logger logger = LogManager.getLogger(Main.class);
		logger.info("Welcome to Console based Quiz Application");
	}
	static int max=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Main.class);
		UserSelection userSelection=new UserSelection();
		int option=0;
		do
		{
		Scanner sc = new Scanner(System.in);
		logger.info("Select the type of role");
		logger.info("1.Admin\n2.User");
		try
		{
		option = sc.nextInt();
		userSelection.createUser(option);
		}
		catch(InputMismatchException e)
		{
			logger.info(e+": Invalid Input");
		}
		}while(option<max);
	}

}
