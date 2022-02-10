package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class connectToDB {
public Connection connect = null;
	
	public Connection getConnection() throws SQLException {
		
		String url  = "jdbc:mysql://localhost:3306/chat_me?useSSL=false";
		String user = "root";
		String pass = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager.getConnection(url,user,pass);
		}
		catch (ClassNotFoundException e){
			System.out.println("Error! Can't connect to database!");
		}
		return connect;
	}
}
