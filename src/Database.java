package src;

import java.sql.*;

public class Database {
	private Connection conn;
	static Statement statement;

	public Database() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_for_java", "root", "Alpha12345@");
			Database.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet runQuery(String query) throws SQLException {
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}

	public static void updateQuery(String query){
		try {
			statement.executeUpdate(query);
			System.out.println("Updated successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update failed!");
		}
	}
}