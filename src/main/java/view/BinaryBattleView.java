package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.BinaryBattleModel;
import model.GameDatabaseHandler;

public class BinaryBattleView extends VBox{
	
	private BinaryBattleModel model;
	private ArrayList<Integer> listOfNumbers;
	private ArrayList<String> binaryStrings;
	private ArrayList<Text> textList;
	//private int childToChange = 0;
	private VBox vBox;
	private int interval = 60;
	//private int score = 0;
	int numsCompleted = 0;
	
	public BinaryBattleView() {
		
		model = new BinaryBattleModel();
		
		listOfNumbers = new ArrayList<>();
		binaryStrings = new ArrayList<>();
		textList = new ArrayList<>();
		
		
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
		vBox = new VBox();
		//this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)) );
		Text welcomeMessage = new Text("Welcome to Binary Battle!");
		Text selectLevelMessage = new Text("Select the level you wish to play:");
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
		
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				vBox.getChildren().clear();
				layoutTutorial();
			}
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
		
		nimbleNibbles.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				vBox.getChildren().clear();
				layoutNimbleNibbles();
			}
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
		bulletBytes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				vBox.getChildren().clear();
				layoutBulletBytes();
			}
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

		/*		

		
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
		*/

	}
	
	private void layoutTutorial() {
		vBox.setAlignment(Pos.TOP_LEFT);
		
		TextFlow flow = new TextFlow();
		
		Text text1 = new Text("Binary Battle");
		text1.setFill(Color.LAWNGREEN);
		text1.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		Text text2 = new Text(" is all about converting binary numbers to their decimal form.\n\n");
		text2.setFill(Color.WHITE);
		text2.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text3 = new Text("Before we get started, we need to cover three basic concepts:\n\n");
		text3.setFill(Color.WHITE);
		text3.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text4 = new Text(" - Binary numbers are made up of individual ");
		text4.setFill(Color.WHITE);
		text4.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text5 = new Text("bits");
		text5.setFill(Color.WHITE);
		text5.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		text5.setUnderline(true);
		Text text6 = new Text(" which can be either a 0, or a 1.\n");
		text6.setFill(Color.WHITE);
		text6.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text7 = new Text(" - A ");
		text7.setFill(Color.WHITE);
		text7.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text8 = new Text("nibble");
		text8.setFill(Color.WHITE);
		text8.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		text8.setUnderline(true);
		Text text9 = new Text(" is made up of 4 bits, such as '0001'.\n");
		text9.setFill(Color.WHITE);
		text9.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text10 = new Text(" - And a ");
		text10.setFill(Color.WHITE);
		text10.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text11 = new Text("byte");
		text11.setFill(Color.WHITE);
		text11.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		text11.setUnderline(true);
		Text text12 = new Text(" is made up of 8 bits, such as '1000 0001'.\n\n");
		text12.setFill(Color.WHITE);
		text12.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text13 = new Text("In-game, you can view the chart on the right hand side to see how binary numbers are read. Basically, they \nare read "
				+ "right-to-left, where each position is a power of 2. So the far right digit is 2^0, which is equal to 1. \nThe next spot "
				+ "is 2^1, which is equal to 2, and so on.\n\n");
		text13.setFill(Color.WHITE);
		text13.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text14 = new Text("In ");
		text14.setFill(Color.WHITE);
		text14.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text15 = new Text("Nimble Nibbles");
		text15.setFill(Color.LAWNGREEN);
		text15.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		Text text16 = new Text(" the objective is to turn nibbles (like '0010') into their decimal form (in this case, 2).\nYou will be timed on how fast "
				+ "you are able to convert 10 nibbles to their decimal equivalent.\nThe shorter your time, the higher you'll be placed on the leaderboards.\n\n");
		text16.setFill(Color.WHITE);
		text16.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text17 = new Text("Bullet Bytes");
		text17.setFill(Color.LAWNGREEN);
		text17.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.ITALIC, 28));
		Text text18 = new Text(" is a little harder, where you have 60 seconds to convert as many bytes (like '1001 0011')\ninto their decimal form. The more you convert correctly, the higher"
				+ "you'll be placed on the leaderboards.\n\n");
		text18.setFill(Color.WHITE);
		text18.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text19 = new Text("Let's try an example. Convert the binary number '");
		text19.setFill(Color.WHITE);
		text19.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text20 = new Text("0011");
		text20.setFill(Color.LAWNGREEN);
		text20.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		Text text21 = new Text("' into it's equivalent decimal form. Click the button to \nsee if you got it correct, and then head back to the main menu and select a game.");
		text21.setFill(Color.WHITE);
		text21.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		
		flow.getChildren().addAll(text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17, text18, text19, text20, text21);
		vBox.getChildren().add(flow);
		
		GridPane gp = new GridPane();
		TextField textField = new TextField();
		textField.requestFocus();
		textField.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		textField.setMaxWidth(100.0);
		textField.setAlignment(Pos.CENTER);
		textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    textField.setText(oldValue);
                }
            }
        });
		
		gp.add(textField, 0, 0);
		GridPane.setMargin(textField, new Insets(20, 20, 20, 20));
		
		Button checkButton = new Button("Check answer");
		checkButton.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
		checkButton.setMinHeight(40.0);
		checkButton.setMinWidth(100.0);
		checkButton.setDefaultButton(true);
		gp.add(checkButton, 1, 0);
		GridPane.setMargin(checkButton, new Insets(20, 20, 20, 20));
		
		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(textField.getText().equals("3")) {
					checkButton.setText("Correct!");
					checkButton.setStyle("-fx-text-fill: green");
				} else {
					checkButton.setText("Incorrect");
					checkButton.setStyle("-fx-text-fill: red");
				}
			}
		});		
		
		gp.setAlignment(Pos.CENTER);
		vBox.getChildren().add(gp);
		
		Button backToMenu = new Button("Back to Menu");
		backToMenu.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 32));
		backToMenu.setMinHeight(60.0);
		backToMenu.setMinWidth(200.0);
		backToMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vBox.getChildren().clear();
				layoutView();
			}
		});	

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(backToMenu);
		VBox.setMargin(backToMenu, new Insets(20, 20, 20, 20));
		VBox.setMargin(flow, new Insets(20, 20, 20, 20));
	}
	
	private void layoutNimbleNibbles() {
		
		vBox.getChildren().clear();
		
		Text startMessage = new Text("\n\n\n\nTime yourself and try to convert 10 numbers as fast as possible.");
		startMessage.setFill(Color.WHITE);
		startMessage.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(startMessage);
		VBox.setMargin(startMessage,  new Insets(20, 20, 20, 20));
		
		Button start = new Button("Start");
		start.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 72));
		vBox.getChildren().add(start);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				vBox.getChildren().clear();
				
				Text timerText = new Text("Time: 0");
				timerText.setFill(Color.WHITE);
				timerText.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 40));
				vBox.setAlignment(Pos.CENTER);
				vBox.getChildren().add(timerText);
				VBox.setMargin(timerText, new Insets(20, 30, 20, 20));
				
				
				
				interval = 0;
				Timer timer = new Timer(true);
				
				

				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {

						System.out.println("numsCompleted: " + numsCompleted);
						if(numsCompleted < 1) {
							System.out.println("Increasing interval");
							interval++;
							Platform.runLater(() -> timerText.setText("Time: " + interval));
							
						} else {
							
							System.out.println("0 reached, break");
							
							Platform.runLater(() -> {
								vBox.getChildren().clear();
								Text finalScore = new Text("Final Score: " + interval + " seconds");
								finalScore.setFill(Color.WHITE);
								finalScore.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 100));
								vBox.getChildren().add(finalScore);
								vBox.setAlignment(Pos.BOTTOM_CENTER);
								VBox.setMargin(finalScore, new Insets(20, 20, 20, 20));
							});
							
							timer.cancel();
						}
					}
				}, 1000, 1000);
				
				Text binaryNumber = new Text();
				binaryNumber.setText(model.generateRandomNibble());
				System.out.println("Binary number: " + binaryNumber.getText());
				binaryNumber.setFill(Color.WHITE);
				binaryNumber.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 100));
				vBox.setAlignment(Pos.BOTTOM_CENTER);
				vBox.getChildren().add(binaryNumber);
				
				GridPane gp = new GridPane();
				TextField textField = new TextField();
				textField.requestFocus();
				textField.setMinHeight(50);
				textField.setMinWidth(150);
				textField.setAlignment(Pos.CENTER);
				textField.textProperty().addListener(new ChangeListener<String>() {
		            @Override
		            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		                if (!newValue.matches("\\d{0,3}?")) {
		                    textField.setText(oldValue);
		                }
		            }
		        });
				gp.add(textField, 0, 0);
				
				Button checkButton = new Button("Check answer");
				checkButton.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
				checkButton.setMinHeight(40.0);
				checkButton.setMinWidth(150.0);
				checkButton.setDefaultButton(true);
				gp.add(checkButton, 1, 0);
				gp.setAlignment(Pos.CENTER);
				GridPane.setMargin(checkButton, new Insets(20, 20, 20, 20));
				gp.setPadding(new Insets(70, 70, 70, 70));
				
				System.out.println("Current byte num: " + model.currentNibbleNum);
				
				checkButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(Integer.parseInt(textField.getText()) == model.currentNibbleNum) {
							textField.clear();
							model.score++;
							numsCompleted++;
							binaryNumber.setText(model.generateRandomNibble());
						}
					}
				});		
				
				vBox.getChildren().add(gp);
				
				
			}
		});
		
	}
	
	private void layoutBulletBytes() {
		
		Text startMessage = new Text("\n\n\n\nConvert as many binary numbers to decimal as you can within 60 seconds.\n\n"
				+ "                      Press the button when you are ready to start.\n\n");
		startMessage.setFill(Color.WHITE);
		startMessage.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(startMessage);
		VBox.setMargin(startMessage,  new Insets(20, 20, 20, 20));
		
		Button start = new Button("Start");
		start.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 72));
		vBox.getChildren().add(start);
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				vBox.getChildren().clear();
				
				Text timerText = new Text("Time: 60");
				setTimer(timerText);
				timerText.setFill(Color.WHITE);
				timerText.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 40));
				vBox.setAlignment(Pos.CENTER);
				vBox.getChildren().add(timerText);
				VBox.setMargin(timerText, new Insets(20, 30, 20, 20));
				
				Text binaryNumber = new Text();
				binaryNumber.setText(model.generateRandomByte());
				System.out.println("Binary number: " + binaryNumber.getText());
				binaryNumber.setFill(Color.WHITE);
				binaryNumber.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 100));
				vBox.setAlignment(Pos.BOTTOM_CENTER);
				vBox.getChildren().add(binaryNumber);
				
				GridPane gp = new GridPane();
				TextField textField = new TextField();
				textField.requestFocus();
				textField.setMinHeight(50);
				textField.setMinWidth(150);
				textField.setAlignment(Pos.CENTER);
				textField.textProperty().addListener(new ChangeListener<String>() {
		            @Override
		            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		                if (!newValue.matches("\\d{0,3}?")) {
		                    textField.setText(oldValue);
		                }
		            }
		        });
				gp.add(textField, 0, 0);
				
				Button checkButton = new Button("Check answer");
				checkButton.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
				checkButton.setMinHeight(40.0);
				checkButton.setMinWidth(150.0);
				checkButton.setDefaultButton(true);
				gp.add(checkButton, 1, 0);
				gp.setAlignment(Pos.CENTER);
				GridPane.setMargin(checkButton, new Insets(20, 20, 20, 20));
				gp.setPadding(new Insets(70, 70, 70, 70));
				
				System.out.println("Current byte num: " + model.currentByteNum);
				
				checkButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(Integer.parseInt(textField.getText()) == model.currentByteNum) {
							textField.clear();
							model.score++;
							binaryNumber.setText(model.generateRandomByte());
						}
					}
				});		
				
				vBox.getChildren().add(gp);
			}
		});
		
		
		
		
	}
	
	private void setTimer(Text timerText) {
		interval = 10;
		Timer timer = new Timer(true);

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(interval > 0) {
					interval--;
					Platform.runLater(() -> timerText.setText("Time: " + interval));
					
				} else {
					timer.cancel();
					layoutBytesScoreScreen();
					System.out.println("0 reached, break");
				}
			}
		}, 1000, 1000);
	}
	
	private void layoutBytesScoreScreen() {
		
		Platform.runLater(() -> {
			vBox.getChildren().clear();
			Text finalScore = new Text("Final Score: " + model.score);
			finalScore.setFill(Color.WHITE);
			finalScore.setFont(Font.font("Sans-serif", FontWeight.NORMAL, FontPosture.REGULAR, 100));
			vBox.getChildren().add(finalScore);
			vBox.setAlignment(Pos.BOTTOM_CENTER);
			VBox.setMargin(finalScore, new Insets(20, 20, 20, 20));
			
			try {
				GameDatabaseHandler gdh = new GameDatabaseHandler();
				String username = MainMenuController.getUser();
				if(username != null) {
					System.out.println("Submitted score");
					gdh.setScore(username, "Binary Battle", model.score);
					gdh.setScore(username, "BinaryBattle", model.score);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
