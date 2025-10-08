package lib.OrderProcessor;

import java.io.*;
import java.util.*;

public class ProductLoader {
    public static List<Product> loadFromCSV(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0];
                    String author = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String image = parts[3];
                    products.add(new Product(name, author, price, image));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
