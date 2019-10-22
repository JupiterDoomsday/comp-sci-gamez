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
	//private GraphicsContext gc;
	private Image emptyTile;
	private Image lampOn;
	private Image lampOff;
	private TileMap boardGame;
	private GridPane layOut;
	public GatesView(TileMap tileBoard) {
		boardGame= tileBoard;ArrayList<Wire>wires= boardGame.getListOfWires();

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
					layOut.add(new  ImageView(lampOff),boardGame.getTile(i,j).getX(),boardGame.getTile(i,j).getY());
				}
			}
		}
		
		for(int i=0;i<wires.size();i++) {
			Wire w= wires.get(i);
			w.setOnAction( ae -> {
				w.invert();
				if(w.state())
					w.setGraphic(new ImageView(w.getOnImg()));
				else
					w.setGraphic(new ImageView(w.getOffImg()));
				run();
			});
			w.setStyle("-fx-padding: 0 0 0 0; -fx-margin: 0 0 0 0;");
			layOut.add(w, wires.get(i).getX(), wires.get(i).getY());
		}
	}
	 /**
	   * This changes and updates the view every game sate change
	   */
	  public void update(Wire w) {
			  System.out.println("Button pressed");
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
		try {
			emptyTile=new Image(new FileInputStream("Image/empty.png"));
			lampOff=new Image(new FileInputStream("Image/lightbulb.png"));
			lampOn=new Image(new FileInputStream("Image/lightbulb_lit.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		this.getChildren().add(layOut);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Gate> gates= boardGame.getListOfGates();
		for(int i=0;i< gates.size();i++) {
			if(gates.get(i).gateOutput())
				layOut.add(new  ImageView(lampOn), gates.get(i).getX(), gates.get(i).getY());
			else 
				layOut.add(new  ImageView(lampOff), gates.get(i).getX(), gates.get(i).getY());
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("You solved it!");
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Button pressed");
	}
}
