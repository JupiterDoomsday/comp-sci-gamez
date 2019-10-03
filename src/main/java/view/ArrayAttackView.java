package view;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.ArrayAttackModel;

public class ArrayAttackView extends MinigameView {

	private Canvas mainCanvas;
	private BorderPane view;
	private GraphicsContext gc;
	private ArrayAttackModel model;
	
	@Override
	public String settings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layoutScene() {
		model = new ArrayAttackModel(this);
		view = new BorderPane();
		mainCanvas = new Canvas(0, 0);
		gc = mainCanvas.getGraphicsContext2D();
		HBox buttBox = new HBox();
		
		//temporary buttons
		Button swapButt = new Button("Swap");
		Button holdButt = new Button("Hold");
		
		swapButt.setOnAction(ae -> {
			model.runBubble(true);
		});
		holdButt.setOnAction(ae -> {
			model.runBubble(false);
		});
		
		buttBox.getChildren().addAll(holdButt, swapButt);
		view.setBottom(buttBox);
		
		this.getChildren().add(view);
		view.setCenter(mainCanvas);
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0,0, 1366, 700);
	}

	@Override
	public void run() {
	}

	@Override
	public void stop() {
	}

	public void update(ArrayList<Integer> array, int curIndex1, Integer pivot, int curIndex2) {
		System.out.println(array + " " + curIndex1);
		
	}

}
