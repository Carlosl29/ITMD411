import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	// Class to allow an object to connect / close a database connection
	
	// Url for the database that will be used "Papa server"
	static final String DB_URL = "jdbc:mysql://www.papademas.net/411labs?autoReconnect=true&useSSL=false";
	
	// Database credentials
	static final String USER = "db411";
	static final String PASS = "411";
	
	public Connection connect()
	{
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		//return DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}
}
