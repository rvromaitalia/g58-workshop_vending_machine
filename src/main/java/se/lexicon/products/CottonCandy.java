package se.lexicon.products;

import se.lexicon.model.Product;

/**
 * Represents a cotton candy product in the vending machine.
 * Inherits common product properties from {@link Product} and adds a specific {@code flavor} field.
 */
public class CottonCandy extends Product {

    /**
     * Flavor of the cotton candy (e.g., Strawberry, Blueberry, Vanilla).
     */
    private String flavor;

    /**
     * Constructs a new CottonCandy product.
     *
     * @param id          the unique identifier of the product
     * @param price       the price of the cotton candy in SEK
     * @param productName the name of the product
     * @param flavor      the flavor of the cotton candy (must be non-null and non-empty)
     */
    public CottonCandy(int id, double price, String productName, String flavor) {
        // Call the constructor of the abstract Product class to set common fields
        super(id, price, productName);
        // Set the specific field for CottonCandy using the setter
        setFlavor(flavor);
    }

    /**
     * Returns the flavor of the cotton candy.
     *
     * @return the flavor string
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of the cotton candy.
     * Must be non-null and non-empty.
     *
     * @param flavor the flavor to set
     */
    public void setFlavor(String flavor) {
        // Check that the flavor is not null or just spaces
        if (flavor == null || flavor.trim().isEmpty()) {
            System.out.println("Warning: Cotton candy flavor cannot be null or empty.");
        } else {
            this.flavor = flavor;
        }
    }

    /**
     * {@inheritDoc}
     * Returns a string describing the cotton candy, including its flavor and price.
     */
    @Override
    public String examine() {
        return getProductName() + " (" + flavor + " flavor) costs " + getPrice() + " SEK.";
    }

    /**
     * {@inheritDoc}
     * Simulates the action of using (eating) the cotton candy.
     */
    @Override
    public String use() {
        return "You enjoy the fluffy " + flavor + " cotton candy.";
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                ", Product: CottonCandy" +
                ", Name: \"" + getProductName() + "\"" +
                ", Flavor: " + flavor +
                ", Price: " + getPrice() + " SEK";
    }

}