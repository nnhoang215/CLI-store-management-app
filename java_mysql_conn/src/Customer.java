package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Customer extends Person{
	public double totalSpending;
	public String membership;

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

}
