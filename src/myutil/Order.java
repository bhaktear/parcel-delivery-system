package myutil;

import java.util.HashMap;
import java.util.Map;

import db.DBConnection;

public class Order {
	private String tbl = "parcel_order";
	
	public Map<String, Object> getOrder(){
		return getOrder(null,null);
	}
	
	public Map<String, Object> getOrder(String user_id){
		return getOrder(user_id,null);
	}
	
	
	public Map<String, Object> getOrder(String user_id, String invoice) {
		Map<String, Object> resp = new HashMap<>();
		String where = "";
		String uWhere = "";
		String whereClause = "";
		if(user_id != null) where += "`user_id` = '" + user_id + "'";
		/*
		if(invoice != null) uWhere += "`invoice` = '" + invoice + "'";
		if(where != null || uWhere != null) {
			whereClause = " where " + where  + (u);
		}
		*/
		if(where != "") whereClause = " where " + where; 
		String sql = "select * from " + tbl + whereClause;
		//System.out.println(sql);
		resp = DBConnection.getData(sql);
		return resp;
	}
	
	private String getInvoice(int number) {
		String invoice = "IN" + String.format("%06d", number % 1000000);
		return invoice;
	}

	public Map<String, Object> insert(Map<String,Object> map) {
		Map<String, Object> resp = new HashMap<>();
		resp = Utils.get_resp(1, "Error Updating information", null);
		
		String sql = DBConnection.generateDynamicInsertSql(tbl, map);
		//System.out.println(sql);
		int lastInsertId = DBConnection.getLastInsertId(sql);
		if(lastInsertId > 0) {
			//System.out.println(lastInsertId);
			String invoice = getInvoice(lastInsertId);
			String sql1 = "Update " + tbl + " set `invoice`='" + invoice + "' where `id`=" + lastInsertId;
			System.out.println(sql1);
			int row = DBConnection.insert(sql1);
			if(row == 1) {
				resp = Utils.get_resp(0, "Data inserted successfully", null);
				return resp;
			}
		}
		
		return resp;
	}
}
