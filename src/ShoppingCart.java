package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart  {
    static List<CartEntry> entries;

    public ShoppingCart() {
        this.entries = new ArrayList<>();
    }

    public static List<CartEntry> addProduct(List<Product> productList){
            List<CartEntry> entries= new ArrayList<>();
            
            while(true){
                
                Scanner sc = new Scanner(System.in);

                System.out.print("Would you like to add more!!!(Y/N)");
                String wordEnd = sc.nextLine();

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
                    if(productList.get(i).productID == productID){
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
            boolean _isEqual = productID == _entries.get(i).product.productID;
            System.out.println(_isEqual);
            if(_isEqual){
                entry.setQuantity(qty);
            }
        }

        return entries;
    }

    

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "entries=" + entries +
                '}';
    }

    public static void displayShoppingCart(List<CartEntry> entries){
        System.out.println("-------------------------------My Cart--------------------------------");
        for(int i = 0; i < entries.size(); i++){
            System.out.printf("productID: %d\t productName: %s\t price: %f\t quantity: %d\n",
                                entries.get(i).product.productID, entries.get(i).product.productName,
                                entries.get(i).product.price, entries.get(i).quantity );
        }
    }

    public static void displayTotalPrice(List<CartEntry> entries,Customer customer){
        System.out.println("-----------------------------Total Price------------------------------");
        int sum = 0;
        for(int i = 0; i < entries.size(); i++){
            sum += (entries.get(i).product.price * entries.get(i).quantity);
        }
        double discount = Customer.discount(customer);


        System.out.println("\t\t\t\t\t\t\tPrice: " + sum);
        System.out.println("\t\t\t\t\t\t\tDiscount: " + discount);
        System.out.println("\t\t\t\t\t\t\tTotal Price: " + calcTotalPrice(entries, customer));
        System.out.println();
        System.out.println();
    }

    public static double calcTotalPrice(List<CartEntry> entries,Customer customer){
        int sum = 0;
        for(int i = 0; i < entries.size(); i++){
            sum += (entries.get(i).product.price * entries.get(i).quantity);
        }
        double discount = Customer.discount(customer);

        double afterDiscount = sum * discount;
        double totalPrice = sum - afterDiscount;
        return totalPrice;
    }

}
