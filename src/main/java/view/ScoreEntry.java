package view;

public class ScoreEntry {

	String username, game;
	Integer score;
	
	public ScoreEntry(String username, String game, Integer score) {
		this.username = username;
		this.score = score;
		this.game = game;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getGame() {
		return game;
	}
	
	public Integer getScore() {
		return score;
	}

}
