package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import model.ModelConnectDb;
import model.ModelUser;

@SuppressWarnings("all")
public class CheckUser {

	public boolean checkUserName(String username) {
		boolean check = true;
		for(User user : new ModelUser().getList()){
			if(user.getUsername().matches(username)){
				check = false;
			}
		}
		/**
		 * tốn bộ nhớ nếu ta xét hết
		 */
		return check;
	}
	public boolean checkUserNameVer(String username){
		boolean checkx = true;
		int check = 0;
		Connection conn = new ModelConnectDb().getConnectMySQL();
		try {
			PreparedStatement pst = conn.prepareStatement(new String("SELECT COUNT(*) AS CheckUser FROM users WHERE username = ?"));
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				check = rs.getInt("CheckUser");
			}
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		}
		if(check > 0){
			checkx = false;
		}
		return checkx;
	}
	public boolean checkUserNameVer1(String username,int id_user){
		boolean checkx = true;
		int check = 0;
		Connection conn = new ModelConnectDb().getConnectMySQL();
		try {
			PreparedStatement pst = conn.prepareStatement(new String("SELECT COUNT(*) AS CheckUser FROM users WHERE username = ? AND id_user NOT IN(?)"));
			pst.setString(1, username);
			pst.setInt(2, id_user);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				check = rs.getInt("CheckUser");
			}
		} catch (SQLException e) {
			System.out.println("Error "+e.getMessage());
		}
		if(check > 0){
			checkx = false;
		}
		return checkx;
	}
}
