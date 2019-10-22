package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
	
	@Override
	public String settings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layoutScene() {
		mainCanvas = new Canvas(1200, 900);
		gc = mainCanvas.getGraphicsContext2D();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		model = new ArrayAttackModel(this);
		VBox view = new VBox();
		HBox buttBox = new HBox(20);
		
		//temporary buttons
		Button swapButt = new Button("Swap");
		Button holdButt = new Button("Hold");
		
		swapButt.setPrefSize(100, 50);
		swapButt.setFont(new Font(20));
		holdButt.setPrefSize(100, 50);
		holdButt.setFont(new Font(20));
		
		swapButt.setOnAction(ae -> {
			model.runBubble(true);
		});
		holdButt.setOnAction(ae -> {
			model.runBubble(false);
		});
		
		buttBox.setPadding(new Insets(25));
		buttBox.setAlignment(Pos.CENTER);
		buttBox.getChildren().addAll(holdButt, swapButt);
		this.getChildren().add(view);
		view.getChildren().addAll(mainCanvas, buttBox);
	}

	@Override
	public void run() {
	}

	@Override
	public void stop() {
	}

	public void update(ArrayList<Integer> array, int curIndex1, int curIndex2, Integer pivot, int score) {
		gc.clearRect(0, 0, 1000, 500);
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0,0, 1366, 700);
		for(int i = 0; i < 10; i++)
			draw(array.get(i), i, i == curIndex1 || i == curIndex2);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score, 500, 100);
		
	}

	private void draw(Integer integer, int index, boolean selected) {
		if (selected)
			gc.setFill(Color.RED);
		else
			gc.setFill(Color.BLACK);
		gc.setFont(new Font(50));
		gc.fillText(integer.toString(), (index * 90) + 85.0, 250.0);
		
	}

}
