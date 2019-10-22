package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;

import javafx.scene.image.Image;

public class Gate extends Observable{
	enum Logic{
		XOR,OR,AND;
	}
	private Logic gateType;
	private Wire wire1;
	private Wire wire2;
	private Image img;
	private int x,y;
	
	public Gate(Wire one, Wire two, String state, int x, int y){
		wire1=one;
		wire2=two;
		this.x=x;
		this.y=y;
		gateType=Logic.valueOf(state);
		String path="";
		if(gateType.equals(Logic.valueOf("AND"))) {
			path="Image/and_gate.png";
		}
		else if(gateType.equals(Logic.valueOf("OR"))) {
			path="Image/or_gate.png";
		}
		try {
			img= new Image(new FileInputStream(path));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	 public int getY() {
		 return y;
	 }
	 public int getX() {
		 return x;
	 }
	public Wire getW1() {
		return wire1;
	}
	public Wire getW2() {
		return wire2;
	}
	public Image getImg() {
		return img;
	}
	public String getLogic() {
		switch(gateType){
		case AND:
			return "AND";
		case OR:
			return "OR";
		case XOR:
			return "XOR";
		default:
			return "DOES NOT";
		}
	}
	public boolean gateOutput() {
		switch(gateType){
		case AND:
			return wire1.state() && wire2.state();
		case OR:
			return wire1.state() || wire2.state();
		case XOR:
			return wire1.state() != wire2.state();
		default:
			return false;
		}
	}
	
	
}
