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
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Order_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtBucketNum;
	private JTextField txtBoxNum;
	private JTextField txtVissNum;
	private JTable tbOrder;
	private JComboBox cboxName, cboxType;
	protected int customerId, productId;
	protected String nameString,typeString;
	Connection connection;
	private JTextField txtCardNum;

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
		String sqlString2 = "select * from customer_order";						
		//String sqlString2 = "select Id_order, Type, Bucket, Box, Card, Viss, Price, Total, Date from customer_order;";
		try {
					
			//String columns[] = {"Id_order","Type", "Bucket", "Box", "Card", "Viss", "Price", "Total", "Date", "Id_customer", "Id_product"};
			//DefaultTableModel model = new DefaultTableModel();
			//model.setColumnIdentifiers(columns);
			//tbOrder.setModel(model);
			
			//columnModel.removeColumn(columnModel.getColumn(8));
			//columnModel.removeColumn(columnModel.getColumn(10));
			
			PreparedStatement pStatement1 = connection.prepareStatement(sqlString2);
			ResultSet rSet = pStatement1.executeQuery();
			tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
			TableColumnModel columnModel = tbOrder.getColumnModel();
			
			tbOrder.removeColumn(columnModel.getColumn(10));
			tbOrder.removeColumn(columnModel.getColumn(9));
			tbOrder.removeColumn(columnModel.getColumn(0));
			
			//System.out.println("Column Model: " + columnModel.getColumnIndex("Id_product"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Show Selected Customer Data
	 */
	
	public void selectedCusOrder() {
		connection = new DbConnection().connect();
		
		nameString = cboxName.getSelectedItem().toString();
		
		if(nameString.equals("All")) {
			refreshTable();
		}
		
		else {
			String sqlString = "select Id_customer from customer where Name = '" + nameString + "';";
			try {
				PreparedStatement pStatement = connection.prepareStatement(sqlString);
				ResultSet rSet = pStatement.executeQuery();
				
				if (rSet.next()) {
					customerId = rSet.getInt("Id_customer");
					System.out.println(customerId + " selected ID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sqlString1 = "select Id_order, Type, Bucket, Box, Viss, Price, Total, Date from customer_order where Id_customer = '" + customerId + "';";
			
			try {
				PreparedStatement pStatement = connection.prepareStatement(sqlString1);
				ResultSet rSet = pStatement.executeQuery();
				tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
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
			
			cboxName.addItem("All");
			
			while(rSet.next()) {
				cboxName.addItem(rSet.getString("Name"));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * public void fillTypeBox() {
	 

		connection = new DbConnection().connect();
		String sqString = "select * from product_price";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqString);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				cboxType.addItem(rSet.getString("Type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

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
		
		JLabel lblNewLabel = new JLabel("ဝယ္သူ");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 18));
		lblNewLabel.setBounds(880, 15, 70, 34);
		contentPane.add(lblNewLabel);
		
		cboxName = new JComboBox();
		cboxName.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		cboxName.setBounds(964, 16, 165, 34);
		contentPane.add(cboxName);
		
		JLabel lblNewLabel_1 = new JLabel("ေတာင္း/ျခင္း‌");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1.setBounds(872, 150, 98, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ေသတၱာ");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(883, 246, 67, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ပိႆာ");
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(1022, 246, 67, 34);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtBucketNum = new JTextField();
		txtBucketNum.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtBucketNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBucketNum.setText("");
			}
		});
		txtBucketNum.setText("0");
		txtBucketNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBucketNum.setText("");
				}
				else {
					txtBucketNum.setEditable(true);
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBoxNum.requestFocus();
					}
				}
			}
		});
		txtBucketNum.setBounds(883, 195, 58, 31);
		contentPane.add(txtBucketNum);
		txtBucketNum.setColumns(10);
		
		txtBoxNum = new JTextField();
		txtBoxNum.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtBoxNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBoxNum.setText("");
			}
		});
		txtBoxNum.setText("0");
		txtBoxNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtBoxNum.setText("");
				}
				else {
					txtBoxNum.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtVissNum.requestFocus();
					}
				}
			}
		});
		txtBoxNum.setColumns(10);
		txtBoxNum.setBounds(883, 291, 58, 31);
		contentPane.add(txtBoxNum);
		
		txtVissNum = new JTextField();
		txtVissNum.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtVissNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtVissNum.setText("");
			}
		});
		txtVissNum.setText("0.00");
		txtVissNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter NUMBER (1,2,3,...) only.","Warning!",JOptionPane.INFORMATION_MESSAGE);
					txtVissNum.setText("");
				}
				else {
					txtVissNum.setEditable(true);
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtBucketNum.requestFocus();
					}
				}
			}
		});
		txtVissNum.setColumns(10);
		txtVissNum.setBounds(1022, 291, 58, 31);
		contentPane.add(txtVissNum);
		
		txtCardNum = new JTextField();
		txtCardNum.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtCardNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCardNum.setText("");
			}
		});
		txtCardNum.setText("0");
		txtCardNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter NUMBER (123,...) only.", "Warning!", JOptionPane.INFORMATION_MESSAGE);
					txtCardNum.setText("");
				}
				else {
					txtCardNum.setEditable(true);
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						txtVissNum.requestFocus();
					}
				}
			}
		});
		txtCardNum.setColumns(10);
		txtCardNum.setBounds(1022, 195, 58, 31);
		contentPane.add(txtCardNum);
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(685, 9, 149, 40);
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
		tbOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbOrder.getSelectedRow();
				String rowString = tbOrder.getModel().getValueAt(row, 0).toString();
				int id = Integer.parseInt(rowString);
				
				String cusIdString = tbOrder.getModel().getValueAt(row, 9).toString(); 
				customerId = Integer.parseInt(cusIdString);
				
				String sqlString = "select * from customer_order where Id_order = "+ id + ";";
				String sqlString2 = "select Name from customer where Id_customer = " + customerId +";";
				connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if(rSet.next()) {
						cboxType.setSelectedItem(rSet.getString("Type"));
						txtBoxNum.setText(rSet.getString("Bucket"));
						txtBucketNum.setText(rSet.getString("Box"));
						txtVissNum.setText(rSet.getString("Viss"));
						txtCardNum.setText(rSet.getString("Card"));		
					}
					
					PreparedStatement pStatement2 = connection.prepareStatement(sqlString2);
					ResultSet rSet2 = pStatement2.executeQuery();
					
					if(rSet2.next()) {
						cboxName.setSelectedItem(rSet2.getString("Name"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tbOrder.setFont(new Font("Zawgyi-One", Font.PLAIN, 13));
		tbOrder.getTableHeader().setFont(new Font("Verdana", Font.BOLD,14));
		tbOrder.setRowHeight(30);
		
		scrollPane.setViewportView(tbOrder);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameString = cboxName.getSelectedItem().toString();
				typeString = cboxType.getSelectedItem().toString();
				int bucketNum = Integer.parseInt(txtBucketNum.getText());
				int boxNum = Integer.parseInt(txtBoxNum.getText());
				double vissNum = Double.parseDouble(txtVissNum.getText());
				int cardNum = Integer.parseInt(txtCardNum.getText());
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				new addOrder_F().addOrder(nameString,typeString, bucketNum, boxNum, vissNum, cardNum, dateString);
				refreshTable();
			}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(861, 346, 98, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbOrder.getSelectedRow();
				
				if(row != -1) {
					String idString = tbOrder.getModel().getValueAt(row, 0).toString();
					String typeString = tbOrder.getModel().getValueAt(row, 1).toString();
					String bucketString = tbOrder.getModel().getValueAt(row, 2).toString();
					String boxString = tbOrder.getModel().getValueAt(row, 3).toString();
					String cardString = tbOrder.getModel().getValueAt(row, 4).toString();
					String vissString = tbOrder.getModel().getValueAt(row, 5).toString();
					String priceString = tbOrder.getModel().getValueAt(row, 6).toString();
					String totalString = tbOrder.getModel().getValueAt(row, 7).toString();
					String dateString = tbOrder.getModel().getValueAt(row, 8).toString();
					
					int id = Integer.parseInt(idString);
					int bucket = Integer.parseInt(bucketString);
					int box = Integer.parseInt(boxString);
					int card = Integer.parseInt(cardString);
					double viss = Double.parseDouble(vissString);
					int price = Integer.parseInt(priceString);
					double total = Double.parseDouble(totalString);
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
					
					if(confirm == 0 ) {
						//new addOrder_Backup().addOrderBackup(id, typeString, dateString);
						//new deleteOrder_F().deleteOrder(id);
					}
				}
			}
		});
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.setBounds(1005, 346, 92, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = tbOrder.getSelectedRow();
				String idString = tbOrder.getModel().getValueAt(row, 0).toString();
				String cusIdString = tbOrder.getModel().getValueAt(row, 9).toString();
				String proIdString = tbOrder.getModel().getValueAt(row, 10).toString();
				
				int orderId = Integer.parseInt(idString);
				customerId = Integer.parseInt(cusIdString);
				productId = Integer.parseInt(proIdString);				
				nameString = cboxName.getSelectedItem().toString();
				typeString = cboxType.getSelectedItem().toString();
				int bucketNum = Integer.parseInt(txtBucketNum.getText());
				int boxNum = Integer.parseInt(txtBoxNum.getText());
				double vissNum = Double.parseDouble(txtVissNum.getText());
				int cardNum = Integer.parseInt(txtCardNum.getText());
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				new updateOrder_F().updateOrder(orderId, nameString, typeString, bucketNum, boxNum, vissNum, cardNum, dateString, customerId, productId);
				
			}
		});
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(861, 401, 98, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtBucketNum.setText("0");
				txtBoxNum.setText("0");
				txtCardNum.setText("0");
				txtVissNum.setText("0.00");
				cboxName.setSelectedIndex(0);
				cboxType.setSelectedIndex(0);
			}
		});
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(1005, 401, 92, 34);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("အမ်ိဳးအစား");
		lblNewLabel_3.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_3.setBounds(861, 78, 98, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("ကဒ္");
		lblNewLabel_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(1022, 150, 67, 34);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး", "အလုပ္သမားခ"};
		cboxType = new JComboBox(item);
		cboxType.setFont(new Font("Zawgyi-One", Font.PLAIN, 17));
		cboxType.setBounds(964, 81, 165, 34);
		contentPane.add(cboxType);
		
		refreshTable();
		fillNameBox();
		//fillTypeBox();
	}
}
