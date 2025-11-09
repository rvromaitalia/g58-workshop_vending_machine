package se.lexicon.products;

/**
 * Represents human-friendly size variants for products (e.g., chocolate bars, drinks).
 * <p>
 * Each constant has a display label intended for UI messages and listings.
 * </p>
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * Size s = Size.SMALL;
 * System.out.println(s.getLabel()); // "small"
 * System.out.println(s);            // "small" (toString returns label)
 *
 * Size parsedA = Size.from("SMALL");        // SMALL
 * Size parsedB = Size.from("extra large");  // XL
 * }</pre>
 */
public enum Size {

    /** Small size variant. */
    SMALL("small"),

    /** Medium size variant. */
    MEDIUM("medium"),

    /** Large size variant. */
    LARGE("large"),

    /** Extra large size variant. */
    XL("extra large");

    /** Human-readable label for UI (e.g., "small", "extra large"). */
    private final String label;

    Size(String label) {
        this.label = label;
    }

    /**
     * Returns the human-readable label of this size (e.g., "small").
     *
     * @return the display label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Backward-compatible alias for {@link #getLabel()}.
     * Prefer {@link #getLabel()} in new code.
     *
     * @return the display label
     * @deprecated Use {@link #getLabel()} instead.
     */
    @Deprecated
    public String getSize() {
        return getLabel();
    }

    /**
     * Returns the label as this enum's string representation.
     *
     * @return the display label
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Parses a size from either an enum name (e.g., "SMALL", "XL")
     * or a label (e.g., "small", "extra large"), case-insensitive.
     *
     * @param value the input text (enum name or label)
     * @return the matching {@code Size}
     * @throws IllegalArgumentException if the value cannot be parsed
     */
    public static Size from(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("value must not be null/blank");
        }
        String normalized = value.trim();

        // Try enum names first (SMALL / MEDIUM / LARGE / XL)
        try {
            return Size.valueOf(normalized.replace(' ', '_').toUpperCase());
        } catch (IllegalArgumentException ignored) {
            // fall through to label matching
        }

        // Try labels ("small", "extra large")
        for (Size s : values()) {
            if (s.label.equalsIgnoreCase(normalized)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown size: " + value);
    }
}
