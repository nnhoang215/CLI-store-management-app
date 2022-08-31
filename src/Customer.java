package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

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

	public static void showActionMenu() {
		System.out.println("\n ################### Action Menu ###################");
		System.out.println("1. Show product menu");
		System.out.println("2. Search product");
		System.out.println("3. See cart");
		System.out.println("4. Checkout");

		System.out.print("Choose your action (1|2|3|4): ");
		Scanner scanner = new Scanner(System.in);
		int action = scanner.nextInt();
		switch (action) {
			case 1:
				Product.showAllProducts(Main.productList);
				break;
			case 2:
				System.out.println("-> Product.searchProduct");
				break;
			case 3:
				System.out.println("-> Cart.viewCart");
				break;
			case 4:
				System.out.println("-> Cart.checkout");
				break;
			default:
				System.out.println("-> This action is unavailable. Try again!");
		}
	}

}
