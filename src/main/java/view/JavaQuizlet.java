package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.Difficulties;
import model.JavaQuizletModel;

public class JavaQuizlet extends VBox{
	private JavaQuizletModel quiz = new JavaQuizletModel();
	private Label welcomeLabel = new Label("Welcome to Java Quizlet!");
	private int difficulty = 0;
	private VBox difficultyBox = new VBox();
	private VBox gameBox = new VBox();
	private ToggleGroup tg = new ToggleGroup();
	private Button setDifficultyButton = new Button("Select");
	
	// set main window
	public JavaQuizlet() {
		this.getChildren().add(welcomeLabel);
		setDifficultyBox();
	}
	
	private void setDifficultyBox() {
		this.getChildren().add(difficultyBox);
		this.getChildren().add(setDifficultyButton);
		
		Difficulties[] difficulties = quiz.getDifficulties();
		
		for (int i = 0; i < difficulties.length; i++) {
			RadioButton newRadio = new RadioButton(difficulties[i].name());
			tg.getToggles().add(newRadio);
			difficultyBox.getChildren().add(newRadio);
		}
		
		tg.selectToggle(tg.getToggles().get(0));
		
		setDifficultyButton.setOnAction(new EventHandler<ActionEvent>() {
		      @Override
		      public void handle(ActionEvent event) {
		    	  ///////////////////////
		      }
		  });
	}
}
