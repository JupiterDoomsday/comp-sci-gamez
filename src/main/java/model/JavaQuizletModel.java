package model;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQuizletModel {
	private Queue<JavaQuizletGame> questions = new LinkedList();
	
	public JavaQuizletModel() {
		setAllQuestions();
	}
	
	public Difficulties[] getDifficulties() {
		Difficulties[] difficulties = Difficulties.values();
		
		return difficulties;
	}
	
	public JavaQuizletGame getNextGame() {
		return questions.poll();
	}
	
	// Setting questions!!!!!
	private void setAllQuestions() {
		setQuestion1();
		setQuestion2();
	}
	
	private void setQuestion1() {
		int questionType = 1;
		String question = "What is the name of this game?";
		String[] options = new String[] {"Array Attack", "Java Quizlet", "CSC 436", "None of the Above"};
		int correctChoice = 1;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion2() {
		int questionType = 1;
		String question = "Are you human?";
		String[] options = new String[] {"Yes", "No"};
		int correctChoice = 0;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
}
