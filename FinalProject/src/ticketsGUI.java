import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//Carlos Lopez
// Final Project
// ITM 411
// Due Date: 04/29/2018
public class ticketsGUI implements ActionListener {

	// class level member objects
	String dbSite = "jdbc:mysql://www.papademas.net/tickets?autoReconnect=true&useSSL=false&user=fp411&password=411";
	Dao dao = new Dao(); // for CRUD operations
	String chkAdmin = null;
	private JFrame mnFrm;

	JScrollPane sp = null;

	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuTickets = new JMenu("Tickets");
	private JMenu mnuCls = new JMenu("Close");
	private JMenu mnuExtr = new JMenu("Extras");

	//add any more Main menu object items below 

	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItmExt;
	JMenuItem mnuItmUpdt;
	JMenuItem mnuItmDlt;
	JMenuItem mnuItmOpnTckt;
	JMenuItem mnuItmVwTckt;
	JMenuItem mnuItmMyTckt;
	JMenuItem mnuItmViewByIssuer;
	JMenuItem mnuItmCls;
	JMenuItem mnuItmRfrsh;
	JMenuItem mnuItmLgOut;
	//JMenuItem mnuItemActive;

	//add any more Sub object items below 

	// constructor
	public ticketsGUI(String verifyRole) 
	{

		chkAdmin = verifyRole;
		JOptionPane.showMessageDialog(null, "Welcome " + verifyRole);
		
		if (chkAdmin.equals("Admin"))
		{

			dao.createTables(); // fire up table creations (tickets / user
								// tables)
		// else do something else if you like
			createMenu();
			prepareGUI();
		}
		else{
			// Means non-admin user logged in.
			createMenu2();
			prepareGUI2();
		}
	}

	private void createMenu() 
	{

		//Initialize sub menu items
		// For Admin users
		
		// Exit Menu Option
		mnuItmExt = new JMenuItem("Exit");
		mnuFile.add(mnuItmExt);

		// Update ticket option
		mnuItmUpdt = new JMenuItem("Update Ticket");
		mnuAdmin.add(mnuItmUpdt);

		// Delete Ticket option
		mnuItmDlt = new JMenuItem("Delete Ticket");
		mnuAdmin.add(mnuItmDlt);

		// Open Ticket option
		mnuItmOpnTckt = new JMenuItem("Open Ticket");
		mnuTickets.add(mnuItmOpnTckt);
		
		// View ticket option
		mnuItmVwTckt = new JMenuItem("View Tickets Table");
		mnuTickets.add(mnuItmVwTckt);
		
		// Close ticket option
		mnuItmCls = new JMenuItem("Close Ticket");
		mnuCls.add(mnuItmCls);
		
		// Refresh the table of tickets
		mnuItmRfrsh = new JMenuItem("Refresh Ticket Table");
		mnuExtr.add(mnuItmRfrsh);
		
		// Logout option
		mnuItmLgOut = new JMenuItem("Logout");
		mnuFile.add(mnuItmLgOut);
		
		//Add action listeners for each desired menu item 
		mnuItmExt.addActionListener(this);
		mnuItmUpdt.addActionListener(this);
		mnuItmDlt.addActionListener(this);
		mnuItmOpnTckt.addActionListener(this);
		mnuItmVwTckt.addActionListener(this);
		//mnuItmViewByIssuer.addActionListener(this);
		mnuItmCls.addActionListener(this);
		mnuItmRfrsh.addActionListener(this);
		mnuItmLgOut.addActionListener(this);
		
	}
	private void prepareGUI() 
	{
		// initialize frame object
		mnFrm = new JFrame("Tickets-Admin");

		// Create jmenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		bar.add(mnuAdmin);
		bar.add(mnuTickets);
		bar.add(mnuCls);
		bar.add(mnuExtr);
		
		
		// Add menu bar to frame
		mnFrm.setJMenuBar(bar);

		mnFrm.addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		
		// set frame options
		mnFrm.setSize(600, 500);
		mnFrm.getContentPane().setBackground(Color.LIGHT_GRAY);	// Change color of frame
		mnFrm.setLocationRelativeTo(null);
		mnFrm.setVisible(true);
	}
	private void createMenu2() 
	{

		// Initialize sub menu items 
		// For non-Admin users

		// Exit Menu Option
		mnuItmExt = new JMenuItem("Exit");
		mnuFile.add(mnuItmExt);


		// Update ticket option
		//mnuItmUpdt = new JMenuItem("Update Ticket");
		//mnuAdmin.add(mnuItmUpdt);

		// Delete Ticket option
		//mnuItmDlt = new JMenuItem("Delete Ticket");
		//mnuAdmin.add(mnuItmDlt);

		// Open Ticket option
		mnuItmOpnTckt = new JMenuItem("Open Ticket");
		mnuTickets.add(mnuItmOpnTckt);

		// View any ticket menu item
		//mnuItmVwTckt = new JMenuItem("View Ticket");
		//mnuTickets.add(mnuItmVwTckt);
		
		// View my tickets menu item
		mnuItmMyTckt = new JMenuItem("My Tickets");
		mnuTickets.add(mnuItmMyTckt);
		
		// Refresh the table of tickets
		mnuItmRfrsh = new JMenuItem("Refresh Ticket Table");
		mnuExtr.add(mnuItmRfrsh);


		// initialize any more desired sub menu items below

		//Add action listeners for each desired menu item
		mnuItmExt.addActionListener(this);
		//mnuItmUpdt.addActionListener(this);
		//mnuItmDlt.addActionListener(this);
		mnuItmOpnTckt.addActionListener(this);
		mnuItmMyTckt.addActionListener(this);
		mnuItmRfrsh.addActionListener(this);
		
		// add any more listeners for any additional sub menu items if desired

	}
	private void prepareGUI2() 
	{
		// initialize frame object
		// Non-admin login
		mnFrm = new JFrame("Tickets-User");

		// create jmenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		//bar.add(mnuAdmin);
		bar.add(mnuTickets);
		bar.add(mnuExtr);
		
		// add menu bar components to frame
		mnFrm.setJMenuBar(bar);

		mnFrm.addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		mnFrm.setSize(500, 500);
		mnFrm.getContentPane().setBackground(Color.LIGHT_GRAY);
		mnFrm.setLocationRelativeTo(null);
		mnFrm.setVisible(true);
	}
	
