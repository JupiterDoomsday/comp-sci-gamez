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
			setTile(3,4,new Image(new FileInputStream("Image/wire.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
