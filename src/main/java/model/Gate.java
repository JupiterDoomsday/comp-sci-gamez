package model;

import java.util.Observable;

public class Gate extends Observable{
	enum Logic{
		XOR,OR,AND;
	}
	private Logic gateType;
	private Wire wire1;
	private Wire wire2;
	
	public Gate(Wire one, Wire two, String state){
		wire1=one;
		wire2=two;
		gateType=Logic.valueOf(state);
	}
	public Wire getW1() {
		return wire1;
	}
	public Wire getW2() {
		return wire2;
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
			return wire1.state && wire2.state;
		case OR:
			return wire1.state || wire2.state;
		case XOR:
			return wire1.state != wire2.state;
		default:
			return false;
		}
	}
	
	
}
