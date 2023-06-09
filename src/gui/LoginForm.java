package gui;

import db.DBConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;



public class LoginForm extends JFrame{
	private JTextField useridField;
	private JPasswordField passwordField;
	private JButton loginButton;
	//private JButton cancelButton;
	private String tbl = "parcel_user";
	private String LoginErrMsg = "Invalid username or password";
	private JButton backBtn;
	
	public LoginForm() {
		setLayout(null);
		setTitle("Login Form");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Create the form components
        JLabel useridLabel = new JLabel("User ID:");
        useridLabel.setBounds(40,20,100,30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40,70,100,30);
        useridField = new JTextField();
        useridField.setBounds(150,20,150,30);
        passwordField = new JPasswordField();
        passwordField.setBounds(150,70,150,30);
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(150, 140, 150, 30);
        backBtn = new JButton("Back");
        backBtn.setBounds(150, 180, 150, 30);
        
        //setLayout(new GridLayout(3, 2));
        
        add(useridLabel);
        add(useridField);
        
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backBtn);
        //add(cancelButton);    
        
        setLocation(450,200);
        setVisible(true);
        
        backBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Home home = new Home();
        	}
        });
        
        loginButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String userID = useridField.getText();
        		String password = new String(passwordField.getPassword());
        		//System.out.println(userID + " " + password);
        		
        		if(userID.equals("") || password.equals("")) {
        			JOptionPane.showMessageDialog(null,"Please select User ID or Password");
        			setVisible(false);
        		}else {
        			//User user = new User();
        			//user.getLogin(userID,password);
        			getLogin(userID, password);
        		}
        		
        	}
        	
        });
       
	}
	
	public void getLogin(String user_id, String password) {
		String sql = "Select * from " + tbl + " where `user_id` = '" +  user_id + "'";
		//System.out.println(sql);
		
		try {
			DBConnection db = new DBConnection();
			Connection con = db.createDBConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String pass = rs.getString("password");
				if(password.equals(pass)) {
					//Login Successful
					JOptionPane.showMessageDialog(null, "Login Successful");
					//method after login
					int role = rs.getInt("role");
					setVisible(false);
					if(role == 1) {
						AdminDashboard userDb = new AdminDashboard(user_id);
						userDb.setVisible(true);
					}else {
						UserDashboad userDb = new UserDashboad(user_id);
						userDb.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(null, this.LoginErrMsg);
					setVisible(false);
				}
			}else {
				JOptionPane.showMessageDialog(null, this.LoginErrMsg);
				setVisible(false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
