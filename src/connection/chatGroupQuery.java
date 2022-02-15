package connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import models.chatGroups;

public class chatGroupQuery {
	public connectToDB connect = new connectToDB();
	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet result = null;
	
	
	public void addChat(chatGroups chat) throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return;
		}
		pst = (PreparedStatement) con.prepareStatement("insert into chat_group (user_id, type, name, content, date) values (?, ?, ?, ?, ?)");
		pst.setInt(1, chat.getU1());
		pst.setInt(2, chat.getType());
		pst.setString(3, chat.getName());
		pst.setString(4, chat.getContent());
		pst.setDate(5, (Date) chat.getTime());
		pst.executeUpdate();
	}
	
	public List<chatGroups> loadChat() throws SQLException {
		con = connect.getConnection();
		if (con == null) {
			return null;
		}
		pst = (PreparedStatement) con.prepareStatement("select * from chat_group");
		result = pst.executeQuery();
		if (!result.isBeforeFirst()) {
			return null;
		}
		else {
			List<chatGroups> listChat = new ArrayList<chatGroups>();
			while (result.next()) {
				chatGroups chat = new chatGroups(result.getInt("id"), result.getInt("user_id"),
						               result.getInt("type"), result.getString("name"), result.getString("content"), result.getDate("date"));
				listChat.add(chat);
			}
			return listChat;
		}
	}
}
