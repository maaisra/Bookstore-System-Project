package Lib.OrderProcessor;

public class Product {
    private String name;
    private String author;
    private double price;
    private String imagePath;

    public Product(String name, String author, double price, String imagePath) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.imagePath = imagePath;
    }
  
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
}