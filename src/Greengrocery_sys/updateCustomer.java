package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updateCustomer {
 
	public void updateCus (int id, String name, String address, String phone) {
		
		Connection connection = new DbConnection().connect();
		String sqlString = "update customer set Name = ?, Address = ?, Phone = ? where Id_customer = " + id + ";";
				
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, name);
			pStatement.setString(2, address);
			pStatement.setString(3, phone);
						
			int result = pStatement.executeUpdate();
			
			if (result != -1) {
				String updateString = "Customer စာရင္းကို ျပင္ဆင္ၿပီးပါၿပီ!";
				JLabel textJLabel = new JLabel(updateString);
				textJLabel.setFont(new Font("Zawgyi-One",Font.BOLD,20));
				JOptionPane.showMessageDialog(null, textJLabel, "Update Information", JOptionPane.PLAIN_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
