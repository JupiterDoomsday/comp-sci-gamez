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
				if(boardGame.getTile(i,j)==null) {
					layOut.add(new ImageView(emptyTile),i,j);
				}
				else
					layOut.add(new ImageView(boardGame.getTile(i,j).getImg()),i,j);
			}
		}
		
		for(int i=0;i<wires.size();i++) {
			layOut.add(wires.get(i), wires.get(i).getX(), wires.get(i).getY());
		}
		this.getChildren().add(layOut);
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
		mainCanvas = new Canvas(1000, 500);
		layOut= new GridPane();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		  if(boardGame.isFinished()) {
			  System.out.println("You solved it!");
			  stop();
		  }
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("You solved it!");
	}
}
