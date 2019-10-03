package view;

import javafx.application.Application;
import javafx.scene.Scene;
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

		// Add a VBox to a BorderPane
		binaryView = new BinaryBattleView();
		bPane.setCenter(binaryView);
		
		// Add a BorderPane to the Scene
		Scene scene = new Scene(bPane, 1200, 900);
		primaryStage.setScene(scene);
		// Don't forget to show the running application:
		primaryStage.setTitle("BINARY BATTLE");
		primaryStage.show();
	}

}
