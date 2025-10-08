package Lib.OrderProcessor;

import java.util.ArrayList;

import Lib.DataModels.Book;
import Lib.DataModels.BookShelf;
import Lib.StrategyPattern.PricingService;

public class Cart {
    // Rep
    ArrayList<Order> orders = new ArrayList<>();
    PricingService pricingService;
    BookShelf bookShelf;

    // Rep Invariant (RI):
    // ArrayList must not null
    // Same Book must not contain in Different Order

    public Cart(){
        this.orders = new ArrayList<>();
        this.pricingService = new PricingService();
        this.bookShelf = new BookShelf();
        CheckRep();
    }

    private void CheckRep(){
        if (orders == null) {
            throw new RuntimeException("Rep invariant violated!");
        }
        
        for (Order order : orders) {
            if (order.equals(order)) {
                throw new RuntimeException("Rep invariant violated!");
            }
        }
    }

    public void addBook(String bookISBN, int quantity){

        // ตรวจสอบ Quatity ที่ถูกต้อง
        if (quantity < 0) {
            throw new RuntimeException("Rep invariant violated!");
        }

        // ค้นหา Book จาก BookShelf ด้วยค่า ISBN
        Book book = bookShelf.findByISBN(bookISBN);
        if (book != null && quantity > 0) {
            if (book.ISBN().equals(bookISBN)) {
                
            }
        }
    }
    
    public void removeBook(String bookISBN){
        Book book = bookShelf.findByISBN(bookISBN);
        if (book != null) {
            for (Order order : orders) {
                if (order.getBook().ISBN().equals(bookISBN)) {
                    orders.remove(book);
                }
            }
        }
        CheckRep();
    }
    
    public double getTotalPrice(){
        double sum = 0;
        for (Order order : orders) {
            sum += pricingService.calculateBookPrice(order);
        }
        return sum;
    }
    
    public int getBookCount(){
        return orders.size();
    }
    
    public void clearOrder(){
        orders.clear();
    }
}
