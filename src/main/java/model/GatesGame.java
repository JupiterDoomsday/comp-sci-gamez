package model;

public class GatesGame {
	private int numOfGates;
	public Gate gate;
	private Gate [] gates;
	private TileMap board;
	public GatesGame(Gate game, int i) {
		gate=game;
		numOfGates=i;
		gates=new Gate[i];
	}
	
}
