import java.util.List;

public abstract class Item {

    private static final double MAX_BASE_STRENGTH = 100;
    private static final double MIN_BASE_STRENGTH = 1;
    private static final double MAX_AMOUNT_OF_SOCKETS = 5;
    private double baseStrength;
    private final List<MagicSocket> sockets;

    public Item(double baseStrength, List<MagicSocket> sockets) {
        if (baseStrength < MIN_BASE_STRENGTH || baseStrength > MAX_BASE_STRENGTH)
            throw new IllegalArgumentException("Strength must be between 1 to 100");
        if (sockets.isEmpty() || sockets.size() > MAX_AMOUNT_OF_SOCKETS)
            throw new IllegalArgumentException("Number of sockets must be between 1 to 5");
        this.baseStrength = baseStrength;
        this.sockets = sockets;
    }

    abstract double accept(ItemVisitor visitor);

    public void setBaseStrength(double newStrength) {
        baseStrength = newStrength;
    }

    public double getBaseStrength() {
        return this.baseStrength;
    }

    public List<MagicSocket> getSockets() {
        return sockets;
    }

    public void addStone(GemStone gemStone) {
        sockets.stream()
                .filter(socket -> socket.getGemStone() == null && socket.getColor() == gemStone.getColor())
                .findFirst()
                .ifPresentOrElse(
                        socket -> socket.addStone(gemStone),
                        () -> {
                            throw new IllegalArgumentException("There is no empty socket of the right color");
                        });
    }

    protected double getCostFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getCost())
                .sum();
    }

    protected double getStrengthFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getStrength())
                .sum();
    }

    protected long countSocketsOfColor(List<MagicSocket> sockets, MagicColor color) {
        return sockets.stream().filter(socket -> socket.getColor() == color).count();
    }
    abstract boolean anySocketOfWrongColor(List<MagicSocket> sockets);
}
