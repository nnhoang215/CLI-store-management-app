package src;
import java.sql.*;

public class Database {
	private Connection conn;
	static Statement statement;

	public Database() {
		try {
//			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_for_java", "root", "password");
//			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_for_java", "root", "Alpha12345@");
			this.conn = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6519106", "sql6519106", "SNpvDH6GlI");
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
			e.printStackTrace();
			System.out.println("Update failed. Try Again");
		}
	}
}