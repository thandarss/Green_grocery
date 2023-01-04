package Greengrocery_sys;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class report_D extends JFrame {

	private JPanel contentPane;
	private JTable tbOrderDetail;
	private JComboBox cboxName, cboxType;
	private JDateChooser endDate, startDate;
	private JTextField txtBucketTotal;
	private JTextField txtBoxTotal;
	private JTextField txtCardTotal;
	private JTextField txtVissTotal;
	private JTextField txtPriceTotal;
	private JTextField txtAllTotal;
	private ArrayList<Integer> listIntegers;
	private ArrayList<BigDecimal> listDecimals;
	private JTable tbPurchaseDetail;
	private JTextField txtPBucketTol;
	private JTextField txtPBoxTotal;
	private JTextField txtPCardTol;
	private JTextField txtPVissTol;
	private JTextField txtPpriceTol;
	private JTextField txtPAllTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report_D frame = new report_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * add frame MouseListener
	 */
	
	public class frameMouseListener implements MouseListener, MouseMotionListener {

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
			this.framebounds = report_D.this.getBounds();
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
			setBounds(framebounds);}
		
	}
	/*
	 * refresh the order table
	 */
	public void refreshOrder() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from customer_order;";
		
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
			TableColumnModel columnModel = tbOrderDetail.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(11));
			columnModel.removeColumn(columnModel.getColumn(10));
			columnModel.removeColumn(columnModel.getColumn(0));
							
			DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
			renderer.setBackground(new Color(210,251,250));
			TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
			priceColumn.setCellRenderer(renderer);
			
			DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
			renderer2.setBackground(new Color(196,186,252));
			tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
			
			DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
			renderer3.setBackground(new Color(251,237,159));
			tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
			
			columnModel.setColumnMargin(11);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Fill the Name box
	 */
	public void fillNameBox() {
		Connection connection = new DbConnection().connect();
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
	 * Fill the Type Box
	 */
	public void fillTypeBox() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from sale_price;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			
			cboxType.addItem("All");
			
			while(rSet.next()) {
				cboxType.addItem(rSet.getString("Type"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Calculate total box (int)
	 */
	
	private int setTotalInteger(int col) {
		//calculate total value
		
		int row = tbOrderDetail.getModel().getRowCount();
		int column = col;
		int sum =0;
		
		listIntegers = new ArrayList();
						
		for(int i=0; i<row; i++) {
			
			listIntegers.add((Integer) tbOrderDetail.getModel().getValueAt(i, column));
		}
		
		for(int j=0; j<listIntegers.size(); j++ ) {
			sum = sum + listIntegers.get(j);
		}
		
		listIntegers.clear();
		
		return sum;
	}
	
	/*
	 * Calculate total box (BigDecimal)
	 */
	private BigDecimal setTotalBigDecimal(int col) {
		int row = tbOrderDetail.getModel().getRowCount();
		int column = col;
		BigDecimal sum = BigDecimal.ZERO;
		
		listDecimals = new ArrayList<>();
		
		for(int i=0; i<row; i++) {
			listDecimals.add((BigDecimal) tbOrderDetail.getModel().getValueAt(i, column));
		}
		
		for(int j=0; j<listDecimals.size(); j++) {
			sum = sum.add(listDecimals.get(j));
		}
		
		return sum;
	}
	

	/**
	 * Create the frame.
	 */
	public report_D() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		frameMouseListener  listener = new frameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1157, 624);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnMinimize = new JButton("-");
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnMinimize.setBackground(Color.RED);
		btnMinimize.setBounds(1072, 0, 43, 31);
		panel.add(btnMinimize);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnClose.setBackground(Color.RED);
		btnClose.setBounds(1114, 0, 43, 31);
		panel.add(btnClose);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		tabbedPane.setBounds(10, 86, 1132, 527);
		panel.add(tabbedPane);
		
		JPanel showOrder = new JPanel();
		showOrder.setBackground(new Color(255, 222, 173));
		tabbedPane.addTab("ေအာ္ဒါစာရင္း", null, showOrder, null);
		showOrder.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 1107, 375);
		showOrder.add(scrollPane);
		
		tbOrderDetail = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbOrderDetail.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		tbOrderDetail.setFont(new Font("Zawgyi-One", Font.PLAIN, 12));
		tbOrderDetail.setRowHeight(30);
		scrollPane.setViewportView(tbOrderDetail);
		
		JLabel lblNewLabel_1_2 = new JLabel("Customer : ");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(10, 11, 123, 32);
		showOrder.add(lblNewLabel_1_2);
		
		cboxName = new JComboBox();
		cboxName.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String nameString = cboxName.getSelectedItem().toString();
				String sql = "select * from customer_order where Customer = '" + nameString + "';";
				
				Connection connection= new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sql);
					ResultSet rSet = pStatement.executeQuery();
					
					if(nameString.equals("All")) {
						refreshOrder();
					}
					
					else {
					
						tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
						TableColumnModel columnModel = tbOrderDetail.getColumnModel();
						columnModel.removeColumn(columnModel.getColumn(11));
						columnModel.removeColumn(columnModel.getColumn(10));
						columnModel.removeColumn(columnModel.getColumn(0));
										
						DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
						renderer.setBackground(new Color(210,251,250));
						TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
						priceColumn.setCellRenderer(renderer);
						
						DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
						renderer2.setBackground(new Color(196,186,252));
						tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
						
						DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
						renderer3.setBackground(new Color(251,237,159));
						tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
						
						columnModel.setColumnMargin(11);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cboxName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		cboxName.setBounds(123, 11, 157, 36);
		showOrder.add(cboxName);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Type : ");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_1.setBounds(357, 11, 76, 32);
		showOrder.add(lblNewLabel_1_2_1);
		
		cboxType = new JComboBox();
		cboxType.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String typeString = cboxType.getSelectedItem().toString();
				String sqlString = "select * from customer_order where Type = '" + typeString + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if(typeString.equals("All")){
						refreshOrder();
					}
					else {
						tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
						TableColumnModel columnModel = tbOrderDetail.getColumnModel();
						columnModel.removeColumn(columnModel.getColumn(11));
						columnModel.removeColumn(columnModel.getColumn(10));
						columnModel.removeColumn(columnModel.getColumn(0));
										
						DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
						renderer.setBackground(new Color(210,251,250));
						TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
						priceColumn.setCellRenderer(renderer);
						
						DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
						renderer2.setBackground(new Color(196,186,252));
						tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
						
						DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
						renderer3.setBackground(new Color(251,237,159));
						tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
						
						columnModel.setColumnMargin(11);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cboxType.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		cboxType.setBounds(429, 8, 157, 36);
		showOrder.add(cboxType);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Total : ");
		lblNewLabel_1_2_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_2.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_2.setBounds(123, 444, 123, 32);
		showOrder.add(lblNewLabel_1_2_2);
		
		txtBucketTotal = new JTextField();
		txtBucketTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtBucketTotal.setBounds(256, 444, 105, 30);
		txtBucketTotal.setEditable(false);
		showOrder.add(txtBucketTotal);
		txtBucketTotal.setColumns(10);
		
		txtBoxTotal = new JTextField();
		txtBoxTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtBoxTotal.setColumns(10);
		txtBoxTotal.setBounds(382, 444, 105, 30);
		txtBoxTotal.setEditable(false);
		showOrder.add(txtBoxTotal);
		
		txtCardTotal = new JTextField();
		txtCardTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtCardTotal.setColumns(10);
		txtCardTotal.setBounds(508, 444, 105, 30);
		txtCardTotal.setEditable(false);
		showOrder.add(txtCardTotal);
		
		txtVissTotal = new JTextField();
		txtVissTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtVissTotal.setColumns(10);
		txtVissTotal.setBounds(636, 444, 105, 30);
		txtVissTotal.setEditable(false);
		showOrder.add(txtVissTotal);
		
		txtPriceTotal = new JTextField();
		txtPriceTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPriceTotal.setColumns(10);
		txtPriceTotal.setBounds(761, 444, 105, 30);
		txtPriceTotal.setEditable(false);
		showOrder.add(txtPriceTotal);
		
		txtAllTotal = new JTextField();
		txtAllTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtAllTotal.setColumns(10);
		txtAllTotal.setBounds(886, 444, 168, 30);
		txtAllTotal.setEditable(false);
		showOrder.add(txtAllTotal);
		
		JButton btnSearch = new JButton("စစ္ေဆးမည္");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String startString = ((JTextField)startDate.getDateEditor().getUiComponent()).getText();
				//String endString = ((JTextField)endDate.getDateEditor().getUiComponent()).getText();

				Date startString = startDate.getDate();
				java.sql.Date sDate = new java.sql.Date(startString.getTime());
				
				Date endString = endDate.getDate();
				java.util.Date eDate = new java.sql.Date(endString.getTime());
				
				System.out.println(startString + " end " + endString);
				System.out.println(" sql " + sDate + " sqlend " + eDate);
				String sqlString = "select * from customer_order where Date Between '" + sDate + "' and '" + eDate + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					tbOrderDetail.setModel(DbUtils.resultSetToTableModel(rSet));
					TableColumnModel columnModel = tbOrderDetail.getColumnModel();
					columnModel.removeColumn(columnModel.getColumn(11));
					columnModel.removeColumn(columnModel.getColumn(10));
					columnModel.removeColumn(columnModel.getColumn(0));
									
					DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
					renderer.setBackground(new Color(210,251,250));
					TableColumn priceColumn = tbOrderDetail.getColumnModel().getColumn(6);
					priceColumn.setCellRenderer(renderer);
					
					DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
					renderer2.setBackground(new Color(196,186,252));
					tbOrderDetail.getColumnModel().getColumn(7).setCellRenderer(renderer2);
					
					DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer();
					renderer3.setBackground(new Color(251,237,159));
					tbOrderDetail.getColumnModel().getColumn(5).setCellRenderer(renderer3);
					
					columnModel.setColumnMargin(11);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBackground(new Color(255, 248, 220));
		btnSearch.setFont(new Font("Zawgyi-One", Font.PLAIN, 19));
		btnSearch.setBounds(858, 43, 149, 32);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1_1 = new JLabel("End Date :");
		lblNewLabel_1_1.setForeground(SystemColor.info);
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(487, 43, 116, 32);
		panel.add(lblNewLabel_1_1);
		
		endDate = new JDateChooser();
		Date date = new Date();
		endDate.setDate(date);
		endDate.setFont(new Font("Verdana",Font.BOLD,15));
		endDate.setBounds(613, 43, 188, 32);
		panel.add(endDate);
		
		JLabel lblNewLabel_1 = new JLabel("Start Date : ");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1.setBounds(143, 43, 121, 32);
		panel.add(lblNewLabel_1);
		
		startDate = new JDateChooser();
		Date date1 = new Date();
		startDate.setDate(date1);
		startDate.setBounds(264, 43, 188, 32);
		startDate.setFont(new Font("Verdana", Font.BOLD, 15) );
		panel.add(startDate);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0,24,0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1157, 655);
		panel.add(lblNewLabel);
		
		refreshOrder();
		fillNameBox();
		fillTypeBox();
		
		txtBucketTotal.setText(String.valueOf(setTotalInteger(3)));
		txtBoxTotal.setText(String.valueOf(setTotalInteger(4)));
		txtCardTotal.setText(String.valueOf(setTotalInteger(5)));
		txtPriceTotal.setText(String.valueOf(setTotalInteger(7)));
		
		txtVissTotal.setText(String.valueOf(setTotalBigDecimal(6)));
		txtAllTotal.setText(String.valueOf(setTotalBigDecimal(8)));
		
		JPanel showPurchase = new JPanel();
		tabbedPane.addTab("အဝယ္စာရင္း", null, showPurchase, null);
		showPurchase.setLayout(null);
		showPurchase.setBackground(new Color(255, 222, 173));
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Customer : ");
		lblNewLabel_1_2_3.setForeground(Color.BLACK);
		lblNewLabel_1_2_3.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_3.setBounds(10, 11, 123, 32);
		showPurchase.add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Type : ");
		lblNewLabel_1_2_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_1_1.setBounds(357, 11, 76, 32);
		showPurchase.add(lblNewLabel_1_2_1_1);
		
		JComboBox cboxType_P = new JComboBox();
		cboxType_P.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		cboxType_P.setBounds(426, 11, 157, 36);
		showPurchase.add(cboxType_P);
		
		JComboBox cboxName_P = new JComboBox();
		cboxName_P.setFont(new Font("Times New Roman", Font.BOLD, 17));
		cboxName_P.setBounds(120, 14, 157, 36);
		showPurchase.add(cboxName_P);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 58, 1089, 380);
		showPurchase.add(scrollPane_1);
		
		tbPurchaseDetail = new JTable();
		scrollPane_1.setViewportView(tbPurchaseDetail);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Total : ");
		lblNewLabel_1_2_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_2_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_2_2_1.setBounds(105, 442, 123, 32);
		showPurchase.add(lblNewLabel_1_2_2_1);
		
		txtPBucketTol = new JTextField();
		txtPBucketTol.setText("0");
		txtPBucketTol.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPBucketTol.setColumns(10);
		txtPBucketTol.setBounds(238, 442, 105, 30);
		showPurchase.add(txtPBucketTol);
		
		txtPBoxTotal = new JTextField();
		txtPBoxTotal.setText("0");
		txtPBoxTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPBoxTotal.setColumns(10);
		txtPBoxTotal.setBounds(364, 442, 105, 30);
		showPurchase.add(txtPBoxTotal);
		
		txtPCardTol = new JTextField();
		txtPCardTol.setText("0");
		txtPCardTol.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPCardTol.setColumns(10);
		txtPCardTol.setBounds(490, 442, 105, 30);
		showPurchase.add(txtPCardTol);
		
		txtPVissTol = new JTextField();
		txtPVissTol.setText("null");
		txtPVissTol.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPVissTol.setColumns(10);
		txtPVissTol.setBounds(618, 442, 105, 30);
		showPurchase.add(txtPVissTol);
		
		txtPpriceTol = new JTextField();
		txtPpriceTol.setText("0");
		txtPpriceTol.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPpriceTol.setColumns(10);
		txtPpriceTol.setBounds(743, 442, 105, 30);
		showPurchase.add(txtPpriceTol);
		
		txtPAllTotal = new JTextField();
		txtPAllTotal.setText("null");
		txtPAllTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtPAllTotal.setColumns(10);
		txtPAllTotal.setBounds(868, 442, 168, 30);
		showPurchase.add(txtPAllTotal);
	}
}