import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame {

    private JPanel recommendPanel;
    private JButton returnBtn, nextBtn;
    private JButton[] pageButtons;
    private String[] products = {
            "Lapvona: Town of Sinner",
            "Book of Shadows",
            "Magic Tales",
            "Adventure Quest",
            "Mystery Island",
            "Legendary Heroes",
            "Epic Journey",
            "Dragon Slayer",
            "Wizard's Code",
            "Treasure Hunt"
    };
    private String[] prices = {
            "284.05 Baht", "350.00 Baht", "420.50 Baht", "199.99 Baht", "310.00 Baht",
            "450.00 Baht", "380.00 Baht", "500.00 Baht", "275.00 Baht", "390.00 Baht"
    };
    private int currentPage = 1;
    private int itemsPerPage = 6;
    private int totalPages;

    public Homepage() {
        setTitle("Homepage");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        totalPages = (int) Math.ceil((double) products.length / itemsPerPage);

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Hayha Book Store");
        titleLabel.setFont(new Font("TH Niramit AS", Font.BOLD, 36));
        JComboBox<String> comboBox = new JComboBox<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        JTextField searchField = new JTextField("Search", 20);
        searchField.setFont(new Font("TH Niramit AS", Font.PLAIN, 18));
        topPanel.add(titleLabel);
        topPanel.add(comboBox);
        topPanel.add(searchField);

        // Products panel
        recommendPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        updateProducts();

        // Navigation panel
        JPanel navPanel = new JPanel();
        returnBtn = new JButton("Return");
        nextBtn = new JButton("Next");
        pageButtons = new JButton[totalPages];
        navPanel.add(returnBtn);

        for (int i = 0; i < totalPages; i++) {
            pageButtons[i] = new JButton(String.valueOf(i + 1));
            final int pageNum = i + 1; // for anonymous class
            pageButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentPage = pageNum;
                    updateProducts();
                }
            });
            navPanel.add(pageButtons[i]);
        }

        navPanel.add(nextBtn);

        returnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateProducts();
                }
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPage < totalPages) {
                    currentPage++;
                    updateProducts();
                }
            }
        });

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(recommendPanel, BorderLayout.CENTER);
        mainPanel.add(navPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        setContentPane(scrollPane);
    }

    private void updateProducts() {
        recommendPanel.removeAll();
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, products.length);
        for (int i = start; i < end; i++) {
            JPanel productPanel = new JPanel();
            productPanel.setBackground(Color.WHITE);
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(products[i]);
            JLabel priceLabel = new JLabel(prices[i]);
            final int index = i;
            JButton addButton = new JButton("Add to Cart");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(Homepage.this, products[index] + " added to cart!");
                }
            });

            productPanel.add(nameLabel);
            productPanel.add(priceLabel);
            productPanel.add(addButton);
            recommendPanel.add(productPanel);
        }
        recommendPanel.revalidate();
        recommendPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }
}
