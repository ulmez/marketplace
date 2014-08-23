package se.gozacke.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost/floggit";
		String user = "flogger";
		String pass = "flogged";
		Class.forName(jdbcDriver);
		Connection conn = DriverManager.getConnection(dbUrl, user, pass);
		return conn;
	}
}