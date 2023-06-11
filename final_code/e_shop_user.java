import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EShopApp extends JFrame {
    private JComboBox<String> sportFacilityComboBox;
    private JComboBox<String> productComboBox;
    private JTextArea productDetailsTextArea;
    private JSpinner quantitySpinner;
    private JButton addToCartButton;
    private JButton checkoutButton;
    private JButton cancelButton;

    public EShopApp() {
        setTitle("E-Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel headerLabel = new JLabel("Electronic Shop");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Create sport facility selection panel
        JPanel sportFacilityPanel = new JPanel();
        sportFacilityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel sportFacilityLabel = new JLabel("Sport Facility:");
        sportFacilityComboBox = new JComboBox<>(new String[]{"Facility 1", "Facility 2", "Facility 3"});
        sportFacilityPanel.add(sportFacilityLabel);
        sportFacilityPanel.add(sportFacilityComboBox);
        contentPanel.add(sportFacilityPanel);

        // Create product selection panel
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel productLabel = new JLabel("Product:");
        productComboBox = new JComboBox<>(new String[]{"Shoes", "T-Shirts", "Shorts"});
        productPanel.add(productLabel);
        productPanel.add(productComboBox);
        contentPanel.add(productPanel);

        // Create product details panel
        JPanel productDetailsPanel = new JPanel();
        productDetailsPanel.setLayout(new BorderLayout());
        productDetailsPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        productDetailsTextArea = new JTextArea(5, 30);
        productDetailsTextArea.setEditable(false);
        productDetailsPanel.add(new JScrollPane(productDetailsTextArea), BorderLayout.CENTER);
        contentPanel.add(productDetailsPanel);

        // Create quantity selection panel
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel quantityLabel = new JLabel("Quantity:");
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        contentPanel.add(quantityPanel);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addToCartButton = new JButton("Add to Cart");
        checkoutButton = new JButton("Checkout");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel);

        // Add action listeners
        sportFacilityComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                     updateProductList();
            }
        });

        productComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProductDetails();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelPurchase();
            }
        });
    }

    private void updateProductList() {
        // Code to update the product list based on the selected sport facility
        String sportFacility = (String) sportFacilityComboBox.getSelectedItem();
        // Update the productComboBox with the products from the selected sport facility
        if (sportFacility.equals("Facility 1")) {
            productComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Shoes", "T-Shirts", "Shorts"}));
        } else if (sportFacility.equals("Facility 2")) {
            productComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Jerseys", "Socks", "Hats"}));
        } else if (sportFacility.equals("Facility 3")) {
            productComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Gloves", "Pants", "Jackets"}));
        }
    }

    private void updateProductDetails() {
        // Code to update the product details based on the selected product
        String product = (String) productComboBox.getSelectedItem();
        // Update the productDetailsTextArea with the details of the selected product
        if (product.equals("Shoes")) {
            productDetailsTextArea.setText("Brand: Nike\nSize: 10\nColor: Black");
        } else if (product.equals("T-Shirts")) {
            productDetailsTextArea.setText("Brand: Adidas\nSize: L\nColor: Red");
        } else if (product.equals("Shorts")) {
            productDetailsTextArea.setText("Brand: Puma\nSize: M\nColor: Blue");
        } else if (product.equals("Jerseys")) {
            productDetailsTextArea.setText("Brand: Reebok\nSize: XL\nColor: Green");
        } else if (product.equals("Socks")) {
            productDetailsTextArea.setText("Brand: Under Armour\nSize: S\nColor: White");
        } else if (product.equals("Hats")) {
            productDetailsTextArea.setText("Brand: New Era\nSize: One Size\nColor: Black");
        } else if (product.equals("Gloves")) {
            productDetailsTextArea.setText("Brand: Wilson\nSize: L\nColor: Gray");
        } else if (product.equals("Pants")) {
            productDetailsTextArea.setText("Brand: Columbia\nSize: M\nColor: Brown");
        } else if (product.equals("Jackets")) {
            productDetailsTextArea.setText("Brand: The North Face\nSize: L\nColor: Navy Blue");
        }
    }

    private void addToCart() {
        // Code to add the selected product to the user's cart
        String product = (String) productComboBox.getSelectedItem();
        int quantity = (int) quantitySpinner.getValue();
        // Add the product and quantity to the cart
        System.out.println("Added to cart: " + product + ", Quantity: " + quantity);
    }

    private void checkout() {
        // Code to proceed with the purchase and payment
        // Display the user's cart and ask for confirmation
        int confirmation = JOptionPane.showConfirmDialog(this, "Proceed with the purchase?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Code to proceed with the payment and confirmation
            String paymentMethod = JOptionPane.showInputDialog(this, "Select payment method:");
            String deliveryAddress = JOptionPane.showInputDialog(this, "Enter delivery address:");
            // Process the payment and complete the purchase
            // Deduct the amount from the user's athlete points
            // Send the email receipt to the user's provided email address
            // Return to the main menu
            System.out.println("Payment Method: " + paymentMethod);
            System.out.println("Delivery Address: " + deliveryAddress);
        }
    }

    private void cancelPurchase() {
        // Code to cancel the purchase and return to the main menu
        // Remove the products from the user's cart
        // Return to the main menu
        System.out.println("Purchase canceled");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EShopApp();
            }
        });
    }
}
               