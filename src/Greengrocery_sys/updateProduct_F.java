package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updateProduct_F {

	public void updateProduct(int id, String type, int bucketP, int vissP, int boxP, int cardP) {
		
		int idProduct = id;
		String typeString = type;
		int bucketPrice = bucketP;
		int vissPrice = vissP;
		int boxPrice = boxP;
		int cardPrice = cardP;
		
		Connection connection = new DbConnection().connect();
		String sqString = "update sale_price set Type=?, Bucket_Price = ?, Viss_Price = ?, Box_Price = ?, Card_Price = ? where Id_Product = " + idProduct + ";";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			pStatement.setString(1, typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, vissPrice);
			pStatement.setInt(4, boxPrice);
			pStatement.setInt(5, cardPrice);
			
			int result = pStatement.executeUpdate();
			
			if(result != -1) {
				String updateString = "လက္ရွိစာရင္းကို ျပင္ၿပီးပါၿပီ";
				JLabel textJLabel = new JLabel(updateString);
				textJLabel.setFont(new Font("Zawgyi-One",Font.BOLD,20));
				JOptionPane.showMessageDialog(null, textJLabel, "Update Information", JOptionPane.PLAIN_MESSAGE);
			}
			
			
			//if(result>0)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Add New Product!");
		}
	}
}
