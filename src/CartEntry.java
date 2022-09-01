package src;

public class CartEntry {
    Product product;
    int quantity;


    public CartEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
     public void setQuantity(int qty){
         if(qty>0){
            this.quantity = qty;
         }else {
            System.out.println("Invalid Input");
         }
     }

     public void addProduct(){
        this.quantity++;
     }

    @Override
    public String toString() {
        return "CartEntry{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
