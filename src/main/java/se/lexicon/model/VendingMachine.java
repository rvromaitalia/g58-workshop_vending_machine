package se.lexicon.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the Vendiong Machine interface interface.
 * Represents a vending machine that can accept currency, dispense products, and return change.
 *
 * <p>This class manages a list of available products and a deposit pool for user transactions.</p>
 */
public class VendingMachine implements IVendingMachine {

    // A list of all products that this vending machine offers.
    private List<Product> products;

    // The current balance (money) that the user has inserted into the machine.
    private double balance;

    // An array of valid currency denominations that the machine accepts.
    // If a user tries to insert a value not in this list, it will be rejected.
    private final double[] validDenominations = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000};

    /**
     * Constructor that initializes the vending machine with a list of products.
     * It also checks for duplicate product IDs and sets the starting balance to 0.
     *
     * @param products the list of products to be available in the vending machine
     */
    public VendingMachine(List<Product> products) {
        // Check if any products have the same ID, which would cause problems when selecting them
        if (hasDuplicateIds(products)) {
            System.out.println("Warning: Duplicate product IDs detected. Vending machine may not function correctly.");
        }

        // Store the product list
        this.products = products;

        // Set the initial balance to 0
        setBalance(0);
    }

    /**
     * Adds currency to the machine if the amount is valid.
     * Only accepts values listed in validDenominations.
     *
     * @param amount the amount of money the user inserts
     */
    @Override
    public void addCurrency(int amount) {
        // Loop through the list of valid denominations
        for (double valid : validDenominations) {
            // If the inserted amount matches a valid denomination
            if (valid == amount) {
                // Add the amount to the current balance
                setBalance(getBalance() + amount);
                return;
            }
        }

        // If the amount is not valid, notify the user
        System.out.println("Invalid denomination: " + amount);
    }

    /**
     * Returns the current balance (how much money the user has inserted).
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance to a new value.
     * Used internally to update the balance after inserting money or buying a product.
     *
     * @param balance the new balance value
     */
    public void setBalance(double balance) {
        // Prevent the balance from being set to a negative number
        if (balance < 0) {
            System.out.println("Warning: Balance cannot be negative.");
        } else {
            this.balance = balance;
        }
    }

    /**
     * Attempts to purchase a product by its ID.
     * If the product exists and the user has enough money, the product is returned and the price is deducted.
     *
     * @param id the ID of the product the user wants to buy
     * @return the product if successful, or null if not found or insufficient funds
     */
    @Override
    public Product request(int id) {
        // Loop through all products to find the one with the matching ID
        for (Product p : products) {
            if (p.getId() == id) {
                // Check if the user has enough money to buy the product
                if (getBalance() >= p.getPrice()) {
                    // Deduct the product's price from the balance
                    setBalance(getBalance() - p.getPrice());

                    // Return the product to the user
                    return p;
                } else {
                    // Not enough money
                    System.out.println("Insufficient funds for product ID: " + id);
                    return null;
                }
            }
        }

        // No product found with the given ID
        System.out.println("Product not found for ID: " + id);
        return null;
    }

    /**
     * Ends the user's session and returns any remaining balance as change.
     * This simulates the user pressing "return change" or finishing their purchase.
     *
     * @return the amount of change returned to the user
     */
    @Override
    public double endSession() {
        // Store the current balance to return as change
        double change = getBalance();

        // Reset the balance to 0
        setBalance(0);

        // Return the change
        return change;
    }

    /**
     * Returns a description of a product by its ID.
     * This is useful for showing details before the user decides to buy.
     *
     * @param id the ID of the product
     * @return a description of the product, or a message if not found
     */
    @Override
    public String getDescription(int id) {
        // Loop through the product list to find the matching ID
        for (Product p : products) {
            if (p.getId() == id) {
                // Return the product's description
                return p.examine();
            }
        }

        // No product found with the given ID
        return "Product not found.";
    }

    /**
     * Returns a list of all products in the vending machine.
     * Each product is represented as a string using its toString() method.
     *
     * @return an array of product descriptions
     */
    @Override
    public String[] getProducts() {
        // Create an array to hold the product descriptions
        String[] list = new String[products.size()];

        // Fill the array with each product's string representation
        for (int i = 0; i < products.size(); i++) {
            list[i] = products.get(i).toString();
        }

        return list;
    }

    /**
     * Checks the product list for duplicate IDs.
     * Each product must have a unique ID to avoid confusion when selecting them.
     *
     * @param products the list of products to check
     * @return true if duplicates are found, false otherwise
     */
    private boolean hasDuplicateIds(List<Product> products) {
        // A set to keep track of IDs we've already seen
        Set<Integer> seenIds = new HashSet<>();

        // Loop through each product
        for (Product p : products) {
            // Try to add the ID to the set
            // If it already exists, it's a duplicate
            if (!seenIds.add(p.getId())) {
                System.out.println("Duplicate ID found: " + p.getId() + " for product '" + p.getProductName() + "'");
                return true;
            }
        }

        // No duplicates found
        return false;
    }
}