package gui;

import java.awt.*;

import javax.swing.*;


public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Parcel Delivery Management System");
        setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu loginMenu = new JMenu("Login");
        menuBar.add(loginMenu);
        
        // Create a Registration menu and add it to the menu bar
        JMenu registrationMenu = new JMenu("Registration");
        menuBar.add(registrationMenu);
		
        loginMenu.addActionListener(e -> {
            // Open the login page
            LoginForm LoginForm = new LoginForm();
            LoginForm.setVisible(true);
        });
        
        registrationMenu.addActionListener(e -> {
        	RegForm RegForm = new RegForm();
        	
        });
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JLabel("Welcome to my app"), BorderLayout.CENTER);
        
		/*
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		*/
	}

}
