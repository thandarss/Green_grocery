package Greengrocery_sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class Login_F {

	public boolean checkLogin(String name,String passString) {
		
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from login where Name = ? and Password = ?";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, name);
			pStatement.setString(2, passString);
			
			ResultSet rSet = pStatement.executeQuery();
			
			if (rSet.next()) {
				JOptionPane.showMessageDialog(null, "Login Succeeded!");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Login Failed!");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
