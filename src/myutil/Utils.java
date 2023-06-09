package myutil;

import java.util.*;

import db.DBConnection;

public class Utils {
	
	public static boolean validateMobile(String mobile) {
		String regex = "^01[0-9]{9}$";
		return mobile.matches(regex);
	}
	
	public static boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";
		return email.matches(regex);
	}
	
	public static boolean validateDropdown(String drop) {
		String str = "Choose One";
		if(!str.contains(str)) return false;
		return true;
	}
	
	public static Map<String, Object> get_resp(int err, String msg, List<Map<String, Object>> data) {
		Map<String, Object> resp = new HashMap<>();
		resp.put("err",err);
		resp.put("msg", msg);
		resp.put("data", data);
		return resp;
	}
	
	public static Map<String, Object> getOptions(){
		return getOptions(null);
	}
	
	
	public static Map<String, Object> getOptions(String type){
		Map<String, Object> resp = new HashMap<>();
		String tbl = "parcel_options";
		String where = " where published=1";
		if(type != "")  where += " and `type`='" + type + "'";
		String sql = "select * from " + tbl + where;
		resp = DBConnection.getData(sql);
		return resp;
	}
	
	public static Map<String, Object> getStatus(){
		return getStatus("");
	}
	
	public static Map<String, Object> getStatus(String code){
		return getStatus(code,"");
	}
	
	public static Map<String, Object> getStatus(String code,String name){
		Map<String, Object> resp = new HashMap<>();
		String tbl = "parcel_status";
		String where = " where published=1";
		if(code != "")  where += " and `code`='" + code + "'";
		if(name != "")  where += " and `name`='" + name + "'";
		String sql = "select * from " + tbl + where;
		//System.out.println(sql);
		resp = DBConnection.getData(sql);
		return resp;
	}
	
	public static void retrieve_data(List<Map<String, Object>> data) {
		for(Map<String,Object> row:data ) {
			System.out.print(data);
			for(Map.Entry<String, Object> entry:row.entrySet()) {
				String columnName = entry.getKey();
				Object value = entry.getValue();
				System.out.println(columnName + ": " + value);
			}
		}
	}
	
	
	public static String generateRandomString() {
		return generateRandomString(8);
	}
	
	
	public static String generateRandomString(int length) {
		String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		String alphanumeric = uuid.replaceAll("[^A-Za-z0-9]", "");
		return alphanumeric.substring(0, length);
	}
	
	
	public static String getRandomUid(int length) {
		String randomString = generateRandomString(length);
		//System.out.println(randomString);
		while(!is_random_uid(randomString)) {
			randomString = generateRandomString(length);
		}
		return randomString; 
	}
	
	public static boolean is_random_uid(String userId) {
		boolean unique = false;
		//System.out.println(userId);
		Map<String, Object> resp = new HashMap<>();
		String tbl = "random_uid";
		String sql = "select * from " + tbl + " where user_id='" + userId + "'";
		resp = DBConnection.getData(sql);
		//System.out.println(resp);
		int err = (int) resp.get("err");
		//err = 1 no data found
		//err = 0 data found
		if(err == 1) {
			//insert data into table
			String sql1 = "insert into " + tbl + "(`user_id`) Values ('" + userId + "')";
			DBConnection.query(sql1);
			unique = true;
		}else {
			//data already exits
		}
		return unique;
	}
}
