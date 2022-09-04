package src;

import src.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Customer extends Person {
	private double totalSpending;
	private String membership;

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
		if(currentCustomer.membership.equals("silver")){
			return 0.05;
		} else if (currentCustomer.membership.equals("gold")){
			return 0.1;
		} else if(currentCustomer.membership.equals("platinum")){
			return 0.15;
		}

		return 0;	
	}
}