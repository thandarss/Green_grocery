package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import Greengrocery_sys.Database.DbConnection;
import javafx.scene.input.KeyCode;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class saleProduct_D extends JFrame {

	private JPanel contentPane;
	private JTable tbProduct;
	private JScrollPane scrollPane;
	private JTextField txtBucketPrice;
	private JTextField txtVissPrice;
	private JTextField txtBoxPrice;
	private JComboBox cBoxSize; 
	private JTextField txtCardPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					saleProduct_D frame = new saleProduct_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/*
	 * FrameMouseListener
	 */
	private class FrameMouseListener implements MouseListener,MouseMotionListener{

		private Point pressPoint;
		private Rectangle frameBounds;
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
			this.frameBounds = new saleProduct_D().getBounds();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			mouseFrame(e);
		}
		
		public void mouseFrame(MouseEvent e) {
			Point endPoint = e.getPoint();
			int xDiff = endPoint.x - pressPoint.x;
			int yDiff = endPoint.y - pressPoint.y;
			frameBounds.x += xDiff;
			frameBounds.y += yDiff;
			setBounds(frameBounds);
		}
	}
	
	/*
	 * refresh product table
	 */	
	public void refreshTable() {
		Connection connection = new DbConnection().connect();
		String sqlString = "select * from sale_price;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbProduct.setModel(DbUtils.resultSetToTableModel(rSet));
			
			TableColumnModel columnModel = tbProduct.getColumnModel();
			tbProduct.removeColumn(columnModel.getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the frame.
	 */
	public saleProduct_D() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1187, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Frame Mouse Listener
		 */
		FrameMouseListener listener = new FrameMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saleProduct_D.this.setVisible(false);
			}
		});
		
		JButton btnClose_1 = new JButton("-");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saleProduct_D.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		
		txtCardPrice = new JTextField();
		txtCardPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCardPrice.setText("");
			}
		});
		txtCardPrice.setText("0");
		txtCardPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtVissPrice.requestFocus();
				}
			}
		});
		
		JButton btnDelete = new JButton("ဖ်က္မည္");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tbProduct.getSelectedRow();
				
				if(row != -1) {
					String idString = tbProduct.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					
					String labelString = "ဖ်က္မွာေသခ်ာပါသလား";
					JLabel textLabel = new JLabel(labelString);
					textLabel.setFont(new Font("Zawgyi-One", Font.PLAIN, 25));
					int confirm = JOptionPane.showConfirmDialog(null, textLabel,"Confirm Dialog",JOptionPane.YES_NO_OPTION);
					if(confirm == 0) {
						new deleteProduct_F().deleteProduct(id);
					}
					
					refreshTable();
				}
			}
		});
		btnDelete.setForeground(new Color(128, 64, 0));
		btnDelete.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnDelete.setBackground(new Color(254, 251, 245));
		btnDelete.setBounds(1050, 356, 118, 34);
		contentPane.add(btnDelete);
		txtCardPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtCardPrice.setColumns(10);
		txtCardPrice.setBackground(new Color(254, 251, 245));
		txtCardPrice.setBounds(1050, 167, 106, 27);
		contentPane.add(txtCardPrice);
		
		JLabel lblTitle_1_1_1_1_1 = new JLabel("ကဒ္ေစ်း");
		lblTitle_1_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1_1_1_1.setBounds(1066, 121, 90, 44);
		contentPane.add(lblTitle_1_1_1_1_1);
		btnClose_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnClose_1.setBackground(new Color(214, 54, 31));
		btnClose_1.setBounds(1095, 0, 44, 34);
		contentPane.add(btnClose_1);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBackground(new Color(214, 54, 31));
		btnClose.setBounds(1136, 0, 44, 34);
		contentPane.add(btnClose);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 66, 830, 392);
		contentPane.add(scrollPane);
		
		tbProduct = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;               
        };
		};
		tbProduct.setBackground(new Color(254, 251, 245));
		tbProduct.setRowHeight(30);
		scrollPane.setViewportView(tbProduct);
		tbProduct.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		tbProduct.getTableHeader().setBackground(new Color(128, 64, 0));
		tbProduct.getTableHeader().setForeground(Color.white);
		tbProduct.setFont(new Font("Zawgyi-one",Font.PLAIN,13));
		tbProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbProduct.getSelectedRow();
				String idString = tbProduct.getModel().getValueAt(row, 0).toString();
				int id = Integer.parseInt(idString);
				//System.out.println("mouse selected id : " +id);
				
					Connection connection = new DbConnection().connect();
					String sqlString = "select * from sale_price where Id_product = "+ id +";";
					try {
						PreparedStatement pStatement = connection.prepareStatement(sqlString);
						ResultSet rSet = pStatement.executeQuery();
						
						if(rSet.next()) {
						cBoxSize.setSelectedItem(rSet.getString("Type"));
						txtBucketPrice.setText(rSet.getString("Bucket_Price"));
						txtVissPrice.setText(rSet.getString("Viss_Price"));
						txtBoxPrice.setText(rSet.getString("Box_Price"));
						txtCardPrice.setText(rSet.getString("Card_Price"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		JLabel lblTitle = new JLabel("အေရာင္းေစ်း စာရင္း");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblTitle.setBounds(659, 11, 191, 44);
		contentPane.add(lblTitle);
		
		JLabel lblTitle_1_1 = new JLabel("ေတာင္း/ျခင္း‌ေဈး");
		lblTitle_1_1.setForeground(SystemColor.info);
		lblTitle_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1.setBounds(884, 121, 111, 44);
		contentPane.add(lblTitle_1_1);
		
		JLabel lblTitle_1_1_1 = new JLabel("ပိႆာေစ်း");
		lblTitle_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1_1.setBounds(1061, 224, 90, 44);
		contentPane.add(lblTitle_1_1_1);
		
		JLabel lblTitle_1_1_1_1 = new JLabel("ေသတၱာေစ်း     ");
		lblTitle_1_1_1_1.setForeground(SystemColor.info);
		lblTitle_1_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblTitle_1_1_1_1.setBounds(894, 224, 129, 44);
		contentPane.add(lblTitle_1_1_1_1);
		
		txtBucketPrice = new JTextField();
		txtBucketPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBucketPrice.setText("");
			}
		});
		txtBucketPrice.setText("0");
		txtBucketPrice.setBackground(new Color(254, 251, 245));
		txtBucketPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBoxPrice.requestFocus();
				}
			}
		});
		txtBucketPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBucketPrice.setColumns(10);
		txtBucketPrice.setBounds(884, 167, 106, 27);
		contentPane.add(txtBucketPrice);
		
		txtVissPrice = new JTextField();
		txtVissPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtVissPrice.setText("");
			}
		});
		txtVissPrice.setText("0");
		txtVissPrice.setBackground(new Color(254, 251, 245));
		txtVissPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isLetter(c)){
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtBucketPrice.requestFocus();
				}
			}
		});
		txtVissPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtVissPrice.setColumns(10);
		txtVissPrice.setBounds(1050, 279, 106, 27);
		contentPane.add(txtVissPrice);
		
		txtBoxPrice = new JTextField();
		txtBoxPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBoxPrice.setText("");
			}
		});
		txtBoxPrice.setText("0");
		txtBoxPrice.setBackground(new Color(254, 251, 245));
		txtBoxPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Please enter the NUMBER (1,2,3,...) only.", "Warning!",JOptionPane.INFORMATION_MESSAGE);
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtCardPrice.requestFocus();
				}
			}
		});
		txtBoxPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBoxPrice.setColumns(10);
		txtBoxPrice.setBounds(884, 279, 106, 27);
		contentPane.add(txtBoxPrice);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.setForeground(new Color(128, 64, 0));
		btnAdd.setBackground(new Color(254, 251, 245));
		btnAdd.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				String typeString= cBoxSize.getSelectedItem().toString();
				int bucketPrice = Integer.parseInt(txtBucketPrice.getText());
				int vissPrice = Integer.parseInt(txtVissPrice.getText());
				int boxPrice = Integer.parseInt(txtBoxPrice.getText());
				int cardPrice = Integer.parseInt(txtCardPrice.getText());
				/*
				 * Get Today Date from DateChooser
				 
				String dateString = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				System.out.print("Date chooser " + dateString);
				*/
				new addProduct_F().addProduct(typeString, bucketPrice, vissPrice, boxPrice, cardPrice);
				refreshTable();
			}
		});
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBounds(884, 356, 118, 34);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.setForeground(new Color(128, 64, 0));
		btnUpdate.setBackground(new Color(254, 251, 245));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typeString = cBoxSize.getSelectedItem().toString();
				int bucketPrice = Integer.parseInt(txtBucketPrice.getText());
				int vissPrice = Integer.parseInt(txtVissPrice.getText());
				int boxPrice = Integer.parseInt(txtBoxPrice.getText());
				int cardPrice = Integer.parseInt(txtCardPrice.getText());
				
				int r = tbProduct.getSelectedRow();
				if(r != -1) {
					String idString = tbProduct.getModel().getValueAt(r, 0).toString();
					int id = Integer.parseInt(idString);
					
					new updateProduct_F().updateProduct(id, typeString, bucketPrice, vissPrice, boxPrice,cardPrice);
					refreshTable();
				}
			}
		});
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBounds(884, 424, 118, 34);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(128, 64, 0));
		btnClear.setBackground(new Color(254, 251, 245));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtBucketPrice.setText("0");
				txtVissPrice.setText("0");
				txtBoxPrice.setText("0");
				txtCardPrice.setText("0");
				cBoxSize.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBounds(1050, 424, 118, 34);
		contentPane.add(btnClear);
		
		JLabel lblTitle_1_2 = new JLabel("အမ်ိဳးအစား");
		lblTitle_1_2.setForeground(SystemColor.info);
		lblTitle_1_2.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		lblTitle_1_2.setBounds(896, 66, 129, 44);
		contentPane.add(lblTitle_1_2);
		
		/*
		 * add item on JcomboBox
		 */
		String item[]= {"ထူးရွယ္", "ေအာက္ခံသီး", "အလတ္သီး", "အလုပ္သမားခ"};
		cBoxSize = new JComboBox(item);
		cBoxSize.setBackground(new Color(253, 248, 236));
		cBoxSize.setFont(new Font("Zawgyi-One", Font.BOLD, 16));
		cBoxSize.setBounds(1023, 75, 145, 27);
		
		
		contentPane.add(cBoxSize);
				
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 28, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblNewLabel.setBounds(0, 0, 1187, 495);
		contentPane.add(lblNewLabel);
		
		refreshTable();
	}
}
