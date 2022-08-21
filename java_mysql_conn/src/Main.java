package src;

import java.util.ArrayList;
import java.util.*;

public class Main {
	static Map result;
	static Customer currentCustomer;
	static Admin currentAdmin;
	static List<Product> productList = new ArrayList<>();

	public static void main(String[] args) {
		// ########## SET UP #########
		Database db = new Database(); // db connection
		productList = Product.getAllProducts(); // products

		do {
			result = Person.login();
		} while (result == null);

		// ########## START PROGRAM #########
		boolean running = true;
		if ((Boolean) result.get("isAdmin")) { // as an admin
			currentAdmin = new Admin(result);
		} else { // as a customer
			currentCustomer = new Customer(result);
			Product.showAllProducts(productList);
//			while (running) {
//				Person.showActionMenu();
//			}
			Product.showAllProducts(Product.sortByPrice(productList, "ascend"));
		}
	}
}


