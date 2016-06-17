package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("all")
public class ModelConnectDb {
	private Connection connection = null;
	private String username = "root";
	private String password = "";
	private String db = "bnews";
	private String url = "jdbc:mysql://localhost:3306/"+db+"?useUnicode=true&characterEncoding=UTF-8";
	public Connection getConnectMySQL(){
		// nạp driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Không thể nạp driver "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Không thể kết nối đến cơ sở dữ liệu");
		}
		return connection;
	}
}
