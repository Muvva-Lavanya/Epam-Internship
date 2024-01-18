package database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Question;
import model.Quiz;

public class Data {
	static int id = 1;
	public static Map<Integer, Question> questionLibraryStorage = new HashMap<>();
	static{
		questionLibraryStorage.put(id++, new Question("who invented java",
				Arrays.asList("james gosling", "dennis ritche"), "Easy", "Java", "james gosling"));
		questionLibraryStorage.put(id++, new Question("which is used to compile,debug,execute java programs",
				Arrays.asList("jre", "jdk", "jvm", "jit"), "Easy", "Java", "jdk"));
		questionLibraryStorage.put(id++, new Question("which is not a feature of oops",
				Arrays.asList("object-oriented", "pointers", "portable"), "Easy", "Java", "pointers"));
		questionLibraryStorage.put(id++, new Question("what is extension of java code files",
				Arrays.asList(".js", ".txt", ".class", ".java"), "Easy", "Java", ".java"));
		questionLibraryStorage.put(id++, new Question("which environment variable is used to set java path",
				Arrays.asList("MAVEN_Path", "JavaPATH", "JAVA", "JAVA_HOME"), "Easy", "Java", "JAVA_HOME"));

	}
	
	public static Map<String, List<Quiz>> quizLibraryStorage = new HashMap<>();
	
	public static Map<String, String> userData = new HashMap<>();
	
	public static Map<String, String> adminData = new HashMap<>();
	static {
		adminData.put("Admin1","Epam@123");
		adminData.put("Admin2","Epam@123");
	}
}
