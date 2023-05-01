package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;

import myutil.Order;
import myutil.User;
import myutil.Utils;

public class OrderList extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private String userID;
	private int userRole;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderList frame = new OrderList(args[0]);
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
	public OrderList(String userID) {
		this.userID = userID;
		//get user info
		Map<String, Object> uresp = new HashMap<>();
		User user = new User();
		uresp = user.getUser(userID);
		List<Map<String,Object>> userData = (List<Map<String,Object>>) uresp.get("data");
		//System.out.println(userData);
		Map<String, Object> ufirst = userData.get(0);
		//System.out.println(ufirst);
		String role = ufirst.get("role").toString();
		String user_id = (Integer.parseInt(role) == 1) ? null:this.userID;
		this.userRole = Integer.parseInt(role);
		//System.out.println(user_id);
		
		setTitle("Order List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//column
		DefaultTableModel model = new DefaultTableModel();
		String[] columns = {"Invoice No", "Merchant Name","Customer Name","Customer Mobile",
				"Customer Address","Status","Action"};
		for(int i=0; i< columns.length; i++) {
			model.addColumn(columns[i]);
		}
		
		Map<String, Object> resp = new HashMap<>();
		Order order = new Order();
		resp = order.getOrder(user_id);
		//System.out.println(resp);
		if((int)resp.get("err") == 1) {
			JOptionPane.showMessageDialog(null, resp.get("msg"));
			//setVisible(false);
			return;
		}
		
		List<Map<String,Object>> orderData = (List<Map<String,Object>>) resp.get("data");
		//System.out.println(orderData);
		String invoice,uid,merchant_name,customer_name, customer_mobile, customer_address
		,customer_district,customer_thana,action,status;
		for(Map<String,Object> row:orderData ) {
			invoice = row.get("invoice").toString();
			uid = row.get("user_id").toString();
			merchant_name = row.get("merchant_name").toString();
			customer_name = row.get("customer_name").toString();
			customer_mobile = row.get("customer_mobile").toString();
			customer_district = row.get("customer_district").toString();
			customer_thana = row.get("customer_thana").toString();
			customer_address = row.get("customer_address").toString() + ", " + customer_thana + "," + customer_district;
			action = "";
			Map<String, Object> sresp = new HashMap<>();
			sresp = Utils.getStatus(row.get("status").toString());
			//System.out.println(sresp);
			List<Map<String,Object>> statusData = (List<Map<String,Object>>) sresp.get("data");
			status = (String) statusData.get(0).get("name");
			
			String[] rows = {invoice,merchant_name,customer_name,customer_mobile,customer_address,status,action};
			model.addRow(rows);
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 40, 766, 311);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Order List ");
		lblNewLabel.setBounds(107, -2, 395, 29);
		contentPane.add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(241, 0, 117, 25);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			setVisible(false);
			if(this.userRole == 1) {
				AdminDashboard userDb = new AdminDashboard(this.userID);
				userDb.setVisible(true);
			}else {
				UserDashboad userDb = new UserDashboad(this.userID);
				userDb.setVisible(true);
			}
		}
	}
}
