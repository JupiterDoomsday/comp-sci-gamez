package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameDatabaseHandler;

public class LeaderboardView extends Stage {
	
	private GameDatabaseHandler database;
	private TableView<ScoreEntry> table;
	private ObservableList<ScoreEntry> list;
	
	public LeaderboardView(GameDatabaseHandler database) {
		super();
		this.database = database;
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 900, 900);
		this.setScene(scene);
		this.setTitle("Leaderboards");
		table = new TableView<>();
		pane.setCenter(table);
		
		list = FXCollections.observableArrayList();
		TableColumn<ScoreEntry, String> userCol = new TableColumn<>("User");
		userCol.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("username"));
		TableColumn<ScoreEntry, String> gameCol = new TableColumn<>("Game");
		gameCol.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("game"));
		TableColumn<ScoreEntry, Integer> scoreCol = new TableColumn<>("Score");
		scoreCol.setCellValueFactory(new PropertyValueFactory<ScoreEntry, Integer>("score"));
		
		table.getColumns().add(userCol);
		table.getColumns().add(gameCol);
		table.getColumns().add(scoreCol);
		table.setItems(list);
	}
	
	public void showLeaderboard(String game) {		
		list.clear();
		try {
			ResultSet rs = database.getScores(null, game);
			
			while (rs.next()) {
				ScoreEntry entry = new ScoreEntry(rs.getString("username"), rs.getString("game"), rs.getInt("score"));
				list.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.show();
		table.refresh();
	}

}
