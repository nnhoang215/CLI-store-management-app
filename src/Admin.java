package src;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import javax.xml.crypto.Data;

public class Admin extends User{
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

	public void addNewProduct() throws SQLException {
		// add new product
		System.out.println("Please add new product!!!");
		System.out.println("----------------------------");

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter product's name: ");
		String name = sc.nextLine();
		System.out.println("Select Category: ");
		String category = " ";
		System.out.println("Press (1) for Food.");
		System.out.println("Press (2) for Drink.");
		System.out.print("Press any number: ");
		String inputCate = sc.nextLine().trim();
		if(inputCate.equals("1")){
			category = "Food";
		} else if(inputCate.equals("2")){
			category = "Drink";
		}
		if (inputCate.equals("1") || inputCate.equals("2")) {
			System.out.print("Enter price: ");
			double price = sc.nextDouble();
			System.out.print("Enter quantity: ");
			int quantity = sc.nextInt();

			String query = String.format(
					"INSERT INTO Product(name,category,price,quantity) VALUES (\"%s\",\"%s\",%f,%d)"
					, name, category, price, quantity
			);
			Database.updateQuery(query);
		} else {
			System.out.println("Wrong input format, try again");
		}
	}

	public List<Product> updatePrice(List<Product> productList) throws SQLException {
		// update price for product
		System.out.println("Please update new price for your products!!!");
		System.out.println("--------------------------------------------");

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter product's ID: ");
		int productID = sc.nextInt();
		System.out.print("Enter new price: ");
		double price = sc.nextDouble();

		String query = String.format("UPDATE Product SET price = %f WHERE productID = %d", price, productID);
		Database.updateQuery(query);

		productList = Product.getAllProducts();

		return productList;
	}

	public static void displayAllUsers() throws SQLException {
		String _query = "select * from Users";
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

	public static void displayAllOrders() throws SQLException {
		String _query = "select * from Orders";
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

	public static void searchOrderByCustomerID() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter customer ID number: ");
		int customerID = sc.nextInt();
		ResultSet rs = Database.runQuery("" +
				"select Orders.orderID as orderID, " +
				"buyerID, " +
				"orderStatus, " +
				"discount, " +
				"totalAmount, " +
				"OrderDetails.quantity as quantity, " +
				"OrderDetails.productID as productID, " +
				"name, " +
				"category, " +
				"price " +
				"from Orders join" +
				" OrderDetails on Orders.orderID = OrderDetails.orderID join Product on OrderDetails.productID = Product.productID" +
				" where Orders.buyerID =" + customerID);
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
}