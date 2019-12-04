package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GameDatabaseHandler {

	// Make this a singleton
	  private static  GameDatabaseHandler single_instance = null;
	  
	  synchronized public static  GameDatabaseHandler getInstance() {
	    try{if(single_instance == null) {
	      single_instance = new  GameDatabaseHandler();
	    }
	    return single_instance;}
	    catch (Exception e) {};
	    return null;
	  }
	  
	  // JDBC driver name and database URL
	 // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	  // Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	  static final String DB_URL = "jdbc:mysql://localhost/comp_sci_games?characterEncoding=utf8";
	  //  Database credentials
	  static final String USER = "root";
	  static final String PASS = "csc436zona";
	  // The adapters connection to a MySQL data base on this computer
	  private Connection conn = null;
	  
	  public  GameDatabaseHandler() throws Exception {
	    // Register the JDBC driver (need an Oracle account and mysql-connector...-bin.jar
	    Class.forName(JDBC_DRIVER);
	    
	    // Open a connection
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	  } //end constructor
	
	public boolean verifyCredentials(String username, String password) {
		 try {
			  Statement stmt = conn.createStatement();
		    // Store the SQL command:
		    String query = "SELECT * FROM accounts";
		    // executeQuery makes a real query on the real database
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) 
		    	if(rs.getString(1).equals(username) && SecureIt.decrypt(rs.getString(2)).equals(password))
		    		return true;
		    return false;
		    
		  }
		  catch (SQLException e) {
			  return false;
		  }
	}
	
	  public boolean registerCredentials(String username, String password) {
		  try {
			  Statement stmt = conn.createStatement();
			  // Store the SQL command:
			  String command = "insert into accounts(username, password) values('" + username + "', '" + SecureIt.encrypt(password) + "');";
			  // executeQuery makes a real query on the real database
			  stmt.executeUpdate(command);
		  }
		  catch (SQLException e)
		  {
			  return false;
		  }
		  return true;
	  }
	  
	  public boolean usernameAvailable(String username) {
		  try {
			  Statement stmt = conn.createStatement();
		    // Store the SQL command:
		    String query = "SELECT username FROM accounts";
		    // executeQuery makes a real query on the real database
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) 
		    	if(rs.getString(1).equals(username))
		    		return false;
		    return true;
		    
		  }
		  catch (SQLException e) {
			  return false;
		  }
		  
	  }
	  
	  public ResultSet getScores(String username, String game) throws SQLException {
		  Statement stmt = conn.createStatement();
		  String query = "select username, game, score from scores";
		  if (username != null) {
			  query += " where username = '" + username + "'";
			if(game != null)
				query += " and ";
		  }
		  if(game != null) {
			  if (username == null)
				  query += " where ";
			  query += " game = '" + game + "'";
		  }
		  query +=";";
		  return stmt.executeQuery(query);
	  }
	  
	  public void setScore(String username, String game, int score) throws SQLException{
		  String id = username + "-" + game;
		  Statement stmt = conn.createStatement();
		  String query = "select score from scores where id = '" + id +"';";
		  int prev_high = stmt.executeQuery(query).getInt("score");
		  if (prev_high >= score)
			  return;
		  query = "delete from scores where id = '" + id + "';" +
			  "insert into scores(id ,username, game, score) values ('" + id + "','" + username + "','" + game + "'," + score + ");";
		  stmt.executeQuery(query);
	  }
	  
	  public ArrayList<Integer> getRating(String game) throws SQLException {
		  Statement stmt = conn.createStatement();
		  String query = "select rating from ratings where game = '" + game + "';";
		  ResultSet rs = stmt.executeQuery(query);
		  ArrayList<Integer> retList = new ArrayList<>();
		  while(rs.next()) {
			  retList.add(rs.getInt("rating"));
		  }
		  return retList;
	  }
	  
	  public void setRating(String username, String game, int rating) throws SQLException{
		  String id = username + "-" + game;
		  Statement stmt = conn.createStatement();
		  String query = "delete from scores where id = '" + id + "';" +
			  "insert into ratings(id ,username, game, rating) values ('" + id + "','" + username + "','" + game + "'," + rating + ");";
		  stmt.executeQuery(query);
	  }

}
