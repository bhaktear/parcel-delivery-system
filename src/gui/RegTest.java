package gui;

import java.awt.*;
import javax.swing.*;

public class RegTest extends JFrame {
	public RegTest() {
		setTitle("Personal Information Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel personalInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        personalInfoPanel.add(new JLabel("Merchant Name:"), c);

        c.gridx = 1;
        c.gridy = 0;
        personalInfoPanel.add(new JTextField(20), c);

        c.gridx = 0;
        c.gridy = 1;
        personalInfoPanel.add(new JLabel("Name:"), c);

        c.gridx = 1;
        c.gridy = 1;
        personalInfoPanel.add(new JTextField(20), c);

        c.gridx = 0;
        c.gridy = 2;
        personalInfoPanel.add(new JLabel("Mobile Number:"), c);

        c.gridx = 1;
        c.gridy = 2;
        personalInfoPanel.add(new JTextField(20), c);

        c.gridx = 0;
        c.gridy = 3;
        personalInfoPanel.add(new JLabel("Email:"), c);

        c.gridx = 1;
        c.gridy = 3;
        personalInfoPanel.add(new JTextField(20), c);

        c.gridx = 0;
        c.gridy = 4;
        personalInfoPanel.add(new JLabel("District:"), c);

        c.gridx = 1;
        c.gridy = 4;
        JComboBox<String> districtComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Sylhet"});
        personalInfoPanel.add(districtComboBox, c);

        c.gridx = 0;
        c.gridy = 5;
        personalInfoPanel.add(new JLabel("Thana:"), c);

        c.gridx = 1;
        c.gridy = 5;
        JComboBox<String> thanaComboBox = new JComboBox<>(new String[]{"Uttara", "Mirpur", "Gulshan"});
        personalInfoPanel.add(thanaComboBox, c);

        c.gridx = 0;
        c.gridy = 6;
        personalInfoPanel.add(new JLabel("Address:"), c);

        c.gridx = 1;
        c.gridy = 6;
        personalInfoPanel.add(new JTextArea(5, 20), c);

        add(personalInfoPanel);
        pack();
        setVisible(true);
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(RegTest::new);
    }
}


