package connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import models.chats;

public class chatQuery {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	
	public void addChat(chats chat) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into chats (user_id1, user_id2, type, name, content, date) values (?, ?, ?, ?, ?, ?)");
		pst.setInt(1, chat.getU1());
		pst.setInt(2, chat.getU2());
		pst.setInt(3, chat.getType());
		pst.setString(4, chat.getName());
		pst.setString(5, chat.getContent());
		pst.setDate(6, (Date) chat.getTime());
		pst.executeUpdate();
	}
	
	public List<chats> loadChat() throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from chats");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			List<chats> listChat = new ArrayList<chats>();
			while (result.next()) {
				chats chat = new chats(result.getInt("id"), result.getInt("user_id1"), result.getInt("user_id2"), 
						               result.getInt("type"), result.getString("name"), result.getString("content"), result.getDate("date"));
				listChat.add(chat);
			}
			return listChat;
		}
	}
}
