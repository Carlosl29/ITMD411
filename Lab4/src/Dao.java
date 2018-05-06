import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	DbConnect conn = null;
	Statement stmt = null;
	
	/* objectAid plugin for UML 
	 * 
	 * public void creatTables{
	 * 		final String createTicketsTable = "Create table jpapa_tickets2(ticket_id INT Auto_increment Primary Key, "
	 * 										+ "ticket_issuer varchar(30),"
	 * 										+ "ticket_description varchar(200),"
	 * 										+ "ticket_status varchar, "
	 * 										+ "ticket_open DATETime,"
	 * 										+ "ticket_close Datetime)";
	 * 		final string createUsersTable = "create table jpapa_users2("uid INT ato_increment primary key, "
	 * 										+ "uname varchar(30), upass varchar(30))";
	 * 
	 * 
	 * private void showtextfield()
	 * {
	 * 		namelabel = new jlabel("user id: ", Jlabel.right);
	 * 		passwordLable = new jlabel("password: ", jlabel.center);
	 * 		usertext = new jtextfield(6);
	 * 		passwordtext = new jpasswordfield(6);
	 * 
	 * 		lgoinButton = new JButton("Login");
	 * 		
	 * 
	 * 
	 * 
	 * 		String usernaem = userText.gettext();
	 * 		//convert characters
	 * }
	 * 
	 * 
	 * 
	 * 
	 */
	
	public Dao()
	{
		conn = new DbConnect();
	}
	
	//Create Table Method
	public void createTable()
	{
		try {
			//Open a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");
		
			//Execute create query
			System.out.println("Creating table in given database...");
			
			stmt = conn.connect().createStatement();
			
			String createTable = "CREATE TABLE cLope_tab" +
					"(pid INT(5) NOT NULL PRIMARY KEY, " + 
					"id VARCHAR(10), " +
					"income DECIMAL(8,2), " +
					"pep VARCHAR(3));";
			
			
			// Check to see if table exist
			DatabaseMetaData dbm = conn.connect().getMetaData();
			ResultSet tables = dbm.getTables(null,null,"cLope_tab", null);
			
			// If table exists
			if(tables.next())
			{
				System.out.println("Table already exists...");
			}
			else// If table doesn't exists, it's created
			{
				stmt.executeUpdate(createTable);
				System.out.println("Created table successfully...");

			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
	}
	
	//Insert into Method
	public void insertRecords(BankRecords[] robjs)
	{
		try {
			//Execute a query
			System.out.println("Inserting " + robjs.length + " records into the table...");
			stmt = conn.connect().createStatement();
			PreparedStatement insertInfo = null;
		
			
			//Include all object data to the database table
			for(int i = 0; i < 100; i++)
			{
				// finish string assignment to insert all object data 
				// (pid, id, income, pep) into your database table
				
				String sqlString = "REPLACE INTO cLope_tab"
						+ "(pid, id, income, pep) "
						+ "VALUES"
						+ "(?, ?, ?, ?)";
				// Above line = Insert into cLope_tab (pid, id, income, pep) Values (i, id, income, pep)
				
				insertInfo = conn.connect().prepareStatement(sqlString);
				insertInfo.setInt(1, i);
				insertInfo.setString(2, robjs[i].getId());
				insertInfo.setDouble(3, robjs[i].getIncome());
				insertInfo.setString(4, robjs[i].getPep());
				insertInfo.addBatch();
			}
			insertInfo.executeBatch();
	      
			System.out.println("Inserted records into the table...");

			conn.connect().close();
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	// Retrieve Records to display
	public ResultSet retrieveRecords()
	{
		ResultSet rs = null;
		
		try {
			
			stmt = conn.connect().createStatement();
			
			System.out.println("Creating Select Statement...");
			String select = "Select * from cLope_tab";
			rs = stmt.executeQuery(select);
			System.out.println("test1");
			while(rs.next()) {
				int pid = rs.getInt("PID");
				String id = rs.getString("ID");
				Double income = rs.getDouble("Income");
				String pep = rs.getString("PEP");
				System.out.println(pid + "\t" + id + "\t" + income + "\t" + pep);
				
			}

		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}	
		return rs;
		
	}
	
	
}
