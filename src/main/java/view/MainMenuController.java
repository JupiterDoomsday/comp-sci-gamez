package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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
		bPane.setTop(menuBar);
		
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
		MenuItem arrayAttack = new MenuItem("Array Attack");
		leaderboards.getItems().add(arrayAttack);
		MenuItem binaryBattle = new MenuItem("Binary Battle");
		leaderboards.getItems().add(binaryBattle);
		MenuItem gatesGame = new MenuItem("Gates Game");
		leaderboards.getItems().add(gatesGame);
		MenuItem javaQuizlet = new MenuItem("Java Quizlet");
		leaderboards.getItems().add(javaQuizlet);
		
		
		
		// Add a VBox to a BorderPane
		binaryView = new BinaryBattleView();
		bPane.setCenter(binaryView);
		
		// Add a BorderPane to the Scene
		Scene scene = new Scene(bPane, 1200, 900);
		
		
		
		primaryStage.setScene(scene);
		// Don't forget to show the running application:
		primaryStage.setTitle("Computer Crash Course");
		primaryStage.show();
	}

}
