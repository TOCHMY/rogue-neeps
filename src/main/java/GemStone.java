public class GemStone {
    private final MagicColor color;
    private final int cost;
    private final int strength;

    public GemStone(MagicColor color, int strength, int cost) {
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
