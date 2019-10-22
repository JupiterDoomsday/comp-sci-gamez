package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Gate;
import model.TileMap;
import model.Wire;

public class GatesView extends MinigameView implements Observer {
<<<<<<< HEAD
	private Canvas mainCanvas;
	private GraphicsContext gc;
	private Image emptyTile;
	private TileMap boardGame;
	private GridPane layOut;
	public GatesView(TileMap tileBoard) {
		boardGame= tileBoard;ArrayList<Wire>wires= boardGame.getListOfWires();
		try {
			emptyTile=new Image(new FileInputStream("Image/empty.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		for(int i=0;i<boardGame.getHeight();i++) {
			for(int j=0;j<boardGame.getWidth();j++) {
				ImageView im;
				if(boardGame.getTile(i,j)==null) {
					im=new ImageView(emptyTile);
					layOut.add(im,i,j);
				}
				else {
					im= new ImageView(boardGame.getTile(i,j).getImg());
					layOut.add(im,i,j);
				}
				im.setFitHeight(165);
				im.setFitWidth(165);
				im.setPreserveRatio(true);
			}
		}
		
		for(int i=0;i<wires.size();i++) {
			layOut.add(wires.get(i), wires.get(i).getX(), wires.get(i).getY());
		}
		this.getChildren().add(layOut);
=======
	private Canvas canvas;
	private GraphicsContext gc;
	private Label message;
	private Button w1;
	private Button w2;
	private Gate game;
	private GridPane testLayOut;
	public GatesView(Gate gate) {
		game= gate;
		canvas= new Canvas(1200,200);
		message=new Label();
		testLayOut= new GridPane();
		gc= canvas.getGraphicsContext2D();
		message.setFont(new Font("serif", 20));
		message.setText("AND GATE");
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
		//setTop(testLayOut);
		//setCenter(canvas);
>>>>>>> 8352c94042622a56596949af90ec50dd2e750b05
	}
	
	 /**
	   * This changes and updates the view every game sate change
	   */
	  @Override
	  public void update(Observable o, Object arg) {
	  }

	@Override
	public String settings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layoutScene() {
<<<<<<< HEAD
		mainCanvas = new Canvas(1000, 500);
		layOut= new GridPane();
=======
		// TODO Auto-generated method stub
		
>>>>>>> 8352c94042622a56596949af90ec50dd2e750b05
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		  if(boardGame.isFinished()) {
			  System.out.println("You solved it!");
			  stop();
		  }
		  System.out.println("Keep swimming");
=======
		
>>>>>>> 8352c94042622a56596949af90ec50dd2e750b05
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		System.out.println("You solved it!");
=======
		
>>>>>>> 8352c94042622a56596949af90ec50dd2e750b05
	}
}
