package src;
import java.sql.*;

public class Database {
	private Connection conn;
	static Statement statement;

	public Database() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_for_java", "root", "Alpha12345@");
			System.out.println(conn);
			this.statement = conn.createStatement();
			System.out.println("Database connection: success");
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Database connection: failed");
		}
	}
	public static ResultSet runQuery(String query) throws SQLException {
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
}
