package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
		static Connection con;
		public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/repairing", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}