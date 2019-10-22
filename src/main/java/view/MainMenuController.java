package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenuController extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private BinaryBattleView binaryView;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bPane = new BorderPane();
		
		

		// Add a MenuBar 
		MenuBar menuBar = new MenuBar();
		
		// Add a main file menu (options, quit, etc.)
		Menu fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		MenuItem options = new MenuItem("Options");
		fileMenu.getItems().add(options);
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().add(exit);
		
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
		Label title = new Label("Computer Crash Course");
		title.setPadding(new Insets(20, 20, 20, 350));
		title.setFont(new Font("Arial", 48));
		
		// Add a GridPane that holds the buttons to play games
		GridPane gp = new GridPane();
		
		// Create a button for each game
		Button arrayAttackButton = new Button("Array Attack");
		gp.add(arrayAttackButton, 0, 0);
		Button binaryBattleButton = new Button("Binary Battle");
		gp.add(binaryBattleButton, 1, 0);
		Button gatesGameButton = new Button("Gates Game");
		gp.add(gatesGameButton, 0, 1);
		Button javaQuizletButton = new Button("Java Quizlet");
		gp.add(javaQuizletButton, 1, 1);
		
		// Styling of GridPane buttons
		gp.setPadding(new Insets(20, 20, 20, 20));
		
		// Create a VBox which lays out the elements in a 
		// top-down order. 
		VBox vBox = new VBox();
		vBox.getChildren().add(menuBar);
		vBox.getChildren().add(title);
		vBox.getChildren().add(gp);
		bPane.setTop(vBox);
		
		
		// Add a VBox to a BorderPane
		//binaryView = new BinaryBattleView();
		//bPane.setCenter(binaryView);
		
		// Add a BorderPane to the Scene
		Scene scene = new Scene(bPane, 1200, 900);
		
		
		
		primaryStage.setScene(scene);
		// Don't forget to show the running application:
		primaryStage.setTitle("Computer Crash Course");
		primaryStage.show();
	}

}
