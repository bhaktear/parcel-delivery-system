package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TestGui extends JFrame {
	private JTextField merchant_nameField;
	private JTextField nameField;
	private JTextField mobileField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGui frame = new TestGui();
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
	public TestGui() {
		setTitle("Registration Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,300);
		getContentPane().setLayout(null);
		
		merchant_nameField = new JTextField();
		merchant_nameField.setBounds(158, 18, 155, 26);
		getContentPane().add(merchant_nameField);
		merchant_nameField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(346, 12, 114, 26);
		getContentPane().add(lblName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(478, 18, 155, 26);
		getContentPane().add(nameField);
		
		JLabel lblMobile = new JLabel("Mobile No");
		lblMobile.setBounds(12, 53, 114, 15);
		getContentPane().add(lblMobile);
		
		mobileField = new JTextField();
		mobileField.setColumns(10);
		mobileField.setBounds(158, 48, 155, 26);
		getContentPane().add(mobileField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(346, 42, 114, 26);
		getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(478, 48, 155, 26);
		getContentPane().add(textField);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 82, 644, 123);
		getContentPane().add(panel);
		
		
		
		JLabel lblMerchantName = new JLabel("Merchant Name");
		lblMerchantName.setBounds(12, 23, 111, 15);
		getContentPane().add(lblMerchantName);
		
	
	}
}
