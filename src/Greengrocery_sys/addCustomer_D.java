package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class addCustomer_D {

	protected JFrame frame;
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
					addCustomer_D window = new addCustomer_D();
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
	public addCustomer_D() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer_D.this.frame.setVisible(false);
			}
		});
		
		JButton btnNewButton_2 = new JButton("ေရွ႕သို႔");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Customer().frame.setVisible(true);
				addCustomer_D.this.frame.setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		btnNewButton_2.setBounds(33, 344, 83, 31);
		frame.getContentPane().add(btnNewButton_2);
		btnClose.setBackground(Color.RED);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnClose.setBounds(548, 0, 46, 31);
		frame.getContentPane().add(btnClose);
		
		JButton btnNewButton_1 = new JButton("ဖ်က္မည္");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnNewButton_1.setBounds(405, 331, 106, 44);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("၀ယ္သူ အသစ္ ထည့္ျခင္း");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel.setBounds(143, 11, 277, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("နာမည္ :");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1.setBounds(65, 87, 90, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("လိပ္စာ :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(65, 167, 90, 44);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ဖုန္း     :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 19));
		lblNewLabel_1_1_1.setBounds(65, 258, 83, 44);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(191, 95, 320, 31);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(191, 175, 320, 31);
		frame.getContentPane().add(txtAddress);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(191, 266, 320, 31);
		frame.getContentPane().add(txtPhone);
		
		JButton btnNewButton = new JButton("ထည့္မည္");
		btnNewButton.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				
				//String id_customer = name.substring(name.length()-3) + phone.substring(phone.length()-2);
				
				//System.out.print(id_customer);
				
				new addCustomer_F().addCustomer(name, address, phone);
			}
		});
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnNewButton.setBounds(191, 331, 106, 44);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Tomato Green Grocery\\Images\\tomato.jpg"));
		lblNewLabel_2.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		lblNewLabel_2.setBounds(0, 0, 594, 398);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
