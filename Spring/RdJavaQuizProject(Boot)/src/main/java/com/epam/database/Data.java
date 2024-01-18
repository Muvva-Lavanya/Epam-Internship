package com.epam.database;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.entity.User;
public class Data { 
	static int id = 1;
	 static Map<Integer, Question> questionLibraryStorage = new HashMap<>();
	static{
		questionLibraryStorage.put(id++,new Question("who invented java",
				Arrays.asList("james gosling", "dennis ritche"), "Easy", "Java", "james gosling").setId(id));
		questionLibraryStorage.put(id++, new Question("which is used to compile,debug,execute java programs",
				Arrays.asList("jre", "jdk", "jvm", "jit"), "Easy", "Java", "jdk"));
		questionLibraryStorage.put(id++, new Question("which is not a feature of oops",
				Arrays.asList("object-oriented", "pointers", "portable"), "Easy", "Java", "pointers"));
		questionLibraryStorage.put(id++, new Question("what is extension of java code files",
				Arrays.asList(".js", ".txt", ".class", ".java"), "Easy", "Python", ".java"));
		questionLibraryStorage.put(id++, new Question("which environment variable is used to set java path",
				Arrays.asList("MAVEN_Path", "JavaPATH", "JAVA", "JAVA_HOME"), "Easy", "Python", "JAVA_HOME"));

	}
	
	 static Map<Integer, Quiz> quizLibraryStorage = new HashMap<>();
	
	 static List<User> userData=new ArrayList<>();
	static{
		userData.add(new User("admin","Admin1","Epam@123"));
		userData.add(new User("admin","Admin2","Epam@123"));
	}
	private Data() {
		super();
	}
	
	
}
