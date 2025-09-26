import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App extends JFrame {

    private JPanel recommendPanel;
    private RoundedButton returnBtn, nextBtn;
    private RoundedButton[] pageButtons;
    private JTextArea cartArea;
    private RoundedButton calcButton;
    private JTextField searchField;

    private JLabel totalLabel, discountLabel, netLabel;

    private String[] products = {
            "Lapvona: Town of Sinner", "Book of Shadows", "Magic Tales", "Adventure Quest",
            "Mystery Island", "Legendary Heroes", "Epic Journey", "Dragon Slayer",
            "Wizard's Code", "Treasure Hunt"
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
    private int itemsPerPage = 8;
    private int totalPages;

    private ArrayList<String> cartItems = new ArrayList<>();
    private ArrayList<Double> cartPrices = new ArrayList<>();

    public App() {
        setTitle("Hayha Book Store");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        totalPages = (int) Math.ceil((double) products.length / itemsPerPage);

        // Headline Panel (Yellow Background)
        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        headlinePanel.setBackground(new Color(255, 204, 0));

        JLabel titleLabel = new JLabel("Hayha Book Store");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);

        searchField = new JTextField("Search", 20);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        searchField.setForeground(Color.DARK_GRAY);

        // Clear placeholder when focused, restore if empty
        searchField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search");
                    searchField.setForeground(Color.DARK_GRAY);
                }
            }
        });

        // Trigger search when Enter is pressed
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterProducts(searchField.getText().trim());
            }
        });

        headlinePanel.add(titleLabel);
        headlinePanel.add(searchField);

        // Products Panel
        recommendPanel = new JPanel();
        recommendPanel.setLayout(new GridLayout(4, 2, 10, 10));
        recommendPanel.setBackground(new Color(245, 245, 245));
        updateProducts();

        JPanel leftWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftWrapper.setBackground(new Color(245, 245, 245));
        leftWrapper.add(recommendPanel);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(245, 245, 245));
        returnBtn = new RoundedButton("Return");
        nextBtn = new RoundedButton("Next");
        pageButtons = new RoundedButton[totalPages];
        navPanel.add(returnBtn);

        for (int i = 0; i < totalPages; i++) {
            pageButtons[i] = new RoundedButton(String.valueOf(i + 1));
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

        // Right Column: Cart and Summary
        JPanel rightWrapper = new JPanel();
        rightWrapper.setLayout(new BoxLayout(rightWrapper, BoxLayout.Y_AXIS));
        rightWrapper.setBackground(new Color(245, 245, 245));

        RoundedPanel cartPanel = new RoundedPanel(new BorderLayout(), 20, Color.WHITE);
        cartPanel.setPreferredSize(new Dimension(250, 250));
        cartPanel.setMaximumSize(new Dimension(250, 250));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setBackground(Color.WHITE);
        cartArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane cartScroll = new JScrollPane(cartArea);
        cartScroll.setBorder(BorderFactory.createEmptyBorder());

        calcButton = new RoundedButton("Calculate");
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCart();
            }
        });

        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.add(calcButton, BorderLayout.SOUTH);

        RoundedPanel summaryPanel = new RoundedPanel(new GridLayout(3,1), 20, Color.WHITE);
        summaryPanel.setPreferredSize(new Dimension(250, 100));
        summaryPanel.setMaximumSize(new Dimension(250, 100));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalLabel = new JLabel("Total: 0.00 Baht");
        discountLabel = new JLabel("Discount: 0.00 Baht");
        netLabel = new JLabel("Net Price: 0.00 Baht");

        summaryPanel.add(totalLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(netLabel);

        rightWrapper.add(cartPanel);
        rightWrapper.add(Box.createVerticalStrut(10));
        rightWrapper.add(summaryPanel);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBackground(new Color(245,245,245));
        mainPanel.add(headlinePanel, BorderLayout.NORTH);
        mainPanel.add(leftWrapper, BorderLayout.CENTER);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        mainPanel.add(rightWrapper, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        setContentPane(scrollPane);
    }

    private void updateProducts() {
        recommendPanel.removeAll();
        int start = (currentPage-1)*itemsPerPage;
        int end = Math.min(start+itemsPerPage, products.length);

        for(int i=start;i<end;i++){
            RoundedPanel productPanel = createProductPanel(i);
            recommendPanel.add(productPanel);
        }

        recommendPanel.revalidate();
        recommendPanel.repaint();
    }

    private RoundedPanel createProductPanel(int i) {
        RoundedPanel productPanel = new RoundedPanel(new BorderLayout(10,10), 20, Color.WHITE);
        productPanel.setMaximumSize(new Dimension(400,150));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(100,150));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            java.net.URL imgURL = getClass().getResource("book" + (i+1) + ".jpg");
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image img = originalIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                iconLabel.setIcon(new ImageIcon(img));
            } else {
                iconLabel.setOpaque(true);
                iconLabel.setBackground(new Color(220,220,220));
                iconLabel.setText("ICON");
            }
        } catch (Exception e) {
            iconLabel.setOpaque(true);
            iconLabel.setBackground(new Color(220,220,220));
            iconLabel.setText("ICON");
        }

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.add(new JLabel("Name: " + products[i]));
        detailsPanel.add(new JLabel("Writer: " + writers[i]));
        detailsPanel.add(new JLabel("Price: " + prices[i]));
        RoundedButton addButton = new RoundedButton("Add to Cart");
        final int index = i;
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart(products[index], prices[index]);
            }
        });
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(addButton);

        productPanel.add(iconLabel, BorderLayout.WEST);
        productPanel.add(detailsPanel, BorderLayout.CENTER);

        return productPanel;
    }

    private void filterProducts(String query) {
        recommendPanel.removeAll();

        if (query.isEmpty() || query.equals("Search")) {
            // Restore normal paginated layout
            updateProducts();
        } else {
            query = query.toLowerCase();
            for (int i = 0; i < products.length; i++) {
                if (products[i].toLowerCase().contains(query)) {
                    RoundedPanel productPanel = createProductPanel(i);
                    recommendPanel.add(productPanel);
                }
            }
        }

        recommendPanel.revalidate();
        recommendPanel.repaint();
    }

    private void addToCart(String product, String priceText){
        cartItems.add(product);
        cartArea.append(product + " - " + priceText + "\n");
        try{
            double price = Double.parseDouble(priceText.replace("Baht","").trim());
            cartPrices.add(price);
        }catch(Exception e){
            cartPrices.add(0.0);
        }
    }

    private void calculateCart(){
        double totalPrice=0.0;
        for(double price: cartPrices) totalPrice+=price;
        double discount=0.0;
        double netPrice=totalPrice-discount;
        totalLabel.setText(String.format("Total: %.2f Baht", totalPrice));
        discountLabel.setText(String.format("Discount: %.2f Baht", discount));
        netLabel.setText(String.format("Net Price: %.2f Baht", netPrice));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}

// Custom RoundedPanel with Shadow
class RoundedPanel extends JPanel{
    private Color backgroundColor;
    private int cornerRadius = 15;
    public RoundedPanel(LayoutManager layout, int radius, Color bgColor){
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2.setColor(new Color(0,0,0,30));
        g2.fillRoundRect(4,4,getWidth()-8,getHeight()-8,cornerRadius,cornerRadius);

        // Background
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0,0,getWidth()-8,getHeight()-8,cornerRadius,cornerRadius);
    }
}

// Custom RoundedButton with hover effect
class RoundedButton extends JButton {
    private int radius = 15;
    private Color normalColor = new Color(52, 152, 219);
    private Color hoverColor = new Color(41, 128, 185);

    public RoundedButton(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setBackground(normalColor);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(5,15,5,15));

        addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                setBackground(hoverColor);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                setBackground(normalColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b){
        // ignore
    }
}
