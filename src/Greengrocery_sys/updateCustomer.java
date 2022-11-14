package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updateCustomer {
 
	public void updateCus (String id, String name, String address, String phone) {
		
		Connection connection = new DbConnection().connect();
		String sqlString = "update customer set Id_customer = ? , Name = ?, Address = ?, Phone = ?;";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, id);
			pStatement.setString(2, name);
			pStatement.setString(3, address);
			pStatement.setString(4, phone);
			
			pStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "အခ်က္အလက္မ်ားျပင္ဆင္ၿပီးပါၿပီ။");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
