package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AdminDashboard extends JFrame implements ActionListener{

	private JPanel contentPane;
	private String userID;
	private JLabel lblUserID;
	private JButton orderList;
	private JButton updateStatus;
	private JButton logoutbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard(args[0]);
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
	public AdminDashboard(String userID) {
		this.userID = userID;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setTitle("Admin Dashboard");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblUserID = new JLabel("Welcome, " + userID + "!");
		
		orderList = new JButton("Order List");
		orderList.addActionListener(this);
		
		updateStatus = new JButton("Update Delivery Status");
		updateStatus.addActionListener(this);
		
		logoutbtn = new JButton("Logout");
		logoutbtn.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(70)
							.addComponent(lblUserID, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(140)
							.addComponent(orderList))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(updateStatus, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(147)
							.addComponent(logoutbtn)))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUserID, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(orderList)
					.addGap(18)
					.addComponent(updateStatus)
					.addGap(38)
					.addComponent(logoutbtn)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		// Handle button clicks
        if (e.getSource() == orderList) {
        	setVisible(false);
        	OrderList orderList = new OrderList(this.userID);
        	orderList.setVisible(true);
        }
        if (e.getSource() == updateStatus) {
        	setVisible(false);
        	UpdateDeliveryStatus ds = new UpdateDeliveryStatus(this.userID);
        	ds.setVisible(true);
        }
        
        if (e.getSource() == logoutbtn) {
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
