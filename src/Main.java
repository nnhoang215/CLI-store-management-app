import java.util.*;

public class Main {
	static Map result;
	static Customer currentCustomer;
	static Admin currentAdmin;
	static List<Product> productList = new ArrayList<>();
	static List<CartEntry> entries = new ArrayList<>();
	public static void main(String[] args) {

		// ########## START PROGRAM #########
		while(true){
			Database db = new Database(); // db connection
			productList = Product.getAllProducts(); // products
			System.out.println("COSC2081 GROUP ASSIGNMENT ");
			System.out.println( "STORE ORDER MANAGEMENT SYSTEM"); 
			System.out.println( "Instructor: Mr. Minh Vu");
			System.out.println( "Group: Group 15"); 
			System.out.println( "sXXXXXXX, Student Name"); 
			System.out.println("sXXXXXXX, Student Name"); 
			System.out.println( "sXXXXXXX, Student Name");

			System.out.println("-----------------------------------");
			System.out.println();
			System.out.println("Press A to Sign In.");
			System.out.println("Press B to Sign Up.");
			System.out.println("Press C to Exit.");
			Scanner sc = new Scanner(System.in);
			System.out.print("Press any letters here: ");
			String letter = sc.nextLine().toUpperCase().trim();
			if(letter.equals("A")){
				displayAMain();
				System.out.println("-------------------------------");
				System.out.println("-------------------------------");
				System.out.println("-------------------------------");
			} else if(letter.equals("B")){
				displayBmain();
				System.out.println("-------------------------------");
				System.out.println("-------------------------------");
				System.out.println("-------------------------------");
			} else if(letter.equals("C")){
				break;
			} else {
				continue;
			}
	}
}
	public static void displayAMain(){
			do {
			result = Person.login();
		} while (result == null);
		boolean running = true;
		if ((Boolean) result.get("isAdmin")) { // as an admin
			currentAdmin = new Admin(result);
			while(true){
				Database db = new Database(); // db connection
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
					Product.sortByPrice(productList, "ascend");
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

	public static void displayBmain(){
		Person.signUp();
	}
}


