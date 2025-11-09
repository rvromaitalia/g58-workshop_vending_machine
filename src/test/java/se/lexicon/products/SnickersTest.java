package se.lexicon.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Snickers unit tests")
class SnickersTest {

    @Test
    @DisplayName("Constructor assigns fields correctly")
    void constructor_assigns_fields() {
        Snickers s = new Snickers(101, 15.0, "Snickers", Size.SMALL);

        assertEquals(101, s.getId());
        assertEquals(15.0, s.getPrice());
        assertEquals("Snickers", s.getProductName());
        assertEquals(Size.SMALL, s.getSize());
    }

    @ParameterizedTest(name = "use() mentions size and name for {0}")
    @EnumSource(Size.class)
    void use_mentions_size_and_name(Size size) {
        Snickers s = new Snickers(102, 19.0, "Snickers", size);
        String msg = s.use().toLowerCase();

        // Should mention "snickers" and the size (label or enum name)
        assertTrue(msg.contains("snickers"), "use() should mention product name");
        assertTrue(
                msg.contains(size.name().toLowerCase()) ||
                        msg.contains(s.getSize().toString().toLowerCase()),
                "use() should mention the size"
        );
    }

    @ParameterizedTest(name = "examine() includes name, size and price for {0}")
    @EnumSource(Size.class)
    void examine_includes_name_size_price(Size size) {
        double price = 25.5;
        Snickers s = new Snickers(103, price, "Snickers", size);
        String line = s.examine().toLowerCase();

        assertTrue(line.contains("snickers"), "examine() should contain product name");
        // accept either enum name or label/toString
        assertTrue(
                line.contains(size.name().toLowerCase()) ||
                        line.contains(size.toString().toLowerCase()),
                "examine() should contain size"
        );
        // basic price presence check (avoid exact formatting assumptions)
        assertTrue(line.contains("25.5") || line.contains("25,5") || line.contains("25.50") || line.contains("25,50"),
                "examine() should contain price");
        assertTrue(line.contains("sek"), "examine() should contain currency SEK");
    }

    @Test
    @DisplayName("Getter and setter for size work")
    void size_getter_setter() {
        Snickers s = new Snickers(104, 12.0, "Snickers", Size.SMALL);
        assertEquals(Size.SMALL, s.getSize());

        s.setSize(Size.LARGE);
        assertEquals(Size.LARGE, s.getSize());
    }

    @Test
    @DisplayName("toString() contains key fields and correct product type")
    void toString_contains_fields() {
        Snickers s = new Snickers(105, 30.0, "Snickers", Size.MEDIUM);
        String t = s.toString();

        // Must not say "MintDrops" (guards against the earlier copy-paste bug)
        assertFalse(t.contains("MintDrops"), "toString() should not contain another product name");

        // Contains essential parts (be lenient on formatting)
        assertTrue(t.contains("ID") || t.toLowerCase().contains("id:"), "ID label present");
        assertTrue(t.contains("Snickers"), "product name present");
        assertTrue(t.toLowerCase().contains("price"), "price label present");
        assertTrue(t.toLowerCase().contains("sek"), "currency present");
        assertTrue(t.toLowerCase().contains("size"), "size label present");
    }
}
