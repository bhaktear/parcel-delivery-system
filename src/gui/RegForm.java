package gui;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.GroupLayout.*;




import javax.swing.LayoutStyle.ComponentPlacement;

import myutil.Merchant;
import myutil.Thana;
import myutil.User;
import myutil.Utils;

public class RegForm extends JFrame{

	//private JFrame frame;
	
	private JLabel lblMerchantName;
	private JTextField merchantNameField;
	private JTextField nameField;
	private JLabel lblMobile;
	private JTextField mobileField;
	private JLabel lblEmail;
	private JTextField emailField;
	private JLabel lblDist;
	private JLabel lblThana;
	private JComboBox <String> thanaField;
	private JLabel lblAddress;
	private JTextField addressField;
	private JLabel lblUserID;
	private JTextField userIdField;
	private JLabel lblPass;
	private JPasswordField passField;
	private JPasswordField confirmPassField;
	private JLabel lblName;
	private JButton submitBtn;
	private JComboBox <String> distField;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegForm window = new RegForm();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegForm() {
		initialize();
		distField.addActionListener(e-> {
			String selectedDist = distField.getSelectedItem().toString();
			//System.out.println(selectedDist);
			Map<String, Object> thana = Thana.getThana(selectedDist);
			List<Map<String,Object>> thanaData = (List<Map<String, Object>>) thana.get("data");
			//System.out.println(thanaData);
			Dropdown.addDropdown(thanaData, "thana_code", "thana_name", thanaField);
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Home home = new Home();
			}
		});
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "hello");
				String merchantName = merchantNameField.getText();
				String name = nameField.getText();
				String mobile = mobileField.getText();
				String email = emailField.getText();
				String distCode = distField.getSelectedItem().toString();
				String thanaCode = thanaField.getSelectedItem().toString();
				String address = addressField.getText();
				String userID = userIdField.getText();
				String password = new String(passField.getPassword());
				String confirmPass = new String(confirmPassField.getPassword());
				
				
				if(merchantName.isEmpty() || name.isEmpty() || mobile.isEmpty() || email.isEmpty() || 
						userID.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || 
						distCode.isEmpty() || thanaCode.isEmpty() || address.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields.");
					return;
				}
				
				//check password & confirm password matched
				if(!password.equals(confirmPass)) {
					JOptionPane.showMessageDialog(null, "Password & Confirm Password donot match");
					return;
				}
				
				//validate mobile number
				if(!Utils.validateMobile(mobile)) {
					JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
					return;
				}
				
				//validate email
				if(!Utils.validateEmail(email)) {
					JOptionPane.showMessageDialog(null, "Invalid Email Address");
					return;
				}
				
				
				//put all form data in HashMap
				HashMap<String, Object> map = new HashMap<>();
				map.put("merchant_name", merchantName);
				map.put("name", name);
				map.put("mobile", mobile);
				map.put("email", email);
				map.put("dist_code", distCode);
				map.put("thana_code", thanaCode);
				map.put("address", address);
				map.put("user_id", userID);
				map.put("password", password);
				//System.out.println(map);
				addReg(map);
				
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Registration Form");
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Map<String, Object> dist = Thana.getDist();
		List<Map<String,Object>> distData = (List<Map<String, Object>>) dist.get("data");
		//System.out.println(distData);	
		//Map<String, Object> thanas = thana.getDist();
		
		/*
		for(Map<String,Object> row:distData ) {
			String key = row.get("dist_code").toString();
			String value = row.get("dist_name").toString();
			System.out.println(key + " " + value);
			distField.addItem(value);
			distField.putClientProperty(key, value);
		}
		*/
		
		
		lblMerchantName = new JLabel("Merchant Name");
		
		merchantNameField = new JTextField();
		merchantNameField.setColumns(10);
		
		lblName = new JLabel("Name");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		lblMobile = new JLabel("Mobile");
		
		mobileField = new JTextField();
		mobileField.setColumns(10);
		
		lblEmail = new JLabel("Email");
		
		emailField = new JTextField();
		emailField.setColumns(10);
		
		lblDist = new JLabel("District");
		distField = new JComboBox<String>();
		Dropdown.addDropdown(distData, "dist_code", "dist_name", distField);
		
		
		lblThana = new JLabel("Thana");
		thanaField = new JComboBox<String>();
		
		lblAddress = new JLabel("Address");
		
		addressField = new JTextField();
		addressField.setColumns(10);
		
		lblUserID = new JLabel("User ID");
		
		userIdField = new JTextField();
		userIdField.setColumns(10);
		
		lblPass = new JLabel("Password");
		
		passField = new JPasswordField();
		
		JLabel lblConPass = new JLabel("Confirm Password");
		
		confirmPassField = new JPasswordField();
		
		submitBtn = new JButton("Submit");
		submitBtn.setBackground(new Color(138, 226, 52));
		
		btnBack = new JButton("Back");
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(addressField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMerchantName, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(merchantNameField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMobile, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mobileField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDist, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblThana, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(thanaField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addComponent(distField, 0, 191, Short.MAX_VALUE))))
							.addContainerGap(59, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConPass, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(confirmPassField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addComponent(passField)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(submitBtn)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnBack))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUserID, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(userIdField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(59, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMerchantName)
						.addComponent(merchantNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobile)
						.addComponent(mobileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDist)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblThana, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(distField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(thanaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUserID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(userIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblConPass, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(submitBtn)
								.addComponent(btnBack)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(confirmPassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);		
		
	}
	
	
	
	private void addReg(HashMap<String,Object> map) {
		Object user_id = map.get("user_id");
		Map<String, Object> resp = new HashMap<>();
		
		User user = new User();
		resp = user.getUser(user_id.toString());
		//System.out.println(resp);
		
		int err = (int) resp.get("err");
		//check duplicate data exists following user_id
		//err = 0 data already exits in db
		//err = 1 data not stored in db
		
		if(err == 0) {
			JOptionPane.showMessageDialog(null, "Duplicate data Found");
			return;
		}
		
		
		//insert user table
		Map<String, Object> insertResp = new HashMap<>();
		insertResp = user.insert(map);
		if((int) insertResp.get("err") == 1) {
			JOptionPane.showMessageDialog(null, insertResp.get("msg"));
			return;
		}
		
		//insert merchant table
		Map<String, Object> merchantResp = new HashMap<>();
		Merchant merchant = new Merchant();
		merchantResp = merchant.insert(map);
		
		//if((int) merchantResp.get("err") == 1) {
		JOptionPane.showMessageDialog(null, merchantResp.get("msg"));
		//err = 0 send to login pagecd 
			//return;
		//}
		if((int) merchantResp.get("err") == 0) {
			setVisible(false);
			LoginForm login = new LoginForm();
			login.setVisible(true);
		}
		
		
		
		//System.out.println(resp.get("msg"));
		
		//System.out.println(userId);
		/*
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    //System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		*/
	}
	
	
	
}
