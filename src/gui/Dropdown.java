package gui;

import java.util.*;

import javax.swing.*;


public class Dropdown {
	
	public static void addDropdown(List<Map<String, Object>> data, String key, String value,
			JComboBox<String> comboBox) {
		comboBox.addItem("Choose One");
		for(Map<String,Object> row:data ) {
			String keyParams = row.get(key).toString();
			String valueParams = row.get(value).toString();
			//System.out.println(key + " " + value);
			comboBox.addItem(valueParams);
			comboBox.putClientProperty(keyParams, valueParams);
		}
	}
}
