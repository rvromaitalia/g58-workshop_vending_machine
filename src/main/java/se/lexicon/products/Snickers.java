package se.lexicon.products;

import se.lexicon.model.Product;

/**
 * Represents a Snickers candy bar sold by the vending machine.
 * <p>
 * This class specializes {@link Product} by adding a {@link Size} attribute,
 * which is used for display and user-facing messages (e.g., in listings,
 * purchase confirmations, and the {@code use()} interaction).
 * </p>
 *
 * <h2>Usage</h2>
 * <pre>{@code
 * Product smallSnickers = new Snickers(101, 15.0, "Snickers", Size.SMALL);
 * System.out.println(smallSnickers.examine()); // "Snickers (SMALL), costs 15.0 SEK."
 * System.out.println(smallSnickers.use());     // "You enjoy the crunchy SMALL Snickers."
 * }</pre>
 *
 * @see Product
 * @see Size
 */
public class Snickers extends Product {

    /**
     * The declared size (e.g., SMALL, MEDIUM, LARGE) of this Snickers bar.
     */
    private Size size;

    /**
     * Creates a new {@code Snickers} product with the given identity and attributes.
     *
     * @param id    the unique identifier of the product (must be unique within the catalog)
     * @param price the price in SEK
     * @param name  the display name (typically {@code "Snickers"})
     * @param size  the physical size/variant of the item
     */
    public Snickers(int id, double price, String name, Size size) {
        super(id, price, name);
        if (size == null) throw new IllegalArgumentException("size must not be null");
        this.size = size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * For {@code Snickers}, returns a short message describing the user consuming
     * this candy bar, including its size.
     * </p>
     *
     * @return a human-friendly confirmation of use/consumption
     */
    @Override
    public String use() {
        // Prefer a human-readable size label if Size provides one; fall back to enum name.
        String sizeLabel = (size != null && size.getLabel() != null) ? size.getLabel() : (size != null ? size.name() : "UNKNOWN");
        return "You enjoy the crunchy " + sizeLabel() + " Snickers.";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a concise description containing name, size, and price.
     * </p>
     *
     * @return a one-line description suitable for product details
     */
    @Override
    public String examine() {
        String sizeLabel = (size != null) ? size.toString() : "UNKNOWN";
        return getProductName() + " (" + sizeLabel + "), costs " + getPrice() + " SEK.";
    }

    /**
     * Returns the configured size of this Snickers item.
     *
     * @return the {@link Size} value; may be {@code null} if unspecified
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of this Snickers item.
     *
     * @param size the new {@link Size} value
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Returns a formatted string that summarizes the product for listings.
     * <p>
     * The format includes: ID, product type, display name, size, and price.
     * </p>
     *
     * @return a formatted summary line for catalogs and debug output
     */
    @Override
    public String toString() {
        String sizeLabel = (size != null && size.getSize() != null) ? size.getSize() : (size != null ? size.name() : "UNKNOWN");
        return "ID: " + getId()
                + ", Product: Snickers"
                + ", Name: \"" + getProductName() + "\""
                + ", Size: " + sizeLabel
                + ", Price: " + getPrice() + " SEK";
    }


    // --- Helpers ---

    private String sizeLabel() {
        // If your Size enum has a display label method like getSize(), prefer that.
        // Otherwise fall back to enum name.
        try {
            String maybe = size.getLabel(); // if you have this
            return (maybe != null && !maybe.isEmpty()) ? maybe : size.name();
        } catch (NoSuchMethodError | NullPointerException e) {
            return (size != null) ? size.name() : "UNKNOWN";
        }
    }
}
