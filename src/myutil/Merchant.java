package myutil;

import java.util.*;

import db.DBConnection;


public class Merchant {
	private String tbl = "parcel_merchant";
	public Map<String, Object> insert(Map<String,Object> map) {
		Map<String, Object> resp = new HashMap<>();
		resp = Utils.get_resp(1, "Error Updating information", null);
		
		String user_id = map.get("user_id").toString();
		String name = map.get("name").toString();
		String merchant_name = map.get("merchant_name").toString();
		String district = map.get("dist_code").toString();
		String thana = map.get("thana_code").toString();
		String address = map.get("name").toString();
		int status = 1;
		
		String sql = "insert into " + tbl + " (`user_id`,`merchant_name`,`name`,`district`,`thana`,`address`,"
				+ "`status`) values ('"+ user_id + "','" + merchant_name + "','" + name + "','" + district
				+ "','" + thana + "','" + address + "','" + status + "')" ;
		int rows = DBConnection.insert(sql);
		//System.out.println(rows);
		if(rows == 1) {
			resp = Utils.get_resp(0, "Data inserted successfully", null);
		}
		//System.out.println(resp);
		return resp;
	}
}
