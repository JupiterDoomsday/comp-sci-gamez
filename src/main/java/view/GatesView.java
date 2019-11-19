package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ANDGateGame;
import model.Challenge3GatesGame;
import model.Gate;
import model.GatesGame;
import model.ORGatesGame;
import model.Wire;
import model.XORGateGame;

public class GatesView extends MinigameView {
	private Image emptyTile;
	private Image lampOn;
	private Image lampOff;
	private Image textBox;
	private Label text;
	private GridPane layOut;
	private ANDGateGame and;
	private ORGatesGame or;
	private XORGateGame xor;
	private Challenge3GatesGame ch3;
	private Button ORButton;
	private Button ANDButton;
	private Button XORButton;
	private Button challenge3;
	private Button tutorial;
	private Button back;
	private Background background;
	private BackgroundFill background_fill;
	
	GatesGame curGame;
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
		//layOut.setAlignment(Pos.CENTER);
		//back.setAlignment(Pos.BOTTOM_RIGHT);
	}
	 /**
	   * This changes and updates the view every game sate change
	   */
	  public void update(Wire w) {
			 // System.out.println("Button pressed");
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
		BackgroundFill background_fill = new BackgroundFill(Color.rgb(8,20,30),CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(background_fill); 
		this.setMinWidth(1200);
		this.setMinHeight(900);
		layOut= new GridPane();
		and = new ANDGateGame();
		or= new ORGatesGame();
		xor= new XORGateGame();
		ch3= new Challenge3GatesGame();
		text=new Label();
		String buttonStyle = "-fx-background-color: #c3a38a;-fx-border-width: 0 3 3 0;-fx-background-radius:0px;";
		String hoverStyle = "-fx-background-color: #816271;-fx-border-width: 0 3 3 0; -fx-background-insets: 0; -fx-background-radius:0px;";
		//setting up the buttons
		ORButton=new Button("OR Gate");
		ORButton.setFont(new Font("Sans-serif", 20));
		ORButton.setStyle(buttonStyle);
		ORButton.setMinHeight(50);
		ORButton.setMinWidth(125);
		ORButton.setOnMouseEntered(e -> {
			ORButton.setStyle(hoverStyle);
		});
		ORButton.setOnMouseExited(e -> {
			ORButton.setStyle(buttonStyle);
		});
		
		ANDButton=new Button("AND Gate");
		ANDButton.setFont(new Font("Sans-serif", 20));
		ANDButton.setMinHeight(50);
		ANDButton.setMinWidth(125);
		ANDButton.setStyle(buttonStyle);
		ANDButton.setOnMouseEntered(e -> {
			ANDButton.setStyle(hoverStyle);
		});
		ANDButton.setOnMouseExited(e -> {
			ANDButton.setStyle(buttonStyle);
		});
		
		XORButton=new Button("XOR Gate");
		XORButton.setFont(new Font("Sans-serif", 20));
		XORButton.setMinHeight(50);
		XORButton.setMinWidth(125);
		XORButton.setStyle(buttonStyle);
		XORButton.setOnMouseEntered(e -> {
			XORButton.setStyle(hoverStyle);
		});
		XORButton.setOnMouseExited(e -> {
			XORButton.setStyle(buttonStyle);
		});
		challenge3= new Button("Challenge 3");
		challenge3.setMinHeight(50);
		challenge3.setMinWidth(125);
		challenge3.setStyle(buttonStyle);
		challenge3.setOnMouseEntered(e -> {
			challenge3.setStyle(hoverStyle);
		});
		challenge3.setOnMouseExited(e -> {
			challenge3.setStyle(buttonStyle);
		});
		tutorial= new Button("Tutorial");
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
		challenge3.setOnAction( ae -> {
			this.getChildren().clear();
			setGatesView(ch3);
		});
		tutorial.setOnAction( ae -> {
			//this.getChildren().clear();
			setTutorial();
		});
		//set up the images
		try {
			emptyTile=new Image(new FileInputStream("Image/empty.png"));
			lampOff=new Image(new FileInputStream("Image/lightbulb.png"));
			lampOn=new Image(new FileInputStream("Image/lightbulb_lit.png"));
			textBox= new Image(new FileInputStream("Image/invo-Background.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMenu();
		
		//
		// TODO Auto-generated method stub
		
	}
	public void setTutorial() {
		//ObservableList list = stackPane.getChildren();
		//list.addAll(text,textBox,layOut);
		
		
	}
	public void setMenu() {
		this.setBackground(background);
		VBox vBox = new VBox();
		Label l= new Label("Choose your level");
		l.setFont(new Font("Sans-serif", 40));
		l.setTextFill(Color.WHITE);
		vBox.getChildren().add(l);
		vBox.getChildren().add(ANDButton);
		vBox.getChildren().add(ORButton);
		vBox.getChildren().add(XORButton);
		vBox.getChildren().add(challenge3);
		vBox.setAlignment(Pos.CENTER);
		vBox.prefHeight(900);
		vBox.prefWidth(1200);
		VBox.setMargin(ANDButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(ORButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(XORButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(challenge3, new Insets(20, 20, 20, 20));
		this.getChildren().add(vBox);
		
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
	public void AnimateText(Label lbl, String descImp) {
	    String content = descImp;
	    final Animation animation = new Transition() {
	        {
	            setCycleDuration(Duration.millis(2000));
	        }

	        protected void interpolate(double frac) {
	            final int length = content.length();
	            final int n = Math.round(length * (float) frac);
	            lbl.setText(content.substring(0, n));
	        }
	    };
	    animation.play();

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
