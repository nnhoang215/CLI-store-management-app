import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {
	int productID;
	String productName;
	String category;
	Double price;
	int quantity;

	public Product(int id, String name, String category, Double price, int quantity) {
		this.productID = id;
		this.productName = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(int id, String name, double price){
		this.productID = id;
		this.productName = name;
		this.price = price;
	}

	@Override
	public String toString(){
		String productInfo = String.format(
			"---------------------------------------" +
			" \n |ID: %d\t name: %s\t category: %s\t price: %.2f\t quantity: %d", productID, productName, category, price, quantity
		);
		return productInfo;
	}

	static List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = Database.runQuery("select * from product");
			while(rs.next()) {
				Product product = new Product(
					rs.getInt("productID"),
					rs.getString("name"),
					rs.getString("category"),
					rs.getDouble("price"),
					rs.getInt("quantity")
				);
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println("getAllProduct failed");
			throw new RuntimeException(e);
		}
		return products;
	}

	 public static void showAllProducts(List<Product> productList) {
		System.out.println("GROUP 15's PRODUCTS FOR SALE:");
		for (int i = 0; i < productList.size(); i++) {
			System.out.println(productList.get(i));
		}
	}

	public static List<Product> sortByPrice(List<Product> productList, String direction){
		List<Product> _productList = new ArrayList<>();
		_productList.addAll(productList);
		int len = _productList.size();
		for (int i=0;i < len-1;++i){

			for(int j=0; j < len-i-1; ++j){
				boolean _ascendCondition = _productList.get(j+1).price < _productList.get(j).price;
				boolean _descendCondition = _productList.get(j+1).price > _productList.get(j).price;
				if(direction == "ascend" ? _ascendCondition : _descendCondition){
					Product swap = _productList.get(j);
					_productList.set(j, _productList.get(j + 1));
					_productList.set(j+1,swap);
				}
			}
		}
		return _productList;
	}

	public static void categoryFilter(List<Product> productList){

		System.out.println("Press A to see Drinks");
		System.out.println("Press B to see Foods");

		Scanner sc = new Scanner(System.in);
		System.out.print("Choose category: ");
		String category = sc.nextLine().toUpperCase().trim();
		if(category.equals("A")){
			category = "Drink";
		} else if(category.equals("B")){
			category = "Food";
		}

		List<Product> _productList = new ArrayList<>();
		_productList.addAll(productList);
		String noSpaceString = category.replaceAll("\\s", "");
		List<Product> _categoryFilteredList = new ArrayList<>();

		int len = _productList.size();
			for(int j = 0; j < len ; j++){
				boolean _isCategory = noSpaceString.equals(_productList.get(j).category);
				if(_isCategory){
					_categoryFilteredList.add(_productList.get(j));
				}
			}

		showAllProducts(_categoryFilteredList);

	}

	

	
}
