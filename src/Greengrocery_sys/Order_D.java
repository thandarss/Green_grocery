package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;

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
import javax.swing.table.TableRowSorter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.KeyStore.PrivateKeyEntry;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Order_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtBucketNum;
	private JTextField txtBoxNum;
	private JTextField txtVissNum;
	private JTable tbOrder;
	private JComboBox cboxName, cboxType, cboxNameSearch;
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
			
			tbOrder.removeColumn(columnModel.getColumn(11));
			tbOrder.removeColumn(columnModel.getColumn(10));
			tbOrder.removeColumn(columnModel.getColumn(0));
			
			//System.out.println("Column Model: " + columnModel.getColumnIndex("Id_product"));
			
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
			
			cboxNameSearch.addItem("All");
			
			while(rSet.next()) {
				cboxName.addItem(rSet.getString("Name"));	
				cboxNameSearch.addItem(rSet.getString("Name"));
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

	/*
	 * filter the data by Date
	
	
	public void filter(String query) {
		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<DefaultTableModel>();
		tbOrder.setRowSorter(rowSorter);
		
		rowSorter.setRowFilter(RowFilter.regexFilter(query));
	}
	 */
	
	/*
	 * frame mouse listener
	 */
	
	public class frameMouseListener implements MouseListener, MouseMotionListener{

		private Point pressedPoint;
		private Rectangle framebounds;
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseFrame(arg0);
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			this.pressedPoint = arg0.getPoint();
			this.framebounds = new Order_D().getBounds();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseFrame(arg0);
			
		}
		
		public void mouseFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressedPoint.x;
			int yDiff = endPoint.y - pressedPoint.y;
			framebounds.x += xDiff;
			framebounds.y += yDiff;
			setBounds(framebounds);
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public Order_D() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		frameMouseListener listener = new frameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		JButton btnMinimize = new JButton("-");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnMinimize.setBackground(Color.RED);
		btnMinimize.setBounds(1054, 0, 43, 31);
		contentPane.add(btnMinimize);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnClose.setBackground(Color.RED);
		btnClose.setBounds(1096, 0, 43, 31);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel = new JLabel("ဝယ္သူ");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 18));
		lblNewLabel.setBounds(879, 51, 70, 34);
		contentPane.add(lblNewLabel);
		
		cboxName = new JComboBox();
		cboxName.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		cboxName.setBounds(963, 52, 165, 34);
		contentPane.add(cboxName);
		
		JLabel lblNewLabel_1 = new JLabel("ေတာင္း/ျခင္း‌");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1.setBounds(871, 186, 98, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ေသတၱာ");
		lblNewLabel_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(882, 282, 67, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ပိႆာ");
		lblNewLabel_1_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(1021, 282, 67, 34);
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
		txtBucketNum.setBounds(882, 231, 58, 31);
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
		txtBoxNum.setBounds(882, 327, 58, 31);
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
		txtVissNum.setBounds(1021, 327, 58, 31);
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
		txtCardNum.setBounds(1021, 231, 58, 31);
		contentPane.add(txtCardNum);
		
		
		JDateChooser dateChooser = new JDateChooser();			
		dateChooser.setBounds(584, 11, 149, 40);
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,17));
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("ေအာ္ဒါစာရင္း");
		lblNewLabel_2.setForeground(SystemColor.info);
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
								
				String cusIdString = tbOrder.getModel().getValueAt(row, 10).toString(); 
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
					
					//set date to Jdatechooser
					
					DefaultTableModel model = (DefaultTableModel)tbOrder.getModel();
					try {
						Date date = new SimpleDateFormat("MMM d, yyyy").parse((String)model.getValueAt(row, 9));
						dateChooser.setDate(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
				
				String bucketString, boxString, cardString, vissString;
				int bucketNum, boxNum, cardNum;
				double vissNum;
				
				nameString = cboxName.getSelectedItem().toString();
				typeString = cboxType.getSelectedItem().toString();
				bucketString = txtBucketNum.getText();
				boxString = txtBoxNum.getText();
				cardString = txtCardNum.getText();
				vissString = txtVissNum.getText();
				
				
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
					if (bucketString.equals("")) {
						bucketNum = 0;
					}
					else {
						bucketNum = Integer.parseInt(txtBucketNum.getText());
					}
					if(boxString.isEmpty()) {
						boxNum = 0;
					}
					else {
						boxNum = Integer.parseInt(txtBoxNum.getText());
					}
					if(vissString.isEmpty()) {
						vissNum = 0;
					}
					else {
						vissNum = Double.parseDouble(txtVissNum.getText());
					}
					if(cardString.isEmpty()) {
						cardNum = 0;
					}
					else {
						cardNum = Integer.parseInt(txtCardNum.getText());
					}					
					new addOrder_F().addOrder(nameString,typeString, bucketNum, boxNum, vissNum, cardNum, dateString);
					refreshTable();
				}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(860, 382, 98, 34);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("ဖ်က္မည္");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbOrder.getSelectedRow();
				
				if(row != -1) {
					String idString = tbOrder.getModel().getValueAt(row, 0).toString();
					String namString = tbOrder.getModel().getValueAt(row, 1).toString();
					String typeString = tbOrder.getModel().getValueAt(row, 2).toString();
					String bucketString = tbOrder.getModel().getValueAt(row, 3).toString();
					String boxString = tbOrder.getModel().getValueAt(row, 4).toString();
					String cardString = tbOrder.getModel().getValueAt(row, 5).toString();
					String vissString = tbOrder.getModel().getValueAt(row, 6).toString();
					String priceString = tbOrder.getModel().getValueAt(row, 7).toString();
					String totalString = tbOrder.getModel().getValueAt(row, 8).toString();
					String dateString = tbOrder.getModel().getValueAt(row, 9).toString();
					String customerIdString = tbOrder.getModel().getValueAt(row, 10).toString();
					String productIdString = tbOrder.getModel().getValueAt(row, 11).toString();
					
					int id = Integer.parseInt(idString);
					int bucket = Integer.parseInt(bucketString);
					int box = Integer.parseInt(boxString);
					int card = Integer.parseInt(cardString);
					double viss = Double.parseDouble(vissString);
					int price = Integer.parseInt(priceString);
					double total = Double.parseDouble(totalString);
					int customerId = Integer.parseInt(customerIdString);
					int productId = Integer.parseInt(productIdString);
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
					
					if(confirm == 0 ) {
						new addOrder_Backup().addOrderBackup(id, namString, typeString, bucket, box, card, viss, price, total, dateString, customerId, productId);
						new deleteOrder_F().deleteOrder(id);;
						refreshTable();
					}
				}
			}
		});
		btnRemove.setForeground(new Color(128, 64, 0));
		btnRemove.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemove.setBackground(new Color(254, 251, 245));
		btnRemove.setBounds(1004, 382, 92, 34);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = tbOrder.getSelectedRow();
				String idString = tbOrder.getModel().getValueAt(row, 0).toString();
				String cusIdString = tbOrder.getModel().getValueAt(row, 10).toString();
				String proIdString = tbOrder.getModel().getValueAt(row, 11).toString();
				
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
		btnUpdate.setBounds(860, 437, 98, 34);
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
		btnClear.setBounds(1004, 437, 92, 34);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("အမ်ိဳးအစား");
		lblNewLabel_3.setForeground(SystemColor.info);
		lblNewLabel_3.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_3.setBounds(860, 114, 98, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("ကဒ္");
		lblNewLabel_1_1_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(1021, 186, 67, 34);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး", "အလုပ္သမားခ"};
		cboxType = new JComboBox(item);
		cboxType.setFont(new Font("Zawgyi-One", Font.PLAIN, 17));
		cboxType.setBounds(963, 117, 165, 34);
		contentPane.add(cboxType);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				System.out.println(dateString);
				//filter(dateString);
				connection = new DbConnection().connect();
				
				
					
				String sqlString1 = "select * from customer_order where Date = '"+ dateString + "';";
					
					try {
						PreparedStatement pStatement = connection.prepareStatement(sqlString1);
						ResultSet rSet = pStatement.executeQuery();
						tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
						
						TableColumnModel columnModel = tbOrder.getColumnModel();
						
						tbOrder.removeColumn(columnModel.getColumn(11));
						tbOrder.removeColumn(columnModel.getColumn(10));
						tbOrder.removeColumn(columnModel.getColumn(0));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSearch.setBackground(SystemColor.info);
		btnSearch.setBounds(751, 15, 83, 33);
		contentPane.add(btnSearch);
		
		cboxNameSearch = new JComboBox();
		cboxNameSearch.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				String nameString = cboxNameSearch.getSelectedItem().toString();
				String sqlString = "select * from customer_order where Customer = '" + nameString + "';";
				
				if(nameString.equals("All")) {
					refreshTable();
				}
				
				else {
					try {
						PreparedStatement pStatement = connection.prepareStatement(sqlString);
						ResultSet rSet = pStatement.executeQuery();
						
						tbOrder.setModel(DbUtils.resultSetToTableModel(rSet));
						TableColumnModel columnModel = tbOrder.getColumnModel();
						
						tbOrder.removeColumn(columnModel.getColumn(11));
						tbOrder.removeColumn(columnModel.getColumn(10));
						tbOrder.removeColumn(columnModel.getColumn(0));
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		
		cboxNameSearch.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		cboxNameSearch.setBounds(151, 11, 165, 34);
		contentPane.add(cboxNameSearch);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel_4.setBounds(0, 0, 1139, 486);
		lblNewLabel_4.setBackground(new Color(0,28,0));
		contentPane.add(lblNewLabel_4);
		
		refreshTable();
		fillNameBox();
		//fillTypeBox();
	}
}
