package se.lexicon.model;

/**
 * Interface representing the core functionality of a vending machine.
 * Defines operations for handling currency, purchasing products, and retrieving product information.
 *
 * <p>Implementations of this interface should manage a deposit pool and a list of available products.</p>
 */
public interface IVendingMachine {

    /**
     * Adds money to the deposit pool.
     * Only accepts the following denominations:
     * 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000 SEK.
     *
     * @param amount the amount of currency to add
     * @throws IllegalArgumentException if the denomination is not accepted
     */
    void addCurrency(int amount);

    /**
     * Returns the current balance in the deposit pool.
     * This method does not reset the balance.
     *
     * @return the current deposit pool amount
     */
    double getBalance();

    /**
     * Attempts to purchase the product with the specified ID.
     * Only proceeds if enough money is available in the deposit pool.
     *
     * @param id the ID of the product to purchase
     * @return the purchased {@link Product}
     * @throws IllegalArgumentException if the product ID is invalid
     * @throws IllegalStateException if there are insufficient funds
     */
    Product request(int id);

    /**
     * Ends the session and returns the remaining balance as change.
     * Resets the deposit pool to zero.
     *
     * @return the amount of change returned
     */
    double endSession();

    /**
     * Returns a description of the product with the specified ID.
     * Includes details such as name and price.
     *
     * @param id the ID of the product
     * @return a string describing the product
     */
    String getDescription(int id);

    /**
     * Returns a list of all available products in the vending machine.
     * Each string contains the product's ID, name, and price.
     *
     * @return an array of product descriptions
     */
    String[] getProducts();

    enum Size {
    }
}