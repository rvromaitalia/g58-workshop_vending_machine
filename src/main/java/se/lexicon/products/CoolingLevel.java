package se.lexicon.products;

/**
 * Enum representing the cooling intensity of mint drops.
 */
public enum CoolingLevel {
    MILD("Mild"),
    SMOOTH("Smooth"),
    STRONG("Strong"),
    INTENSE("Intense"),
    EXTREME("Extreme");

    private final String label;

    CoolingLevel(String label) {
        this.label = label;
    }

    /**
     * Returns the human-readable label for the cooling level.
     *
     * @return label as string
     */
    public String getLabel() {
        return label;
    }

    /**
     * Converts an integer (1–5) to the corresponding CoolingLevel enum.
     *
     * @param level integer level
     * @return CoolingLevel enum
     */
    public static CoolingLevel fromInt(int level) {
        switch (level) {
            case 1: return MILD;
            case 2: return SMOOTH;
            case 3: return STRONG;
            case 4: return INTENSE;
            case 5: return EXTREME;
            default:
                throw new IllegalArgumentException("Cooling level must be between 1 and 5.");
        }
    }
}