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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Difficulties;
import model.JavaQuizletGame;
import model.JavaQuizletModel;

public class JavaQuizlet extends MinigameView {
	BorderPane bPane = new BorderPane();
	VBox welcomeScreen = new VBox();
	JavaQuizletModel quiz = new JavaQuizletModel();
	JavaQuizletGame nextQuestion;
	int font_size = 32;

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
			newRadio.setStyle("-fx-text-fill: white;-fx-font-size: 18;");
			toggleDifficulty.getToggles().add(newRadio);
			difficultyBox.getChildren().add(newRadio);
		}

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

			Label lastQuestion = new Label("That was the last question!");
			lastQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			Label scoreLabel = new Label("Your score is " + score);
			scoreLabel.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
			results.getChildren().addAll(lastQuestion, scoreLabel);
			Button retry = new Button("Want to try again?");
			results.getChildren().add(retry);
			
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
		score = 0;
		welcomeScreen.getChildren().clear();
		difficultyBox.getChildren().clear();
		toggleDifficulty.getToggles().clear();
		gameBox.getChildren().clear();
		toggleQuestion.getToggles().clear();
		diffRulesBox.getChildren().clear();
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

		scoreLabel.setText("Current Score: " + score);
		scoreLabel.setStyle("-fx-text-fill: white;");
		gameBox.getChildren().add(scoreLabel);

		// set question
		currQuestion.setText(nextQuestion.getQuestion()[0]);
		currQuestion.setStyle("-fx-font-size: " + font_size + "px; -fx-text-fill: white;");
		gameBox.getChildren().add(currQuestion);

		if (!nextQuestion.getQuestion()[1].equals("")) {
			currQuestionCode.setText(nextQuestion.getQuestion()[1]);
			currQuestionCode.setStyle("-fx-text-fill: turquoise; -fx-font-size: " + font_size + "px;");
			gameBox.getChildren().add(currQuestionCode);
		}

		// set options
		String[] options = nextQuestion.getOptions();
		toggleQuestion = new ToggleGroup();

		for (int i = 0; i < options.length; i++) {
			RadioButton newOption = new RadioButton(options[i]);
			newOption.setStyle("-fx-text-fill: white;-fx-font-size: 18;");
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
				setUpScreenForNewQuestion();
			}
		});

		toggleQuestion.getToggles().forEach(toggle -> {
			RadioButton node = (RadioButton) toggle;
			node.setDisable(true);

			if (node.getText().equals(nextQuestion.getCorrectChoice())) {
				node.setStyle("-fx-text-fill: green;-fx-font-size: 18;");
			} else {
				node.setStyle("-fx-text-fill: red;-fx-font-size: 18;");
			}
		});

		if (currentSelection.equals(correctChoice)) {
			gameSelectorButton.setText("Correct!");
			
			if (chosenDifficulty.equals("easy")) {
				score = score + 1;
			}
			else if (chosenDifficulty.equals("medium")) {
				score = score + 2;
			}
			else if (chosenDifficulty.equals("hard")) {
				score = score + 3;
			}
		} else {
			gameSelectorButton.setText("Incorrect");
			score -= 1;
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
