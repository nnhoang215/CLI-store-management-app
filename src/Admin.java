package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.crypto.Data;

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

	public void addNewProduct(){
		System.out.println("Please add new product!!!");
		System.out.println("----------------------------");

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter product's name: ");
		String name = sc.nextLine();
		System.out.print("Enter category: ");
		String category = sc.nextLine();
		System.out.print("Enter price: ");
		double price = sc.nextDouble();
		System.out.print("Enter quantity: ");
		int quantity = sc.nextInt();

		String query = String.format("INSERT INTO Product(name,category,price,quantity) VALUES (\"%s\",\"%s\",%f,%d)"
									, name, category, price, quantity);
		Database.updateQuery(query);
	}

	public List<Product> upDatePrice(List<Product> productList){
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

	
}