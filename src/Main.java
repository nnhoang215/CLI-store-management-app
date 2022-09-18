package src;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Main {
	static Map result;
	static Customer currentCustomer;
	static Admin currentAdmin;
	static List<Product> productList = new ArrayList<>();
	static List<CartEntry> entries = new ArrayList<>();
	public static void main(String[] args) throws SQLException {
		// ########## SET UP ###############
		String number = " ";
		Database db = new Database(); // db connection

		// ########## START PROGRAM #########
		while(!number.equals("3")){
			productList = Product.getAllProducts(); // products
			System.out.println("COSC2081 GROUP ASSIGNMENT ");
			System.out.println( "STORE ORDER MANAGEMENT SYSTEM"); 
			System.out.println( "Instructor: Mr. Minh Vu");
			System.out.println( "Group: Group 15"); 
			System.out.println( "s3926555, Nguyen Nhat Hoang");
			System.out.println("s3928848, Bui Quang Kien");
			System.out.println( "s8886969, Nguyen Gia Thanh");

			System.out.println("-----------------------------------");
			System.out.println();
			System.out.println("Press 1 to Sign In.");
			System.out.println("Press 2 to Sign Up.");
			System.out.println("Press 3 to Exit.");
			Scanner sc = new Scanner(System.in);
			System.out.print("Press any number here: ");
			String letter = sc.nextLine().toUpperCase().trim();
			number = letter;

			switch(letter){
				case "1":
					displayAMain();
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					break;
				case "2":
					showSignUp();
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					System.out.println("-------------------------------");
					break;
				default:
					break;
			}
		}
	}
	public static void displayAMain() throws SQLException {
		Database db = new Database(); // db connection
		do {
			result = User.login();
		} while (result == null);
		if ((Boolean) result.get("isAdmin")) { // as an admin
			currentAdmin = new Admin(result);
			String number = " ";
			while(!number.equals("6")){
				productList = Product.getAllProducts(); // products
				System.out.println("Press (1) to show all products.         Press (2) to update product's price.  Press (3) to add new products.");
				System.out.println("Press (4) to show orders by customerID. Press (5) to update order status      Press (6) to exit to main menu");
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter any number: ");
				String letter = sc.nextLine().toUpperCase().trim();
				number = letter;
				switch(letter){
					case "1":
						Product.showAllProducts(productList);
						break;
					case "2":
						Product.showAllProducts(productList);
						System.out.println("---------------------------------");
						currentAdmin.updatePrice(productList);
						break;
					case "3":
						currentAdmin.addNewProduct();
						break;
					case "4":
						Order.searchOrderByCustomerID();
						break;
					case "5":
						Order.updateOrderStatus();
						break;
					case "6":
					default:
						break;
				}
			}
		} else { // as a customer
			currentCustomer = new Customer(result);
			String input = "";
			Scanner sc = new Scanner(System.in);
			while(!input.equals("8")){
				System.out.println("Press (0) to show your information.                    Press (1) to show all products.            Press (2) to sort product by price.");
				System.out.println("Press (3) to sort product by category.                 Press (4) to add product to Shopping Cart. Press (5) to see your Shopping Cart.");
				System.out.println("Press (6) to checkout from your current Shopping Cart. Press (7) to search for order.             Press (8) to exit to main menu.");
				System.out.print("Enter any number: ");
				input = sc.nextLine().toUpperCase().trim();

				switch(input){
					case "0":
						currentCustomer.printInfo();
						break;
					case "1":
						Product.showAllProducts(productList);
						break;
					case "2":
						Product.showAllProducts(Product.sortByPrice(productList, "ascend"));
						break;
					case "3":
						Product.categoryFilter(productList);
						break;
					case "4":
						Product.showAllProducts(productList);
						entries = ShoppingCart.addProduct(productList, entries);
						break;
					case "5":
						ShoppingCart.displayShoppingCart(entries);
						ShoppingCart.displayTotalPrice(entries, currentCustomer);
						break;
					case "6":
						ShoppingCart.displayShoppingCart(entries);
						ShoppingCart.displayTotalPrice(entries, currentCustomer);
						ShoppingCart.checkout(entries, currentCustomer);
						break;
					case "7":
						Order.searchOrderByOrderID();
						break;
					default:
						System.out.println("Cannot recognize command, try again!");
						break;
				}
			}
		}
	}

	public static void showSignUp(){
		User.signUp();
	}
}


