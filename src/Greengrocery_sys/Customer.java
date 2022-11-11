package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Customer {

	protected JFrame frame;
	private JTable tbCustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		//close the Jframe_autoClose button
		//frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JButton btnNewButton = new JButton("ဝယ္သူအသစ္");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addCustomer_D().frame.setVisible(true);
			}
		});
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		btnNewButton.setBounds(400, 28, 172, 47);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCustomerList = new JButton("ကုန္သည္စာရင္း");
		btnCustomerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = new DbConnection().connect();
				String sqlString = "select Name, Address, Phone from customer;";
				
				try {
					PreparedStatement pStatement = conn.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					tbCustomer.setModel(DbUtils.resultSetToTableModel(rSet));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnCustomerList.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		btnCustomerList.setBackground(SystemColor.info);
		btnCustomerList.setBounds(10, 28, 172, 47);
		frame.getContentPane().add(btnCustomerList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 586, 334);
		frame.getContentPane().add(scrollPane);
		
		tbCustomer = new JTable();
		scrollPane.setViewportView(tbCustomer);
		
		JButton btnClose = new JButton("ပိတ္မည္");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setBackground(SystemColor.activeCaption);
		btnClose.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		btnClose.setBounds(459, 431, 137, 29);
		frame.getContentPane().add(btnClose);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Tomato Green Grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 608, 475);
		frame.getContentPane().add(lblNewLabel);
	}
}
