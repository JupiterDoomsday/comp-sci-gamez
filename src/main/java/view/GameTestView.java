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
		ArrayAttackView game = new ArrayAttackView();
		pane.setCenter(game);
		Scene scene = new Scene(pane, 1000, 600);
	    stage.setScene(scene);
	    stage.show();
	    game.run();
	}
	
}
