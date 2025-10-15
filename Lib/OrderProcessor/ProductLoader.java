package Lib.OrderProcessor;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/*
 * For changing and viewing products in Admin and User interfaces
 */
public class ProductLoader {

    private JPanel productContainer;
    private JScrollPane scrollPane;

    public ProductLoader(JPanel productContainer, JScrollPane scrollPane) {
        this.productContainer = productContainer;
        this.scrollPane = scrollPane;

        productContainer.setLayout(new GridLayout(0, 2, 20, 20)); 
        loadProducts("lib/OrderProcessor/products.csv");
    }

    private void loadProducts(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String author = parts[1];
                String price = parts[2];
                String imagePath = parts[3];

                JPanel productPanel = createProductPanel(name, author, price, imagePath);
                productContainer.add(productPanel);
            }

            productContainer.revalidate();
            productContainer.repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel createProductPanel(String name, String author, String price, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 250));
        panel.setBackground(new Color(204, 204, 204));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setLayout(null); 

        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image scaled = icon.getImage().getScaledInstance(190, 230, Image.SCALE_SMOOTH);
        JLabel pic = new JLabel(new ImageIcon(scaled));
        pic.setBounds(10, 10, 190, 230);

        JLabel lblName = new JLabel("Name: " + name);
        lblName.setBounds(205, 10, 180, 25);

        JLabel lblAuthor = new JLabel("Author: " + author);
        lblAuthor.setBounds(205, 40, 180, 25);

        JLabel lblPrice = new JLabel("Price: " + price);
        lblPrice.setBounds(205, 70, 180, 25);

        JButton btnAdd = new JButton("ADD TO CART");
        btnAdd.setBounds(205, 200, 210, 30);

        panel.add(pic);
        panel.add(lblName);
        panel.add(lblAuthor);
        panel.add(lblPrice);
        panel.add(btnAdd);

        return panel;
    }
}