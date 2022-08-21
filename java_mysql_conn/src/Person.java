package src;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person{
	public int userId;
	public String username;
	public String fullName;
	public String age;
	public String email;
	public String phone;

	public boolean isAdmin;

	public Person(int userId, String username, String fullName, String age, String email, String phone, boolean isAdmin) {
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.age = age;
		this.email = email;
		this.phone = phone;

		this.isAdmin = isAdmin;
	}

	public void setFullName(String fullName) {this.fullName = fullName;}
	public void setAge(String age) {this.age = age;}
	public void setEmail(String email) { this.email = email;}
	public void setPhone(String phone) {this.phone = phone;}

	public static Map login() {
		Map user = new HashMap();

		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------");
		System.out.print("Enter username: ");
//			String username = scanner.nextLine().trim();
		String username = "hoang";
		System.out.println("");
		System.out.print("Enter password: ");
//			String password = scanner.nextLine().trim();
		String password = "123";

		try {
			String query = String.format("select * from person where username='%s' and password='%s'", username, password);
			ResultSet rs = Database.runQuery(query);
			// check if query returns anything
			if (isResultSetEmpty(rs)) {
				System.out.println("Wrong username or password, please try again!");
				return null;
			}
			while (rs.next()) {
				user.put("userID", rs.getInt("userID"));
				user.put("username", rs.getString("username"));
				user.put("password", rs.getString("password"));
				user.put("email", rs.getString("email"));
				user.put("name", rs.getString("f_Name"));
				user.put("age", rs.getString("age"));
				user.put("email", rs.getString("email"));
				user.put("phone", rs.getString("phone"));
				user.put("totalSpending", rs.getDouble("totalSpending"));
				user.put("membership", rs.getString("membership"));
				user.put("isAdmin", rs.getBoolean("isAdmin"));
			}
		} catch (SQLException e) {
			System.out.println("Login error");
		}

		return user;
	}

	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		// from https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results/15750832#15750832
		return (!rs.isBeforeFirst() && rs.getRow() == 0);
	}

	public static void showActionMenu() {

	}
}

