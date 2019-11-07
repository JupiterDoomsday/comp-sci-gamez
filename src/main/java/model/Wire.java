package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.glass.ui.Application.EventHandler;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Wire extends Button{
	private boolean state;
	private Image on;
	private Image off;
	private int x, y;
	 public Wire(boolean b,int x, int y) {
		 super();
		 this.setMaxSize(55, 55);
		 this.state=b;
		 this.x=x;
		 this.y=y;
		 try {
				on =new Image(new FileInputStream("Image/switch_on.png"));
				off=new Image(new FileInputStream("Image/switch_off.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error Images is missing from the Image folder");
			}
		if(state)
			this.setGraphic(new ImageView(on));
		else
			this.setGraphic(new ImageView(off));
			
	 }
	 public boolean state() {
		 return state;
	 }
	 public int getY() {
		 return y;
	 }
	 public int getX() {
		 return x;
	 }
	 public void invert() {
		 state=!state;
	 }
	 public Image getOnImg() {
		 return on;
	 }
	 public Image getOffImg() {
		 return off;
	 }
}
