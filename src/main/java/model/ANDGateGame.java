package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class ANDGateGame extends GatesGame {
	public ANDGateGame() {
		super(1,18,14);
		Gate gate= new Gate(new Wire(false,1,1),new Wire(false,1,3),"AND",5,0);
		setTile(5, 2, gate);
		try {
			setTile(2,1,new Image(new FileInputStream("Image/wire.png")));
			setTile(2,3,new Image(new FileInputStream("Image/wire.png")));
			setTile(3,1,new Image(new FileInputStream("Image/wire.png")));
			setTile(3,3,new Image(new FileInputStream("Image/wire.png")));
			setTile(4,1,new Image(new FileInputStream("Image/wire3.png")));
			setTile(4,3,new Image(new FileInputStream("Image/wire2.png")));
			setTile(4,2,new Image(new FileInputStream("Image/wires.png")));
			setTile(6,2,new Image(new FileInputStream("Image/circuit2.png")));
			setTile(6,1,new Image(new FileInputStream("Image/circuit3.png")));
			setTile(6,0,new Image(new FileInputStream("Image/circuit3.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
