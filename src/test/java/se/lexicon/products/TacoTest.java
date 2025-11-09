package se.lexicon.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.lexicon.products.Taco;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Taco unit tests")
class TacoTest {

    @Test
    @DisplayName("Constructor without spicy flag defaults to not spicy")
    void ctor_defaults_not_spicy() {
        Taco t = new Taco(201, 49.0, "Taco");
        assertEquals(201, t.getId());
        assertEquals(49.0, t.getPrice());
        assertEquals("Taco", t.getProductName());
        assertFalse(t.isSpicy(), "Default spicy should be false");
    }

    @ParameterizedTest(name = "Constructor with spicy={0} assigns correctly")
    @ValueSource(booleans = {true, false})
    void ctor_with_spicy_assigns(boolean spicy) {
        Taco t = new Taco(202, 55.0, "Taco (Spec)", spicy);
        assertEquals(202, t.getId());
        assertEquals(55.0, t.getPrice());
        assertEquals("Taco (Spec)", t.getProductName());
        assertEquals(spicy, t.isSpicy());
    }

    @Test
    @DisplayName("Getter and setter for spicy work")
    void spicy_getter_setter() {
        Taco t = new Taco(203, 39.0, "Taco");
        assertFalse(t.isSpicy());
        t.setSpicy(true);
        assertTrue(t.isSpicy());
        t.setSpicy(false);
        assertFalse(t.isSpicy());
    }

    @ParameterizedTest(name = "use() reflects spiciness for spicy={0}")
    @ValueSource(booleans = {true, false})
    void use_mentions_name_and_spiciness(boolean spicy) {
        Taco t = new Taco(204, 45.0, "Taco Deluxe", spicy);
        String msg = t.use();

        assertNotNull(msg);
        String lower = msg.toLowerCase();
        assertTrue(lower.contains("taco"), "use() should mention product name");

        if (spicy) {
            assertTrue(lower.contains("spicy"), "use() should mention spicy when spicy=true");
        } else {
            // Not strictly required to say "not spicy"; just ensure it doesn't *incorrectly* claim spicy
            // (Your current implementation only adds the word when spicy=true)
            assertFalse(lower.contains("spicy") && !lower.contains("not spicy"),
                    "use() should not imply spicy when spicy=false");
        }
    }

    @ParameterizedTest(name = "examine() contains name, spiciness, and price for spicy={0}")
    @ValueSource(booleans = {true, false})
    void examine_includes_name_spiciness_price(boolean spicy) {
        double price = 52.5;
        Taco t = new Taco(205, price, "Taco", spicy);
        String line = t.examine();
        String lower = line.toLowerCase();

        assertTrue(lower.contains("taco"), "examine() should contain product name");
        if (spicy) {
            assertTrue(lower.contains("spicy"), "examine() should indicate spicy");
        } else {
            assertTrue(lower.contains("not spicy") || lower.contains("not so spicy"),
                    "examine() should indicate not spicy");
        }
        // be tolerant about decimal/comma and 1–2 decimals
        assertTrue(lower.contains("52.5") || lower.contains("52,5") ||
                        lower.contains("52.50") || lower.contains("52,50"),
                "examine() should include the price");
        assertTrue(lower.contains("sek"), "examine() should include currency SEK");
    }

    @Test
    @DisplayName("toString() includes ID, name, spicy flag, and price")
    void toString_contains_fields() {
        Taco t = new Taco(206, 60.0, "Taco Fiesta", true);
        String s = t.toString();
        String lower = s.toLowerCase();

        assertTrue(lower.contains("id"), "toString() should include ID label");
        assertTrue(s.contains("206"), "toString() should include the ID value");
        assertTrue(lower.contains("taco fiesta"), "toString() should include the product name");
        assertTrue(lower.contains("spicy") || lower.contains("isspicy"), "toString() should include spicy flag/label");
        assertTrue(lower.contains("true"), "toString() should include spicy value");
        assertTrue(lower.contains("price"), "toString() should include price label");
        assertTrue(lower.contains("sek"), "toString() should include currency");
    }
}
