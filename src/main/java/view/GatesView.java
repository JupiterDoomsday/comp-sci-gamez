package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Queue;

import javafx.scene.media.Media;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;


import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	private StackPane textBox;
	private ImageView mascot;
	private Image bitwise;
	private Image gateScreen;
	private Image gateExample;
	private Image lightExample;
	private Image offExample;
	private Image onExample;
	private Label text;
	private VBox vBox;
	//private Queue<GatesGame> levels;
	private GridPane layOut;
	private ANDGateGame and;
	private ORGatesGame or;
	private XORGateGame xor;
	private MediaPlayer mediaPlayer;
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
	private int count;
	private String[] dialouge= {"Oh my! It seems you need a course on gates!",
			"No worry! I Commie-chan am here to assist you!",
			"First things first, Us computers represent our logic in binary bits of 1's and 0's",
			"True is represented by 1 and false is represented by 0",
			"In this minigame FALSE is represented by an off switch", 
			"While TRUE is represented by an on switch!",
			"Each switch represents a wire connected to a gate",
			"Our goal is to figure what sequence of switches will make our gate output true", 
			"Our Gate output is represented by a lightbulb",
			"On is TRUE while off is FALSE",
			"different gates require different sequences of switch outputs,\nSo I highly encourage you try out the levels yourself and practice"};
	
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
						layOut.add(new ImageView(lampOn),g.getX(),g.getY());
					else
						layOut.add(new ImageView(lampOff),g.getX(),g.getY());
				}
			}
		}
		
		for(int i=0;i<wires.size();i++) {
			Wire w= wires.get(i);
			w.setOnAction( ae -> {
				w.invert();
				 if(w.state()) {
						w.setGraphic(new ImageView(w.getOnImg()));
						playSound("./resources/gatesgameSoundFX/footstep.wav");
				 }
					else {
						w.setGraphic(new ImageView(w.getOffImg()));
						playSound("./resources/gatesgameSoundFX/switch.wav");
					}
				checkState();
				
			});
			w.setStyle("-fx-padding: 0 0 0 0; -fx-margin: 0 0 0 0;");
			layOut.add(w, wires.get(i).getX(), wires.get(i).getY());
		}
		bp.getChildren().add(0,layOut);
		bp.getChildren().add(1,back);
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
		//levels= new LinkedList<GatesGame>();
		text= new Label();
		textBox= new StackPane();
		text.setFont(new Font("Sans-serif", 250));
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
		challenge1= new Button("Challenge");
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
			this.getChildren().clear();
			setTutorial();
		});
		//set up the images
		ImageView img=null;
		try {
			emptyTile=new Image(new FileInputStream("Image/empty.png"));
			lampOff=new Image(new FileInputStream("Image/lightbulb.png"));
			lampOn=new Image(new FileInputStream("Image/lightbulb_lit.png"));
			img= new ImageView(new Image(new FileInputStream("Image/text_box.png")));
			mascot= new ImageView(new Image(new FileInputStream("Image/commie-chan.png")));
			gateScreen= new Image(new FileInputStream("Image/gates_screen.png"));
			offExample= new Image(new FileInputStream("Image/switch_off_example.png"));
			onExample= new Image(new FileInputStream("Image/switch_on_example.png"));
			bitwise= new Image(new FileInputStream("Image/example.png"));
			gateExample= new Image(new FileInputStream("Image/gateExample.png"));
			lightExample= new Image(new FileInputStream("Image/lightbulbExample.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textBox.getChildren().addAll(img,text);
		bp.setCenter(vBox);
		this.getChildren().add(bp);	
		stack.getChildren().add(mascot);
		stack.getChildren().add(textBox);
		
	}
	private void setTutorial() { 
		stack.setBackground(background);
		TranslateTransition animation= new TranslateTransition();
		animation.setDuration(Duration.millis(3000));
		animation.setNode(mascot);
		animation.setFromY(-50);
		animation.setFromX(850);
		animation.setByX(-600);
		TranslateTransition textBoxAni= new TranslateTransition();
		textBoxAni.setDuration(Duration.millis(2000));
		textBoxAni.setFromY(400);
		//textBoxAni.setFromX(10);
		textBoxAni.setByY(-100);
		textBoxAni.setNode(textBox);
		animation.play();
		textBoxAni.play();
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent e) { 
				   text.setText("");
				   if(count == dialouge.length) {
					count=0;
					stack.getChildren().remove(0);
					getChildren().clear();
					getChildren().add(bp);
					mediaPlayer.stop();
					return;
				   }
				   else if(count==2) {
					   stack.getChildren().add(0,new ImageView(bitwise));
				   }
				   else if(count==4) {
					   stack.getChildren().remove(0);
					   stack.getChildren().add(0,new ImageView(offExample));
				   }
				   else if(count==5) {
					   stack.getChildren().remove(0);
					   stack.getChildren().add(0,new ImageView(onExample));
				   }
				   else if(count==6) {
					   stack.getChildren().remove(0);
					   stack.getChildren().add(0,new ImageView(gateExample));
				   }
				   else if(count==8) {
					   stack.getChildren().remove(0);
					   stack.getChildren().add(0,new ImageView(lightExample));
				   }
				   else if(count==10) {
					   stack.getChildren().remove(0);
					   stack.getChildren().add(0, new ImageView(gateScreen));
				   }
				   AnimateText(text,dialouge[count]);
				   count++;
			   } 
			};
		stack.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		this.getChildren().add(stack);
		playSound("./resources/gatesgameSoundFX/Aso - Chillhop Essentials - Summer 2016 - 14 Ultra Violet.mp3");
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
		//challenge3 button style
				tutorial.setMinHeight(50);
				tutorial.setMinWidth(125);
				tutorial.setStyle(buttonStyle);
				tutorial.setOnMouseEntered(e -> {
					tutorial.setStyle(hoverStyle);
				});
				tutorial.setOnMouseExited(e -> {
					tutorial.setStyle(buttonStyle);
				});
	}
	public void setMenu() {
		bp.setBackground(background);
		vBox = new VBox();
		Label l= new Label("Level Select");
		l.setFont(new Font("Sans-serif", 40));
		l.setTextFill(Color.WHITE);
		//buttons
		vBox.getChildren().add(l);
		vBox.getChildren().add(tutorial);
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
		VBox.setMargin(tutorial, new Insets(20, 20, 20, 20));
	}
	private void checkState() {
		//System.out.println("Checking state of game");
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
	private void playSound(String file) {
		Media sound = new Media(new File(file).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
		
	}
}
