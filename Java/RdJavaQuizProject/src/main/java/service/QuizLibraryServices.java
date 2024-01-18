package service;

interface QuizLibraryServices {
	public String addQuiz(String title, int numberOfQuestions);

	public String deleteQuiz(String title);

	public String updateQuiz(String oldTitle, String newTitle);

	public StringBuffer viewQuiz();

}
