package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Gate;
import model.TileMap;
import model.Wire;

public class GateGUI extends Application {
	public static void main(String[] args) {
	    launch(args);
	  }
	private GatesView selector;
	public void start(Stage primaryStage) throws Exception {
	     
	    BorderPane sprint1 = new BorderPane();
	    sprint1.setTop(new Heading());
	    Gate gate= new Gate(new Wire(false,1,1),new Wire(true,1,3),"AND");
	    TileMap map= new TileMap(18,14);
	    map.setTile(5, 2, gate);
	    selector=new GatesView(map);
	    sprint1.setCenter(selector);
	    
	    // Add a BorderPane to the Scene
	    Scene scene = new Scene(sprint1, 1200, 900);
	    primaryStage.setScene(scene);
	    // Don't forget to show the running application:
	    primaryStage.show();
	    selector.run();
	}
}
