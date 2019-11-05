package view;

import com.sun.prism.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import model.Difficulties;
import model.JavaQuizletGame;
import model.JavaQuizletModel;

public class JavaQuizlet extends MinigameView {
	BorderPane bPane = new BorderPane();
	VBox welcomeScreen = new VBox();
	JavaQuizletModel quiz = new JavaQuizletModel();
	JavaQuizletGame nextQuestion;
	int font_size = 24;
	
	// difficulty level
	int difficulty = 0;
	VBox difficultyBox = new VBox();
	ToggleGroup toggleDifficulty = new ToggleGroup();
	Button setDifficultyButton = new Button("Select");

	// current Question
	Label scoreLabel = new Label();

	// game box
	private VBox gameBox = new VBox();
	private Label currQuestion = new Label();
	private Label currQuestionCode = new Label();
	private ToggleGroup toggleQuestion;
	private Button gameSelectorButton = new Button("Select");
	private int score = 0;

	public JavaQuizlet() {
		Image image = new Image("file:resources/image/quizlet-background.png");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(bImage);
		bPane.setBackground(background);
		bPane.setPrefSize(1197, 873);
		welcomeScreen();

		this.getChildren().add(bPane);
	}

	private void welcomeScreen() {
		bPane.setCenter(welcomeScreen);
		Label welcomeLabel = new Label("Welcome to Java Quizlet!");
		welcomeLabel.setStyle("-fx-font-size: " + font_size + "px;-fx-text-fill: white;");
		Label difLabel = new Label("Please Choose Difficulty Level:");
		difLabel.setStyle("-fx-font-size: " + font_size + "px;-fx-text-fill: white;");
		welcomeScreen.getChildren().addAll(welcomeLabel, difLabel);
		welcomeScreen.getChildren().add(difficultyBox);
		welcomeScreen.setAlignment(Pos.CENTER);
		difficultyBox.setAlignment(Pos.CENTER);

		
		Difficulties[] difficulties = quiz.getDifficulties();

		for (int i = 0; i < difficulties.length; i++) {
			RadioButton newRadio = new RadioButton(difficulties[i].name());
			newRadio.setStyle("-fx-text-fill: white;");
			toggleDifficulty.getToggles().add(newRadio);
			difficultyBox.getChildren().add(newRadio);
		}

		difficultyBox.getChildren().add(setDifficultyButton);
		difficultyBox.setSpacing(10);
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
		gameSelectorButton.setText("Select Choice");
		bPane.setCenter(null);

		int returnCode = setQuestion();

		if (returnCode == 0) {
			bPane.setCenter(null);
			VBox results = new VBox();
			results.setAlignment(Pos.CENTER);
			bPane.setCenter(results);
			
			Label lastQuestion = new Label("That was the last question!");
			lastQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			Label scoreLabel = new Label("Your score is " + score);
			scoreLabel.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			results.getChildren().addAll(lastQuestion, scoreLabel);
			
		}

		// bPane.setCenter(new Label("New Question!"));
	}

	// returns 0 if no more questions
	// returns 1 if succesfull
	private int setQuestion() {
		gameBox.setAlignment(Pos.CENTER);
		int test = 0;
		gameBox.getChildren().clear();
		nextQuestion = quiz.getNextGame();

		if (nextQuestion == null)
			return 0;

		scoreLabel.setText("Current Score: " + score);
		scoreLabel.setStyle("-fx-text-fill: white;");
		gameBox.getChildren().add(scoreLabel);
		
		// set question
		currQuestion.setText(nextQuestion.getQuestion()[0]);
		currQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
		currQuestionCode.setText(nextQuestion.getQuestion()[1]);
		currQuestionCode.setStyle("-fx-text-fill: turquoise; -fx-font-size: " + font_size + "px;");
		gameBox.getChildren().add(currQuestion);
		gameBox.getChildren().add(currQuestionCode);
		

		// set options
		String[] options = nextQuestion.getOptions();
		toggleQuestion = new ToggleGroup();

		for (int i = 0; i < options.length; i++) {
			RadioButton newOption = new RadioButton(options[i]);
			newOption.setStyle("-fx-text-fill: white;");
			toggleQuestion.getToggles().add(newOption);
			gameBox.getChildren().add(newOption);
		}
		toggleQuestion.selectToggle(toggleQuestion.getToggles().get(0));

		// set button selector
		gameBox.getChildren().add(gameSelectorButton);

		gameSelectorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameSelectorButton.setText("Select Choice");
				checkIfCorrectAnswer(nextQuestion.getCorrectChoice());
				//setUpScreenForNewQuestion();
			}
		});

		// add to screen
		bPane.setCenter(gameBox);

		return 1;
	}
	
	public void checkIfCorrectAnswer(String correctChoice) {
		RadioButton selected = (RadioButton)toggleQuestion.getSelectedToggle();
		String currentSelection = selected.getText();
		
		gameSelectorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//checkIfCorrectAnswer(nextQuestion.getCorrectChoice());
				setUpScreenForNewQuestion();
			}
		});
		
		toggleQuestion.getToggles().forEach(toggle -> {
		    RadioButton node = (RadioButton) toggle ;
		    node.setDisable(true);
		    
		    if (node.getText().equals(nextQuestion.getCorrectChoice())) {
		    	node.setStyle("-fx-text-fill: green;");
		    }
		    else {
		    	node.setStyle("-fx-text-fill: red;");
		    }
		});
		
		if (currentSelection.equals(correctChoice)) {
			gameSelectorButton.setText("Correct!");
			score++;
		}
		else {
			gameSelectorButton.setText("Incorrect");
		}
		
		scoreLabel.setText("Current Score: " + score);
	}
	
	@Override
	public String settings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layoutScene() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
