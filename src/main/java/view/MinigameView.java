package view;

import javafx.scene.Node;

public abstract class MinigameView extends Node {

	public MinigameView() {
		super();
		layoutScene();
	}
	
	public void updateLeadboard(String game, String username, int score, String mode) {
		//TODO: Implement
	}
	
	/**
	 * This method gives the menu bar information on what settings can be changed for this minigame
	 * @return TBD
	 */
	public abstract String settings(); //TODO: Return type to be changed
	
	/**
	 * This method performs the initial setup to layout the minigame scene, 
	 * is called once when the program first starts.
	 */
	protected abstract void layoutScene();
	
	/**
	 * This method is used to start any threads needed for the game to run, 
	 * is called whenever the minigame starts and may be called multiple times during program execution
	 */
	public abstract void run();
	
	/**
	 * This method is used to terminate any threads not needed once the game stops,
	 * is called whenever the minigame stops and may be called multiple times during program execution
	 */
	public abstract void stop();
	
}
