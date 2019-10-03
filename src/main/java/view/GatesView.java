package view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Gate;

public class GatesView extends BorderPane implements Observer {
	private Canvas canvas;
	private GraphicsContext gc;
	private Label message;
	private Button w1;
	private Button w2;
	private Gate game;
	private GridPane testLayOut;
	public GatesView(Gate gate) {
		game= gate;
		canvas= new Canvas(200,200);
		testLayOut= new GridPane();
		gc= canvas.getGraphicsContext2D();
		setTop(message);
		w1=new Button();
		w1.setText("Switch 1");
		w2= new Button();
		w2.setText("Switch 2");
		
		w1.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		game.getW1().invert();
	    		checkGate();
	    		System.out.println("Switch 1: "+game.getW1().state);
	    	}
	    });
		w2.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		game.getW2().invert();
	    		checkGate();
	    		System.out.println("Switch 2: "+game.getW1().state);
	    	}
	    });
		testLayOut.add(w1,0,0);
		testLayOut.add(w2,0,1);
		initializePane();
		setTop(testLayOut);
		setCenter(canvas);
	}
	
	 private void initializePane() {
		 if(game.getW1().state)
			 gc.setStroke(Color.GREEN);
		 else
			 gc.setStroke(Color.RED);
		 gc.strokeLine(0, 10, 128, 10);
		 
		 if(game.getW2().state)
			 gc.setStroke(Color.GREEN);
		 else
			 gc.setStroke(Color.RED);
		 gc.strokeLine(0, 25, 128, 25);
		 gc.setStroke(Color.BLUE);
		 gc.strokeOval(130, 0,40, 40);
	 }
	 private void checkGate() {
		 gc.clearRect(0, 0, 200, 200);
		 initializePane();
		 if(game.gateOutput()) {
				System.out.println(game.getLogic()+" GATE is true");
				gc.setFill(Color.YELLOW);
				gc.fillOval(130, 0,40, 40);
			}
	 }
	 /**
	   * This changes and updates the view every game sate change
	   */
	  @Override
	  public void update(Observable o, Object arg) {
		  //gc.clearRect(0,0,200,200);
			 if(game.getW1().state)
				 gc.setFill(Color.GREEN);
			 else
				 gc.setFill(Color.RED);
			 gc.strokeLine(0, 0, canvas.getWidth()/2, 0);
			 if(game.getW2().state)
				 gc.setFill(Color.GREEN);
			 else
				 gc.setFill(Color.RED);
			 gc.setFill(Color.BLUE);
			 gc.fillOval(64, 0,64, 0);
			 gc.strokeLine(0, 25, canvas.getWidth()/2, 25);
		  
	  }
}
