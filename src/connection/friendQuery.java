package connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import models.friends;

public class friendQuery {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	public void addRFriends(friends addf) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into request_friends (user_1, user_2) values (?, ?)");
		pst.setInt(1, addf.getU1());
		pst.setInt(2, addf.getU2());
		pst.executeUpdate();
	}
	
	public void removeRFriends(friends addf) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return;
		}
		pst = (PreparedStatement) con.prepareStatement("delete from request_friends where user_1 = ? and user_2 = ?");
		pst.setInt(1, addf.getU1());
		pst.setInt(2, addf.getU2());
		pst.executeUpdate();
	}
	
	public void addFriends(friends addf) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into friends (user_1, user_2) values (?, ?)");
		pst.setInt(1, addf.getU1());
		pst.setInt(2, addf.getU2());
		pst.executeUpdate();
	}
	
	public List<friends> listMF() throws SQLException{
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from friends");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			List<friends> list = new ArrayList<friends>();
			while (result.next()) {
				friends friend = new friends(result.getInt("user_1"), result.getInt("user_2"));
				list.add(friend);
			}
			return list;
		}
	}
	
	public List<friends> listRF() throws SQLException{
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from request_friends");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			List<friends> list = new ArrayList<friends>();
			while (result.next()) {
				friends friend = new friends(result.getInt("user_1"), result.getInt("user_2"));
				list.add(friend);
			}
			return list;
		}
	}
}
