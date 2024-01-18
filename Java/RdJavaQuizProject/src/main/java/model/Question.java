package model;
import java.util.List;

public class Question {

	String title;
	List<String> options;
	String difficultyLevel;
	String tag;
	String answer;

	public Question(String title, List<String> list, String difficultyLevel, String tag, String answer) {
		super();
		this.title = title;
		this.options = list;
		this.difficultyLevel = difficultyLevel;
		this.tag = tag;
		this.answer = answer;

	}

	public Question() {
		// TODO Auto-generated constructor stub
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
		return (title+" - "+tag+" ("+difficultyLevel+")\nOptions - "+options+"\nAnswer - "+answer+"\n");
	}

}
