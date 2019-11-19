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
import model.Challenge2GatesGame;
import model.Challenge3GatesGame;
import model.Gate;
import model.GatesGame;
import model.ORGatesGame;
import model.Wire;
import model.XORGateGame;
import model.challenge1GatesGame;

public class GatesView extends MinigameView {
	private BorderPane bp;
	private StackPane stack;
	private Image emptyTile;
	private Image lampOn;
	private Image lampOff;
	private Image textBox;
	private Label text;
	private VBox vBox;
	private GridPane layOut;
	private ANDGateGame and;
	private ORGatesGame or;
	private XORGateGame xor;
	private challenge1GatesGame ch1;
	private Challenge2GatesGame ch2;
	private Challenge3GatesGame ch3;
	private Button ORButton;
	private Button ANDButton;
	private Button XORButton;
	private Button challenge1;
	private Button challenge3;
	private Button challenge2;
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
		bp.getChildren().add(layOut);
		bp.getChildren().add(back);
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
		this.setMinWidth(1200);
		this.setMinHeight(900);
		BackgroundFill background_fill = new BackgroundFill(Color.rgb(8,20,30),CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(background_fill); 
		bp= new BorderPane();
		bp.setMinHeight(900);
		bp.setMinWidth(1200);
		stack= new StackPane();
		stack.setMinHeight(900);
		stack.setMinWidth(1200);
		layOut= new GridPane();
		and = new ANDGateGame();
		or= new ORGatesGame();
		xor= new XORGateGame();
		ch1 = new challenge1GatesGame();
		ch2= new Challenge2GatesGame();
		ch3= new Challenge3GatesGame();
		text=new Label();
		
		//setting up the buttons
		ORButton=new Button("OR Gate");
		ANDButton=new Button("AND Gate");
		XORButton=new Button("XOR Gate");
		challenge1= new Button("Challenge 1");
		challenge3= new Button("Challenge 3");
		challenge2= new Button("Challenge 2");
		tutorial= new Button("Tutorial");
		back = new Button("Back");
		setButtons();
		setMenu();
		//seting up the events for each menu button
		back.setOnAction( ae -> {
			this.getChildren().clear();
			this.getChildren().add(vBox);
		});
		ANDButton.setOnAction( ae -> {
			bp.getChildren().clear();
			setGatesView(and);
		});
		ORButton.setOnAction( ae -> {
			bp.getChildren().clear();
			setGatesView(or);
		});
		XORButton.setOnAction( ae -> {
			bp.getChildren().clear();
			setGatesView(xor);
		});
		challenge1.setOnAction( ae -> {
			bp.getChildren().clear();
			setGatesView(ch1);
		});
		challenge2.setOnAction( ae -> {
			bp.getChildren().clear();
			setGatesView(ch2);
		});
		challenge3.setOnAction( ae -> {
			bp.getChildren().clear();
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
		bp.setCenter(vBox);
		this.getChildren().add(bp);	
	}
	private void setTutorial() {
		ObservableList list = stack.getChildren();
		list.addAll(text,textBox,layOut);
	}
	
	//this helper function sets up the events for the button styles and whatnot
	private void setButtons() {
		String buttonStyle = "-fx-background-color: #c3a38a;-fx-border-width: 0 3 3 0;-fx-background-radius:0px; -fx-border-color: #997577;";
		String hoverStyle = "-fx-background-color: #997577;-fx-border-width: 0 3 3 0; -fx-background-insets: 0; -fx-background-radius:0px; -fx-border-color: #c3a38a;";
		String backButtonStyle="-fx-background-color: #816271;-fx-border-width: 3 3 3 3; -fx-background-radius:0px;-fx-border-color:#4e495f;";
		//XOR button style
		back.setStyle(backButtonStyle);
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
		//OR button style
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
		//AND button style
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
		//challenge1 button style
		challenge1.setMinHeight(50);
		challenge1.setMinWidth(125);
		challenge1.setStyle(buttonStyle);
		challenge1.setOnMouseEntered(e -> {
			challenge1.setStyle(hoverStyle);
		});
		challenge1.setOnMouseExited(e -> {
			challenge1.setStyle(buttonStyle);
		});
		//challenge2 button style
				challenge2.setMinHeight(50);
				challenge2.setMinWidth(125);
				challenge2.setStyle(buttonStyle);
				challenge2.setOnMouseEntered(e -> {
					challenge2.setStyle(hoverStyle);
				});
				challenge2.setOnMouseExited(e -> {
					challenge2.setStyle(buttonStyle);
				});
		//challenge3 button style
		challenge3.setMinHeight(50);
		challenge3.setMinWidth(125);
		challenge3.setStyle(buttonStyle);
		challenge3.setOnMouseEntered(e -> {
			challenge3.setStyle(hoverStyle);
		});
		challenge3.setOnMouseExited(e -> {
			challenge3.setStyle(buttonStyle);
		});
	}
	public void setMenu() {
		bp.setBackground(background);
		vBox = new VBox();
		Label l= new Label("Level Select");
		l.setFont(new Font("Sans-serif", 40));
		l.setTextFill(Color.WHITE);
		vBox.getChildren().add(l);
		vBox.getChildren().add(ANDButton);
		vBox.getChildren().add(ORButton);
		vBox.getChildren().add(XORButton);
		vBox.getChildren().add(challenge1);
		vBox.getChildren().add(challenge2);
		vBox.getChildren().add(challenge3);
		vBox.setAlignment(Pos.CENTER);
		VBox.setMargin(ANDButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(ORButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(XORButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(challenge1, new Insets(20, 20, 20, 20));
		VBox.setMargin(challenge2, new Insets(20, 20, 20, 20));
		VBox.setMargin(challenge3, new Insets(20, 20, 20, 20));
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
