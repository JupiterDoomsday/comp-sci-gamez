package model;
import java.util.ArrayList;

/*
 * @AUTHOR: Isabela Hutchings
 * This works as a Tile board that lays out my 16 x 16 pixel tiles
 */
import javafx.scene.image.Image;

public class TileMap {
	private int width;
	private int height;
	private ArrayList<Wire> wires;
	private ArrayList<Gate> gates;
	private Gate [][] tileset;
	public TileMap(int w, int h) {
		width=w;
		height=h;
		tileset=new Gate[h][w];
		wires=new ArrayList<Wire>();
		gates=new ArrayList<Gate>();
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public void setTile(int h, int w, Gate g) {
		tileset[h][w]=g;
		if(wires.contains(g.getW1())==false) {
			System.out.println("Adding Wire 1");
			wires.add(g.getW1());
		}
		if(wires.contains(g.getW2())==false) {
			System.out.println("Adding Wire 2");
			wires.add(g.getW2());
		}
		gates.add(g);
	}
	public Gate getTile(int h, int w) {
		return tileset[h][w];
	}
	public Gate[][] getBoard(){
		return tileset;
	}
	public ArrayList <Wire> getListOfWires() {
		return wires;
	}
	public ArrayList <Gate> getListOfGates() {
		return gates;
	}
	public boolean isFinished() {
		for(int i=0;i< gates.size();i++) {
			if(gates.get(i).gateOutput()==false)
				return false;
		}
		return true;
	}
}
