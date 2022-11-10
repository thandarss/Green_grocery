package Greengrocery_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login_D extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_D frame = new login_D();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login_D() {
		
		setUndecorated(true);
		setSize(300,300);
		//setLocationRelativeTo(null);
		
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		//setBounds(150, 100, size.width, size.height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 398, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("အေကာင့္ဝင္မည္");
		lblNewLabel.setFont(new Font("Zawgyi-One", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 26, 229, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("နာမည္    : ");
		lblNewLabel_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblNewLabel_1.setBounds(70, 109, 86, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("စကားဝွက္ : ");
		lblNewLabel_1_1.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(70, 185, 86, 41);
		contentPane.add(lblNewLabel_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(195, 114, 141, 31);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(195, 190, 141, 31);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameString = txtName.getText();
				String passString = txtPassword.getText();
				
				new login_F().checkLogin(nameString, passString);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Zawgyi-One", Font.BOLD, 15));
		btnNewButton.setBounds(159, 254, 86, 38);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("X");
		btnClose.setForeground(Color.BLACK);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login_D.this.setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClose.setBackground(new Color(176, 0, 0));
		btnClose.setBounds(339, 0, 59, 31);
		contentPane.add(btnClose);
	}
}
