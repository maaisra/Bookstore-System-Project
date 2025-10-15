package Lib.User;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "admin");
    }

    public void manageBooks() {
        System.out.println("Admin managing books...");
    }

    public void viewSalesReport() {
        System.out.println("Admin viewing sales report...");
    }
}
