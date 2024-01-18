package model;

import java.util.Arrays;
import java.util.List;

public class Quiz {
	String question;
	List<String> options;
	String difficultyLevel;
	int marks;

	public Quiz(String question, List<String> list, String difficultyLevel, int marks) {
		super();
		this.question = question;
		this.options = list;
		this.difficultyLevel = difficultyLevel;
		this.marks = marks;

	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "[question=" + question + ", options=" + Arrays.asList(options) + ", difficultyLevel="
				+ difficultyLevel + ", marks=" + marks + "]";
	}

}
