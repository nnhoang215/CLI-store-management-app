package src;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Order {
    int orderID;
    String customerName;
    int customerID;
    String status;
    Product products;
    String discount;
    float totalAmount;

    public Order(int orderID, String customerName, int customerID, String status, Product products, String discount, float totalAmount) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.customerID = customerID;
        this.status = status;
        this.products = products;
        this.discount = discount;
        this.totalAmount = totalAmount;
    }

    public static void updateOrderStatus() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter orderID number: ");
        int orderID = sc.nextInt();
        System.out.print("Update status to: ");
        Scanner sc1 = new Scanner(System.in);
        String status = sc1.nextLine();

        String query = String.format("UPDATE Orders SET orderStatus = '%s' WHERE orderID = %d", status, orderID);
        Database.updateQuery(query);
    }
    public static void searchOrderByOrderID() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter order ID number: ");
        int orderID = sc.nextInt();
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
                " where Orders.orderID =" + orderID);
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
        System.out.println("\u001B[31m --------------------------------------- \u001B[0m");
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
