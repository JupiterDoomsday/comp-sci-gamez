package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Gate;
import model.Wire;

public class MainMenuController extends Application {
	
	/*
	 * TO-DO:
	 * 
	 * 1. Add menu button functionality
	 * 2. Create background for menu bar
	 * 3. Add credits-clickable button instead of menubar?
	 * 4. Create backgrounds for game button
	 */
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bPane = new BorderPane();
		// Add a background image for the main menu
		Image image = new Image("file:resources/images/csbackground.png");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(bImage);
		bPane.setBackground(background);

		// Add a MenuBar
		MenuBar menuBar = new MenuBar();

		// Add a main file menu (options, quit, etc.)
		Menu fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		MenuItem mainMenu = new MenuItem("Main Menu");
		fileMenu.getItems().add(mainMenu);
		
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		MenuItem options = new MenuItem("Options");
		fileMenu.getItems().add(options);
		MenuItem credits = new MenuItem("Credits");
		fileMenu.getItems().add(credits);
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().add(exit);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				primaryStage.close();
			}
		});

		// Add a profile menu (login, register, etc.)
		Menu profile = new Menu("Profile");
		menuBar.getMenus().add(profile);
		MenuItem login = new MenuItem("Login");
		profile.getItems().add(login);
		MenuItem register = new MenuItem("Register");
		profile.getItems().add(register);

		// Add a leaderboards menu (overall + leaderboard for each game)
		Menu leaderboards = new Menu("Leaderboards");
		menuBar.getMenus().add(leaderboards);
		MenuItem overall = new MenuItem("Overall");
		leaderboards.getItems().add(overall);
		MenuItem arrayAttackMenu = new MenuItem("Array Attack");
		leaderboards.getItems().add(arrayAttackMenu);
		MenuItem binaryBattleMenu = new MenuItem("Binary Battle");
		leaderboards.getItems().add(binaryBattleMenu);
		MenuItem gatesGameMenu = new MenuItem("Gates Game");
		leaderboards.getItems().add(gatesGameMenu);
		MenuItem javaQuizletMenu = new MenuItem("Java Quizlet");
		leaderboards.getItems().add(javaQuizletMenu);

		// Add a title for our main menu
		Text title = new Text("\n    Computer\n          Crash Course");
		title.setFont(Font.loadFont("file:resources/fonts/MAINBRG_.ttf", 75));
		title.setFill(Color.WHITE);
		title.setStyle("-fx-stroke: black;-fx-stroke-width: 4;");

		// Add a GridPane that holds the buttons to play games
		GridPane gp = new GridPane();
		
		String buttonStyle = "-fx-background-color: #3F3F3F;-fx-background-radius:25px;-fx-text-fill: white";
		String hoverStyle = "-fx-background-color: #6B6B6B;-fx-background-radius:25px;-fx-text-fill: white";
		// Create a button for each game
		Button arrayAttackButton = new Button("Array Attack");
		arrayAttackButton.setMinHeight(200.0);
		arrayAttackButton.setMinWidth(500.0);
		arrayAttackButton.setFont(new Font("Arial", 48));
		arrayAttackButton.setStyle(buttonStyle);
		arrayAttackButton.setOnMouseEntered(e -> {
			arrayAttackButton.setStyle(hoverStyle);
		});
		arrayAttackButton.setOnMouseExited(e -> {
			arrayAttackButton.setStyle(buttonStyle);
		});
		gp.add(arrayAttackButton, 0, 0);

		Button binaryBattleButton = new Button("Binary Battle");
		binaryBattleButton.setMinHeight(200.0);
		binaryBattleButton.setMinWidth(500.0);
		binaryBattleButton.setFont(new Font("Arial", 48));
		binaryBattleButton.setStyle(buttonStyle);
		binaryBattleButton.setOnMouseEntered(e -> {
			binaryBattleButton.setStyle(hoverStyle);
		});
		binaryBattleButton.setOnMouseExited(e -> {
			binaryBattleButton.setStyle(buttonStyle);
		});
		gp.add(binaryBattleButton, 1, 0);

		Button gatesGameButton = new Button("Gates Game");
		gatesGameButton.setMinHeight(200.0);
		gatesGameButton.setMinWidth(500.0);
		gatesGameButton.setFont(new Font("Arial", 48));
		gatesGameButton.setStyle(buttonStyle);
		gatesGameButton.setOnMouseEntered(e -> {
			gatesGameButton.setStyle(hoverStyle);
		});
		gatesGameButton.setOnMouseExited(e -> {
			gatesGameButton.setStyle(buttonStyle);
		});
		gp.add(gatesGameButton, 0, 1);

		Button javaQuizletButton = new Button("Java Quizlet");
		javaQuizletButton.setMinHeight(200.0);
		javaQuizletButton.setMinWidth(500.0);
		javaQuizletButton.setFont(new Font("Arial", 48));
		javaQuizletButton.setStyle(buttonStyle);
		javaQuizletButton.setOnMouseEntered(e -> {
			javaQuizletButton.setStyle(hoverStyle);
		});
		javaQuizletButton.setOnMouseExited(e -> {
			javaQuizletButton.setStyle(buttonStyle);
		});
		gp.add(javaQuizletButton, 1, 1);
		
		// Styling of GridPane buttons
		gp.setPadding(new Insets(80, 20, 20, 70));
		gp.setHgap(50);
		gp.setVgap(50);

		// Create a VBox which lays out the elements in a
		// top-down order.
		VBox vBox = new VBox();
		vBox.getChildren().add(menuBar);
		vBox.getChildren().add(title);
		vBox.getChildren().add(gp);
		bPane.setTop(vBox);

		// Now we need to add the on-click actions for each button.
		// 1. Array Attack
		arrayAttackButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				ArrayAttackView arrayAttackView = new ArrayAttackView();
				bPane.setCenter(arrayAttackView);
			}
		});
		
		// 2. Binary Battle
		binaryBattleButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				BinaryBattleView binaryView = new BinaryBattleView();
				bPane.setCenter(binaryView);
			}
		});
		
		// 3. Gates Game
		gatesGameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				Gate gate= new Gate(new Wire(false),new Wire(true),"AND");
				GatesView gatesView = new GatesView(gate);
				bPane.setCenter(gatesView);
			}
		});
		
		// 4. Java Quizlet
		javaQuizletButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				JavaQuizlet javaQuizletView = new JavaQuizlet();
				bPane.setCenter(javaQuizletView);
			}
		});
		

		// Add a BorderPane to the Scene
		Scene scene = new Scene(bPane, 1200, 900);
		primaryStage.setScene(scene);

		// Start the application
		primaryStage.setTitle("Computer Crash Course");
		primaryStage.show();
	}

}
