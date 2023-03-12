import java.sql.*;


public class EARSdatabase {
	
	private Statement statement = null;
	private Connection connection = null;
	
	public EARSdatabase() throws SQLException, ClassNotFoundException{		
		
		//loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		
		//connecting to database
		connection = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "");
		System.out.println("Database connected.");
		
		statement = connection.createStatement();
	}
	
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
	public void updateDatabase(String s) throws SQLException {
		statement.executeUpdate(s);
	}
	
	public ResultSet queryDatabase(String s) throws SQLException {
		return statement.executeQuery(s);
	}
	
}
