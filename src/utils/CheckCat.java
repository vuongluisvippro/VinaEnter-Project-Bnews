package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ModelConnectDb;

public class CheckCat {
	/**
	 * Check trường hợp chúng ta muốn thêm mới
	 * @param name
	 * @return
	 */
	public boolean checkCatName(String name){
		boolean checkx = true;
		int check = 0;
		Connection conn = new ModelConnectDb().getConnectMySQL();
		try {
			PreparedStatement pst = conn.prepareStatement(new String("SELECT COUNT(*) AS CheckNameCat FROM category WHERE name = ?"));
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				check = rs.getInt("CheckNameCat");
			}
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		}
		if(check > 0){
			checkx = false;
		}
		return checkx;
	}
	/**
	 * Kiểm tra với trường hợp là người dùng muốn sữa
	 * @param args
	 */
	public boolean checkCatNameVer(String name,int id){
		boolean checkx = false;
		int check = 0;
		Connection conn = new ModelConnectDb().getConnectMySQL();
		try {
			PreparedStatement pst = conn.prepareStatement(new String("SELECT COUNT(*) AS CheckNameCat FROM category WHERE name = ? AND id_cat NOT IN(?)"));
			pst.setString(1, name);
			pst.setInt(2,id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				check = rs.getInt("CheckNameCat");
			}
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		}
		if(check > 0){
			checkx = true;
		}
		return checkx;
	}
}
