package view;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BinaryBattleView extends VBox{
	
	private ArrayList<Integer> listOfNumbers;
	private ArrayList<String> binaryStrings;
	private ArrayList<Text> textList;
	private int childToChange = 0;
	
	public BinaryBattleView() {
		
		listOfNumbers = new ArrayList<>();
		binaryStrings = new ArrayList<>();
		textList = new ArrayList<>();
		
		beginBinaryGame();
		
		layoutView();
	}
	
	private void beginBinaryGame() {
		
		// Begin generating random numbers
		Random rand = new Random();
		for(int i = 0;i < 10;i++) {
			
			int val;
			while(true) {
				val = rand.nextInt(16);
				
				if(!listOfNumbers.contains(val)) {
					break;
				}
			}
	
			
			
			String bString = Integer.toBinaryString(val);
			while(bString.length() < 4) {
				bString = "0" + bString;
			}
			//System.out.println("val: " + val + "  bString: " + bString);
			
			listOfNumbers.add(val);
			binaryStrings.add(bString);
		}
		
		
	}
	private void layoutView() {
		
		Image image = new Image("file:resources/image/binarybackground.png");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(bImage);
		this.setBackground(background);
		
		
		this.setMinWidth(1200);
		this.setMinHeight(900);
		VBox vBox = new VBox();
		//this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)) );
		Text welcomeMessage = new Text("Welcome to Binary Battle!");
		Text selectLevelMessage = new Text("Select the level you wish to play.");
		welcomeMessage.setFill(Color.WHITE);
		welcomeMessage.setFont(new Font("Sans-serif", 72));
		selectLevelMessage.setFill(Color.WHITE);
		selectLevelMessage.setFont(new Font("Sans-serif", 40));
		vBox.getChildren().add(welcomeMessage);
		vBox.getChildren().add(selectLevelMessage);
		
		String buttonStyle = "-fx-background-color: #007505;-fx-background-radius:25px;-fx-text-fill: white";
		String hoverStyle = "-fx-background-color: #00A207;-fx-background-radius:25px;-fx-text-fill: white";
		
		Button tutorialButton = new Button("Tutorial");
		tutorialButton.setMinHeight(150);
		tutorialButton.setMinWidth(500);
		tutorialButton.setFont(new Font("Sans-serif", 40));
		tutorialButton.setStyle(buttonStyle);
		tutorialButton.setOnMouseEntered(e -> {
			tutorialButton.setStyle(hoverStyle);
		});
		tutorialButton.setOnMouseExited(e -> {
			tutorialButton.setStyle(buttonStyle);
		});
		Button nimbleNibbles = new Button("Nimble Nibbles");
		nimbleNibbles.setMinHeight(150);
		nimbleNibbles.setMinWidth(500);
		nimbleNibbles.setFont(new Font("Sans-serif", 40));
		nimbleNibbles.setStyle(buttonStyle);
		nimbleNibbles.setOnMouseEntered(e -> {
			nimbleNibbles.setStyle(hoverStyle);
		});
		nimbleNibbles.setOnMouseExited(e -> {
			nimbleNibbles.setStyle(buttonStyle);
		});
		Button bulletBytes = new Button("Bullet Bytes");
		bulletBytes.setMinHeight(150);
		bulletBytes.setMinWidth(500);
		bulletBytes.setFont(new Font("Sans-serif", 40));
		bulletBytes.setStyle(buttonStyle);
		bulletBytes.setOnMouseEntered(e -> {
			bulletBytes.setStyle(hoverStyle);
		});
		bulletBytes.setOnMouseExited(e -> {
			bulletBytes.setStyle(buttonStyle);
		});
		
		
		vBox.getChildren().add(tutorialButton);
		vBox.getChildren().add(nimbleNibbles);
		vBox.getChildren().add(bulletBytes);
		vBox.setAlignment(Pos.CENTER);
		VBox.setMargin(welcomeMessage, new Insets(20, 20, 20, 20));
		VBox.setMargin(tutorialButton, new Insets(20, 20, 20, 20));
		VBox.setMargin(nimbleNibbles, new Insets(20, 20, 20, 20));
		VBox.setMargin(bulletBytes, new Insets(20, 20, 20, 20));
		this.getChildren().add(vBox);
		
		Text startMessage = new Text("Enter the base-10 values of the following numbers: ");
		startMessage.setFont(Font.font("Sans-serif", FontWeight.BOLD, 28));
		startMessage.setFill(Color.WHITE);
		//this.getChildren().add(startMessage);
		
		/*
		Text lineBreak = new Text("");
		Text num0 = new Text("   " + binaryStrings.get(0));
		Text num1 = new Text("   " + binaryStrings.get(1));
		Text num2 = new Text("   " + binaryStrings.get(2));
		Text num3 = new Text("   " + binaryStrings.get(3));
		Text num4 = new Text("   " + binaryStrings.get(4));
		Text num5 = new Text("   " + binaryStrings.get(5));
		Text num6 = new Text("   " + binaryStrings.get(6));
		Text num7 = new Text("   " + binaryStrings.get(7));
		Text num8 = new Text("   " + binaryStrings.get(8));
		Text num9 = new Text("   " + binaryStrings.get(9));
		num0.setFont(Font.font("Sans-serif", 20));
		num0.setFill(Color.DARKGREEN);
		num0.setId("#b0");
		num1.setFont(Font.font("Sans-serif", 20));
		num1.setFill(Color.DARKGREEN);
		num2.setFont(Font.font("Sans-serif", 20));
		num2.setFill(Color.DARKGREEN);
		num3.setFont(Font.font("Sans-serif", 20));
		num3.setFill(Color.DARKGREEN);
		num4.setFont(Font.font("Sans-serif", 20));
		num4.setFill(Color.DARKGREEN);
		num5.setFont(Font.font("Sans-serif", 20));
		num5.setFill(Color.DARKGREEN);
		num6.setFont(Font.font("Sans-serif", 20));
		num6.setFill(Color.DARKGREEN);
		num7.setFont(Font.font("Sans-serif", 20));
		num7.setFill(Color.DARKGREEN);
		num8.setFont(Font.font("Sans-serif", 20));
		num8.setFill(Color.DARKGREEN);
		num9.setFont(Font.font("Sans-serif", 20));
		num9.setFill(Color.DARKGREEN);
		this.getChildren().add(num0);
		this.getChildren().add(num1);
		this.getChildren().add(num2);
		this.getChildren().add(num3);
		this.getChildren().add(num4);
		this.getChildren().add(num5);
		this.getChildren().add(num6);
		this.getChildren().add(num7);
		this.getChildren().add(num8);
		this.getChildren().add(num9);
		*/
		
		for(int i = 0;i < listOfNumbers.size();i++) {
			Text newText = new Text("   " + binaryStrings.get(i));
			//System.out.println("TEST: newText is: " + newText.getText());
			newText.setFont(Font.font("Sans-serif", 20));
			newText.setFill(Color.WHITE);
			textList.add(newText);
			//this.getChildren().add(newText);
		}
		
		
		TextField userGuess = new TextField();
		userGuess.maxWidth(50);
		
		Button checkButton = new Button("Check");
		
		
		GridPane gp = new GridPane();
		gp.add(userGuess, 0, 0);
		GridPane.setMargin(userGuess, new Insets(20, 20, 20, 20));
		gp.add(checkButton, 1, 0);
		//this.getChildren().add(gp);
	
		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!userGuess.getText().isEmpty()) {
					for(int i = 0;i < listOfNumbers.size();i++) {
						if(Integer.parseInt(userGuess.getText()) == (listOfNumbers.get(i))) {
							System.out.println("Match found at position: " + i);
							childToChange = i;
							System.out.println("Child to change is: " + childToChange);
							textList.get(i).setFill(Color.GREEN);
							checkIfWin();
							
						}
					}
				}
				
				userGuess.clear();
			}
		});
		

		
		
		
		
	}
	
	
	private void checkIfWin() {
		
		boolean didWin = true;
		
		for(Text text : textList) {
			if(text.getFill() != Color.GREEN) {
				didWin = false;
			}
		}
		
		if(didWin) {
			Label winMessage = new Label("Congratulations! You won!");
			winMessage.setFont(new Font("Sans-serif", 48));
			this.getChildren().add(winMessage);
		}
	}
	

}
