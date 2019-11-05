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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ANDGateGame;
import model.Gate;
import model.GatesGame;
import model.ORGatesGame;
import model.Wire;
import model.XORGateGame;

public class GatesView extends MinigameView {
	private Canvas mainCanvas;
	private Image emptyTile;
	private Image lampOn;
	private Image lampOff;
	private GridPane layOut;
	private ANDGateGame and;
	private ORGatesGame or;
	private XORGateGame xor;
	private Button ORButton;
	private Button ANDButton;
	private Button XORButton;
	private Button back;
	GatesGame curGame;
	public GatesView() {
		
	}
	private void  setGatesView(GatesGame game) {
		curGame=game;
		layOut.getChildren().clear();
		ArrayList<Wire>wires= game.getListOfWires();

		for(int i=0;i<game.getHeight();i++) {
			for(int j=0;j<game.getWidth();j++) {
				ImageView im;
				if(game.getTile(j,i)==null) {
					
					if(game.getTileMap(j,i)==null)
						im=new ImageView(emptyTile);
					else
						im=new ImageView(game.getTileMap(j,i));
					layOut.add(im,j,i);
				}
				else {
					Gate g= game.getTile(j,i);
					im= new ImageView(g.getImg());
					layOut.add(im,j,i);
					if(g.gateOutput())
						layOut.add(new  ImageView(lampOn),g.getX(),g.getY());
					else
						layOut.add(new  ImageView(lampOff),g.getX(),g.getY());
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
				checkState();
			});
			w.setStyle("-fx-padding: 0 0 0 0; -fx-margin: 0 0 0 0;");
			layOut.add(w, wires.get(i).getX(), wires.get(i).getY());
		}
		this.getChildren().add(layOut);
		this.getChildren().add(back);
	}
	 /**
	   * This changes and updates the view every game sate change
	   */
	  public void update(Wire w) {
			  System.out.println("Button pressed");
			  if(w.state())
					w.setGraphic(new ImageView(w.getOnImg()));
			 else
					w.setGraphic(new ImageView(w.getOffImg()));
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
		and = new ANDGateGame();
		or= new ORGatesGame();
		xor= new XORGateGame();
		ORButton=new Button("OR Gate");
		ANDButton=new Button("AND Gate");
		XORButton=new Button("XOR Gate");
		back = new Button("Back");
		back.setOnAction( ae -> {
			this.getChildren().clear();
			setMenu();
		});
		ANDButton.setOnAction( ae -> {
			this.getChildren().clear();
			setGatesView(and);
		});
		ORButton.setOnAction( ae -> {
			this.getChildren().clear();
			setGatesView(or);
		});
		XORButton.setOnAction( ae -> {
			this.getChildren().clear();
			setGatesView(xor);
		});
		try {
			emptyTile=new Image(new FileInputStream("Image/empty.png"));
			lampOff=new Image(new FileInputStream("Image/lightbulb.png"));
			lampOn=new Image(new FileInputStream("Image/lightbulb_lit.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		setMenu();
		//
		// TODO Auto-generated method stub
		
	}
	public void setMenu() {
		VBox vbox= new VBox();
		vbox.getChildren().add(new Label("Choose your level"));
		vbox.getChildren().add(ANDButton);
		vbox.getChildren().add(ORButton);
		vbox.getChildren().add(XORButton);
		this.getChildren().add(vbox);
	}
	private void checkState() {
		ArrayList<Gate> gates= curGame.getListOfGates();
		for(int i=0;i< gates.size();i++) {
			if(gates.get(i).gateOutput())
				layOut.add(new  ImageView(lampOn), gates.get(i).getX(), gates.get(i).getY());
			else 
				layOut.add(new  ImageView(lampOff), gates.get(i).getX(), gates.get(i).getY());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("You solved it!");
		
	}
}
