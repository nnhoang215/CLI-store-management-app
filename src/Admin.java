package src;

import java.util.Map;
import java.util.Scanner;

public class Admin extends Person{
	public Admin(Map user){
		super(
				(Integer) user.get("userID"),
				(String) user.get("username"),
				(String) user.get("name"),
				(String) user.get("age"),
				(String) user.get("email"),
				(String) user.get("phone"),
				(Boolean) user.get("isAdmin")
		);
	}
	public static void showActionMenu() {
		System.out.println("\n ################### Action Menu ###################");

		System.out.println("1. Show product menu");
		System.out.println("2. Search product");
		System.out.println("3. Add product");
		System.out.println("4. Delete product");
		System.out.println("5. See orders");
		System.out.println("6. Update orders");

		Scanner _scanner = new Scanner(System.in);
		System.out.print("Choose your action (1|2|3|4|5|6): ");
		int action = _scanner.nextInt();
		switch (action) {
			case 1:
				Product.showAllProducts(Main.productList);
				break;
			case 2:
				System.out.println("-> Search product");
				break;
			case 3:
				System.out.println("-> Add product");
				break;
			case 4:
				System.out.println("-> Delete product");
				break;
			case 5:
				System.out.println("See orders");
				break;
			case 6:
				System.out.println("Update orders");
				break;
			default:
				System.out.println("This action is not available. Try again!");
		}
	}
}
