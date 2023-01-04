package Greengrocery_sys;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import Greengrocery_sys.Order_D.frameMouseListener;
import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.toedter.calendar.JDateChooser;

public class purchaseProduct_D extends JFrame {

	private JPanel contentPane;
	private JTable tbPurchase;
	private JTextField txtBucketNum;
	private JTextField txtCardNum;
	private JTextField txtBoxNum;
	private JTextField txtVissNum;
	private JComboBox cboxType;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchaseProduct_D frame = new purchaseProduct_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Frame mouse listener
	 */
	
	private class FrameMouseListener implements MouseListener, MouseMotionListener{

		private Point pressPoint;
		private Rectangle framebounds;
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			mouseFrame(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			this.pressPoint = e.getPoint();
			this.framebounds = new purchaseProduct_D().getBounds();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			mouseFrame(e);
		}
		
		public void mouseFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x -pressPoint.x;
			int yDiff = endPoint.y - pressPoint.y;
			
			framebounds.x += xDiff;
			framebounds.y += yDiff;
			setBounds(framebounds);
		}
		
	}
	
	/*
	 * refresh the table
	 */
	private void refreshTable() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from purchase_order";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			tbPurchase.setModel(DbUtils.resultSetToTableModel(rSet));
			
			TableColumnModel columnModel = tbPurchase.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public purchaseProduct_D() {
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1117, 476);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnMinimize = new JButton("-");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseProduct_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Zawgyi-One", Font.BOLD, 17));
		dateChooser.setBounds(519, 18, 149, 40);
		Date date = new Date();
		dateChooser.setDate(date);
		panel.add(dateChooser);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				Date dateString = dateChooser.getDate();
				java.sql.Date searchDate = new java.sql.Date(dateString.getTime());
				String sqlString = "select * from purchase_order where Date = '" + searchDate + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					tbPurchase.setModel(DbUtils.resultSetToTableModel(rSet));
					
					TableColumnModel columnModel = tbPurchase.getColumnModel();
					columnModel.removeColumn(columnModel.getColumn(0));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSearch.setBackground(SystemColor.info);
		btnSearch.setBounds(686, 22, 83, 33);
		panel.add(btnSearch);
		
		txtPrice = new JTextField();
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPrice.setText("");
			}
		});
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBucketNum.requestFocus();
				}
			}
		});
		txtPrice.setText("0");
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtPrice.setColumns(10);
		txtPrice.setBackground(new Color(254, 251, 245));
		txtPrice.setBounds(875, 303, 111, 27);
		panel.add(txtPrice);
		
		JLabel lblTitle_1_1_1_2 = new JLabel("၁ ပိႆာ ေစ်း");
		lblTitle_1_1_1_2.setForeground(SystemColor.info);
		lblTitle_1_1_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_2.setBounds(875, 264, 106, 44);
		panel.add(lblTitle_1_1_1_2);
		btnMinimize.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnMinimize.setBackground(new Color(214, 54, 31));
		btnMinimize.setBounds(1022, 0, 44, 34);
		panel.add(btnMinimize);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseProduct_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBackground(new Color(214, 54, 31));
		btnClose.setBounds(1063, 0, 44, 34);
		panel.add(btnClose);
		
		JButton btnDelete = new JButton("ဖ်က္မည္");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbPurchase.getSelectedRow();
				String idString = tbPurchase.getModel().getValueAt(row, 0).toString();
				int id = Integer.parseInt(idString);
				
				String labelString = "ဖ်က္မွာေသခ်ာပါသလား";
				JLabel textLabel = new JLabel(labelString);
				textLabel.setFont(new Font("Zawgyi-One", Font.PLAIN, 25));
				int confirm = JOptionPane.showConfirmDialog(null, textLabel,"Confirm Dialog",JOptionPane.YES_NO_OPTION);
				if(confirm == 0) {
					new deletePurchase_F().deletePurchase(id);
					refreshTable();
				}
				
			}
		});
		btnDelete.setForeground(new Color(128, 64, 0));
		btnDelete.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnDelete.setBackground(new Color(254, 251, 245));
		btnDelete.setBounds(945, 361, 118, 34);
		panel.add(btnDelete);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bucketString, boxString, cardString, vissString, typeString, priceString;
				int bucketNum, boxNum, cardNum, price;
				double vissNum;
				
				typeString = cboxType.getSelectedItem().toString();
				bucketString = txtBucketNum.getText();
				boxString = txtBoxNum.getText();
				cardString = txtCardNum.getText();
				vissString = txtVissNum.getText();
				priceString = txtPrice.getText();
				
				Date dateString = dateChooser.getDate();
				
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
					if(priceString.isEmpty()) {
						price = 0;
					}
					else {
						price = Integer.parseInt(txtPrice.getText());
					}
				
				new addPurchase_F().addPurchase(typeString, bucketNum, boxNum, vissNum, cardNum, price, dateString);
				refreshTable();
			}
		});
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.setBounds(779, 361, 118, 34);
		panel.add(btnAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtBucketNum.setText("0");
				txtBoxNum.setText("0");
				txtCardNum.setText("0");
				txtVissNum.setText("0.00");
				txtPrice.setText("");
				cboxType.setSelectedIndex(0);
			}
		});
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.setBounds(945, 418, 118, 34);
		panel.add(btnClear);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbPurchase.getSelectedRow();
				String idString = tbPurchase.getModel().getValueAt(row, 0).toString();
				
				int purchaseId = Integer.parseInt(idString);			
				String typeString = cboxType.getSelectedItem().toString();
				int bucketNum = Integer.parseInt(txtBucketNum.getText());
				int boxNum = Integer.parseInt(txtBoxNum.getText());
				double vissNum = Double.parseDouble(txtVissNum.getText());
				int cardNum = Integer.parseInt(txtCardNum.getText());
				int vissPrice = Integer.parseInt(txtPrice.getText());
				//String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				Date dateString = dateChooser.getDate();
								
				if(row != -1) {					
					new updatePurchase_F().updatePurchase(purchaseId, typeString, bucketNum, boxNum, cardNum, vissNum, vissPrice, dateString);
					refreshTable();
				}
			}
		});
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.setBounds(779, 418, 118, 34);
		panel.add(btnUpdate);
		
		txtVissNum = new JTextField();
		txtVissNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBucketNum.requestFocus();
				}
			}
		});
		txtVissNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtVissNum.setText("");
			}
		});
		txtVissNum.setText("0");
		txtVissNum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtVissNum.setColumns(10);
		txtVissNum.setBackground(new Color(254, 251, 245));
		txtVissNum.setBounds(960, 231, 106, 27);
		panel.add(txtVissNum);
		
		txtBoxNum = new JTextField();
		txtBoxNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtCardNum.requestFocus();
				}
			}
		});
		txtBoxNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBoxNum.setText("");
			}
		});
		txtBoxNum.setText("0");
		txtBoxNum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBoxNum.setColumns(10);
		txtBoxNum.setBackground(new Color(254, 251, 245));
		txtBoxNum.setBounds(794, 231, 106, 27);
		panel.add(txtBoxNum);
		
		txtCardNum = new JTextField();
		txtCardNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtVissNum.requestFocus();
				}
			}
		});
		txtCardNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCardNum.setText("");
			}
		});
		txtCardNum.setText("0");
		txtCardNum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtCardNum.setColumns(10);
		txtCardNum.setBackground(new Color(254, 251, 245));
		txtCardNum.setBounds(960, 153, 106, 27);
		panel.add(txtCardNum);
		
		txtBucketNum = new JTextField();
		txtBucketNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBoxNum.requestFocus();
				}
			}
		});
		txtBucketNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBucketNum.setText("");
			}
		});
		txtBucketNum.setText("0");
		txtBucketNum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBucketNum.setColumns(10);
		txtBucketNum.setBackground(new Color(254, 251, 245));
		txtBucketNum.setBounds(794, 153, 106, 27);
		panel.add(txtBucketNum);
		
		JLabel lblTitle_1_1_1_1_1 = new JLabel("ကဒ္");
		lblTitle_1_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1_1.setBounds(960, 110, 90, 44);
		panel.add(lblTitle_1_1_1_1_1);
		
		JLabel lblTitle_1_1_1 = new JLabel("ပိႆာ");
		lblTitle_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1.setBounds(960, 191, 90, 44);
		panel.add(lblTitle_1_1_1);
		
		JLabel lblTitle_1_1 = new JLabel("ေတာင္း/ျခင္း‌");
		lblTitle_1_1.setForeground(SystemColor.info);
		lblTitle_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1.setBounds(794, 110, 111, 44);
		panel.add(lblTitle_1_1);
		
		JLabel lblTitle_1_1_1_1 = new JLabel("ေသတၱာ");
		lblTitle_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_1_1_1.setBounds(794, 191, 129, 44);
		panel.add(lblTitle_1_1_1_1);
		
		JLabel lblTitle_1_2 = new JLabel("အမ်ိဳးအစား");
		lblTitle_1_2.setForeground(SystemColor.info);
		lblTitle_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_2.setBounds(794, 53, 129, 44);
		panel.add(lblTitle_1_2);
		
		/*
		 * add item
		 */
		
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး", "အလုပ္သမားခ"};
		cboxType = new JComboBox(item);
		cboxType.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		cboxType.setBackground(new Color(253, 248, 236));
		cboxType.setBounds(921, 62, 145, 27);
		panel.add(cboxType);
		
		JLabel lblNewLabel_1 = new JLabel("အဝယ္ စာရင္း");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 23));
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setBounds(20, 18, 175, 37);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 66, 749, 399);
		panel.add(scrollPane);
		
		tbPurchase = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbPurchase.getSelectedRow();
				String idString = tbPurchase.getModel().getValueAt(row, 0).toString();
				int id = Integer.parseInt(idString);
				
				Connection connection = new DbConnection().connect();
				String sqlString = "select * from purchase_order where Id_purchase = " + id + ";";
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if(rSet.next()) {
						cboxType.setSelectedItem(rSet.getString("Type"));
						txtBucketNum.setText(rSet.getString("Bucket"));
						txtBoxNum.setText(rSet.getString("Box"));
						txtVissNum.setText(rSet.getString("Viss"));
						txtCardNum.setText(rSet.getString("Card"));
						txtCardNum.setText(rSet.getString("Price"));
						txtPrice.setText(rSet.getString("Price"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tbPurchase.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		tbPurchase.setRowHeight(30);
		tbPurchase.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		tbPurchase.getTableHeader().setBackground(new Color(128, 64, 0));
		tbPurchase.getTableHeader().setForeground(Color.white);
		scrollPane.setViewportView(tbPurchase);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1107, 476);
		lblNewLabel.setBackground(new Color(0,28,0));
		panel.add(lblNewLabel);
		
		refreshTable();
		
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
}