package myutil;

import java.sql.*;
import java.util.*;


import db.DBConnection;

public class User {
	private String user_id;
	private String password;
	private String tbl = "parcel_user";
	//private String LoginErrMsg = "Invalid username or password";
	
	public Map<String, Object> getUser(String user_id) {
		Map<String, Object> resp = new HashMap<>();
		String sql = "Select * from " + tbl + " where `user_id` = '" +  user_id + "'";
		//String sql = "Select * from " + tbl;
		//System.out.println(sql);
		
		resp = DBConnection.getData(sql);
		return resp;
	}
	
	public Map<String, Object> insert(Map<String,Object> map) {
		Map<String, Object> resp = new HashMap<>();
		resp = Utils.get_resp(1, "Error Updating information", null);
		
		String user_id = map.get("user_id").toString();
		String user_name = map.get("name").toString();
		String email = map.get("email").toString();
		String mobile = map.get("mobile").toString();
		String password = map.get("password").toString();
		int role = 2;
		int published = 1;
		
		String sql = "insert into " + tbl + " (`user_id`,`user_name`,`email`,`mobile`,`password`,`role`,"
				+ "`published`) values ('"+ user_id + "','" + user_name + "','" + email + "','" + mobile
				+ "','" + password + "','" + role + "','" + published + "')" ;
		//System.out.println(sql);
		int rows = DBConnection.insert(sql);
		//System.out.println(rows);
		if(rows == 1) {
			resp = Utils.get_resp(0, "Data inserted successfully", null);
		}
		//System.out.println(resp);
		return resp;
		
		/*
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    //System.out.println(entry.getKey() + " : " + entry.getValue());
			
		}
		*/
		
	}
}