	 //action listener fires up items clicked on from sub menus with one action
	 //performed event handler!
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// implement actions for sub menu items
		if (e.getSource() == mnuItmExt) {
			System.exit(0);
		}
		// Used to logout of the ticket system
		else if(e.getSource() == mnuItmLgOut)
		{
			new Login();
		}
		
		else if (e.getSource() == mnuItmOpnTckt) 
		{

			try 
			{

				// Get ticket information
				String ticketName = JOptionPane.showInputDialog(null, "Enter your name");
				String ticketDesc = JOptionPane.showInputDialog(null, "Enter a ticket description");
				//String ticketActive= JOptionPane.showInputDialog(null,"Enter active");
				String ticketActive="active";
				
				// Insert ticket information to database
				Connection con = DriverManager.getConnection(dbSite);
				Statement stmnt = con.createStatement();
				
				int result = stmnt.executeUpdate("INSERT INTO clope_tckts(ticket_issuer, ticket_description, ticket_status) VALUES('"+ ticketName + "', '" + ticketDesc + "', '" + ticketActive + "')", Statement.RETURN_GENERATED_KEYS);
				
				// Insertion
				ResultSet resultSet = null;
				resultSet = stmnt.getGeneratedKeys();
				int id = 0;
				if (resultSet.next()) 
				{
					id = resultSet.getInt(1); // retrieve first field in table
				}
				
				// Display results if successful or not to console / dialog box
				if (result != 0) 
				{
					System.out.println("Ticket ID : " + id + " created successfully!!!");
					JOptionPane.showMessageDialog(null, "Ticket ID: " + id + " created");	
				} else 
				{
					System.out.println("Ticket cannot be created!!!");
				}

			} catch (SQLException ex) 
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} else if (e.getSource() == mnuItmVwTckt) 
		{

			// Retrieve ticket information for viewing in 
			try 
			{
				Connection con = DriverManager.getConnection(dbSite);
				Statement stmnt = con.createStatement();
				ResultSet rslts = stmnt.executeQuery("SELECT * FROM clope_tckts");

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(rslts));

				jt.setBounds(30, 40, 200, 300);
				sp = new JScrollPane(jt);
				mnFrm.add(sp);
				mnFrm.setVisible(true); // refreshes or repaints frame on
											// screen
				stmnt.close();
				con.close(); // close connections!!!

			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mnuItmMyTckt) 
		{

			// retrieve ticket information for viewing in JTable

			try 
			{
				Connection con = DriverManager.getConnection(dbSite);
				Statement stmnt = con.createStatement();
				String tcktUsr = JOptionPane.showInputDialog(null, "Enter your name?");
				ResultSet rslts = stmnt.executeQuery("SELECT * FROM clope_tckts WHERE ticket_issuer='"+tcktUsr+"'");

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(rslts));

				jt.setBounds(30, 40, 200, 300);
				sp = new JScrollPane(jt);
				mnFrm.add(sp);
				mnFrm.setVisible(true); // refreshes or repaints frame on
											// screen
				stmnt.close();
				con.close(); // close connections!!!

			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mnuItmViewByIssuer) 
		{

			// retrieve ticket information for viewing in JTable

			try 
			{
				Connection con = DriverManager.getConnection(dbSite);

				Statement stmnt = con.createStatement();
				String tcktUsr = JOptionPane.showInputDialog(null, "Enter issuer name?");
				ResultSet rslts = stmnt.executeQuery("SELECT * FROM clope_tckts WHERE ticket_issuer = '" + tcktUsr + "'");

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(rslts));

				jt.setBounds(30, 40, 200, 300);
				sp = new JScrollPane(jt);
				mnFrm.add(sp);
				mnFrm.setVisible(true); // refreshes or repaints frame on
											// screen
				stmnt.close();
				con.close(); // close connections!!!

			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == mnuItmDlt) 
		{
			try 
			{
				// get ticket information
				String ticketId = JOptionPane.showInputDialog(null, "Enter ticket number you want to delete?");

				// insert ticket information to database
				Connection con = DriverManager.getConnection(dbSite);

				Statement stmnt = con.createStatement();
				int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete ticket number "+ ticketId +"?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if(confirm==JOptionPane.YES_OPTION)
				{
					stmnt.executeUpdate("DELETE FROM clope_tckts WHERE ticket_id =" + ticketId);
					JOptionPane.showMessageDialog(null, "Ticket id: " + ticketId + "deleted");
				}
				else if(confirm==JOptionPane.NO_OPTION)
				{
					System.out.println("Ticket was not deleted");
				}
				// retrieve ticket id number newly auto generated upon record
				// insertion
								

			} catch (SQLException ex) 
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} 
		}
		else if (e.getSource() == mnuItmUpdt) 
		{			 	
			try 
			{
				// get ticket information
				String ticketId = JOptionPane.showInputDialog(null, "Enter ticket number you want to update: ");
				
				// insert ticket information to database
				Connection con = DriverManager.getConnection(dbSite);


				Statement stmnt = con.createStatement();
				String newDescription = JOptionPane.showInputDialog(null, "Enter new description");
				String sql =" UPDATE clope_tckts SET ticket_description := '" + newDescription + "' WHERE ticket_Id = "+ticketId;
			
				stmnt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Ticket id: " + ticketId + " updated");
					
			}catch(SQLException es)
			{
				es.printStackTrace();
			}	

		}else if (e.getSource() == mnuItmCls)
		{			 	
			try 
			{
				// get ticket information
				String ticketId = JOptionPane.showInputDialog(null, "Enter ticket number you want to close: ");

				// insert ticket information to database
				Connection con = DriverManager.getConnection(dbSite);

				Statement stmnt = con.createStatement();
				String newStatus = "close";
				String sql =" UPDATE clope_tckts  SET ticket_status =  '" + newStatus + "'  WHERE ticket_Id ="+ticketId;
						
				stmnt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Ticket id: " + ticketId + " closed");
						
			}catch(SQLException es)
			{
				es.printStackTrace();
			}			
		}
		else if(e.getSource() == mnuItmRfrsh)
		{
			prepareGUI();
		}
	}		 
}