import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Homepage extends JFrame {

    private JPanel recommendPanel;
    private JButton returnBtn, nextBtn;
    private JButton[] pageButtons;
    private JTextArea cartArea;
    private JButton calcButton;

    private JLabel totalLabel;
    private JLabel discountLabel;
    private JLabel netLabel;

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
    private String[] writers = {
            "Author A", "Author B", "Author C", "Author D", "Author E",
            "Author F", "Author G", "Author H", "Author I", "Author J"
    };
    private String[] prices = {
            "284.05 Baht", "350.00 Baht", "420.50 Baht", "199.99 Baht", "310.00 Baht",
            "450.00 Baht", "380.00 Baht", "500.00 Baht", "275.00 Baht", "390.00 Baht"
    };

    private int currentPage = 1;
    private int itemsPerPage = 4;
    private int totalPages;

    // Cart
    private ArrayList<String> cartItems = new ArrayList<>();
    private ArrayList<Double> cartPrices = new ArrayList<>();

    public Homepage() {
        setTitle("Homepage");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        totalPages = (int) Math.ceil((double) products.length / itemsPerPage);

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Hayha Book Store");
        titleLabel.setFont(new Font("TH Niramit AS", Font.BOLD, 36));
        JTextField searchField = new JTextField("Search", 20);
        searchField.setFont(new Font("TH Niramit AS", Font.PLAIN, 18));
        topPanel.add(titleLabel);
        topPanel.add(searchField);

        // Products panel
        recommendPanel = new JPanel();
        recommendPanel.setLayout(new BoxLayout(recommendPanel, BoxLayout.Y_AXIS));
        updateProducts();

        JPanel leftPanelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanelWrapper.add(recommendPanel);

        // Navigation panel
        JPanel navPanel = new JPanel();
        returnBtn = new JButton("Return");
        nextBtn = new JButton("Next");
        pageButtons = new JButton[totalPages];
        navPanel.add(returnBtn);

        for (int i = 0; i < totalPages; i++) {
            pageButtons[i] = new JButton(String.valueOf(i + 1));
            final int pageNum = i + 1;
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

        // Cart panel
        JPanel cartPanel = new JPanel();
        cartPanel.setPreferredSize(new Dimension(250, 250));
        cartPanel.setMaximumSize(new Dimension(250, 250)); 
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("🛒 Cart"));

        cartArea = new JTextArea();
        cartArea.setEditable(false);

        JScrollPane cartScroll = new JScrollPane(cartArea);
        cartPanel.add(cartScroll, BorderLayout.CENTER);

        // Calculate button at bottom of cart
        calcButton = new JButton("Calculate");
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCart();
            }
        });
        cartPanel.add(calcButton, BorderLayout.SOUTH);

        // Summary panel
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(3, 1));
        summaryPanel.setPreferredSize(new Dimension(250, 100));
        summaryPanel.setMaximumSize(new Dimension(250, 100));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));

        totalLabel = new JLabel("Total: 0.00 Baht");
        discountLabel = new JLabel("Discount: 0.00 Baht");
        netLabel = new JLabel("Net Price: 0.00 Baht");

        summaryPanel.add(totalLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(netLabel);

        // Right side column
        JPanel rightWrapper = new JPanel();
        rightWrapper.setLayout(new BoxLayout(rightWrapper, BoxLayout.Y_AXIS));
        rightWrapper.add(cartPanel);
        rightWrapper.add(Box.createVerticalStrut(10));
        rightWrapper.add(summaryPanel);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanelWrapper, BorderLayout.CENTER);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        mainPanel.add(rightWrapper, BorderLayout.EAST);

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
            productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productPanel.setLayout(new BorderLayout(10, 10));

            // Book icon
            JLabel iconLabel = new JLabel();
            iconLabel.setPreferredSize(new Dimension(100, 150));
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

            try {
                java.net.URL imgURL = getClass().getResource("book" + (i + 1) + ".jpg");
                if (imgURL != null) {
                    ImageIcon originalIcon = new ImageIcon(imgURL);
                    Image img = originalIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(img);
                    iconLabel.setIcon(scaledIcon);
                } else {
                    iconLabel.setOpaque(true);
                    iconLabel.setBackground(Color.LIGHT_GRAY);
                    iconLabel.setText("ICON");
                }
            } catch (Exception e) {
                iconLabel.setOpaque(true);
                iconLabel.setBackground(Color.LIGHT_GRAY);
                iconLabel.setText("ICON");
            }

            // Details
            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
            detailsPanel.add(new JLabel("Name: " + products[i]));
            detailsPanel.add(new JLabel("Writer: " + writers[i]));
            detailsPanel.add(new JLabel("Price: " + prices[i]));

            JButton addButton = new JButton("Add to Cart");
            final int index = i;
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addToCart(products[index], prices[index]);
                }
            });
            detailsPanel.add(addButton);

            productPanel.add(iconLabel, BorderLayout.WEST);
            productPanel.add(detailsPanel, BorderLayout.CENTER);

            recommendPanel.add(productPanel);
            recommendPanel.add(Box.createVerticalStrut(10));
        }

        recommendPanel.revalidate();
        recommendPanel.repaint();
    }

    private void addToCart(String product, String priceText) {
        cartItems.add(product);
        cartArea.append(product + " - " + priceText + "\n");

        try {
            double price = Double.parseDouble(priceText.replace("Baht", "").trim());
            cartPrices.add(price);
        } catch (Exception e) {
            cartPrices.add(0.0);
        }
    }

    private void calculateCart() {
        double totalPrice = 0.0;
        for (double price : cartPrices) totalPrice += price;

        double discount = 0.0; 
        double netPrice = totalPrice - discount;

        totalLabel.setText(String.format("Total: %.2f Baht", totalPrice));
        discountLabel.setText(String.format("Discount: %.2f Baht", discount));
        netLabel.setText(String.format("Net Price: %.2f Baht", netPrice));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }
}
