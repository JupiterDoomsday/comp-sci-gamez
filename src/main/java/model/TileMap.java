package model;
/*
 * @AUTHOR: Isabela Hutchings
 * This works as a Tile board that lays out my 16 x 16 pixel tiles
 */
import javafx.scene.image.Image;

public class TileMap {
	private int width;
	private int height;
	private Image [][] tileset;
	public TileMap(int w, int h) {
		width=w;
		height=h;
		tileset=new Image[h][w];
	}
	public void setTile(int h, int w, Image i) {
		tileset[h][w]=i;
	}
	public Image getTile(int h, int w) {
		return tileset[h][w];
	}
	public Image[][] getBoard(){
		return tileset;
	}
}
