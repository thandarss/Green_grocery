package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Greengrocery_sys.Database.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

public class Customer {

	protected JFrame frame;
	protected JTable tbCustomer;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;

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
		updateTable();
	}

	/**
	 * Retrieving database data to JTable
	 */
	
	public void updateTable () {
		Connection conn = new DbConnection().connect();
		String sqlString = "select Id_customer, Name, Address, Phone from customer;";
	
		try {
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery();
			tbCustomer.setModel(DbUtils.resultSetToTableModel(rSet));
			
			//JOptionPane.showMessageDialog(null, "Updated successfully");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * MouseListener
	 */
	public class FrameMouseListener implements MouseListener, MouseMotionListener {
        
        private Point pressedPoint;
        private Rectangle frameBounds;
        
        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            this.frameBounds = frame.getBounds();
            this.pressedPoint = event.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            moveJFrame(event);
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            moveJFrame(event);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
        
        private void moveJFrame(MouseEvent event) {
            Point endPoint = event.getPoint();
            
            int xDiff = endPoint.x - pressedPoint.x;
            int yDiff = endPoint.y - pressedPoint.y;
            frameBounds.x += xDiff;
            frameBounds.y += yDiff;
            frame.setBounds(frameBounds);
        }
        
    }
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 972, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.addMouseListener(null);
		//close the Jframe_autoClose button
		//frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		/*
		 * Add MouseListener of Jframe
		 */
		
		FrameMouseListener listener = new FrameMouseListener();
		frame.addMouseListener(listener);
		frame.addMouseMotionListener(listener);
		
		JButton btnAdd = new JButton("ထည့္မည္");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				
				//String id_customer = name.substring(name.length()-1) + address.substring(address.length()-1) + phone.substring(phone.length()-2);
				
				new addCustomer_F().addCustomer(name, address, phone);
				updateTable();
				//new addCustomer_D().frame.setVisible(true);
			}
		});
		
		JButton btnRemoveRow = new JButton("ဖ်က္မည္");
		
		btnRemoveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Backup the deleted row
				 */
				
				int row = tbCustomer.getSelectedRow();
																						
				if(tbCustomer.getSelectedRow() != -1) {
					
					String idString = tbCustomer.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
					
					if(confirm == 0) {
						new addCustomerBackup().backupCus(id);
						new deleteCustomer().deleteCus(id);
						
						DefaultTableModel model= (DefaultTableModel) tbCustomer.getModel();
						model.removeRow(tbCustomer.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Remove row successfully!");
					}
													
					updateTable();
				}	
				
			}
		});
		btnRemoveRow.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnRemoveRow.setBackground(SystemColor.info);
		btnRemoveRow.setBounds(827, 313, 119, 40);
		frame.getContentPane().add(btnRemoveRow);
		btnAdd.setBackground(SystemColor.info);
		btnAdd.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnAdd.setBounds(667, 313, 125, 40);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 82, 611, 334);
		frame.getContentPane().add(scrollPane);
		
		/*
		 * Unable the table cell editing
		 */
		tbCustomer = new JTable(){
	        //private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    tbCustomer.getTableHeader().setFont(new Font("Verdana",Font.BOLD,18));
	    tbCustomer.setOpaque(false);
	    tbCustomer.getTableHeader().setBackground(new Color(201, 248, 194));
		//tbCustomer.setBackground(new Color(201, 248, 194));
		tbCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tbCustomer.getSelectedRow();
				String selectString = tbCustomer.getModel().getValueAt(row, 0).toString();
				System.out.println("mouse selected id : " + selectString);
								
				String sqlString = "select * from customer where id_customer = '" + selectString + "';";
				
				Connection connection = new DbConnection().connect();
				try {
					PreparedStatement pStatement = connection.prepareStatement(sqlString);
					ResultSet rSet = pStatement.executeQuery();
					
					if (rSet.next()) {
						txtName.setText(rSet.getString("Name"));
						txtAddress.setText(rSet.getString("Address"));
						txtPhone.setText(rSet.getString("Phone"));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(tbCustomer);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setBackground(Color.RED);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClose.setBounds(903, 0, 64, 29);
		frame.getContentPane().add(btnClose);
		
		JLabel lblNewLabel = new JLabel("နာမည္    :");
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel.setBounds(667, 60, 98, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("လိပ္စာ    :");
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_1.setBounds(667, 151, 98, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ဖုန္းနံပါတ္ :");
		lblNewLabel_1_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(667, 251, 111, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		/*
		 * focus cursor into next jtextfile by enter key
		 */
		txtName = new JTextField();
		txtName.requestFocus();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtAddress.requestFocus();
				}
			}
		});
		txtName.setToolTipText("Enter your name");
		txtName.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtName.setBounds(775, 59, 172, 39);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPhone.requestFocus();
				}
			}
		});
		txtAddress.setToolTipText("Enter the address");
		txtAddress.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtAddress.setColumns(10);
		txtAddress.setBounds(775, 151, 172, 39);
		frame.getContentPane().add(txtAddress);
		
		txtPhone = new JTextField();
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAdd.requestFocus();
				}
			}
		});
		txtPhone.setToolTipText("Enter the phone number");
		txtPhone.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		txtPhone.setColumns(10);
		txtPhone.setBounds(775, 245, 172, 39);
		frame.getContentPane().add(txtPhone);
		
		JLabel lblNewLabel_2 = new JLabel("ကုန္သည္စာရင္း");
		lblNewLabel_2.setForeground(new Color(245, 245, 245));
		lblNewLabel_2.setFont(new Font("Zawgyi-One", Font.BOLD, 20));
		lblNewLabel_2.setBounds(490, 26, 145, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnUpdate = new JButton("ျပင္မည္");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nameString = txtName.getText();
				String addressString = txtAddress.getText();
				String phoneString = txtPhone.getText();
				
				int row = tbCustomer.getSelectedRow();
				if(row != -1) {
					String idString = tbCustomer.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(idString);
					new updateCustomer().updateCus(id, nameString, addressString, phoneString);
					updateTable();
				}
			}
		});
		btnUpdate.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnUpdate.setBackground(SystemColor.info);
		btnUpdate.setBounds(667, 376, 119, 40);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
			}
		});
		btnClear.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnClear.setBackground(SystemColor.info);
		btnClear.setBounds(827, 376, 119, 40);
		frame.getContentPane().add(btnClear);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBackground(new Color(0, 28, 0));
		lblBackground.setOpaque(true);
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon("C:\\Users\\User\\git\\Green_grocery\\Images\\tomato.jpg"));
		lblBackground.setBounds(0, 0, 967, 447);
		frame.getContentPane().add(lblBackground);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 967, 447);
		frame.getContentPane().add(panel);
	}
}
