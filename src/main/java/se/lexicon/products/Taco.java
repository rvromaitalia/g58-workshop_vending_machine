package se.lexicon.products;

import se.lexicon.model.Product;

import java.util.Locale;

/**
 * Represents a Taco product in the vending machine.
 * <p>
 * Extends {@link Product} by adding a spiciness attribute to customize
 * how the product is displayed and described in interactions.
 * </p>
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * Product mildTaco = new Taco(201, 49.0, "Taco");
 * Product spicyTaco = new Taco(202, 55.0, "Taco (Spicy)", true);
 *
 * System.out.println(mildTaco.examine()); // "Taco is not spicy and costs 49.00 SEK."
 * System.out.println(spicyTaco.use());    // "You enjoy the spicy Taco."
 * }</pre>
 *
 * @see Product
 */
public class Taco extends Product {

    /**
     * Whether this taco is spicy. Defaults to {@code false} when not specified.
     */
    private boolean isSpicy;

    /**
     * Creates a new {@code Taco} with spiciness defaulted to {@code false}.
     *
     * @param id          unique product identifier (unique within the catalog)
     * @param price       price in SEK
     * @param productName display name shown to users
     */
    public Taco(int id, double price, String productName) {
        super(id, price, productName);
        this.isSpicy = false;
    }

    /**
     * Creates a new {@code Taco} with explicit spiciness.
     *
     * @param id          unique product identifier (unique within the catalog)
     * @param price       price in SEK
     * @param productName display name shown to users
     * @param isSpicy     whether the taco is spicy
     */
    public Taco(int id, double price, String productName, boolean isSpicy) {
        super(id, price, productName);
        this.isSpicy = isSpicy;
    }

    /**
     * {@inheritDoc}
     * <p>
     * For {@code Taco}, includes whether it is spicy in the user-facing message.
     * </p>
     *
     * @return a short confirmation describing consumption of the taco
     */
    @Override
    public String use() {
        return "You enjoy the " + (isSpicy ? "spicy " : "") + getProductName() + ".";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a concise description including spiciness and a formatted price.
     * </p>
     *
     * @return a one-line product description
     */
    @Override
    public String examine() {
        String priceStr = String.format(Locale.US, "%.2f", getPrice());
        String spicyPart = isSpicy ? "is spicy and " : "is not spicy and ";
        return getProductName() + " " + spicyPart + "costs " + priceStr + " SEK.";
    }

    /**
     * Indicates whether this taco is spicy.
     *
     * @return {@code true} if spicy; {@code false} otherwise
     */
    public boolean isSpicy() {
        return isSpicy;
    }

    /**
     * Updates the spiciness of this taco.
     * <p>
     * Consider removing this setter and making the field immutable if your catalog
     * does not allow changing attributes after creation.
     * </p>
     *
     * @param spicy whether the taco should be spicy
     */
    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    /**
     * Returns a formatted summary suitable for listings and logs.
     *
     * @return a formatted line with ID, product type, name, spiciness, and price
     */
    @Override
    public String toString() {
        String priceStr = String.format(Locale.US, "%.2f", getPrice());
        return "ID: " + getId()
                + ", Product: " + getClass().getSimpleName()
                + ", Name: \"" + getProductName() + "\""
                + ", Spicy: " + isSpicy
                + ", Price: " + priceStr + " SEK";
    }
}