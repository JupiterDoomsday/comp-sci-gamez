package model;

import com.sun.glass.ui.Application.EventHandler;

import javafx.scene.control.Button;

public class Wire {
	public boolean state;
	public Button button;
	 public Wire(boolean b) {
		 this.state=b;
	 }
	 public void invert() {
		 state=!state;
	 }
}
