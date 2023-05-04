package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import myutil.Order;
import myutil.Utils;

import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class UpdateDeliveryStatus extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField invoiceNumberField;
	private JButton btnSubmit;
	private String userID;
	private JComboBox<String> deliveryStatusField;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDeliveryStatus frame = new UpdateDeliveryStatus(args[0]);
					//UpdateDeliveryStatus frame = new UpdateDeliveryStatus();
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
	public UpdateDeliveryStatus(String userID) {
	//public UpdateDeliveryStatus() {
		this.userID = userID;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Update Delivery Status");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Map<String, Object> resp = new HashMap<>();
		resp = Utils.getStatus();
		//System.out.println(resp);
		List<Map<String,Object>> statusData = (List<Map<String, Object>>) resp.get("data");
		
		
		JLabel lblInvoice = new JLabel("Invoice Number");
		
		invoiceNumberField = new JTextField();
		invoiceNumberField.setColumns(10);
		
		JLabel lblUpdateStatus = new JLabel("Delivery Status");
		
		deliveryStatusField = new JComboBox<String>();
		Dropdown.addDropdown(statusData, "code", "name", deliveryStatusField);
		
		btnSubmit = new JButton("Submit");
		
		btnSubmit.addActionListener(this);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInvoice, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUpdateStatus, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSubmit)
							.addGap(18)
							.addComponent(btnBack))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(deliveryStatusField, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(invoiceNumberField, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInvoice, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(invoiceNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpdateStatus)
						.addComponent(deliveryStatusField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnBack))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		// Handle button clicks
        if (e.getSource() == btnBack) {
        	setVisible(false);
        	AdminDashboard userDb = new AdminDashboard(this.userID);
			userDb.setVisible(true);
        }
        
        if (e.getSource() == btnSubmit) {
        	String invoiceNo = invoiceNumberField.getText();
			String status = deliveryStatusField.getSelectedItem().toString();
			
			if(invoiceNo.isEmpty() || status.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields.");
				return;
			}
			
			if(status.equals("Choose One")) {
				JOptionPane.showMessageDialog(null, "Please select Delivery Status");
				return;
			}
			
			Order order = new Order();
			Map<String, Object> oresp = new HashMap();
			oresp = order.updateDeliveryStatus(invoiceNo, status);
			JOptionPane.showMessageDialog(null,oresp.get("msg"));
			
			if((int) oresp.get("err") == 0) {
				setVisible(false);
				AdminDashboard userDb = new AdminDashboard(this.userID);
				userDb.setVisible(true);
			}
        }
	}
}