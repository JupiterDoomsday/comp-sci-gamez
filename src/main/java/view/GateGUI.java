package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Gate;
import model.Wire;

public class GateGUI extends Application {
	public static void main(String[] args) {
	    launch(args);
	  }
	private GatesView selector;
	public void start(Stage primaryStage) throws Exception {
	     
	    BorderPane sprint1 = new BorderPane();
	    sprint1.setTop(new Heading());
	    Gate gate= new Gate(new Wire(false),new Wire(true),"AND");
	    selector=new GatesView(gate);
	    sprint1.setCenter(selector);
	    
	    // Add a BorderPane to the Scene
	    Scene scene = new Scene(sprint1, 1400, 800);
	    primaryStage.setScene(scene);
	    // Don't forget to show the running application:
	    primaryStage.show();
	}
}
