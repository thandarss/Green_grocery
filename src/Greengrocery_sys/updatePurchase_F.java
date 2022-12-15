package Greengrocery_sys;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Greengrocery_sys.Database.DbConnection;

public class updatePurchase_F {

	public void updatePurchase(int id, String type, int bucketP, int boxP, int cardP, int vissP) {
		
		int idPurchase = id;
		String typeString = type;
		int bucketPrice = bucketP;
		int vissPrice = vissP;
		int boxPrice = boxP;
		int cardPrice = cardP;
		
		Connection connection = new DbConnection().connect();
		String sqlString = "update purchase_price set Type =?, Bucket_Price=?, Box_Price=?, Card_Price=?, Viss_Price =? where Id_purchase = " + idPurchase + ";";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1,typeString);
			pStatement.setInt(2, bucketPrice);
			pStatement.setInt(3, boxPrice);
			pStatement.setInt(4, cardPrice);
			pStatement.setInt(5, vissPrice);
			int result = pStatement.executeUpdate();
			
			if(result != -1) {
				String updateString = "လက္ရွိစာရင္းကို ျပင္ၿပီးပါၿပီ";
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
