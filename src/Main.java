package src;

import java.util.*;

public class Main {
	static Map result;
	static Customer currentCustomer;
	static Admin currentAdmin;
	static List<Product> productList = new ArrayList<>();
	static List<CartEntry> entries = new ArrayList<>();
	public static void main(String[] args) {
		Database db = new Database(); // db connection
		productList = Product.getAllProducts(); // products
		System.out.print(
			"COSC2081 GROUP ASSIGNMENT\n"
			+ "STORE ORDER MANAGEMENT SYSTEM\n"
			+ "Instructor: Mr. Minh Vu\n"
			+ "Group: 15\n"
			+ "s3926555, Nguyen Nhat Hoang\n"
			+ "sXXXXXXX, Student Name\n"
			+ "sXXXXXXX, Student Name\n"
		);
		// ########## START PROGRAM #########
		while(true){
			System.out.println("-----------------------------------\n"
				+ "Press 1 to Sign In.\n"
				+ "Press 2 to Sign Up\n"
				+ "Press X to Exit\n"
			);
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter here: ");
			String letter = sc.nextLine().trim();
			switch (letter){
				case "1":
					showSignIn();
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
				case "2":
					showSignUp();
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
				case "X":
				case "x":
					break;
				default:
					System.out.println("We didn't recognize the command, try again!");
			}
		}
	}
	public static void showSignIn(){
		do {
			result = Person.login();
		} while (result == null);
		if ((Boolean) result.get("isAdmin")) { // as an admin
			currentAdmin = new Admin(result);
			while(true){
				productList = Product.getAllProducts(); // products
				System.out.println("Press S to show all products.");
				System.out.println("Press N to update product's price.");
				System.out.println("Press M to add new products.");
				System.out.println("Press E to exit to main menu");
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter any letter: ");
				String letter = sc.nextLine().toUpperCase().trim();
				if(letter.equals("S")){
					Product.showAllProducts(productList);
				} else if(letter.equals("N")){
					currentAdmin.upDatePrice(productList);
				} else if(letter.equals("M")){
					currentAdmin.addNewProduct();
				} else if(letter.equals("E")){
					break;
				} else {
					continue;
				}
			}
		} else { // as a customer
			currentCustomer = new Customer(result);
			while(true){
				System.out.println("Press S to show all products.");
				System.out.println("Press N to sort product by price.");
				System.out.println("Press M to sort product by category.");
				System.out.println("Press B to add product to Shopping Cart.");
				System.out.println("Press L to see your Shopping Cart.");
				System.out.println("Press E to exit to main menu.");
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter any letter: ");
				String letter = sc.nextLine().toUpperCase().trim();
				if(letter.equals("S")){
					Product.showAllProducts(productList);
				} else if(letter.equals("N")){
					Product.showAllProducts(Product.sortByPrice(productList, "ascend"));
				} else if(letter.equals("M")){
					Product.categoryFilter(productList);
				} else if(letter.equals("E")){
					break;
				} else if(letter.equals("B")){
					Product.showAllProducts(productList);
					entries = ShoppingCart.addProduct(productList);
				} else if(letter.equals("L")){
					ShoppingCart.displayShoppingCart(entries);
					ShoppingCart.displayTotalPrice(entries, currentCustomer);
				}
			}
		}
	}

	public static void showSignUp(){
		Person.signUp();
	}
}


