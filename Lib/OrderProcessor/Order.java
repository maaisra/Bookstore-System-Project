package Lib.OrderProcessor;

public class Order {

    //Rep
    private final Book book;
    private int quantity;

    public Order(Book book, int quantity){
        this.book = book;
        this.quantity = quantity;
        CheckRep();
    }

    public Book getBook(){
        return book;
    }

    public int getQuantity(){
        return quantity;
    }

    public void increaseQuantity(int amount){
        this.quantity += amount;
        CheckRep();
    }
    
    private void CheckRep(){
        if (book == null || quantity < 0) {
            throw new RuntimeException("Rep invariant violated!");
        }
    }
}