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
		String[] question = {"",""};
		question[0] = "What year was Java developed?\n\n";
		question[1] = "";
		String[] options = new String[] {"1996", "1998", "2000", "2002"};
		int correctChoice = 0;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion2() {
		int questionType = 1;
		String[] question = {"",""};
		question[0] = "What is the output of the following:\n\n";
		question[1] = "Class Equals{\n";
		question[1] += "\tpublic static void main(String[] args){\n";
		question[1] += "\t\tString s1 = \"Hello\";\n";
		question[1] += "\t\tString s2 = new String(s1);\n";
		question[1] += "\t\tSystem.out.pritnln(s1==s2);\n";
		question[1] += "\t}\n}\n\n";
		String[] options = new String[] {"true", "false", "0", "Hello"};
		int correctChoice = 1;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion3() {
		int questionType = 1;
		String[] question = {"",""};
		question[0] = "Use the following declaration and initialization to evaluate the java expression:\n\n";
		question[1] = "int a = 2, b = 3, c = 4, d = 5;\n";
		question[1] += "float k = 4.3f;\n";
		question[1] += "System.out.println( b * a + c * d * k);\n\n";
		String[] options = new String[] {"135", "135.0", "92", "92.0"};
		int correctChoice = 3;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
	
	private void setQuestion4() {
		int questionType = 1;
		String[] question = {"",""};
		question[0] = "The default value of a static integer variable in Java is:\n\n";
		String[] options = new String[] {"true", "false", "0", "1", "Null"};
		int correctChoice = 2;
		
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question,options, correctChoice);
		
		questions.add(newGame);
	}
}
