package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class deleteCustomer {
	
	public void deleteCus(String idString ) {
		Connection connection = new DbConnection().connect();
		String sqlString = "delete from customer where Id_customer='" + idString + "';";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.executeUpdate();
			
			//JOptionPane.showMessageDialog(null, "Delected Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
