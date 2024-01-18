package database;

import java.util.Map;

import model.Question;

public class QuestionLibraryDao {
	Data data = new Data();
	static int id = Data.questionLibraryStorage.size()+1;
	private static Map<Integer, Question> questionDatabase = Data.questionLibraryStorage;
	public Map<Integer, Question> getQuestionData() {
		return questionDatabase;
	}

	public void setQuestionData(Question question) {
		questionDatabase.put(id, question);
		id+=1;
	}
	
	public void deleteQuestionData(int questionId)
	{
		questionDatabase.remove(questionId);
	}

	public void updateQuestionData(int id,Question updateQuestion) {
		questionDatabase.remove(id);
		questionDatabase.put(id, updateQuestion);
	}
}
