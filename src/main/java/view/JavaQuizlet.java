package view;

import java.io.File;
import java.sql.SQLException;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Difficulties;
import model.GameDatabaseHandler;
import model.JavaQuizletGame;
import model.JavaQuizletModel;

public class JavaQuizlet extends MinigameView {
	BorderPane bPane = new BorderPane();
	VBox welcomeScreen = new VBox();
	JavaQuizletModel quiz = new JavaQuizletModel();
	JavaQuizletGame nextQuestion;
	int font_size = 32;
	Label difficultyLabel = new Label();

	//rules + difficulty
	HBox diffRulesBox = new HBox();
	
	// difficulty level
	int difficulty = 0;
	VBox difficultyBox = new VBox();
	ToggleGroup toggleDifficulty = new ToggleGroup();
	Button setDifficultyButton = new Button("Select");
	String chosenDifficulty = "";

	// current Question
	Label scoreLabel = new Label();

	// game box
	private VBox gameBox = new VBox();
	private Label currQuestion = new Label();
	private Label currQuestionCode = new Label();
	private ToggleGroup toggleQuestion = new ToggleGroup();
	private Button gameSelectorButton = new Button("Select");
	
	//scores
	private int totalCorrect = 0;
	private int totalIncorrect = 0;
	private int finalScore = 0;

	public JavaQuizlet() {
		// set background
		Image image = new Image("file:resources/image/quizlet-background.png");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(bImage);
		bPane.setBackground(background);
		bPane.setPrefSize(1197, 873);
		
		//start
		welcomeScreen();
		
		setDifficultyLabel();
		
		this.getChildren().add(bPane);
	}

	/*
	 * This method will set up the difficulty label on top
	 */
	private void setDifficultyLabel() {
		VBox difficultyLabelBox = new VBox();
		difficultyLabelBox.getChildren().add(difficultyLabel);
		bPane.setTop(difficultyLabelBox);
		difficultyLabelBox.setAlignment(Pos.CENTER);
		difficultyLabel.setStyle("-fx-font-size: 64px;-fx-text-fill: gray;");
	}

	/*
	 * This method will set up the welcome screen and level difficulty
	 */
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
		VBox difficultyRadioBox = new VBox();
		difficultyRadioBox.setAlignment(Pos.CENTER_LEFT);
		difficultyRadioBox.setPadding(new Insets(0,0,0,550));
		
		for (int i = 0; i < difficulties.length; i++) {
			RadioButton newRadio = new RadioButton(difficulties[i].name());
			newRadio.setStyle("-fx-text-fill: white;-fx-font-size: 18;");
			toggleDifficulty.getToggles().add(newRadio);
			difficultyRadioBox.getChildren().add(newRadio);
		}
		difficultyBox.getChildren().add(difficultyRadioBox);
		Button rulesButton = new Button ("Rules");
		diffRulesBox.getChildren().addAll(setDifficultyButton, rulesButton);
		diffRulesBox.setSpacing(20);
		diffRulesBox.setAlignment(Pos.CENTER);
		
		difficultyBox.getChildren().add(diffRulesBox);
		difficultyBox.setSpacing(10);
		toggleDifficulty.selectToggle(toggleDifficulty.getToggles().get(0));

		setDifficultyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				RadioButton selected = (RadioButton) toggleDifficulty.getSelectedToggle();
				chosenDifficulty = selected.getText().toLowerCase();
				quiz.setDifficultyLevel(chosenDifficulty);
				difficultyLabel.setText(((RadioButton) toggleDifficulty.getSelectedToggle()).getText() + " Mode");
				setUpScreenForNewQuestion();
			}
		});
		
		// Rules
		rulesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clearEverything();
				VBox expl = new VBox();
				String explanationStr = "Easy Mode = 1 point per correct answer.\n"
									  + "Medium Mode = 2 points per correct answer.\n"
									  + "Hard Mode = 3 points per correct answer.\n\n"
									  + "All = -1 points per incorrect answer!";
				Label explanation = new Label(explanationStr);
				explanation.setStyle("-fx-font-size: " + font_size + "px;-fx-text-fill: white;");
				Button goBackButton = new Button("Go Back");
				
				bPane.setCenter(expl);
				expl.getChildren().addAll(explanation, goBackButton);
				expl.setAlignment(Pos.CENTER);
				
				goBackButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						clearEverything();
							welcomeScreen();
						}
				});
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

			Label lastQuestion = new Label("That was the last question! This are your statistics:");
			Label correctScoreLabel = new Label("You had " + totalCorrect + " correct answers.");
			Label incorrectScoreLabel = new Label("You had " + totalIncorrect + " wrong answers.");
			Label finalScoreLabel = new Label("Your final score is:  " + finalScore + ".");
			lastQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			correctScoreLabel.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			incorrectScoreLabel.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			finalScoreLabel.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");

			GameDatabaseHandler database = GameDatabaseHandler.getInstance();
			try {
				if (MainMenuController.getUser() != null)
					database.setScore(MainMenuController.getUser(), "JavaQuizlet", finalScore);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			results.getChildren().addAll(lastQuestion, correctScoreLabel,incorrectScoreLabel,finalScoreLabel);
			Button retry = new Button("Want to try again?");
			results.getChildren().add(retry);
			
			if (finalScore <= 0) {
				playSound("./resources./java_quizlet_sounds/level_failed_javaquizlet.mp3");
			}
			else {
				playSound("./resources./java_quizlet_sounds/level_passed_javaquizlet.mp3");
			}
			
			retry.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					clearEverything();
					welcomeScreen();
				}
			});
		}

		// bPane.setCenter(new Label("New Question!"));
	}

	private void clearEverything() {
		finalScore = 0;
		totalCorrect = 0;
		totalIncorrect = 0;
		welcomeScreen.getChildren().clear();
		difficultyBox.getChildren().clear();
		toggleDifficulty.getToggles().clear();
		gameBox.getChildren().clear();
		toggleQuestion.getToggles().clear();
		diffRulesBox.getChildren().clear();
		difficultyLabel.setText("");
	}
	
	// returns 0 if no more questions
	// returns 1 if succesfull
	private int setQuestion() {
		gameBox.setAlignment(Pos.CENTER);
		gameBox.setSpacing(5);
		int test = 0;
		gameBox.getChildren().clear();
		nextQuestion = quiz.getNextGame();

		if (nextQuestion == null)
			return 0;

		scoreLabel.setText("Current Score: " + finalScore);
		scoreLabel.setStyle("-fx-text-fill: white;");
		gameBox.getChildren().add(scoreLabel);

		// set question
		currQuestion.setText(nextQuestion.getQuestion()[0]);
		currQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
		gameBox.getChildren().add(currQuestion);

		if (!nextQuestion.getQuestion()[1].equals("")) {
			currQuestionCode.setText(nextQuestion.getQuestion()[1]);
			currQuestionCode.setStyle("-fx-text-fill: turquoise; -fx-font-size: 20px;"
					+ "-fx-font-family: 'Courier New';");
			gameBox.getChildren().add(currQuestionCode);
		}

		// set options
		String[] options = nextQuestion.getOptions();
		toggleQuestion = new ToggleGroup();

		VBox radioButtonsBox = new VBox();
		radioButtonsBox.setAlignment(Pos.CENTER_LEFT);
		radioButtonsBox.setPadding(new Insets(0,0,0,550));
		
		for (int i = 0; i < options.length; i++) {
			RadioButton newOption = new RadioButton(options[i]);
			newOption.setStyle("-fx-text-fill: white;-fx-font-size: 18;");
			radioButtonsBox.getChildren().add(newOption);
			toggleQuestion.getToggles().add(newOption);
		}
		toggleQuestion.selectToggle(toggleQuestion.getToggles().get(0));
		gameBox.getChildren().add(radioButtonsBox);
		// set button selector
		gameBox.getChildren().add(gameSelectorButton);

		gameSelectorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameSelectorButton.setText("Select Choice");
				checkIfCorrectAnswer(nextQuestion.getCorrectChoice());
			}
		});

		// add to screen
		bPane.setCenter(gameBox);

		return 1;
	}

	public void checkIfCorrectAnswer(String correctChoice) {
		RadioButton selected = (RadioButton) toggleQuestion.getSelectedToggle();
		String currentSelection = selected.getText();

		gameSelectorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// checkIfCorrectAnswer(nextQuestion.getCorrectChoice());
				gameSelectorButton.setStyle(null);
				setUpScreenForNewQuestion();
			}
		});

		toggleQuestion.getToggles().forEach(toggle -> {
			RadioButton node = (RadioButton) toggle;
			node.setDisable(true);

			if (node.getText().equals(nextQuestion.getCorrectChoice())) {
				node.setStyle("-fx-text-fill: lightgreen;-fx-font-size: 18;-fx-opacity: 1;");
			} else {
				node.setStyle("-fx-text-fill: pink;-fx-font-size: 18;-fx-opacity: 1;");
			}
		});

		Label explanation = new Label(nextQuestion.getExplanation());
		explanation.setStyle("-fx-font-size: " + font_size + "px;-fx-text-fill: white");
		gameBox.getChildren().add(explanation);
		
		if (currentSelection.equals(correctChoice)) {
			gameSelectorButton.setText("Correct!");
			gameSelectorButton.setStyle("-fx-background-color: green");
			
			if (chosenDifficulty.equals("easy")) {
				finalScore = finalScore + 1;
			}
			else if (chosenDifficulty.equals("medium")) {
				finalScore = finalScore + 2;
			}
			else if (chosenDifficulty.equals("hard")) {
				finalScore = finalScore + 3;
			}
			totalCorrect += 1;
			playSound("./resources./java_quizlet_sounds/correct_answer_javaquizlet.mp3");
		} else {
			gameSelectorButton.setText("Incorrect");
			gameSelectorButton.setStyle("-fx-background-color: red");
			finalScore -= 1;
			totalIncorrect += 1;
			playSound("./resources./java_quizlet_sounds/incorrect_answer_javaquizlet.mp3");
		}

		scoreLabel.setText("Current Score: " + finalScore);
	}

	private void playSound(String file) {

		Media sound = new Media(new File(file).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
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
