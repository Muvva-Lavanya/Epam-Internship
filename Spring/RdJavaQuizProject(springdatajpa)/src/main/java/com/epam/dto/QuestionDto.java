package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto{
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String title;
	private List<String> options=new ArrayList<>(); 
	private String difficultyLevel;
	private String tag;
	private String answer;
	public QuestionDto(int id,String title, List<String> options, String difficultyLevel, String tag, String answer) {
		super();
		this.id=id;
		this.title = title;
		this.options = options;
		this.difficultyLevel = difficultyLevel;
		this.tag = tag;
		this.answer = answer;
	}
	public QuestionDto() {
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "QuestionDto " + id + ", title=" + title + ", options=" + options + ", difficultyLevel="
				+ difficultyLevel + ", tag=" + tag + ", answer=" + answer;
	}
	
}