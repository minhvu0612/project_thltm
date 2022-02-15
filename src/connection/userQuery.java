package connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import models.users;

public class userQuery {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	
	public int addUser(users user) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return 0;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into users (username, avatar, password, email, phone, gender) values (?, ?, ?, ?, ?, ?)");
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getAvatar());
		pst.setString(3, user.getPassword());
		pst.setString(4, user.getEmail());
		pst.setString(5, user.getPhone());
		pst.setString(6, user.getGender());
		if (pst.executeUpdate() == 1) {
			return 1;
		}
		return 0;
	}
	
	public users searchUser(String u) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from users where username = ?");
		pst.setString(1, u);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			users user = null;
			while (result.next()) {
				user = new users(result.getInt("id"), result.getString("username"), this.result.getString("avatar"), 
				         result.getString("password"), result.getString("email"), 
				         result.getString("phone"), result.getString("gender"));
			}
			return user;
		}
	}
	
	
	public users handleLogin(String u, String p) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from users where username = ? and password = ?");
		pst.setString(1, u);
		pst.setString(2, p);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			users user = null;
			while (result.next()) {
				user = new users(result.getInt("id"), result.getString("username"), this.result.getString("avatar"), 
						         result.getString("password"), result.getString("email"), 
						         result.getString("phone"), result.getString("gender"));
			}
			return user;
		}
	}
	
	public users getUser(int i) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from users where id = ?");
		pst.setInt(1, i);
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			users user = null;
			while (result.next()) {
				user = new users(result.getInt("id"), result.getString("username"), this.result.getString("avatar"), 
						         result.getString("password"), result.getString("email"), 
						         result.getString("phone"), result.getString("gender"));
			}
			return user;
		}
	}
	
	/* load all user form database */
	public List<users> loadAllUser() throws SQLException{
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from users");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			List<users> list = new ArrayList<users>();
			while (result.next()) {
				users user = new users(result.getInt("id"), result.getString("username"), this.result.getString("avatar"), 
				         result.getString("password"), result.getString("email"), 
				         result.getString("phone"), result.getString("gender"));
				list.add(user);
				
			}
			return list;
		}
	}
}
