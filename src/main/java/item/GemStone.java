package item;

public record GemStone(MagicColor color, int strength, double cost) {
    private static final int MIN_STRENGTH = 1;
    private static final int MAX_STRENGTH = 30;
    private static final double MIN_COST = 0;
    private static final double MAX_COST = 30;
    public GemStone {
        if (strength < MIN_STRENGTH || strength > MAX_STRENGTH)
            throw new IllegalArgumentException("Strength must be between 1-30");
        if (cost < MIN_COST || cost > MAX_COST)
            throw new IllegalArgumentException("Cost must be between 1-30");

    }
}
