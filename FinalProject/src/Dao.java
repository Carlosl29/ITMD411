import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Carlos Lopez
//Final Project
//ITM 411
//Due Date: 04/29/2018

public class Dao {
	// instance fields
	static Connection con = null;
	Statement stmnt = null;

	// constructor
	public static Connection getConnection() 
	{
		// Setup the connection with the DB
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?autoReconnect=true&useSSL=false"
							+ "&user=fp411&password=411");
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public void createTables() 
	{
		// variables for SQL Query table creations
		//final String createTicketsTable = "CREATE TABLE dtelle_tickets(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200), ticket_status VARCHAR(30))";
		final String createTicketsTable = "CREATE TABLE clope_tckts(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200), ticket_status VARCHAR(30))";
		final String createUsersTable = "CREATE TABLE clope_usrs(uid INT AUTO_INCREMENT PRIMARY KEY, uname VARCHAR(30), upass VARCHAR(30))";

		try 
		{

			// create table
			stmnt = getConnection().createStatement();

			stmnt.executeUpdate(createTicketsTable);
			stmnt.executeUpdate(createUsersTable);
			System.out.println("Created tables in given database...");

			// end create table
			// close connection/statement object
			stmnt.close();
			con.close();
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		// add users to user table
		addUsers();
	}

	public void addUsers() 
	{
		// add list of users from userlist.csv file to users table

		// variables for SQL Query inserts
		String sql;
		Connection con = null;
		Statement stmnt = null;
		BufferedReader br;
		File usrList = new File("./userList2.csv");
		FileReader fr = null;
		List<List<String>> array = new ArrayList<>(); // array list to hold
														// spreadsheet rows &
														// columns

		// read data from file
		try 
		{
			fr = new FileReader(usrList);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) 
			{
				array.add(Arrays.asList(line.split(",")));
			}
		} catch (Exception e) 
		{
			System.out.println("There was a problem loading the file");
		}
		try 
		{
			// Setup the connection with the DB
			con = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?autoReconnect=true&useSSL=false"
							+ "&user=fp411&password=411");
			stmnt = con.createStatement();

			// create loop to grab each array index containing a list of values
			// and PASS (insert) that data into your User table
			for (List<String> rowData : array) 
			{
				sql = "insert into clope_usrs(uname,upass) " + "values('" + rowData.get(0) + "','" + rowData.get(1)
						+ "');";
				stmnt.executeUpdate(sql);
			}
			System.out.println("Inserts completed in the given database...");

			// close connection/statement object
			stmnt.close();
			con.close();
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void deleteRecord()
	{
		try 
		{
			// Setup the connection with the DB
			con = DriverManager.getConnection("jdbc:mysql://www.papademas.net/tickets?autoReconnect=true&useSSL=false"
							+ "&user=fp411&password=411");
			stmnt = con.createStatement();
			
			}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	// add other desired CRUD methods needed like for updates, deletes, etc.
}
