package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class invoice extends JFrame {

	private JPanel contentPane;
	private JTextField txtAddress;
	private JTable tbInvoice;
	private JTextField txtTotal;
	private JTextField txtPrepay;
	private JTextField txtDebut;
	private JPanel mainPanel;
	private JComboBox cboxName;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal totalBigDecimal;
	private ArrayList list;
	Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					invoice frame = new invoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Calculate debut
	 */
	public void setDebut() {
		
			String prepayString = txtPrepay.getText();
			int prepay = Integer.parseInt(prepayString);
			
			String totalString = txtTotal.getText();
			double total = Double.parseDouble(totalString);
			
			double debut = total - prepay;
			txtDebut.setText(String.valueOf(debut));
				
	}
	
	/*
	 * Calculate total
	 */ 
	public void setTotal() {
		//calculate total value
		
		int row = tbInvoice.getModel().getRowCount();
		TableColumnModel columnModel = tbInvoice.getColumnModel();
		int column = columnModel.getColumnIndex("Total");
		
		list = new ArrayList();
						
		for(int i=0; i<row; i++) {
			
			list.add(tbInvoice.getModel().getValueAt(i, column));
		}
		
		for(int j=0; j<list.size(); j++ ) {
			totalBigDecimal = (BigDecimal) list.get(j);
			total = total.add(totalBigDecimal);

		}
		txtTotal.setText(total.toString());
		
		//reset big decimal array value
		totalBigDecimal = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
		
		list.clear();
	}
	
	/*
	 * Add database data into 
	 */
	public void refreshTable() {
		Connection connection = new DbConnection().connect();
		String nameString = cboxName.getSelectedItem().toString();
		String sqlString = "select Date, Type, Bucket, Box, Card, Viss, Price, Total from customer_order where Customer = '" + nameString + "';";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbInvoice.setModel(DbUtils.resultSetToTableModel(rSet));
			
			TableColumnModel columnModel = tbInvoice.getColumnModel();
			TableColumn tbcolumn, tbcolumn1, tbcolumn2, tbcolumn3, tbColumn4, tbColumn5, tbColumn6;
			tbcolumn = columnModel.getColumn(0);
			tbcolumn1 = columnModel.getColumn(1);
			tbcolumn2 = columnModel.getColumn(2);
			tbcolumn3 = columnModel.getColumn(3);
			tbColumn4 = columnModel.getColumn(4);
			tbColumn5 = columnModel.getColumn(5);
			tbColumn6 = columnModel.getColumn(6);
			
			tbcolumn.setPreferredWidth(70);
			tbcolumn1.setPreferredWidth(60);
			tbcolumn2.setPreferredWidth(30);
			tbcolumn3.setPreferredWidth(30);
			tbColumn4.setPreferredWidth(30);
			tbColumn5.setPreferredWidth(60);
			tbColumn6.setPreferredWidth(60);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Mouse Listener
	 */
	
	public class FrameMouseListener implements MouseListener,MouseMotionListener{

		private Point pressedPoint;
		private Rectangle frameBound;
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			moveFrame(e);
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
			this.frameBound = new invoice().getBounds();
			this.pressedPoint = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			moveFrame(e);
		}
		
		public void moveFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressedPoint.x;
			int yDiff = endPoint.y - pressedPoint.y;
			frameBound.x += xDiff;
			frameBound.y += yDiff;
			setBounds(frameBound);
		}
		
	}
	
	/*
	 * Fill Name on ComboBox
	 */
	
	public void fillNameBox() {
		connection = new DbConnection().connect();
		String sqlString = "select * from customer";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			cboxName.addItem("Choose customer");
			while(rSet.next()) {
				cboxName.addItem(rSet.getString("Name"));
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public invoice() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 80, 570, 636);
		contentPane = new JPanel();
		//contentPane.setPreferredSize(new Dimension(mainPanel.getWidth(),mainPanel.getHeight()));
		
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 51, 0));
		mainPanel.setBounds(0, 0, 570, 636);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				invoice.this.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(199, 5, 58));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(521, 0, 49, 23);
		mainPanel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("<html><strong>09 52111 54<br>\r\n09 78 52111 54<br>09 560 1148</strong></html>",JLabel.LEFT);
		lblNewLabel_2.setFont(new Font("Sesame", Font.PLAIN, 9));
		lblNewLabel_2.setBackground(new Color(0, 255, 0));
		lblNewLabel_2.setForeground(SystemColor.info);
		lblNewLabel_2.setBounds(403, 30, 141, 56);
		mainPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("ရာဇာၿဖိဳး");
		lblNewLabel.setForeground(new Color(255, 255, 119));
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 0, 252, 66);
		mainPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ကုန္စိမ္းေရာင္းဝယ္ေရး");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(107, 58, 341, 28);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblAddress = new JLabel();
		lblAddress.setText("ေဖာင္ေတာ္ျပန္လမ္း၊နန္းပန္ရပ္(၂)၊ကမ္းနားလမ္း၊ေညာင္ေ႐ႊၿမိဳ႕။");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(UIManager.getColor("info"));
		lblAddress.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress.setBounds(50, 123, 456, 41);
		mainPanel.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel();
		lblAddress_1.setText("ျမနႏၵာရေထ​ြက္ဦးလမ္း၊ေညာင္ပင္ေထာင္ရပ္က​ြက္၊ျမနႏၵာ(၃)လမ္း၊ေအာင္ပန္းၿမိဳ႕၊");
		lblAddress_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_1.setForeground(UIManager.getColor("info"));
		lblAddress_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress_1.setBounds(40, 91, 504, 41);
		mainPanel.add(lblAddress_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(199, 236, 183));
		panel_1.setBounds(10, 163, 550, 443);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("အမည္ :");
		lblNewLabel_3.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(52, 11, 46, 33);
		panel_1.add(lblNewLabel_3);
		
		cboxName = new JComboBox();
		cboxName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboxName.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {

				String selectedName = cboxName.getSelectedItem().toString();
				String sqlString2 = "select * from customer where Name ='" + selectedName + "';";
				PreparedStatement pStatement2;
				try {
					pStatement2 = connection.prepareStatement(sqlString2);
					
					ResultSet rSet2 = pStatement2.executeQuery();
					
					if(rSet2.next()) {
						txtAddress.setText(rSet2.getString("Address"));
					}
					else {
						txtAddress.setText("");
					}
					
					refreshTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				setTotal();
				setDebut();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cboxName.setBounds(108, 15, 140, 22);
		panel_1.add(cboxName);
		
		JLabel lblNewLabel_3_1 = new JLabel("ေန႔စ​ြဲ :");
		lblNewLabel_3_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(295, 11, 46, 33);
		panel_1.add(lblNewLabel_3_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(336, 11, 149, 29);
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setFont(new Font("Zawgyi-one",Font.BOLD,15));
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_3_2 = new JLabel("လိပ္စာ :");
		lblNewLabel_3_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_2.setBounds(52, 48, 46, 33);
		panel_1.add(lblNewLabel_3_2);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(108, 53, 377, 20);
		panel_1.add(txtAddress);
		txtAddress.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 530, 236);
		panel_1.add(scrollPane);
		
		tbInvoice = new JTable();
		tbInvoice.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Type", "Bucket", "Box", "Card", "Viss", "Price", "Total"
			}
		));
		tbInvoice.getTableHeader().setFont(new Font("Zawgyi-One",Font.PLAIN,10));
		tbInvoice.setFont(new Font("Zawgyi-One", Font.PLAIN, 13));
		tbInvoice.setRowHeight(30);
		scrollPane.setViewportView(tbInvoice);
		
		JLabel lblNewLabel_3_3 = new JLabel("စုစုေပါင္း :");
		lblNewLabel_3_3.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3.setBounds(342, 337, 56, 33);
		panel_1.add(lblNewLabel_3_3);
		
		txtTotal = new JTextField();
		txtTotal.setText("0");
		txtTotal.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtTotal.setBounds(408, 342, 132, 20);
		txtTotal.setEditable(false);
		panel_1.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("စရံေင​ြ :");
		lblNewLabel_3_3_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_1.setBounds(352, 364, 46, 33);
		panel_1.add(lblNewLabel_3_3_1);
		
		txtPrepay = new JTextField();
		txtPrepay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPrepay.setText("");
			}
		});
		txtPrepay.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					setDebut();
				}
			}
		});
		txtPrepay.setText("0");
		txtPrepay.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtPrepay.setColumns(10);
		txtPrepay.setBounds(408, 373, 132, 20);
		panel_1.add(txtPrepay);
		
		txtDebut = new JTextField();
		txtDebut.setText("0");
		txtDebut.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtDebut.setColumns(10);
		txtDebut.setBounds(408, 404, 132, 20);
		txtDebut.setEditable(false);
		panel_1.add(txtDebut);
		
		JLabel lblNewLabel_3_3_1_1 = new JLabel("က်န္ေင​ြ :");
		lblNewLabel_3_3_1_1.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_1_1.setBounds(342, 399, 46, 33);
		panel_1.add(lblNewLabel_3_3_1_1);
		
		JLabel lblAddress_2 = new JLabel();
		lblAddress_2.setText("ဝယ္ယူအားေပးမႈကိုေက်းဇူးတင္ပါသည္");
		lblAddress_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_2.setForeground(SystemColor.activeCaptionText);
		lblAddress_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblAddress_2.setBounds(10, 333, 193, 41);
		panel_1.add(lblAddress_2);
		
		JLabel lblNewLabel_3_3_2 = new JLabel("လက္မွတ္ :");
		lblNewLabel_3_3_2.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		lblNewLabel_3_3_2.setBounds(10, 399, 56, 33);
		panel_1.add(lblNewLabel_3_3_2);
		
		JLabel lblImage = new JLabel("");
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Tomato Green Grocery\\Images\\tomato.jpg"));
		lblImage.setBounds(0, 0, 570, 625);
		mainPanel.add(lblImage);
		//refreshTable();		
		fillNameBox();
	}
}
