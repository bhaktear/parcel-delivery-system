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
			where = " where `dist_code` ='" + dist_code + "'";
		}
		String sql = "select distinct(dist_code),dist_name from " + tbl + where;
		//System.out.println(sql);
		resp = DBConnection.getData(sql);
		return resp;
	}
	
	public Map<String, Object> getThana() {
		return getThana(null);
	}
	
	public Map<String, Object> getThana(String dist_name) {
		Map<String, Object> resp = new HashMap<>();
		String where = "";
		if(dist_name != null) {
			where = " where `dist_name` ='" + dist_name + "'";
		}
		String sql = "select distinct(thana_code),thana_name from " + tbl + where;
		//String sql = "select id, thana_code,thana_name from " + tbl + where + " group by thana_code";
		//System.out.println(sql);
		resp = DBConnection.getData(sql);
		return resp;
	}
	
}
