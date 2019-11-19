package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Challenge3GatesGame extends GatesGame {

	public Challenge3GatesGame() {
		super(2,18,14);
		Wire w2=new Wire(false,1,3);
		Gate gate= new Gate(new Wire(false,1,1),w2,"AND",4,0);
		Gate gateTwo= new Gate(w2,new Wire(false,1,5), "XOR",5,0);
		setTile(5, 2, gate);
		setTile(5, 4, gateTwo);
		try {
			setTile(2,1,new Image(new FileInputStream("Image/wire.png")));
			setTile(2,3,new Image(new FileInputStream("Image/wire.png")));
			setTile(3,1,new Image(new FileInputStream("Image/wire.png")));
			setTile(3,3,new Image(new FileInputStream("Image/buss.png")));
			setTile(4,1,new Image(new FileInputStream("Image/wire3.png")));
			setTile(4,3,new Image(new FileInputStream("Image/wire2.png")));
			setTile(4,2,new Image(new FileInputStream("Image/wires.png")));
			//3rd wire pattern
			setTile(3,4,new Image(new FileInputStream("Image/wire4.png")));
			setTile(4,4,new Image(new FileInputStream("Image/wires2.png")));
			setTile(2,5,new Image(new FileInputStream("Image/wire.png")));
			setTile(3,5,new Image(new FileInputStream("Image/wire.png")));
			setTile(4,5,new Image(new FileInputStream("Image/wire2.png")));
			//gates wire pattern
			setTile(6,2,new Image(new FileInputStream("Image/circuit4.png")));
			setTile(6,4,new Image(new FileInputStream("Image/circuit2.png")));
			setTile(6,1,new Image(new FileInputStream("Image/circuit3.png")));
			setTile(6,3,new Image(new FileInputStream("Image/circuit3.png")));
			setTile(6,0,new Image(new FileInputStream("Image/circuit3.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

}
