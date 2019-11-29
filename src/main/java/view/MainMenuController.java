package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GameDatabaseHandler;
import model.Gate;
import model.Wire;

public class MainMenuController extends Application {
	
	/*
	 * TO-DO:
	 * 
	 * 1. Add menu button functionality
	 * 2. Create background for menu bar
	 * 3. Add credits-clickable button instead of menubar?
	 * 4. Create backgrounds for game buttons 
	 */
	
	private Stage loginWindow, registerWindow;

	private GameDatabaseHandler database;
	private LeaderboardView LbView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		database = GameDatabaseHandler.getInstance();
		LbView = new LeaderboardView(database);
		BorderPane bPane = new BorderPane();
		ScrollPane sPane = new ScrollPane();
		// Add a background image for the main menu
		Image image = new Image("file:resources/image/csbackground.png");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(bImage);
		bPane.setBackground(background);

		// Add a MenuBar
		MenuBar menuBar = new MenuBar();

		// Add a main file menu (options, quit, etc.)
		Menu fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		MenuItem mainMenu = new MenuItem("Main Menu");
		fileMenu.getItems().add(mainMenu);
		
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		MenuItem options = new MenuItem("Options");
		fileMenu.getItems().add(options);
		MenuItem credits = new MenuItem("Credits");
		fileMenu.getItems().add(credits);
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().add(exit);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				primaryStage.close();
			}
		});

		// Add a profile menu (login, register, etc.)
		Menu profile = new Menu("Profile");
		menuBar.getMenus().add(profile);
		MenuItem login = new MenuItem("Login");
		profile.getItems().add(login);
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Login button clicked - loginPopup() called in MainMenuController");
				loginPopup();
			}
		});
		
		MenuItem register = new MenuItem("Register");
		profile.getItems().add(register);
		// When create account button is clicked, we want to open the create account
				// popup window.
				register.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("Create Account button clicked - createAccount() called in SongLibraryView");
						registerPopup();
					}
				});

		// Add a leaderboards menu (overall + leaderboard for each game)
		Menu leaderboards = new Menu("Leaderboards");
		menuBar.getMenus().add(leaderboards);
		MenuItem overall = new MenuItem("Overall");
		leaderboards.getItems().add(overall);
		MenuItem arrayAttackMenu = new MenuItem("Array Attack");
		leaderboards.getItems().add(arrayAttackMenu);
		MenuItem binaryBattleMenu = new MenuItem("Binary Battle");
		leaderboards.getItems().add(binaryBattleMenu);
		MenuItem gatesGameMenu = new MenuItem("Gates Game");
		leaderboards.getItems().add(gatesGameMenu);
		MenuItem javaQuizletMenu = new MenuItem("Java Quizlet");
		leaderboards.getItems().add(javaQuizletMenu);
		
		overall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LbView.showLeaderboard(null);
				
			} 
		});
		
		arrayAttackMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LbView.showLeaderboard("ArrayAttack");
			} 
		});
		
		gatesGameMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LbView.showLeaderboard("GatesGame");
			} 
		});
		
		binaryBattleMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LbView.showLeaderboard("BinaryBattle");
			} 
		});
		
		javaQuizletMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LbView.showLeaderboard("JavaQuizlet");
			} 
		});

		// Add a title for our main menu
		Text title = new Text("\n    Computer\n          Crash Course");
		title.setFont(Font.loadFont("file:resources/fonts/MAINBRG_.ttf", 75));
		title.setFill(Color.WHITE);
		title.setStyle("-fx-stroke: black;-fx-stroke-width: 4;");

		// Add a GridPane that holds the buttons to play games
		GridPane gp = new GridPane();
		
		String buttonStyle = "-fx-background-color: #3F3F3F;-fx-background-radius:25px;-fx-text-fill: white";
		String hoverStyle = "-fx-background-color: #6B6B6B;-fx-background-radius:25px;-fx-text-fill: white";
		// Create a button for each game
		Button arrayAttackButton = new Button("Array Attack");
		arrayAttackButton.setMinHeight(200.0);
		arrayAttackButton.setMinWidth(500.0);
		arrayAttackButton.setFont(new Font("Arial", 48));
		arrayAttackButton.setStyle(buttonStyle);
		arrayAttackButton.setOnMouseEntered(e -> {
			arrayAttackButton.setStyle(hoverStyle);
		});
		arrayAttackButton.setOnMouseExited(e -> {
			arrayAttackButton.setStyle(buttonStyle);
		});
		gp.add(arrayAttackButton, 0, 0);

		Button binaryBattleButton = new Button("Binary Battle");
		binaryBattleButton.setMinHeight(200.0);
		binaryBattleButton.setMinWidth(500.0);
		binaryBattleButton.setFont(new Font("Arial", 48));
		binaryBattleButton.setStyle(buttonStyle);
		binaryBattleButton.setOnMouseEntered(e -> {
			binaryBattleButton.setStyle(hoverStyle);
		});
		binaryBattleButton.setOnMouseExited(e -> {
			binaryBattleButton.setStyle(buttonStyle);
		});
		gp.add(binaryBattleButton, 1, 0);

		Button gatesGameButton = new Button("Gates Game");
		gatesGameButton.setMinHeight(200.0);
		gatesGameButton.setMinWidth(500.0);
		gatesGameButton.setFont(new Font("Arial", 48));
		gatesGameButton.setStyle(buttonStyle);
		gatesGameButton.setOnMouseEntered(e -> {
			gatesGameButton.setStyle(hoverStyle);
		});
		gatesGameButton.setOnMouseExited(e -> {
			gatesGameButton.setStyle(buttonStyle);
		});
		gp.add(gatesGameButton, 0, 1);

		Button javaQuizletButton = new Button("Java Quizlet");
		javaQuizletButton.setMinHeight(200.0);
		javaQuizletButton.setMinWidth(500.0);
		javaQuizletButton.setFont(new Font("Arial", 48));
		javaQuizletButton.setStyle(buttonStyle);
		javaQuizletButton.setOnMouseEntered(e -> {
			javaQuizletButton.setStyle(hoverStyle);
		});
		javaQuizletButton.setOnMouseExited(e -> {
			javaQuizletButton.setStyle(buttonStyle);
		});
		gp.add(javaQuizletButton, 1, 1);
		
		// Styling of GridPane buttons
		gp.setPadding(new Insets(80, 20, 20, 70));
		gp.setHgap(50);
		gp.setVgap(50);

		// Create a VBox which lays out the elements in a
		// top-down order.
		VBox vBox = new VBox();
		vBox.getChildren().add(menuBar);
		vBox.getChildren().add(title);
		vBox.getChildren().add(gp);
		bPane.setTop(vBox);

		// Now we need to add the on-click actions for each button.
		// 1. Array Attack
		arrayAttackButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				ArrayAttackView arrayAttackView = new ArrayAttackView();
				sPane.setContent(arrayAttackView);
				bPane.setCenter(sPane);
			}
		});
		
		// 2. Binary Battle
		binaryBattleButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				BinaryBattleView binaryView = new BinaryBattleView();
				sPane.setContent(binaryView);
				bPane.setCenter(sPane);
			}
		});
		
		// 3. Gates Game
		gatesGameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				GatesView gatesView = new GatesView();
				sPane.setContent(gatesView);
				bPane.setCenter(sPane);
			}
		});
		
		// 4. Java Quizlet
		javaQuizletButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				// Clear background and current vBox
				bPane.getChildren().clear();
				bPane.setBackground(null);
				bPane.setTop(menuBar);
				JavaQuizlet javaQuizletView = new JavaQuizlet();
				sPane.setContent(javaQuizletView);
				bPane.setCenter(sPane);
			}
		});
		

		// Add a BorderPane to the Scene
		Scene scene = new Scene(bPane, 1200, 900);
		primaryStage.setScene(scene);

		// Start the application
		primaryStage.setTitle("Computer Crash Course");
		primaryStage.show();
	}
	
	private void registerPopup() {
		// This is just for creating the create account popup.
		registerWindow = new Stage();
		registerWindow.initModality(Modality.APPLICATION_MODAL);

		// Create a VBox/Gridpane to hold our elements.
		VBox createAccountVbox = new VBox(20);
		GridPane createAccountGP = new GridPane();

		// Create a username field
		Text username = new Text("Username");
		username.setFont(new Font("Arial", 16));
		createAccountGP.add(username, 0, 0);
		GridPane.setMargin(username, new Insets(40, 20, 20, 25));

		// Create a password field
		Text password = new Text("Password");
		password.setFont(new Font("Arial", 16));
		GridPane.setMargin(password, new Insets(0, 20, 20, 25));
		createAccountGP.add(password, 0, 1);

		// Create a verify-password field
		Text reenterPassword = new Text("Re-enter Password");
		reenterPassword.setFont(new Font("Arial", 16));
		GridPane.setMargin(reenterPassword, new Insets(0, 20, 20, 25));
		createAccountGP.add(reenterPassword, 0, 2);

		// Create a text field for the username
		TextField userField = new TextField();
		userField.setPromptText("Enter username here");
		GridPane.setMargin(userField, new Insets(20, 0, 0, 0));
		userField.setFont(new Font("Arial", 14));
		userField.setMaxWidth(150);
		createAccountGP.add(userField, 1, 0);

		// Create a password field for the username
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter password here");
		GridPane.setMargin(passwordField, new Insets(-25, 0, 0, 0));
		passwordField.setFont(new Font("Arial", 14));
		passwordField.setMaxWidth(150);
		createAccountGP.add(passwordField, 1, 1);

		// Create another password field to verify it
		PasswordField reenterPasswordField = new PasswordField();
		reenterPasswordField.setPromptText("Verify password");
		GridPane.setMargin(reenterPasswordField, new Insets(-25, 0, 0, 0));
		reenterPasswordField.setFont(new Font("Arial", 14));
		reenterPasswordField.setMaxWidth(150);
		createAccountGP.add(reenterPasswordField, 1, 2);

		// Create a button to register an account
		Button register = new Button("Register");
		register.setPadding(new Insets(10, 40, 10, 40));
		register.setFont(new Font("Arial", 16));
		VBox.setMargin(register, new Insets(0, 20, 20, 110));

		// When the register button is clicked, call the createAccount() method.
		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("- Register new account - createAccount() called in SongLibraryView");

				if (createAccount(userField.getText(), passwordField.getText(), reenterPasswordField.getText())) {
					// If this is true, need to update a message somewhere saying 'Account created!'
					System.out.println("  - Account successfully created!");
				} else {
					// Add a message stating what went wrong on the popup.
					System.out.println("  - Something went wrong when attempting to create the account.");
				}
			}
		});

		// Finally, add the gridpane/register button to our main vbox and show the
		// scene.
		createAccountVbox.getChildren().add(createAccountGP);
		createAccountVbox.getChildren().add(register);
		Scene loginScene = new Scene(createAccountVbox, 375, 250);
		registerWindow.setScene(loginScene);
		registerWindow.setTitle("Register");
		registerWindow.show();
	}

	private boolean createAccount(String username, String password, String reenterPassword) {
		// This handles attempting to create an account.
		// It would call usernameAvailable() and registerCredentials().

		System.out.println("- Attempting to register a new user (" + username + ") and password (" + password + ")");

		// Check if the username is available for registration
		if (! database.usernameAvailable(username)) {
			System.out.println("Error - username already taken.");
			return false;
		}

		// Check if the passwords correctly match each other
		if (!password.equals(reenterPassword)) {
			System.out.println("Error - passwords don't match during account creation.");
			return false;
		}

		// If all error checks are passed, create the account and return true.
		database.registerCredentials(username, password);
		registerWindow.close();
		return true;
	}
	
	private void loginPopup() {
		// Start by making a new stage
		loginWindow = new Stage();
		loginWindow.initModality(Modality.APPLICATION_MODAL);

		// Create a VBox and GridPane to hold our elements.
		VBox loginVbox = new VBox(20);
		GridPane loginGP = new GridPane();

		// Add a username field
		Text username = new Text("Username");
		username.setFont(new Font("Arial", 16));
		loginGP.add(username, 0, 0);
		GridPane.setMargin(username, new Insets(40, 20, 20, 25));

		// Add a password field
		Text password = new Text("Password");
		password.setFont(new Font("Arial", 16));
		GridPane.setMargin(password, new Insets(0, 20, 20, 25));
		loginGP.add(password, 0, 1);

		// Add a text field for the username
		TextField userField = new TextField();
		userField.setPromptText("Enter username here");
		GridPane.setMargin(userField, new Insets(20, 0, 0, 0));
		userField.setFont(new Font("Arial", 14));
		userField.setMaxWidth(150);
		loginGP.add(userField, 1, 0);

		// Add a password field for the password
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter password here");
		GridPane.setMargin(passwordField, new Insets(-25, 0, 0, 0));
		passwordField.setFont(new Font("Arial", 14));
		passwordField.setMaxWidth(150);
		loginGP.add(passwordField, 1, 1);

		// Add a button to confirm logging in.
		Button loginButton = new Button("Login");
		loginButton.setPadding(new Insets(10, 40, 10, 40));
		loginButton.setFont(new Font("Arial", 16));
		VBox.setMargin(loginButton, new Insets(0, 20, 20, 90));

		// When login button is clicked, call loginAccount()
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Attempting to login with account - loginAccount() called in MainMenuController");
				loginAccount(userField.getText(), passwordField.getText());
			}
		});

		// Finally, add the login gridpane and login button to our main VBox, and start
		// the scene.
		loginVbox.getChildren().add(loginGP);
		loginVbox.getChildren().add(loginButton);
		Scene loginScene = new Scene(loginVbox, 300, 200);
		loginWindow.setScene(loginScene);
		loginWindow.setTitle("Login");
		loginWindow.show();
	}

	private void loginAccount(String username, String password) {
		// This handles attempting to actually log into the account.
		// It would call verifyCredentials() from SongLibrary.

		System.out.println("- Login with user (" + username + ") and password (" + password + ")");
		
		if (database.verifyCredentials(username, password)) {
			// Successfully logged in, update windows
			System.out.println("  - Successfully logged in!");
			//this.username = username;
		} else {
			// State why login failed (incorrect username/password, etc.)
			System.out.println("  - Error when attempting to login.");
			return;
		}

		//System.out.println("  - Clearing login pane");
		//loginPane.getChildren().clear();
		
		
		loginWindow.close();
		/*
		Button logout = new Button("Logout");
		logout.setPadding(new Insets(10, 50, 10, 50));
		logout.setFont(new Font("Arial", 14));

		// When login button is clicked, we want to open the login popup window.
		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Logout button clicked");
				clearUser();
				loginPane.getChildren().clear();
				observableListLayout();
				System.out.println("About to clean the song queue");
				songQueue.clearSongQueue();
				System.out.println("Song queue cleared");
			}
		});

		loginPane.add(logout, 0, 0);
		GridPane.setMargin(logout, new Insets(0, 20, 20, 120));

		Button songHistory = new Button("Song History");
		songHistory.setPadding(new Insets(10, 10, 10, 10));

		// When create account button is clicked, we want to open the create account
		// popup window.
		songHistory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Song History button clicked");
				songHistoryPopup();
			}
		});

		loginPane.add(songHistory, 0, 1);
		GridPane.setMargin(songHistory, new Insets(0, 0, 0, 130));
		*/
	}

}
