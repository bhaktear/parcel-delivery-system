package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	static Connection con = null;
	static String databaseName = "java_user"; //dbname
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
}
