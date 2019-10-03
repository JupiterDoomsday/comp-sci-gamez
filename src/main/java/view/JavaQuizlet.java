package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.Difficulties;
import model.JavaQuizletGame;
import model.JavaQuizletModel;

public class JavaQuizlet extends VBox{
	private JavaQuizletModel quiz = new JavaQuizletModel();
	private Label welcomeLabel = new Label("Welcome to Java Quizlet!");
	
	// difficulty level
	private int difficulty = 0;
	private VBox difficultyBox = new VBox();
	private ToggleGroup toggleDifficulty = new ToggleGroup();
	private Button setDifficultyButton = new Button("Select");
	
	// main game box
	private VBox mainBox = new VBox();
	
	//game box
	private VBox gameBox = new VBox();
	private Label currQuestion = new Label();
	private ToggleGroup toggleQuestion;
	private Button gameSelectorButton = new Button("Select");
	private int score = 0;
	
	
	public JavaQuizlet() {
		this.getChildren().add(welcomeLabel);
		this.getChildren().add(mainBox);
		setDifficultyBox();
	}
	
	// This method sets up the difficulty box
	private void setDifficultyBox() {
		mainBox.getChildren().add(difficultyBox);
		
		Difficulties[] difficulties = quiz.getDifficulties();
		
		for (int i = 0; i < difficulties.length; i++) {
			RadioButton newRadio = new RadioButton(difficulties[i].name());
			toggleDifficulty.getToggles().add(newRadio);
			difficultyBox.getChildren().add(newRadio);
		}
		
		difficultyBox.getChildren().add(setDifficultyButton);
		
		toggleDifficulty.selectToggle(toggleDifficulty.getToggles().get(0));
		
		setDifficultyButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override
		      public void handle(ActionEvent event) {
		    	  setUpScreenForNewQuestion();
		      }
		  });
	}
	
	// this method sets up a new question
	private void setUpScreenForNewQuestion() {
		welcomeLabel.setText("Current Score: " + score);
		mainBox.getChildren().clear();
		int returnCode = setQuestion();
		
		if (returnCode == 0) {
			mainBox.getChildren().clear();
			this.getChildren().clear();
			this.getChildren().add(new Label ("That was the last question!\nYour score is " + score));
		}
	}
	
	// returns 0 if no more questions
	// returns 1 if succesfull
	private int setQuestion() {
		gameBox.getChildren().clear();
		
		JavaQuizletGame newQuestion = quiz.getNextGame();
		
		if (newQuestion == null)
			return 0;
		
		// set question
		currQuestion.setText(newQuestion.getQuestion());
		gameBox.getChildren().add(currQuestion);
		
		//set options
		String[] options = newQuestion.getOptions();
		toggleQuestion = new ToggleGroup();
		
		for (int i = 0; i < options.length; i++) {
			RadioButton newOption = new RadioButton(options[i]);
			toggleQuestion.getToggles().add(newOption);
			gameBox.getChildren().add(newOption);
		}
		toggleQuestion.selectToggle(toggleQuestion.getToggles().get(0));
		
		//set button selector
		gameBox.getChildren().add(gameSelectorButton);
		
		gameSelectorButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override
		      public void handle(ActionEvent event) {
		    	  checkIfCorrectAnswer(newQuestion.getCorrectChoice());
		    	  setUpScreenForNewQuestion();
		      }
		  });
		
		// add to screen
		mainBox.getChildren().add(gameBox);
		
		return 1;
	}
	
	public void checkIfCorrectAnswer(String correctChoice) {
		RadioButton selected = (RadioButton)toggleQuestion.getSelectedToggle();
		String currentSelection = selected.getText();
		
		if (currentSelection.equals(correctChoice))
			score++;
	}
}
