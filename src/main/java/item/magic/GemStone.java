package item.magic;

public class GemStone {
    private final MagicColor color;
    private final int cost;
    private final int strength;

    public GemStone(MagicColor color, int strength, int cost) {
        if(strength < 1 || strength > 30)
            throw new IllegalArgumentException("Strength must be between 1-30");
        if(cost < 1 || cost > 30)
            throw new IllegalArgumentException("Cost must be between 1-30");

        this.color = color;
        this.cost = cost;
        this.strength = strength;
    }

    public MagicColor getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

    public int getStrength() {
        return strength;
    }
}
