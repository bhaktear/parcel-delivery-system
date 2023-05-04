package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserDashboad extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton logoutButton;
	private JButton orderButton;
	private JLabel userIDLabel;
	private String userID;
	private JButton orderList;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String userID = args[0];
					UserDashboad frame = new UserDashboad(userID);
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
	public UserDashboad(String userID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("User Dashboard");
        setSize(400, 300);
        //System.out.println(userID);
        this.userID = userID;
        
        userIDLabel = new JLabel("Welcome, " + userID + "!");
        logoutButton = new JButton("Logout");
        
        orderButton = new JButton("Add Order");
        
        orderButton.addActionListener(this);
        
        orderList = new JButton("Order List");
        orderList.addActionListener(this);
        logoutButton.addActionListener(this);
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(111)
        					.addComponent(userIDLabel))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(123)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(orderList, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
        								.addGap(86))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addComponent(logoutButton)
        								.addGap(96)))
        						.addComponent(orderButton))))
        			.addContainerGap(75, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(userIDLabel)
        			.addGap(36)
        			.addComponent(orderButton)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(orderList)
        			.addGap(78)
        			.addComponent(logoutButton)
        			.addGap(35))
        );
        getContentPane().setLayout(groupLayout);
        
        
		
		/*
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		*/
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		// Handle button clicks
        if (e.getSource() == orderButton) {
        	setVisible(false);
        	OrderForm orderForm = new OrderForm(this.userID);
        	orderForm.setVisible(true);
        }
        if (e.getSource() == orderList) {
        	setVisible(false);
        	OrderList orderList = new OrderList(this.userID);
        	orderList.setVisible(true);
        }
        
        if (e.getSource() == logoutButton) {
        	int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
            	setVisible(false);
            	LoginForm login = new LoginForm();
    			login.setVisible(true);
            }
        }
        
        
	}
}
