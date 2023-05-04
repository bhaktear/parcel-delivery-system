package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;

import myutil.Order;
import myutil.Thana;
import myutil.User;
import myutil.Utils;

import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;


public class OrderForm extends JFrame implements ActionListener{
	private String deliveryCharge = "80"; //by default delivery charge
	private JPanel contentPane;
	private JTextField merchantNameField;
	private JTextField customerNameField;
	private JTextField customerMobileField;
	private JTextField customerAddressField;
	private JTextField productTypeField;
	private JTextField productDetailsField;
	private JTextField productPriceField;
	private JTextField deliveryChargeField;
	private JTextField deliveryChargeDiscountField;
	private JTextField merchantMobileField;
	private String userID;
	private JComboBox<String> orderTypeField;
	private JComboBox<String> customerDistField;
	private JComboBox<String> customerThanaField;
	private JTextField ProductWeigthField;
	private JComboBox<String> paymentMethodField;
	private JTextField totalAmountField;
	private JButton submitBtn;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String userID = args[0];
					OrderForm frame = new OrderForm(userID);
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
	public OrderForm(String userID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		this.userID = userID;
		System.out.println(this.userID);
		
		Map<String, Object> resp = new HashMap<>();
		User user = new User();
		resp = user.getUser(userID);
		List<Map<String,Object>> userData = (List<Map<String, Object>>) resp.get("data");
		Map<String, Object> userIndex = userData.get(0);

		String merchantMobile = userIndex.get("mobile").toString();
		String merchantName = userIndex.get("user_name").toString();
		//System.out.print(userData);
		//System.out.print(userIndex);
		Map<String, Object> orderType = Utils.getOptions("order_type");
		List<Map<String,Object>> orderTypeData = (List<Map<String, Object>>) orderType.get("data");
		
		Map<String, Object> paymentMethod = Utils.getOptions("payment_method");
		List<Map<String,Object>> paymentMethodData = (List<Map<String, Object>>) paymentMethod.get("data");
		
		Map<String, Object> dist = Thana.getDist();
		List<Map<String,Object>> distData = (List<Map<String, Object>>) dist.get("data");
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		setTitle("Add Order");
		JLabel lblOrderType = new JLabel("Order Type");
		
		JLabel lblMerchantName = new JLabel("Merchant Name");
		
		merchantNameField = new JTextField();
		merchantNameField.setColumns(10);
		merchantNameField.setEditable(false);
		merchantNameField.setText(merchantName);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBackground(Color.GREEN);
		
		customerNameField = new JTextField();
		customerNameField.setColumns(10);
		
		customerMobileField = new JTextField();
		customerMobileField.setColumns(10);
		
		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		
		orderTypeField = new JComboBox<String>();
		Dropdown.addDropdown(orderTypeData, "code", "name", orderTypeField);
		
		JLabel lblCustomerDist = new JLabel("Customer District");
		customerDistField = new JComboBox<String>();
		Dropdown.addDropdown(distData, "dist_code", "dist_name", customerDistField);
		customerDistField.addActionListener(this);
		
		customerThanaField = new JComboBox<String>();
		
		JLabel lblCustomerThana = new JLabel("Customer Thana");
		
		JLabel lblCustomerAddress = new JLabel("Customer Address");
		
		customerAddressField = new JTextField();
		customerAddressField.setColumns(10);
		
		JLabel lblProductType = new JLabel("Product Type");
		
		productTypeField = new JTextField();
		productTypeField.setColumns(10);
		
		JLabel lblProductDetails = new JLabel("Product Details");
		
		productDetailsField = new JTextField();
		productDetailsField.setColumns(10);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		
		productPriceField = new JTextField();
		productPriceField.setColumns(10);
		//productPriceField.setText("0"); // by default discount = 0
		
		
		JLabel lblProductWeight = new JLabel("Product Weight");
		
		JLabel lblDeliveryCharge = new JLabel("Delivery Charge");
		
		deliveryChargeField = new JTextField();
		deliveryChargeField.setColumns(10);
		deliveryChargeField.setEditable(false);
		deliveryChargeField.setText(deliveryCharge); // by default delivery charge
		
		JLabel lblDeliveryChargeDiscountarge = new JLabel("Delivery Charge Discount");
		
		deliveryChargeDiscountField = new JTextField();
		deliveryChargeDiscountField.setColumns(10);
		deliveryChargeDiscountField.setText("0"); // by default discount = 0
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		
		JLabel lblMerchantMobile = new JLabel("Merchant Mobile");
		
		merchantMobileField = new JTextField();
		merchantMobileField.setColumns(10);
		merchantMobileField.setEditable(false);
		merchantMobileField.setText(merchantMobile);
		
		ProductWeigthField = new JTextField();
		ProductWeigthField.setColumns(10);
		
		paymentMethodField = new JComboBox<String>();
		Dropdown.addDropdown(paymentMethodData, "code", "name", paymentMethodField);
		
		totalAmountField = new JTextField();
		totalAmountField.setColumns(10);
		totalAmountField.setEditable(false);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		
		productPriceField.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
			@Override
		    public void insertUpdate(DocumentEvent e) {
		        // text was inserted into the document
				calculateTotalPrice();
		    }
		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        // text was removed from the document
		    	calculateTotalPrice();
		    }
		    @Override
		    public void changedUpdate(DocumentEvent e) {
		       calculateTotalPrice();
		    }
		});
		
		deliveryChargeDiscountField.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
			@Override
		    public void insertUpdate(DocumentEvent e) {
		        // text was inserted into the document
				calculateTotalPrice();
		    }
		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        // text was removed from the document
		    	calculateTotalPrice();
		    }
		    @Override
		    public void changedUpdate(DocumentEvent e) {
		       calculateTotalPrice();
		    }
		});
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMerchantName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblOrderType, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addComponent(lblProductType, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductPrice, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalAmount, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMerchantMobile, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerMobile, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
						.addComponent(lblCustomerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblCustomerDist, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblCustomerAddress, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(lblCustomerThana, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblProductDetails, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblProductWeight, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
						.addComponent(lblDeliveryCharge, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPaymentMethod, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(lblDeliveryChargeDiscountarge, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(merchantMobileField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(submitBtn)
							.addGap(18)
							.addComponent(btnBack))
						.addComponent(deliveryChargeDiscountField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addComponent(deliveryChargeField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(productPriceField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(productDetailsField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(productTypeField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(customerThanaField, 0, 240, Short.MAX_VALUE)
							.addComponent(customerDistField, 0, 240, Short.MAX_VALUE)
							.addComponent(customerMobileField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(customerNameField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(merchantNameField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addComponent(orderTypeField, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(customerAddressField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
						.addComponent(ProductWeigthField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addComponent(paymentMethodField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalAmountField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderType)
						.addComponent(orderTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMerchantName)
						.addComponent(merchantNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(merchantMobileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblMerchantMobile)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(customerNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerMobileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerMobile))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerDistField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerDist))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerThanaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerThana))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(customerAddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerAddress))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductType)
						.addComponent(productTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(productDetailsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductDetails))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductPrice)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblProductWeight))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(productPriceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ProductWeigthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeliveryCharge)
						.addComponent(deliveryChargeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeliveryChargeDiscountarge)
						.addComponent(deliveryChargeDiscountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalAmount)
						.addComponent(totalAmountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPaymentMethod)
						.addComponent(paymentMethodField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitBtn)
						.addComponent(btnBack))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == customerDistField) {
			String selectedDist = customerDistField.getSelectedItem().toString();
			Map<String, Object> thana = Thana.getThana(selectedDist);
			List<Map<String,Object>> thanaData = (List<Map<String, Object>>) thana.get("data");
			Dropdown.addDropdown(thanaData, "thana_code", "thana_name", customerThanaField);
		}
		
		if(e.getSource() == btnBack) {
			setVisible(false);
			UserDashboad userDb = new UserDashboad(this.userID);
			userDb.setVisible(true);
		}
		
		if(e.getSource() == submitBtn) {
			String orderType = orderTypeField.getSelectedItem().toString();
			String customer_name = customerNameField.getText();
			String customer_mobile = customerMobileField.getText();
			String distCode = customerDistField.getSelectedItem().toString();
			String thanaCode = customerThanaField.getSelectedItem().toString();
			String customer_address = customerAddressField.getText();
			String product_type = productTypeField.getText();
			String product_details = productDetailsField.getText();
			String product_price = productPriceField.getText();
			String delivery_charge = deliveryChargeField.getText();
			String discount = deliveryChargeDiscountField.getText();
			int total_delivery_charge = Integer.parseInt(delivery_charge) - Integer.parseInt(discount);
			String total_amount = totalAmountField.getText();
			String payment_method = paymentMethodField.getSelectedItem().toString();
			
			//validate
			if(customer_name.isEmpty() || customer_mobile.isEmpty() || customer_address.isEmpty() ||
					product_type.isEmpty() || product_details.isEmpty() || product_price.isEmpty() ||
					delivery_charge.isEmpty() || discount.isEmpty() || total_amount.isEmpty() || 
					product_details.isEmpty() || payment_method.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields.");
				return;
			}
			
			if(!Utils.validateMobile(customer_mobile)) {
				JOptionPane.showMessageDialog(null, "Invalid Customer Mobile Number");
				return;
			}
			
			/*
			String str = "Choose One";
			if(orderType.contains(str)) {
				JOptionPane.showMessageDialog(null, "Please select Order Type or Dist or Thana or Payment Method");
				return;
			}
			*/
			
			/*
			if(!Utils.validateDropdown(payment_method) || !Utils.validateDropdown(orderType) ||
					!Utils.validateDropdown(distCode) || !Utils.validateDropdown(thanaCode)) {
				JOptionPane.showMessageDialog(null, "Please select Order Type or Dist or Thana or Payment Method");
				return;
			}
			*/
			
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("order_type", orderType);
			map.put("user_id", this.userID);
			map.put("merchant_name", merchantNameField.getText());
			map.put("merchant_mobile", merchantMobileField.getText());
			map.put("customer_name", customer_name);
			map.put("customer_mobile", customer_mobile);
			map.put("customer_district", distCode);
			map.put("customer_thana", thanaCode);
			map.put("customer_address", customer_address);
			map.put("product_type", product_type);
			map.put("product_details", product_details);
			map.put("product_price", product_price);
			map.put("delivery_charge", delivery_charge);
			map.put("total_delivery_charge", total_delivery_charge);
			map.put("discount", discount);
			map.put("total_amount", total_amount);
			map.put("payment_method",payment_method);
			map.put("status", 1);
			
			//System.out.println(map);
			addOrder(map);
		}
		
			
	}
	
	public void calculateTotalPrice() {
		int totalPrice = 0;
		try {
			int priceInt = 0;
			int discountInt = 0;
			if(!productPriceField.getText().isEmpty()) {
				String price = productPriceField.getText();
				priceInt = Integer.parseInt(price);
			}
				 
			if(!deliveryChargeDiscountField.getText().isEmpty()) {
				String discount = deliveryChargeDiscountField.getText();
				discountInt = Integer.parseInt(discount);
			}
		
			String delCharge = deliveryChargeField.getText();
			
			totalPrice = priceInt + (Integer.parseInt(delCharge) - discountInt);
			//totalPrice = Integer.parseInt(price) + (Integer.parseInt(delCharge) - Integer.parseInt(discount));
			totalAmountField.setText(Double.toString(totalPrice));
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter Number in Total Price & Delivery Charge Discount Field");
		}
	}
	
	private void addOrder(HashMap<String,Object> map) {
		Map<String, Object> resp = new HashMap<>();
		Order order = new Order();
		resp = order.insert(map);
		JOptionPane.showMessageDialog(null, resp.get("msg"));
		if((int)resp.get("err") == 0) {
			setVisible(false);
			UserDashboad userDb = new UserDashboad(this.userID);
			userDb.setVisible(true);
		}
		
	}
}