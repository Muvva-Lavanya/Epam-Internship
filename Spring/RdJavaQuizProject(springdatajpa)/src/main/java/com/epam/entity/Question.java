package com.epam.entity;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="question_library")
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String title;
	@ElementCollection
	private List<String> options; 
	private String difficultyLevel;
	private String tag;
	private String answer;
	public Question(String title, List<String> list, String difficultyLevel, String tag, String answer) {
		this.title = title;
		this.options = list;
		this.difficultyLevel = difficultyLevel; 
		this.tag = tag;
		this.answer = answer;

	}
	public Question() {
	}
	public int getId() {
		return id;
	}

	public Question setId(int id) {
		this.id = id;
		return this;
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
		return "Question " + id + ", title=" + title + ", options=" + options + ", difficultyLevel="
				+ difficultyLevel + ", tag=" + tag + ", answer=" + answer;
	}
	
	

    

}
