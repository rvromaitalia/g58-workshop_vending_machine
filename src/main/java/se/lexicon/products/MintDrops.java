package se.lexicon.products;

import se.lexicon.model.Product;

/**
 * Represents a mint drops product in the vending machine.
 * Inherits common product properties from {@link Product} and adds a specific {@code coolingLevel} field.
 */
public class MintDrops extends Product {

    /**
     * Cooling intensity of the mint drops.
     * This is represented using an enum called {@link CoolingLevel}, which maps numeric levels to descriptive labels.
     */
    private CoolingLevel coolingLevel;

    /**
     * Constructs a new MintDrops product.
     *
     * @param id          the unique identifier of the product
     * @param price       the price of the mint drops in SEK
     * @param productName the name of the product
     * @param level       the cooling intensity (must be between 1 and 5)
     */
    public MintDrops(int id, double price, String productName, int level) {
        // Call the constructor of the abstract Product class to set common fields
        super(id, price, productName);
        // Set the specific field for MintDrops using the setter (includes validation and enum conversion)
        setCoolingLevel(level);
    }

    /**
     * Returns the cooling intensity level.
     * This is the enum value, not the raw integer.
     *
     * @return the CoolingLevel enum
     */
    public CoolingLevel getCoolingLevel() {
        return coolingLevel;
    }

    /**
     * Sets the cooling intensity level using an integer.
     * Converts the integer to the corresponding enum value.
     * If the input is outside the valid range (1–5), a warning is printed.
     *
     * @param level the cooling level (1–5)
     */
    public void setCoolingLevel(int level) {
        try {
            // Convert the integer to a descriptive enum value
            this.coolingLevel = CoolingLevel.fromInt(level);
        } catch (IllegalArgumentException e) {
            // Print a warning if the level is invalid
            System.out.println("Warning: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * Returns a string describing the mint drops, including its cooling level and price.
     */
    @Override
    public String examine() {
        // Use the enum's label to describe the cooling effect
        return getProductName() + " (Cooling: " + coolingLevel.getLabel() + ") costs " + getPrice() + " SEK.";
    }

    /**
     * {@inheritDoc}
     * Simulates the action of using (sucking) the mint drops.
     */
    @Override
    public String use() {
        // Describe the sensation using the enum's label in lowercase
        return "You feel a " + coolingLevel.getLabel().toLowerCase() + " mint sensation.";
    }

    /**
     * Returns a formatted string representation of the mint drops product.
     * Includes ID, name, cooling level, and price.
     *
     * @return a string for display in product listings
     */
    @Override
    public String toString() {
        return "ID: " + getId() +
                ", Product: MintDrops" +
                ", Name: \"" + getProductName() + "\"" +
                ", Cooling: " + coolingLevel.getLabel() +
                ", Price: " + getPrice() + " SEK";
    }
}