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

	private static int CANVAS_WIDTH = 1200, CANVAS_HEIGHT = 700;
	
	private Canvas mainCanvas;
	private GraphicsContext gc;
	private ArrayAttackModel model;
	private HBox controllerBox, newGameButtBox, bubbleSortButtBox, mergeSortButtBox, quickSortButtBox;
	
	@Override
	public String settings() {
		// TODO Change this
		return null;
	}

	@Override
	protected void layoutScene() {
		this.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)) );
		
		mainCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

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
		Button quickButt = new Button("Quick Sort");
		
		bubbleButt.setPrefSize(200, 50);
		bubbleButt.setFont(new Font(20));
		mergeButt.setPrefSize(200, 50);
		mergeButt.setFont(new Font(20));
		quickButt.setPrefSize(200, 50);
		quickButt.setFont(new Font(20));
		
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
		quickButt.setOnAction(ae -> {
			model.startQuick();
			controllerBox.getChildren().clear();
			controllerBox.getChildren().add(quickSortButtBox);
		});
		
		newGameButtBox.setPadding(new Insets(25));
		newGameButtBox.setAlignment(Pos.CENTER);
		newGameButtBox.getChildren().addAll(bubbleButt, mergeButt, quickButt);
		
		
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
		
		//Setup the buttons for mergesort
		mergeSortButtBox = new HBox(20);
		
		Button mergeLeftButt = new Button("Left");
		Button mergeRightButt = new Button("Right");
		
		mergeLeftButt.setPrefSize(100, 50);
		mergeLeftButt.setFont(new Font(20));
		mergeRightButt.setPrefSize(100, 50);
		mergeRightButt.setFont(new Font(20));
		
		mergeLeftButt.setOnAction(ae -> {
			model.runMerge(true);
		});
		mergeRightButt.setOnAction(ae -> {
			model.runMerge(false);
		});
		
		mergeSortButtBox.setPadding(new Insets(25));
		mergeSortButtBox.setAlignment(Pos.CENTER);
		mergeSortButtBox.getChildren().addAll(mergeLeftButt, mergeRightButt);
		
		//Setup the buttons for quicksort
		quickSortButtBox = new HBox(20);
		
		Button quickLeftButt = new Button("Left");
		Button quickRightButt = new Button("Right");
		
		quickLeftButt.setPrefSize(100, 50);
		quickLeftButt.setFont(new Font(20));
		quickRightButt.setPrefSize(100, 50);
		quickRightButt.setFont(new Font(20));
		
		quickLeftButt.setOnAction(ae -> {
			model.runQuick(true);
		});
		quickRightButt.setOnAction(ae -> {
			model.runQuick(false);
		});
		
		quickSortButtBox.setPadding(new Insets(25));
		quickSortButtBox.setAlignment(Pos.CENTER);
		quickSortButtBox.getChildren().addAll(quickLeftButt, quickRightButt);
		
		this.getChildren().add(view);
		view.getChildren().addAll(mainCanvas, controllerBox);
		gc.setFont(new Font(50));
		newGame();
	}

	private void newGame() {
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
		drawBackground(score);
		for(int i = 0; i < 8; i++)
			drawBubble(array.get(i), i, i == curIndex1 || i == curIndex2);
	}

	private void drawBubble(Integer value, int index, boolean selected) {
		if (selected)
			gc.setFill(Color.RED);
		else 
			gc.setFill(Color.BLACK);
		gc.fillText(value.toString(), (index * CANVAS_WIDTH/8.0) + CANVAS_WIDTH/16.0, CANVAS_HEIGHT/2.0);
		
	}
	
	public void updateMerge(ArrayList<Integer> array1, ArrayList<Integer> array2, int s1i1, int s1i2, int s2i1, int s2i2, int score) {
		drawBackground(score);
		for(int i = 0; i < 8; i++)
			drawMerge(array1.get(i), array2.get(i), i, s1i2 - s1i1 + 1, (i >= s1i1 && i <= s1i2), (i >= s2i1 && i <= s2i2));
	}
	
	private void drawMerge(Integer value1, Integer value2, int index, int selectionSize, boolean selected1, boolean selected2) {
		if (selected1)
			gc.setFill(Color.RED);
		else if (selected2)
			gc.setFill(Color.GREEN);
		else 
			gc.setFill(Color.BLACK);
		
		int topPos = getPosition(index, selectionSize);
		int bottomPos = getPosition(index, selectionSize * 2);
		
		if (value1 != null)
			gc.fillText(value1.toString(), topPos , CANVAS_HEIGHT/2.0); // (index * CANVAS_WIDTH/8) + CANVAS_WIDTH/16;
		gc.setFill(Color.BLACK);
		if (value2 != null)
			gc.fillText(value2.toString(), bottomPos, CANVAS_HEIGHT/2 + CANVAS_HEIGHT/4);
	}
	
	private int getPosition(int index, int selectionSize) {
		int blocks =  16/selectionSize;
		int curBlock = index / selectionSize;
		int indInBlock = index % selectionSize;
		int blockMid = (2 * curBlock * CANVAS_WIDTH/blocks) + CANVAS_WIDTH/(blocks);
		return blockMid + (indInBlock - selectionSize/2)*75;
	}

	public void updateQuick(ArrayList<Integer> array1, ArrayList<Integer> array2, int curIndex, Integer pivot, int score) {
		drawBackground(score);
		gc.fillText(pivot.toString(), CANVAS_WIDTH/2.0, CANVAS_HEIGHT/2.0);
		drawQuick(array1, array2, curIndex);
	}
	
	private void drawQuick(ArrayList<Integer> array1, ArrayList<Integer> array2, int curIndex) {
		gc.setFill(Color.BLACK);
		for(int i = 0; i < 8; i++)
			gc.fillText(array2.get(i).toString(), (i * CANVAS_WIDTH/8.0) + CANVAS_WIDTH/16.0, CANVAS_HEIGHT*3/4.0);
		for(int i = 0; i < array1.size(); i++) {
			if(i == curIndex)
				gc.setFill(Color.GREEN);
			else
				gc.setFill(Color.BLACK);
			
			gc.fillText(array1.get(i).toString(), (i * CANVAS_WIDTH/array2.size()) + CANVAS_WIDTH/16.0, CANVAS_HEIGHT/2.0);	
		}
	}
	
	private void drawBackground(int score) {
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0,0, CANVAS_WIDTH, CANVAS_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score, CANVAS_WIDTH/2.0, CANVAS_HEIGHT/6.0);
	}

}
