package com.epam.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="quiz_library")
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String title;
	int numberOfQuestions;
	long totalMarks;
	@ManyToMany(targetEntity = Question.class)
	List<Question> question;
	public Quiz(String title, int numberOfQuestions,long totalMarks, List<Question> question) { 
		super();
		this.title = title;
		this.numberOfQuestions = numberOfQuestions;  
		this.totalMarks = totalMarks;
		this.question=question;
	}
	public Quiz(int id, String title, int numberOfQuestions, long totalMarks, List<Question> question) {
		super();
		this.id = id;
		this.title = title;
		this.numberOfQuestions = numberOfQuestions;
		this.totalMarks = totalMarks;
		this.question = question;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	public long getTotalMarks() {
		return totalMarks;
	}
	public Quiz() {
	}
	public void setTotalMarks(long l) {
		this.totalMarks = l;
	}
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "Quiz " + id + ". " + title + "- " + numberOfQuestions + "Questions - "
				+ totalMarks +"Marks";
	}

	

}
