package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JavaQuizletModel {
	private Queue<JavaQuizletGame> questions = new LinkedList();

	public JavaQuizletModel() {

	}

	public Difficulties[] getDifficulties() {
		Difficulties[] difficulties = Difficulties.values();

		return difficulties;
	}

	public JavaQuizletGame getNextGame() {
		return questions.poll();
	}

	public void setDifficultyLevel(String chosen) {
		if (chosen.equals("easy")) {
			setAllQuestionsEasy();
		} else if (chosen.equals("medium")) {
			setAllQuestionsMedium();
		} else if (chosen.equals("hard")) {
			setAllQuestionsHard();
		}
	}

	// Setting questions!!!!!
	private void setAllQuestionsEasy() {
		setQuestion1Easy();
		setQuestion2Easy();
		setQuestion3Easy();
		setQuestion4Easy();
		setQuestion5Easy();
	}

	private void setAllQuestionsMedium() {
		setQuestion1Medium();
		setQuestion2Medium();
		setQuestion3Medium();
		setQuestion4Medium();
		setQuestion5Medium();
	}

	private void setAllQuestionsHard() {
		setQuestion1Hard();
		setQuestion2Hard();
		setQuestion3Hard();
		setQuestion4Hard();
		setQuestion5Hard();

	}
	
//	private static void setAllQuestions() {
//		File questions_file = new File("./resources/questions/java_quizlet_questions.txt");
//		Scanner scanner = null;
//		try {
//			scanner = new Scanner(questions_file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		int currQue = 0, currCorr = 0, currInc = 0, currExpl = 0;
//		
//		int questionType = 1;
//		String[] question = { "", "" };
//		String[] options = {};
//		int correctChoice = 0;
//		
//		while (scanner.hasNextLine()) {
//		   String line = scanner.nextLine();
//		   if (line.equals(""))
//			   continue;
//		   
//		   if (line.contains("Question #")) {
//			   line = scanner.nextLine();
//			   currQue = 1;
//			   currCorr = 0;
//			   currInc = 0;
//			   currExpl = 0;
//		   }
//		   else if (line.contains("Correct answer")) {
//			   line = scanner.nextLine();
//			   currQue = 0;
//			   currCorr = 1;
//			   currInc = 0;
//			   currExpl = 0;
//		   }
//		   else if (line.contains("Incorrect answer")) {
//			   line = scanner.nextLine();
//			   currQue = 0;
//			   currCorr = 0;
//			   currInc = 1;
//			   currExpl = 0;
//		   }
//		   else if (line.contains("Explanation")) {
//			   line = scanner.nextLine();
//			   currQue = 0;
//			   currCorr = 0;
//			   currInc = 0;
//			   currExpl = 1;
//		   }
//		   
//		   if (currQue == 1) {
//			   System.out.println(line);
//		   }
//		   else if(currCorr == 1) {
//			   
//		   }
//		   else if(currInc == 1) {
//			   
//		   }
//		   else if (currExpl == 1) {
//			   
//		   }
//		}
//	}
	
//	public static void main(String[] args) {
//		setAllQuestions();
//	}
//
//	// Function to add x in arr 
//    private String[] addX(int n, String arr[], String x) 
//    { 
//        int i; 
//  
//        // create a new array of size n+1 
//        String newarr[] = new String[n + 1]; 
//  
//        // insert the elements from 
//        // the old array into the new array 
//        // insert all elements till n 
//        // then insert x at n+1 
//        for (i = 0; i < n; i++) 
//            newarr[i] = arr[i]; 
//  
//        newarr[n] = x; 
//  
//        return newarr; 
//    }
	
	// EASY QUESTIONS
	private void setQuestion1Easy() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What values can a boolean expression have in Java?\n\n";
		question[1] = "";
		String[] options = new String[] { "true, false, 0, 1", "true, false", "TRUE, FALSE", "TRUE, FALSE, 0, 1" };
		int correctChoice = 1;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion2Easy() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following:\n\n";
		question[1] = "Class Equals{\n";
		question[1] += "\tpublic static void main(String[] args){\n";
		question[1] += "\t\tString s1 = \"Fizz\";\n";
		question[1] += "\t\tString s2 = \"Bar\";\n";
		question[1] += "\t\ts2 += s2;\n";
		question[1] += "\t\tSystem.out.pritnln(s1);\n";
		question[1] += "\t}\n}\n\n";
		String[] options = new String[] { "Fizz", "Bar", "FizzBar", "BarFizz" };
		int correctChoice = 2;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion3Easy() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "Use the following declaration and initialization\n" + "to evaluate the java expression:\n\n";
		question[1] = "int a = 2, b = 3, c = 4, d = 5;\n";
		question[1] += "float k = 4.3f;\n";
		question[1] += "System.out.println( b * a + c * d * k);\n\n";
		String[] options = new String[] { "135", "135.0", "92", "92.0" };
		int correctChoice = 3;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion4Easy() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "The default value of a static integer variable\n" + "in Java is:\n\n";
		String[] options = new String[] { "true", "false", "0", "1", "Null" };
		int correctChoice = 2;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion5Easy() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following code:";
		question[1] = "System.out.pritnln(Math.ceil(-3.22));";
		String[] options = new String[] { "-3", "-4", "-3.0", "-4.0" };
		int correctChoice = 2;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	// MEDIUM QUESTIONS!
	private void setQuestion1Medium() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "Variable name can begin with a letter, \"$\",\n" + "or \"_\".";
		String[] options = new String[] { "True", "False" };
		int correctChoice = 0;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion2Medium() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following question?";
		question[1] = "class Test3 {\n" + 
				"	public static void main(String args[]) {\n" + 
				"		int var1 = 5;\n" + 
				"		int var2 = 6;\n" + 
				"		if ((var2 = 1) == var1)\n" + 
				"			System.out.print(var2);\n" + 
				"		else\n" + 
				"			System.out.print(++var2);\n" + 
				"	}\n" + 
				"}";
		String[] options = new String[] { "1", "2", "7", "8" };
		int correctChoice = 1;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion3Medium() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following code?";
		question[1] = "public class Main \n" + 
				"{ \n" + 
				"    public static void gfg(String s) \n" + 
				"    {     \n" + 
				"        System.out.println(\"String\"); \n" + 
				"    } \n" + 
				"    public static void gfg(Object o) \n" + 
				"    { \n" + 
				"        System.out.println(\"Object\"); \n" + 
				"    } \n" + 
				"  \n" + 
				"    public static void main(String args[]) \n" + 
				"    { \n" + 
				"        gfg(null); \n" + 
				"    } \n" + 
				"}";
		String[] options = new String[] { "String", "Object" };
		int correctChoice = 0;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
	
	private void setQuestion4Medium() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following code?";
		question[1] = "public class Test \n" + 
				"{ \n" + 
				"    public static void main(String[] args) \n" + 
				"    { \n" + 
				"        int temp = 9; \n" + 
				"        int data = 8; \n" + 
				"        System.out.println(temp & data); \n" + 
				"    } \n" + 
				"} ";
		String[] options = new String[] { "1001", "1000", "9", "8"};
		int correctChoice = 3;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
	
	private void setQuestion5Medium() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following code?";
		question[1] = "class Test {\n" + 
				"   public static void main(String args[]) {\n" + 
				"     int arr[2];  \n" + 
				"     System.out.print(arr[0] + \" | \");\n" + 
				"     System.out.print(arr[1]);\n" + 
				"   }\n" + 
				"}";
		String[] options = new String[] { "0 | 0", "garbage value | garbage value", "Compile Error", "Exception"};
		int correctChoice = 2;
		;
		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	// HARD QUESTIONS!
	private void setQuestion1Hard() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "Java programming is not statically-typed,\n"
				+ "means all variables should not first be declared\n" + "before they can be used.";
		String[] options = new String[] { "True", "False" };
		int correctChoice = 1;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}

	private void setQuestion2Hard() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "The JRE deletes objects when it determines\n"
				+ "that they are no longer being used. This process\n" + " is called Garbage Cleaning.";
		String[] options = new String[] { "True", "False" };
		int correctChoice = 1;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
	
	private void setQuestion3Hard() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following question?";
		question[1] = "class Test2 { \n" + 
				"public\n" + 
				"    static void main(String[] args) \n" + 
				"    { \n" + 
				"        byte x = 12; \n" + 
				"        byte y = 13; \n" + 
				"        byte result = x + y; \n" + 
				"        System.out.print(result); \n" + 
				"    } \n" + 
				"}";
		String[] options = new String[] { "25", "Error", "-25", "none" };
		int correctChoice = 1;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
	
	private void setQuestion4Hard() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following question?";
		question[1] = "class Test3 { \r\n" + 
				"public\n" + 
				"    static void main(String[] args) \n" + 
				"    { \n" + 
				"        int x = 011; \n" + 
				"        int y = 0xf; \n" + 
				"        int result = x + y; \n" + 
				"        System.out.print(x + \":\" + y + \":\" + result); \n" + 
				"    } \n" + 
				"} ";
		String[] options = new String[] { "Error", "11:16:24", " 9:15:24", "11:15:24" };
		int correctChoice = 2;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
	
	private void setQuestion5Hard() {
		int questionType = 1;
		String[] question = { "", "" };
		question[0] = "What is the output of the following code?";
		question[1] = "public class Test { \r\n" + 
				"public static void main(String[] args) \n" + 
				"    { \n" + 
				"        int[] x = { 120, 200, 016 }; \n" + 
				"        for (int i = 0; i < x.length; i++) \n" + 
				"            System.out.print(x[i] + \" \"); \n" + 
				"    } \n" + 
				"} ";
		String[] options = new String[] { "120 200 014", "120 200 016", " 120 200 14", "120 200 16" };
		int correctChoice = 2;

		JavaQuizletGame newGame = new JavaQuizletGame(questionType, question, options, correctChoice);

		questions.add(newGame);
	}
}
