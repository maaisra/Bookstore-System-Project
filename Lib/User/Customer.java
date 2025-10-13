package Lib.User;

public class Customer extends User {
    public Customer(String username, String password) {
        super(username, password, "customer");
    }

    public void browseBooks() {
        System.out.println("Customer browsing books...");
    }

    public void placeOrder() {
        System.out.println("Customer placing order...");
    }
}
