package src;

import src.Product;

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
