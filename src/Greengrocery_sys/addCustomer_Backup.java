package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class addCustomer_Backup {
	
	public void backupCus(int id) {
		
		String name=null;
		String address=null;
		String phone=null;
		PreparedStatement pStatement;
		ResultSet rSet;
		Connection connection = new DbConnection().connect();
		String sqlString1 =  "select * from customer where id_customer = " + id + ";";
		
		try {
			pStatement = connection.prepareStatement(sqlString1);
			rSet = pStatement.executeQuery();
			
			if(rSet.next()) {
				name = rSet.getString("Name");
				address = rSet.getString("Address");
				phone = rSet.getString("Phone");
				
				System.out.println("select : " + name+ address + phone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String sqlString2 = "insert into customer_backup (Id_customer, Name, Address, Phone) values (? , ?, ?, ?);";
		PreparedStatement pStatement2;
		
		try {
			pStatement2 = connection.prepareStatement(sqlString2);
			pStatement2.setInt(1, id);
			pStatement2.setString(2, name);
			pStatement2.setString(3, address);
			pStatement2.setString(4, phone);
			pStatement2.executeUpdate();
			
			System.out.println("insert : " + name+ address + phone);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * try {
		
			pStatement = connection.prepareStatement(sqlString1);
			rSet = pStatement.executeQuery();
			
			if (rSet.next()) {
				
				id = rSet.getString("Id_customer");
				name = rSet.getString("Name");
				address = rSet.getString("Address");
				phone = rSet.getString("Phone");
				
				pStatement = connection.prepareStatement(sqlString2);
				pStatement.setString(1, id);
				pStatement.setString(2, name);
				pStatement.setString(3, address);
				pStatement.setString(4, phone);
				
				System.out.println("Insert :"+ name + id + address +phone );
				
				pStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Inserted successfully");
				
				pStatement = connection.prepareStatement(sqlString3);				
				pStatement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Deleted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
}
}
