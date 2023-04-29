package myutil;

import java.util.HashMap;
import java.util.Map;

import db.DBConnection;

public class Thana {
	
	private String tbl = "parcel_thana";
	
	public Map<String, Object> getDist() {
		return getDist(null);
	}
	
	public Map<String, Object> getDist(String dist_code){
		Map<String, Object> resp = new HashMap<>();
		String where = "";
		if(dist_code != null) {
			where = " where `dist_code` ='" + dist_code;
		}
		String sql = "select distinct(dist_code) as code,dist_name as name from " + tbl + where;
		//System.out.println(sql);
		resp = DBConnection.getData(sql);
		return resp;
	}
	
}
