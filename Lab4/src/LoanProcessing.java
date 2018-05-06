import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanProcessing extends BankRecords {
	//methods to have:
	/* main() calls readData() to process BankRecords
	Instantiate DAO object and call createTable(), 
	insertRecords(your BankRecords array object), and
	retrieveRecords() method in that order.
	
	once recordSet is retrieved, print out all the records
	from the recordset to the console in nice columns with
	headings for id, income, and pep.  Even add title to it
	
	*/
	public static void main(String[] args)
	{
		BankRecords br = new BankRecords();
		br.readData();
		Dao dao = new Dao();
		dao.createTable();
		dao.insertRecords(robjs); // This line performs inserts
		ResultSet rs = dao.retrieveRecords(); // Fill result set object with info
		
		//Create heading for displaying it
		System.out.println("----Loan Analysis Report----");
		System.out.println("ID \tIncome \tPEP");
		
		try {	//Extract data from result set
			while(rs.next())
			{
				//Retrieve data by column name(i.e., for id, income, pep)
				String id = rs.getString(2);
				double income = rs.getDouble(3);
				String pep = rs.getString(4);
				//Display values for id, income, pep
				System.out.println(id + "\t" + income + "\t" + pep);
			}
			
			rs.close(); // closes result set object
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
}
