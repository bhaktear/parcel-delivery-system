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
}
