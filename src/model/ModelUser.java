package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Category;
import bean.User;
import library.LibraryString;

@SuppressWarnings("all")
public class ModelUser {
	
	PreparedStatement pst;
	ResultSet rs;
	Connection conn;
	ModelConnectDb mConnect;
	String tableName = "users";
	String idName = "id_user";
	
	public ModelUser(){
		mConnect = new ModelConnectDb();
		/**
		 * Vấn đề là chúng ta có thể sử constructor cho linh hoạt
		 * Ví dụ: truyền vào tên bảng để thực hiện truy vấn
		 * => new ModelCategory(truyền table ở bên ngoài vào)
		 */
	}
	public ArrayList<User> getList(){
		ArrayList<User> alItem = new ArrayList<User>();
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT * FROM "+tableName;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				alItem.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}			
		}
		return alItem;
	}
	public int addItem(User objectItem) {
		int result = 0;
		conn = mConnect.getConnectMySQL();
		String sql = "INSERT INTO "+tableName+"(username,password,fullname) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objectItem.getUsername());
			pst.setString(2, new LibraryString().md5(objectItem.getPassword()));
			pst.setString(3, objectItem.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public int delItem(int id) {
		int result = 0;
		conn = mConnect.getConnectMySQL();
		String sql = "DELETE FROM "+tableName+" WHERE "+idName+" = ? LIMIT 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		User user = null;
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT * FROM "+tableName+" WHERE "+idName+" = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}			
		}
		return user;
	}
	public int editItem(User user) {
		int result = 0;	
		conn = mConnect.getConnectMySQL();	
		if(user.getUsername() == null){
			if(user.getPassword().isEmpty() && user.getFullname().isEmpty()){
				result = 1;
			}else{
				String sql = "UPDATE "+tableName+" SET password = ?,fullname = ? WHERE "+idName+" = ? LIMIT 1";
				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, new LibraryString().md5(user.getPassword()));
					pst.setString(2, user.getFullname());
					pst.setInt(3, user.getId_user());
					result = pst.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally{
					try {
						pst.close();
						conn.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}else{
			result = 1;
		}
		return result;
	}
	public User getUser(String username, String password) {
		User user = null;
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT * FROM "+tableName+" WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2, new LibraryString().md5(password));
			rs = pst.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}			
		}
		return user;
	}
}