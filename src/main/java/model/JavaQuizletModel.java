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
		setQuestion3();
		setQuestion4();
	}
	
	private void setQuestion1() {
		int questionType = 1;
		String question = "What year was Java developed?\n\n";
		String[] options = new String[] {"1996", "1998", "2000", "2002"};
		int correctChoice = 0;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion2() {
		int questionType = 1;
		String question = "What is the output of the following:\n\n";
		question += "Class Equals{\n";
		question += "\tpublic static void main(String[] args){\n";
		question += "\t\tString s1 = \"Hello\";\n";
		question += "\t\tString s2 = new String(s1);\n";
		question += "\t\tSystem.out.pritnln(s1==s2);\n";
		question += "\t}\n}\n\n";
		String[] options = new String[] {"true", "false", "0", "Hello"};
		int correctChoice = 1;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion3() {
		int questionType = 1;
		String question = "Use the following declaration and initialization to evaluate the java expression:\n\n";
		question += "int a = 2, b = 3, c = 4, d = 5;\n";
		question += "float k = 4.3f;\n";
		question += "System.out.println( b * a + c * d * k);\n\n";
		String[] options = new String[] {"135", "135.0", "36", "36.0"};
		int correctChoice = 3;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion4() {
		int questionType = 1;
		String question = "The default value of a static integer variable in Java is:\n\n";
		String[] options = new String[] {"true", "false", "0", "1", "Null"};
		int correctChoice = 2;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
}
