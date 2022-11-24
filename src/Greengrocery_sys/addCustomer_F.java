package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class addCustomer_F {

	protected void addCustomer(String name, String address, String phone) {

		Connection connection = new DbConnection().connect();
		
		/*
		 * insert new data
		 */
		String sqlString = "insert into customer (Name, Address, Phone) values ( ? , ?, ?);";
		String nameString = name;
		String addressString = address;
		String phoneString = phone;
		
		PreparedStatement pStatement;
		try {
			pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, nameString);
			pStatement.setString(2, addressString);
			pStatement.setString(3, phoneString);
			
			pStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null,"Added New Customer Successfully!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Add New Customer!");
		}
		
		
		
	}
	
	
}
