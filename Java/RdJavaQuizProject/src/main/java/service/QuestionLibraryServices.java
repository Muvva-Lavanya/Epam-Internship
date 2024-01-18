package service;

import model.Question;

interface QuestionLibraryServices
{
	public String createQuestion(Question question);

	public String deleteQuestion(Integer questionId);

	public StringBuffer viewQuestion();

	public boolean updateTitle(int id, String title);
	public boolean updateAnswer(int id,  int correctOption);
	public boolean updateDifficulty(int id, String difficulty);
	public boolean updateTag(int id, String taggingTopics);
	public boolean updateOptions(int id,int option,String value);
}