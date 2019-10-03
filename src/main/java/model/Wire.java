package model;

public class Wire {
	public boolean state;
	 public Wire(boolean b) {
		 this.state=b;
	 }
	 public void invert() {
		 state=!state;
	 }
}
