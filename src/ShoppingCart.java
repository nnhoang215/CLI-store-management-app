package src;
import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ShoppingCart  {
    static List<CartEntry> entries;

    public ShoppingCart() {
        this.entries = new ArrayList<>();
    }

    public static List<CartEntry> addProduct(List<Product> productList, List<CartEntry> entries){

            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.print("Would you like to add more!!!(Y/N): ");
                String wordEnd = sc.nextLine().toUpperCase().trim();

                if(wordEnd.equals("N")){
                    break;
                } else {
                    System.out.print("Enter product ID: ");
                    int productID = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    List<Product> _productList = new ArrayList<>();
                    _productList.addAll(productList);
                    int len = _productList.size();
                    for(int i = 0; i < len; i++){
                    if(productList.get(i).getProductID() == productID){
                        CartEntry newEntry = new CartEntry(productList.get(i), 1);
                        newEntry.setQuantity(qty);
                        entries.add(newEntry);
                        
                    }
                }
            }
        }
        return entries;
    } 

    public static List<CartEntry> changeQuantity(List<CartEntry> entries){
        List<CartEntry> _entries = new ArrayList<>();
        _entries.addAll(entries);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productID = sc.nextInt();
        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();
        
        int len = entries.size();

        for(int i = 0; i < len; i++){
            CartEntry entry = entries.get(i);
            boolean _isEqual = productID == _entries.get(i).product.getProductID();
            System.out.println(_isEqual);
            if(_isEqual){
                entry.setQuantity(qty);
            }
        }

        return entries;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "entries=" + entries + '}';
    }

    public static void displayShoppingCart(List<CartEntry> entries){
        System.out.println("-------------------------------My Cart--------------------------------");
        for(int i = 0; i < entries.size(); i++){
            System.out.printf("productID: %d\t productName: %s\t price: %.2f\t quantity: %d\n",
                entries.get(i).product.getProductID(), entries.get(i).product.getProductName(),
                entries.get(i).product.getPrice(), entries.get(i).getQuantity()
            );
        }
    }

    public static void displayTotalPrice(List<CartEntry> entries,Customer customer){
        System.out.println("-----------------------------Total Price------------------------------");
        int sum = 0;
        for(int i = 0; i < entries.size(); i++){
            sum += (entries.get(i).product.getPrice() * entries.get(i).getQuantity());
        }
        double discount = Customer.discount(customer);


        System.out.println("\t\t\t\t\t\t\tPrice: " + sum);
        System.out.println("\t\t\t\t\t\t\tDiscount: " + discount);
        System.out.printf("\t\t\t\t\t\t\tTotal Price: %.2f" ,calcTotalPrice(entries, customer));
        System.out.println();
        System.out.println();
    }

    public static double calcTotalPrice(List<CartEntry> entries, Customer customer){
        int sum = 0;
        for(int i = 0; i < entries.size(); i++){
            sum += (entries.get(i).product.getPrice() * entries.get(i).getQuantity());
        }
        double discount = Customer.discount(customer);

        double afterDiscount = sum * discount;
        double totalPrice = sum - afterDiscount;
        return totalPrice;
    }

    public static void updateQuantity(List<CartEntry> entries){
        for (int i=0; i< entries.size();i++){
            int productID = entries.get(i).product.getProductID();
            int changedQty = (entries.get(i).product.getQuantity()-entries.get(i).getQuantity());

            String query = String.format("UPDATE test_for_java.product t SET t.quantity = %d WHERE t.productID = %d",
                    changedQty,productID);
            Database.updateQuery(query);
        }
    }

    public static void checkout(List<CartEntry> entries,Customer customer){
        double totalPrice = calcTotalPrice(entries, customer);
        double totalSpending = customer.getTotalSpending();
        totalSpending += totalPrice;
        customer.setTotalSpending(totalSpending);
        if (totalSpending >= 5000000 && totalSpending<10000000){
            customer.setMembership("silver");
        } else if (totalSpending >= 10000000 && totalSpending<25000000) {
            customer.setMembership("gold");
        } else if (totalSpending >= 25000000) {
            customer.setMembership("platinum");
        } else {
            customer.setMembership(null);
        }
        // add to product
        // sort by orderID desc
        // select orderID
        // use selected ID to insert to orderDetails

        String updateOrder = String.format("INSERT INTO Orders(buyerID, orderStatus,discount, totalAmount) VALUES (%d, '%s', '%s', %f)",
                customer.getUserId(),"delivered",customer.getMembership(),totalPrice);
        Database.updateQuery(updateOrder);

        String selectOrderID = String.format("select * from Orders order by orderID DESC limit 1;");
        int orderID = 0;
        try{
            ResultSet rs = Database.runQuery(selectOrderID);
            rs.next();
            orderID = rs.getInt("orderID");

            for (CartEntry entry:entries){
                int productID = entry.product.getProductID();
                int quantity = entry.getQuantity();
                String addOrderDetail = String.format("INSERT INTO OrderDetails(orderID,productID,quantity) VALUES(%d,%d,%d)",
                        orderID,productID,quantity);
               Database.updateQuery(addOrderDetail);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
//                customer.getUserId(),"Completed","null",customer.getMembership(),totalPrice);
        String updateMembership = String.format("UPDATE Users t SET t.totalSpending = '%f', t.membership = '%s' WHERE t.userID = %d",
                customer.getTotalSpending(),customer.getMembership(),customer.getUserId());
//        updateQuantity(entries);
//        // add a function that decrease product quantity after a successful checkout
//        // add another condition to check if there are stock in the db
//        Database.updateQuery(query);
        Database.updateQuery(updateMembership);
        System.out.print("Checkout successfully!!!\n"
                +"Your order ID is: " + orderID + "\n"
                +"-----------------------------\n"
                +"-----------------------------\n"
                +"-----------------------------\n"
        );

    }

}
