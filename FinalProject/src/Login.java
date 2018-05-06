import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//Carlos Lopez
//Final Project
//ITM 411
//Due Date: 04/29/2018

import com.mysql.jdbc.PreparedStatement;

public class Login {

	// create instance fields for class
	private JFrame mnFrm;
	private JLabel hdrLbl;
	private JLabel sttsLbl;
	private JLabel nmLbl;
	private JLabel pswdLbl;
	private JTextField usrTxt;
	private JPasswordField pswdTxt;
	private JButton lgnBtn;
	private JPanel cntrlPnl;

	public Login() 
	{
		prepareGUI();
		showTextFields();
	}

	private void prepareGUI() 
	{
		// instantiate objects
		
		mnFrm = new JFrame("Login"); // title of window form
		hdrLbl = new JLabel("", JLabel.CENTER);
		sttsLbl = new JLabel("", JLabel.CENTER);
		cntrlPnl = new JPanel();

		// window frame settings
		mnFrm.setSize(400, 400);
		mnFrm.setLayout(new GridLayout(3, 1));
		mnFrm.getContentPane().setBackground(Color.white);
		mnFrm.setLocationRelativeTo(null);

		// frame object settings
		hdrLbl.setText("System Login");
		sttsLbl.setSize(350, 100);

		// add frame objects to mainframe
		mnFrm.add(hdrLbl);
		mnFrm.add(cntrlPnl);
		mnFrm.add(sttsLbl);

		mnFrm.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent wE) { // define a window close
				// operation
				System.exit(0);
			}
		});
	}

	private void showTextFields() 
	{
		// instantiate controls
		nmLbl = new JLabel("User ID: ", JLabel.RIGHT);
		pswdLbl = new JLabel("Password: ", JLabel.CENTER);
		usrTxt = new JTextField(6);
		pswdTxt = new JPasswordField(6);

		lgnBtn = new JButton("Login");
		lgnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 //Check credentials for various users
				 
				 // Administrator is a super user to the ticket system Code below
				 // uses a default (temporary) hard coded admin user
				 //name/password for verification. You can change this setting
				 //if you like.
				
				// Create user friendly variable name to store user name from text box
				String usrNm = usrTxt.getText();
				
				// convert characters from password field to string for input validation
				String pswd = new String(pswdTxt.getPassword());
				boolean adminFlag = false;
				
				if (usrNm.equals("admin") && pswd.equals("admin1")) 
				{
					adminFlag = true;
					// close of Login window
					mnFrm.dispose();
					// open up ticketsGUI file upon successful login
					new ticketsGUI("Admin"); // establish role as admin via constructor call
				}
						
				
				//
				// match credentials from text fields with users table for a
				 // match for regular users
				 //
				else if (!adminFlag) 
				{
					Connection connect = Dao.getConnection();
					String qryStrng = "SELECT uname, upass FROM clope_usrs where uname=? and upass=?";
					PreparedStatement ps;
					ResultSet results = null;
				
					try 
					{
						// set up prepared statements to execute query string cleanly and safely
						ps = (PreparedStatement) connect.prepareStatement(qryStrng);
						ps.setString(1, usrNm);
						ps.setString(2, pswd);
						results = ps.executeQuery();
						
						if (results.next()) 
						{ // verify if a record match exists
							// in table
							JOptionPane.showMessageDialog(null, "Username and Password exist");
							// close of Login window
							mnFrm.dispose();
							// open up ticketsGUI file upon successful login
							new ticketsGUI(usrNm); // establish role as
														// regular user via
														// constructor call
							
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Please Check Username and Password ");
						}
					} catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					} finally 
					{
						try 
						{
							results.close();
						} catch (SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try 
						{
							connect.close();
						} catch (SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}

		});
		// add layout type /background color to control panel
		cntrlPnl.setLayout(new FlowLayout());
		cntrlPnl.setBackground(Color.pink);

		// add controls to control panel
		cntrlPnl.add(nmLbl);
		cntrlPnl.add(usrTxt);
		cntrlPnl.add(pswdLbl);
		cntrlPnl.add(pswdTxt);
		cntrlPnl.add(lgnBtn);
		// lastly set visibility of Window as all controls are instantiated for
		// frame
		mnFrm.setVisible(true);

	}

	public static void main(String[] args) 
	{
		new Login();
		
	}

}