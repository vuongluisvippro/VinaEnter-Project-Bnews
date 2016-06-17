package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Category;
import bean.New;
import bean.User;
import library.LibraryConstant;
import library.LibraryString;

@SuppressWarnings("all")
public class ModelNew {
	
	PreparedStatement pst;
	ResultSet rs;
	Connection conn;
	ModelConnectDb mConnect;
	String tableName = "news";
	String idName = "id_news";
	
	public ModelNew(){
		mConnect = new ModelConnectDb();
		/**
		 * Vấn đề là chúng ta có thể sử constructor cho linh hoạt
		 * Ví dụ: truyền vào tên bảng để thực hiện truy vấn
		 * => new ModelCategory(truyền table ở bên ngoài vào)
		 */
	}
	public ArrayList<New> getList(){
		ArrayList<New> alItem = new ArrayList<New>();
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT news.id_news,news.name,news.preview_text,news.detail_text,news.id_cat,news.picture,category.name FROM news INNER JOIN category ON news.id_cat = category.id_cat";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				alItem.add(new New(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
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
	public int addItem(New objectItem) {
		int result = 0;
		conn = mConnect.getConnectMySQL();
		String sql = "INSERT INTO "+tableName+"(name,preview_text,detail_text,id_cat,picture) VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objectItem.getName());
			pst.setString(2, objectItem.getPreview_text());
			pst.setString(3, objectItem.getDetail_text());
			pst.setInt(4, objectItem.getId_cat());
			pst.setString(5, objectItem.getPicture());
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
	public int editItem(New newx) {
		int result = 0;	
		conn = mConnect.getConnectMySQL();
		
		if(newx.getPicture().isEmpty()){
			String sql = "UPDATE "+tableName+" SET name = ?,preview_text = ?,detail_text = ?,id_cat = ? WHERE "+idName+" = ? LIMIT 1";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, newx.getName());
				pst.setString(2, newx.getPreview_text());
				pst.setString(3, newx.getDetail_text());
				pst.setInt(4, newx.getId_cat());
				pst.setInt(5, newx.getId_news());
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
		}else{
			String sql = "UPDATE "+tableName+" SET name = ?,preview_text = ?,detail_text = ?,id_cat = ?,picture = ? WHERE "+idName+" = ? LIMIT 1";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, newx.getName());
				pst.setString(2, newx.getPreview_text());
				pst.setString(3, newx.getDetail_text());
				pst.setInt(4, newx.getId_cat());
				pst.setString(5, newx.getPicture());
				pst.setInt(6, newx.getId_news());
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
	public New getItem(int id) {
		// TODO Auto-generated method stub
		New newx = null;
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT news.id_news,news.name,news.preview_text,news.detail_text,news.id_cat,news.picture,category.name FROM news INNER JOIN category ON news.id_cat = category.id_cat WHERE news.id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				newx = new New(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
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
		return newx;
	}
	public ArrayList<New> getList(String id) {
		ArrayList<New> alItem = new ArrayList<New>();
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT news.id_news,news.name,news.preview_text,news.detail_text,news.id_cat,news.picture,category.name FROM news INNER JOIN category ON news.id_cat = category.id_cat WHERE news.id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next()){
				alItem.add(new New(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
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
	public int getSum() {
		int sodong = 0;
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT COUNT(id_news) AS sodong FROM news";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				sodong = rs.getInt("sodong");
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
		return sodong;
	}
	public ArrayList<New> getListForPaginator(int offset, int rowCount) {
		ArrayList<New> alItem = new ArrayList<New>();
		conn = mConnect.getConnectMySQL();
		String sql = "SELECT news.id_news,news.name,news.preview_text,news.detail_text,news.id_cat,news.picture,category.name FROM news INNER JOIN category ON news.id_cat = category.id_cat LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, rowCount);			
			rs = pst.executeQuery();
			while(rs.next()){
				alItem.add(new New(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
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
}