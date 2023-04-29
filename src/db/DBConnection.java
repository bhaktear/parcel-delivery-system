package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myutil.Utils;

public class DBConnection {
	static Connection con = null;
	static String databaseName = "parcel_delivery"; //dbname
	static String url = "jdbc:mysql://localhost:3306/" +  databaseName;
	
	static String userName = "bhaktear";	//db userName
	static String password = "bhaktear";	//dbpass
	
	public static Connection createDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static void query(String query) {
		try {
			Connection con = createDBConnection();
			Statement stmt = con.createStatement();
			stmt.execute(query);
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Object> getData(String sql) {
		Map<String, Object> resp = new HashMap<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		try {
			DBConnection db = new DBConnection();
			Connection con = db.createDBConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Map<String,Object> row = new HashMap<>();
			//int rowCount = rs.getRow();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			
			if(!rs.next()) {
				resp = Utils.get_resp(1, "No data Found", null);
				//System.out.println(resp);
				return resp;
			}else {
				do {
					for(int i = 1; i<= columnsNumber; i++) {
						String columnName = rsmd.getColumnName(i);
						Object columnValue = rs.getObject(i);
						//System.out.println(columnName + " " + columnValue);
						row.put(columnName, columnValue);
					}
					rows.add(row);
				}while(rs.next());
				//System.out.println(rows);
				resp = Utils.get_resp(0, "Data Found", rows);
				//System.out.println(resp);
				return resp;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
