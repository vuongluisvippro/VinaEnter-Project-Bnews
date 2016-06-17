package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Category;
@SuppressWarnings("all")
public class ModelCategory {
	
	PreparedStatement pst;
	ResultSet rs;
	Connection conn;
	ModelConnectDb mConnect;
	String tableName = "category";
	String idName = "id_cat";
	
	public ModelCategory(){
		mConnect = new ModelConnectDb();
		/**
		 * Vấn đề là chúng ta có thể sử constructor cho linh hoạt
		 * Ví dụ: truyền vào tên bảng để thực hiện truy vấn
		 * => new ModelCategory(truyền table ở bên ngoài vào)
		 */
	}
	public ArrayList<Category> getList(){
		ArrayList<Category> alItem = new ArrayList<Category>();
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT * FROM "+tableName;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				alItem.add(new Category(rs.getInt("id_cat"), rs.getString("name")));
			}
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		} finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
			}			
		}
		return alItem;
	}
	public int addItem(Category objectItem) {
		int result = 0;
		conn = mConnect.getConnectMySQL();
		String sql = "INSERT INTO "+tableName+"(name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objectItem.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
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
			System.out.println("Error "+e.getMessage());
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
			}
		}
		return result;
	}
	public Category getItem(int id) {
		// TODO Auto-generated method stub
		Category cat = null;
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT * FROM "+tableName+" WHERE "+idName+" = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				cat = new Category(rs.getInt("id_cat"), rs.getString("name"));
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
		return cat;
	}
	public int editItem(Category objectItem) {
		int result = 0;
		conn = mConnect.getConnectMySQL();
		if(objectItem.getName().isEmpty()){
			// Lấy lại dữ liệu cũ 
			result = 1;
		}else{
			String sql = "UPDATE "+tableName+" SET name = ? WHERE id_cat = ? LIMIT 1";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, objectItem.getName());
				pst.setInt(2, objectItem.getId_cat());
				result = pst.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
			} finally{
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println("Error "+e.getMessage());
				}
			}
		}
		return result;
	}
}
