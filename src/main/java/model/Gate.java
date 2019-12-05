package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;

import javafx.scene.image.Image;

public class Gate{
	enum Logic{
		XOR,OR,AND;
	}
	private Logic gateType;
	private Wire wire1;
	private Wire wire2;
	private Image img;
	private boolean isNot=false;
	private int x,y;
	
	public Gate(Wire one, Wire two, String state, int x, int y){
		wire1=one;
		wire2=two;
		this.x=x;
		this.y=y;
		gateType=Logic.valueOf(state);
		String path="";
		if(gateType.equals(Logic.valueOf("AND"))) {
			path="Image/AND.png";
		}
		else if(gateType.equals(Logic.valueOf("OR"))) {
			path="Image/or.png";
		}
		else if(gateType.equals(Logic.valueOf("XOR"))) {
			path="Image/xor.png";
		}
		try {
			img= new Image(new FileInputStream(path));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error Images is missing from the Image folder");
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
	public void Negate() {
		isNot=true;
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
		boolean output;
		switch(gateType){
		case AND:
			output= wire1.state() && wire2.state();
		case OR:
			output= wire1.state() || wire2.state();
		case XOR:
			output= (!(wire1.state() && wire2.state()) && (wire1.state() || wire2.state()));
		default:
			output=false;
		}
		if(isNot)
			return !output;
		return output;
	}
	
	
}
