package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.ArrayAttackModel;

public class ArrayAttackView extends MinigameView {

	private Canvas mainCanvas;
	private GraphicsContext gc;
	private ArrayAttackModel model;
	private HBox controllerBox, newGameButtBox, bubbleSortButtBox, mergeSortButtBox;
	
	@Override
	public String settings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layoutScene() {
		this.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)) );
		
		mainCanvas = new Canvas(1200, 700);

		gc = mainCanvas.getGraphicsContext2D();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		model = new ArrayAttackModel(this);
		VBox view = new VBox();
		controllerBox = new HBox();
		
		//Setup the buttons to start a new game
		newGameButtBox = new HBox(20);
		
		Button bubbleButt = new Button("Bubble Sort");
		Button mergeButt = new Button("Merge Sort");
		
		bubbleButt.setPrefSize(200, 50);
		bubbleButt.setFont(new Font(20));
		mergeButt.setPrefSize(200, 50);
		mergeButt.setFont(new Font(20));
		
		bubbleButt.setOnAction(ae -> {
			model.startBubble();
			controllerBox.getChildren().clear();
			controllerBox.getChildren().add(bubbleSortButtBox);
		});
		mergeButt.setOnAction(ae -> {
			model.startMerge();
			controllerBox.getChildren().clear();
			controllerBox.getChildren().add(mergeSortButtBox);
		});
		
		newGameButtBox.setPadding(new Insets(25));
		newGameButtBox.setAlignment(Pos.CENTER);
		newGameButtBox.getChildren().addAll(bubbleButt);//, mergeButt);
		
		
		//Setup the buttons for bubblesort
		bubbleSortButtBox = new HBox(20);
		
		Button bubbleSwapButt = new Button("Swap");
		Button bubbleHoldButt = new Button("Hold");
		
		bubbleSwapButt.setPrefSize(100, 50);
		bubbleSwapButt.setFont(new Font(20));
		bubbleHoldButt.setPrefSize(100, 50);
		bubbleHoldButt.setFont(new Font(20));
		
		bubbleSwapButt.setOnAction(ae -> {
			model.runBubble(true);
		});
		bubbleHoldButt.setOnAction(ae -> {
			model.runBubble(false);
		});
		
		bubbleSortButtBox.setPadding(new Insets(25));
		bubbleSortButtBox.setAlignment(Pos.CENTER);
		bubbleSortButtBox.getChildren().addAll(bubbleHoldButt, bubbleSwapButt);
		
		
		
		
		this.getChildren().add(view);
		view.getChildren().addAll(mainCanvas, controllerBox);
		newGame();
	}

	private void newGame() {
		gc.clearRect(0, 0, 1200, 700);
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0,0, 1200, 700);
		controllerBox.getChildren().clear();
		controllerBox.getChildren().add(newGameButtBox);
	}

	@Override
	public void run() {
		//TODO:
	}

	@Override
	public void stop() {
	}

	public void updateBubble(ArrayList<Integer> array, int curIndex1, int curIndex2, int score) {
		gc.clearRect(0, 0, 1200, 700);
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0,0, 1200, 700);
		for(int i = 0; i < 8; i++)
			drawBubble(array.get(i), i, i == curIndex1 || i == curIndex2);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score, 500, 100);
		
	}

	private void drawBubble(Integer value, int index, boolean selected) {
		if (selected)
			gc.setFill(Color.RED);
		else 
			gc.setFill(Color.BLACK);
		gc.setFont(new Font(50));
		gc.fillText(value.toString(), (index * 90) + 85.0, 250.0);
		
	}
	
	public void updateMerge() {
		
	}
	
	private void drawMerge() {
		
	}

}
