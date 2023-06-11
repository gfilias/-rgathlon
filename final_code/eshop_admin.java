import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Product {
    String name;
    String description;
    double price;
    int inventoryLevel;

    public Product(String name, String description, double price, int inventoryLevel) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventoryLevel = inventoryLevel;
    }
}

class EShopAdmin {
    private List<Product> products;

    public EShopAdmin() {
        this.products = initializeProducts();
    }

    private List<Product> initializeProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "Description 1", 10.99, 50));
        products.add(new Product("Product 2", "Description 2", 19.99, 25));
        products.add(new Product("Product 3", "Description 3", 8.99, 100));
        return products;
    }

    public void manageProducts() {
        JFrame frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JTextArea productListTextArea = new JTextArea(10, 30);
        productListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productListTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit Product");
        JButton deleteButton = new JButton("Delete Product");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        StringBuilder productListText = new StringBuilder("Existing Products:\n");
        for (int i = 0; i < products.size(); i++) {
            productListText.append(i + 1).append(". ").append(products.get(i).name).append("\n");
        }
        productListTextArea.setText(productListText.toString());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productListTextArea.getSelectedIndex();
                if (selectedIndex != -1) {
                    Product selectedProduct = products.get(selectedIndex);
                    editProduct(selectedProduct);
                    productListTextArea.setText(getProductListText());
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a product.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productListTextArea.getSelectedIndex();
                if (selectedIndex != -1) {
                    Product selectedProduct = products.get(selectedIndex);
                    int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this product?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        products.remove(selectedProduct);
                        productListTextArea.setText(getProductListText());
                        JOptionPane.showMessageDialog(frame, "Product deleted.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a product.");
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void editProduct(Product product) {
        JFrame frame = new JFrame("Edit Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(product.name);
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(product.description);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(String.valueOf(product.price));
        JLabel inventoryLabel = new JLabel("Inventory Level:");
        JTextField inventoryField = new JTextField(String.valueOf(product.inventoryLevel));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(descriptionLabel);
        frame.add(descriptionField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(inventoryLabel);
        frame.add(inventoryField);
        frame.add(saveButton);
        frame.add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product.name = nameField.getText();
                product.description = descriptionField.getText();
                try {
                    product.price = Double.parseDouble(priceField.getText());
                    product.inventoryLevel = Integer.parseInt(inventoryField.getText());
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Product details updated.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Price must be a number, and inventory level must be an integer.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addProduct() {
        JFrame frame = new JFrame("Add New Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel inventoryLabel = new JLabel("Inventory Level:");
        JTextField inventoryField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(descriptionLabel);
        frame.add(descriptionField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(inventoryLabel);
        frame.add(inventoryField);
        frame.add(addButton);
        frame.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                try {
                    double price = Double.parseDouble(priceField.getText());
                    int inventoryLevel = Integer.parseInt(inventoryField.getText());
                    Product newProduct = new Product(name, description, price, inventoryLevel);
                    products.add(newProduct);
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "New product added successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Price must be a number, and inventory level must be an integer.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("E-Shop Administration");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new GridLayout(4, 1, 10, 10));

                JButton manageProductsButton = new JButton("Product Management");
                JButton addProductButton = new JButton("Add New Product");
                JButton exitButton = new JButton("Exit");

      frame.add(manageProductsButton);
                frame.add(addProductButton);
                frame.add(exitButton);

                manageProductsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageProducts();
                    }
                });

                addProductButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addProduct();
                    }
                });

                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Exiting the program. Goodbye!");
                        System.exit(0);
                    }
                });

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        EShopAdmin eshopAdmin = new EShopAdmin();
        eshopAdmin.run();
    }
}

            