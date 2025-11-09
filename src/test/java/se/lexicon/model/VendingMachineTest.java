package se.lexicon.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the abstract {@link Product} base class.
 * Uses a minimal concrete subclass (TestProduct) to exercise behavior.
 */
class ProductTest {

    /** Minimal concrete implementation for testing. */
    static class TestProduct extends Product {
        public TestProduct(int id, double price, String productName) {
            super(id, price, productName);
        }
        @Override public String examine() { return "Test examine"; }
        @Override public String use()     { return "Test use"; }
    }

    @Test
    @DisplayName("Constructor assigns fields correctly")
    void constructor_assigns_fields() {
        Product p = new TestProduct(1, 25.0, "Test Item");
        assertEquals(1, p.getId());
        assertEquals(25.0, p.getPrice());
        assertEquals("Test Item", p.getProductName());
    }

    @Test
    @DisplayName("setPrice: valid positive value updates")
    void setPrice_updates_on_valid_value() {
        Product p = new TestProduct(1, 10.0, "A");
        p.setPrice(19.99);
        assertEquals(19.99, p.getPrice());
    }

    @Test
    @DisplayName("setPrice: zero and negative values throw IllegalArgumentException")
    void setPrice_throws_on_zero_or_negative() {
        Product p = new TestProduct(1, 10.0, "A");

        assertThrows(IllegalArgumentException.class, () -> p.setPrice(0.0));
        assertThrows(IllegalArgumentException.class, () -> p.setPrice(-5.0));

        // original value remains unchanged after exceptions
        assertEquals(10.0, p.getPrice());
    }

    @Test
    @DisplayName("setProductName rejects blank or null")
    void setProductName_rejects_blank_and_null() {
        Product p = new TestProduct(1, 10.0, "Valid");

        p.setProductName("Updated");
        assertEquals("Updated", p.getProductName());

        assertThrows(IllegalArgumentException.class, () -> p.setProductName(" "));
        assertThrows(IllegalArgumentException.class, () -> p.setProductName(""));
        assertThrows(IllegalArgumentException.class, () -> p.setProductName(null));
    }

    @Test
    @DisplayName("examine() and use() are delegated to subclass")
    void examine_and_use_delegated() {
        Product p = new TestProduct(1, 10.0, "Sample");
        assertEquals("Test examine", p.examine());
        assertEquals("Test use", p.use());
    }
}
