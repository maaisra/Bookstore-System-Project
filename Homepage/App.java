import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App extends JFrame {

    private JPanel recommendPanel;
    private JButton returnBtn, nextBtn;
    private JButton[] pageButtons;
    private JTextField searchField;
    private JPanel navPanel;
    private JButton calcButton;

    private JLabel totalLabel;
    private JLabel discountLabel;
    private JLabel netLabel;

    private JPanel cartItemsPanel; // panel for clickable cart items

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

    private ArrayList<String> filteredProducts = new ArrayList<>();
    private ArrayList<String> filteredWriters = new ArrayList<>();
    private ArrayList<String> filteredPrices = new ArrayList<>();

    private int currentPage = 1;
    private int itemsPerPage = 8;
    private int totalPages;

    // Cart
    private ArrayList<String> cartItems = new ArrayList<>();
    private ArrayList<Double> cartPrices = new ArrayList<>();

    public App() {
        setTitle("Homepage");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Initialize filtered lists
        for (int i = 0; i < products.length; i++) {
            filteredProducts.add(products[i]);
            filteredWriters.add(writers[i]);
            filteredPrices.add(prices[i]);
        }

        initUI();
        updateProducts();
    }

    private void initUI() {
        JPanel topPanel = createTopPanel();

        // Products panel with GridLayout 4x2
        recommendPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        recommendPanel.setBackground(Color.WHITE);

        // Navigation panel
        navPanel = new JPanel();
        navPanel.setBackground(Color.WHITE);
        returnBtn = createNavButton("Return");
        nextBtn = createNavButton("Next");

        // Right panel (cart + summary)
        JPanel rightWrapper = createRightPanel();

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(recommendPanel, BorderLayout.CENTER);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        mainPanel.add(rightWrapper, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        setContentPane(scrollPane);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        topPanel.setBackground(new Color(255, 204, 0));

        JLabel titleLabel = new JLabel("Hayha Book Store");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);

        searchField = new JTextField("Search", 15);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(200, 30));

        // Placeholder behavior
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search")) searchField.setText("");
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) searchField.setText("Search");
            }
        });

        // Enter key triggers search
        searchField.addActionListener(e -> filterProducts(searchField.getText().trim().toLowerCase()));

        topPanel.add(titleLabel);
        topPanel.add(searchField);
        return topPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightWrapper = new JPanel();
        rightWrapper.setLayout(new BoxLayout(rightWrapper, BoxLayout.Y_AXIS));
        rightWrapper.setBackground(Color.WHITE);
        rightWrapper.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel cartPanel = new JPanel();
        cartPanel.setPreferredSize(new Dimension(250, 250));
        cartPanel.setMaximumSize(new Dimension(250, 250));
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("🛒 Cart"));
        cartPanel.setBackground(Color.WHITE);

        // Cart items panel with top alignment
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);
        cartItemsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // Instruction text (fixed)
        JLabel instructionLabel = new JLabel("Click on the product name to remove");
        instructionLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        instructionLabel.setForeground(Color.DARK_GRAY);
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // fix alignment
        cartItemsPanel.add(instructionLabel);
        cartItemsPanel.add(Box.createVerticalStrut(10)); // spacing after instruction


        JPanel cartWrapper = new JPanel(new BorderLayout());
        cartWrapper.setBackground(Color.WHITE);
        cartWrapper.add(cartItemsPanel, BorderLayout.NORTH);

        JScrollPane cartScroll = new JScrollPane(cartWrapper);
        cartScroll.getViewport().setBackground(Color.WHITE);
        cartPanel.add(cartScroll, BorderLayout.CENTER);

        calcButton = createActionButton("Calculate");
        calcButton.addActionListener(e -> calculateCart());
        cartPanel.add(calcButton, BorderLayout.SOUTH);

        JPanel summaryPanel = new JPanel(new GridLayout(3,1));
        summaryPanel.setPreferredSize(new Dimension(250,100));
        summaryPanel.setMaximumSize(new Dimension(250,100));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        summaryPanel.setBackground(Color.WHITE);

        totalLabel = new JLabel("Total: 0.00 Baht");
        discountLabel = new JLabel("Discount: 0.00 Baht");
        netLabel = new JLabel("Net Price: 0.00 Baht");

        summaryPanel.add(totalLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(netLabel);

        rightWrapper.add(cartPanel);
        rightWrapper.add(Box.createVerticalStrut(10));
        rightWrapper.add(summaryPanel);

        return rightWrapper;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt){ btn.setBackground(new Color(65,105,225)); }
            public void mouseExited(MouseEvent evt){ btn.setBackground(new Color(30,144,255)); }
        });
        return btn;
    }

    private JButton createActionButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(50,205,50));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt){ btn.setBackground(new Color(34,139,34)); }
            public void mouseExited(MouseEvent evt){ btn.setBackground(new Color(50,205,50)); }
        });
        return btn;
    }

    private void filterProducts(String query) {
        filteredProducts.clear();
        filteredWriters.clear();
        filteredPrices.clear();
        for(int i=0;i<products.length;i++){
            if(query.isEmpty() || query.equals("search") || products[i].toLowerCase().contains(query)
                    || writers[i].toLowerCase().contains(query)){
                filteredProducts.add(products[i]);
                filteredWriters.add(writers[i]);
                filteredPrices.add(prices[i]);
            }
        }
        currentPage = 1;
        updateProducts();
    }

    private void updateProducts() {
        recommendPanel.removeAll();

        totalPages = (int)Math.ceil((double)filteredProducts.size()/itemsPerPage);
        if(totalPages==0) totalPages=1;

        int start = (currentPage-1)*itemsPerPage;
        int end = Math.min(start + itemsPerPage, filteredProducts.size());

        // Add actual products
        for (int i = start; i < end; i++) {
            recommendPanel.add(createProductPanel(i));
        }

        // Fill remaining cells with empty panels to keep 4x2 layout
        int filledCells = end - start;
        for (int i = filledCells; i < itemsPerPage; i++) {
            JPanel empty = new JPanel();
            empty.setOpaque(false);
            recommendPanel.add(empty);
        }

        recommendPanel.revalidate();
        recommendPanel.repaint();

        refreshPageButtons();
    }

    private JPanel createProductPanel(int index){
        JPanel productPanel = new JPanel();
        productPanel.setPreferredSize(new Dimension(250,200));
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));
        productPanel.setLayout(new BorderLayout(10,10));

        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(100,150));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try{
            java.net.URL imgURL = getClass().getResource("book"+(index+1)+".jpg");
            if(imgURL!=null){
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image img = originalIcon.getImage().getScaledInstance(100,150,Image.SCALE_SMOOTH);
                iconLabel.setIcon(new ImageIcon(img));
            } else {
                iconLabel.setOpaque(true);
                iconLabel.setBackground(Color.LIGHT_GRAY);
                iconLabel.setText("ICON");
            }
        }catch(Exception e){
            iconLabel.setOpaque(true);
            iconLabel.setBackground(Color.LIGHT_GRAY);
            iconLabel.setText("ICON");
        }

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel,BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.add(new JLabel("Name: " + filteredProducts.get(index)));
        detailsPanel.add(new JLabel("Writer: " + filteredWriters.get(index)));
        detailsPanel.add(new JLabel("Price: " + filteredPrices.get(index)));

        JButton addButton = new JButton("Add to Cart");
        addButton.setFocusPainted(false);
        addButton.setBackground(new Color(30,144,255));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(MouseEvent evt){ addButton.setBackground(new Color(65,105,225)); }
            public void mouseExited(MouseEvent evt){ addButton.setBackground(new Color(30,144,255)); }
        });

        final int idx = index;
        addButton.addActionListener(e -> addToCart(filteredProducts.get(idx), filteredPrices.get(idx)));

        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(addButton);

        productPanel.add(iconLabel, BorderLayout.WEST);
        productPanel.add(detailsPanel, BorderLayout.CENTER);

        return productPanel;
    }

    private void refreshPageButtons() {
        navPanel.removeAll();
        navPanel.add(returnBtn);

        pageButtons = new JButton[totalPages];
        for(int i=0;i<totalPages;i++){
            JButton btn = createNavButton(String.valueOf(i+1));
            final int pageNum = i+1;
            btn.addActionListener(e -> { currentPage = pageNum; updateProducts(); });
            navPanel.add(btn);
            pageButtons[i]=btn;
        }

        navPanel.add(nextBtn);

        returnBtn.addActionListener(e -> { if(currentPage>1){ currentPage--; updateProducts(); }});
        nextBtn.addActionListener(e -> { if(currentPage<totalPages){ currentPage++; updateProducts(); }});

        navPanel.revalidate();
        navPanel.repaint();
    }

    private void addToCart(String product, String priceText) {
        cartItems.add(product);
        try {
            double price = Double.parseDouble(priceText.replace("Baht","").trim());
            cartPrices.add(price);
        } catch(Exception e) { cartPrices.add(0.0); }

        // Container panel for label + spacing
       JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 


        JLabel itemLabel = new JLabel(product + " - " + priceText);
        itemLabel.setOpaque(true);
        itemLabel.setBackground(Color.WHITE);
        itemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover and click behavior
        itemLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e){ itemLabel.setForeground(Color.RED); }
            public void mouseExited(MouseEvent e){ itemLabel.setForeground(Color.BLACK); }
            public void mouseClicked(MouseEvent e){
                int index = cartItemsPanel.getComponentZOrder(itemPanel) - 2; // subtract instruction label and spacing
                if(index >= 0 && index < cartItems.size()){
                    cartItems.remove(index);
                    cartPrices.remove(index);
                    cartItemsPanel.remove(itemPanel);
                    cartItemsPanel.revalidate();
                    cartItemsPanel.repaint();
                    calculateCart();
                }
            }
        });

        itemPanel.add(itemLabel, BorderLayout.CENTER);
        itemPanel.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);

        cartItemsPanel.add(itemPanel);
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();

        calculateCart(); 
    }

    private void calculateCart() {
        double totalPrice=0.0;
        for(double price:cartPrices) totalPrice+=price;
        double discount=0.0;
        double netPrice = totalPrice - discount;

        totalLabel.setText(String.format("Total: %.2f Baht", totalPrice));
        discountLabel.setText(String.format("Discount: %.2f Baht", discount));
        netLabel.setText(String.format("Net Price: %.2f Baht", netPrice));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
