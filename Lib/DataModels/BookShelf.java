package Lib.DataModels;
import java.util.ArrayList;

public class BookShelf {
    
    // Rep
    ArrayList<Book> books;

    // Rep Invariant(RI):
    // Arraylist must not null.

    public BookShelf(){
        books = new ArrayList<>();
    }

    private void CheckRep(){
        for (Book book : books) { // check null
            if (book == null) {
                throw new RuntimeException("Rep invariant violated!");
            }
            if (books.equals(books)) { // check book is the same or not
                throw new RuntimeException("Rep invariant violated!");
            }
        }
    }

    public void addBook(Book book){
        books.add(book);
        CheckRep();
    }

    public void removeBook(Book book){
        books.remove(book);
        CheckRep();
    } 

    public Book findByISBN(String bookISBN){
        for (Book book : books) {
            if (book.ISBN().equals(bookISBN)) {
                return book;
            }
        }
        CheckRep();
        return null;
    }
}
