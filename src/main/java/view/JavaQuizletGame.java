package view;

public class JavaQuizletGame {
	private String question = "";
	private String[] options;
	private int correctChoice;
	private int questionType;
	public JavaQuizletGame(int newQuestionType, String newQuestion, String[] newOptions, int newCorrectChoice) {
		question = newQuestion;
		options = newOptions;
		correctChoice = newCorrectChoice;
		questionType = newQuestionType;
	}
	
}
