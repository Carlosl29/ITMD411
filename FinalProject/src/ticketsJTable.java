
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
//Carlos Lopez
//Final Project
//ITM 411
//Due Date: 04/29/2018

import javax.swing.table.DefaultTableModel;

public class ticketsJTable 
{
	@SuppressWarnings("unused")
	private final DefaultTableModel tableModel = new DefaultTableModel();

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException 
	{
		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> clmnNm = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		
		for (int column = 1; column <= columnCount; column++) 
		{
			clmnNm.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		while (rs.next()) 
		{
			Vector<Object> vector = new Vector<Object>();
			for (int clmnIndx = 1; clmnIndx <= columnCount; clmnIndx++) 
			{
				vector.add(rs.getObject(clmnIndx));
			}
			data.add(vector);
		}
		// return data/col.names for JTable
		return new DefaultTableModel(data, clmnNm); 

	}

}
