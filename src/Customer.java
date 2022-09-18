package src;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public class Customer extends User {
	private double totalSpending;
	private String membership;
	public String getMembership() {
		return membership;
	}

	public double getTotalSpending() {
		return totalSpending;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public void setTotalSpending(double totalSpending) {
		this.totalSpending = totalSpending;
	}



	public Customer(Map user) {
		super(
			(Integer) user.get("userID"),
			(String) user.get("username"),
			(String) user.get("name"),
			(String) user.get("age"),
			(String) user.get("email"),
			(String) user.get("phone"),
			(Boolean) user.get("isAdmin")
		);

		this.totalSpending = (Double) user.get("totalSpending");
		this.membership = (String) user.get("membership");
	}
	public void printInfo() {
		// formats and prints current user's information
		System.out.println(
			"\u001B[32m This is your personal information"
			+ "\n| Username: " + this.getUsername()
			+ "\n| Name: " + this.getFullName()
			+ "\n| Age: " + this.getAge()
			+ "\n| Email: " + this.getEmail()
			+ "\n| Phone: " + this.getPhone()
			+ "\n| Total spending: " + this.getTotalSpending()
			+ "\n| Membership: " + this.getMembership() + "\u001B[0m"
		);
	}

	public void showMyOrders() throws SQLException {
		String _query = "select * from Orders where buyerID = " + this.getUserId();
		ResultSet rs = Database.runQuery(_query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			System.out.println("\u001B[31m --------------------------------------- \u001B[0m");
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print("  |");
				String columnValue = rs.getString(i);
				System.out.print(" " + rsmd.getColumnName(i) + ": " + columnValue);
			}
			System.out.println("");
		}
	}
	public static double discount(Customer currentCustomer){
		if("silver".equals(currentCustomer.membership)){
			return 0.05;
		} else if ("gold".equals(currentCustomer.membership)){
			return 0.1;
		} else if("platinum".equals(currentCustomer.membership)){
			return 0.15;
		}
		return 0;
	}

}