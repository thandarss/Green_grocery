package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Order_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtBucketPrice;
	private JTextField txtBoxPrice;
	private JTextField txtRentPeople;
	private JTextField txtVissPrice;
	private JTable tbOrder;
	private JComboBox cboxName;
	protected int customerId, productId;
	Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_D frame = new Order_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 *refresh table 
	 */
	
	public void refreshTable() {
		connection = new DbConnection().connect();
		String sqlString = "select Id_Invoice, Type, Bucket, Box, Viss, Price, Total, Date from customer_order;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Fill ComboBox Name
	 */
	
	public void fillNameBox() {
		connection = new DbConnection().connect();
		String sqlString = "select * from customer;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()) {
				cboxName.addItem(rSet.getString("Name"));
				cboxName.addItem("All");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Order_D() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("နာမည္");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		lblNewLabel.setBounds(862, 37, 98, 34);
		contentPane.add(lblNewLabel);
		
		cboxName = new JComboBox();
		cboxName.setBounds(944, 39, 185, 34);
		contentPane.add(cboxName);
		
		JLabel lblNewLabel_1 = new JLabel("ေတာင္းေစ်း");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1.setBounds(894, 105, 98, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ေသတၱာေစ်း");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(894, 168, 116, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ပိႆာေစ်း");
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(894, 237, 116, 34);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("လုပ္အားခ");
		lblNewLabel_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(894, 310, 76, 34);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtBucketPrice = new JTextField();
		txtBucketPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBucketPrice.setText("");
				}
				else {
					txtBucketPrice.setEditable(true);
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBoxPrice.requestFocus();
					}
				}
			}
		});
		txtBucketPrice.setBounds(996, 107, 116, 31);
		contentPane.add(txtBucketPrice);
		txtBucketPrice.setColumns(10);
		
		txtBoxPrice = new JTextField();
		txtBoxPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBoxPrice.setText("");
				}
				else {
					txtBoxPrice.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtVissPrice.requestFocus();
					}
				}
			}
		});
		txtBoxPrice.setColumns(10);
		txtBoxPrice.setBounds(996, 168, 116, 34);
		contentPane.add(txtBoxPrice);
		
		txtRentPeople = new JTextField();
		txtRentPeople.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtRentPeople.setText("");;
				}
				else {
					txtRentPeople.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBucketPrice.requestFocus();
					}
				}
			}
		});
		txtRentPeople.setColumns(10);
		txtRentPeople.setBounds(996, 307, 116, 40);
		contentPane.add(txtRentPeople);
		
		txtVissPrice = new JTextField();
		txtVissPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtVissPrice.setText("");
				}
				else {
					txtVissPrice.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtRentPeople.requestFocus();
					}
				}
			}
		});
		txtVissPrice.setColumns(10);
		txtVissPrice.setBounds(996, 234, 116, 40);
		contentPane.add(txtVissPrice);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(685, 16, 149, 40);
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,17));
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("ေအာ္ဒါစာရင္း");
		lblNewLabel_2.setFont(new Font("Zawgyi-One", Font.BOLD, 21));
		lblNewLabel_2.setBounds(10, 11, 131, 34);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 824, 390);
		contentPane.add(scrollPane);
		
		tbOrder = new JTable();
		scrollPane.setViewportView(tbOrder);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(894, 371, 98, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.setBounds(1025, 371, 92, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(894, 423, 98, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(1025, 423, 92, 34);
		contentPane.add(btnClear);
		
		refreshTable();
		fillNameBox();
	}
}
