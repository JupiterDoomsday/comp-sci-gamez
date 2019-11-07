package model;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.image.Image;

public abstract class GatesGame extends Observable{
	private int numOfGates;
	private ArrayList<Gate> gates;
	private ArrayList<Wire> wires;
	private int w;
	private int h;
	private Gate [][] tileset;
	private Image [][] tilemap;
	public GatesGame( int i, int x, int y) {
		numOfGates=i;
		wires=new ArrayList<Wire>();
		gates=new ArrayList<Gate>();
		this.w=x;
		this.h=y;
		tileset=new Gate[x][y];
		tilemap=new Image[x][y];
	}
	public boolean isFinished() {
		for(int i=0;i< gates.size();i++) {
			if(gates.get(i).gateOutput()==false)
				return false;
		}
		return true;
		
	}
	public ArrayList <Wire> getListOfWires() {
		return wires;
	}
	public ArrayList <Gate> getListOfGates() {
		return gates;
	}
	public int getHeight() {
		return h;
	}
	public int getWidth() {
		return w;
	}
	public Gate getTile(int h, int w) {
		return tileset[h][w];
	}
	public Image getTileMap(int h, int w) {
		return tilemap[h][w];
	}
	public void setTile(int h, int w, Image o) {
		tilemap[h][w]= o;
	}
	public void setTile(int h, int w, Gate g) {
		if(gates.size()==numOfGates)
			return;
		tileset[h][w]=g;
		gates.add(g);
		if(wires.contains(g.getW1())==false) {
			wires.add(g.getW1());
		}
		if(wires.contains(g.getW2())==false) {
			wires.add(g.getW2());
		}
	}
	
}
