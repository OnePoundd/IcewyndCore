package WebConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
	
	protected ResultSet getResultFromQuery(String query) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/factions", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	protected void sendQuery(String query) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/factions", "root", "root");
			Statement statement = connection.createStatement();
			statement.execute(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
