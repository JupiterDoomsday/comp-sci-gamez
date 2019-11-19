package model;

public class JavaQuizletGame {
	private String question[];
	private String[] options;
	private String explanation;
	private int correctChoice;
	private int questionType;
	public JavaQuizletGame(int newQuestionType, String[] newQuestion, String[] newOptions, int newCorrectChoice, String newExpl) {
		question = newQuestion;
		options = newOptions;
		correctChoice = newCorrectChoice;
		questionType = newQuestionType;
		explanation = newExpl;
	}
	
	public String[] getQuestion() {
		String[] questionArr = question.clone();
		return questionArr;
	}
	
	public String[] getOptions() {
		String[] optionArr = options.clone();
		return optionArr;
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	public String getCorrectChoice() {
		return options[correctChoice];
	}
	
	public int getQuestionType() {
		return questionType;
	}
}
