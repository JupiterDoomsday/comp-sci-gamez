package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameTestView extends Application{

	 public static void main(String[] args) {
		 launch(args);
	 }

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		ArrayAttackView AAGame = new ArrayAttackView();
		pane.setCenter(AAGame);
		Scene scene = new Scene(pane, 1366, 700);
	    stage.setScene(scene);
	    stage.show();
	    AAGame.run();
	}
	
}
