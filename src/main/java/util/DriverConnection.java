package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DriverConnection {

	public void register() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/ooj4", "root", "root");
		return conn;
	}

}
