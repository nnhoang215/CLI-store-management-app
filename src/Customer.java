package src;

import java.sql.ResultSet;
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