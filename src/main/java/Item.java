import java.util.List;

public abstract class Item {

    private static final double MAX_STRENGTH = 100;
    private static final double MIN_STRENGTH = 1;
    private static final double MAX_AMOUNT_OF_SOCKETS = 5;
    private double strength;
    private List<MagicSocket> sockets;

    public Item(double strength, List<MagicSocket> sockets) {
        if (strength < MIN_STRENGTH || strength > MAX_STRENGTH)
            throw new IllegalArgumentException("Strength must be between 1 to 100");
        if (sockets.isEmpty() || sockets.size() > MAX_AMOUNT_OF_SOCKETS)
            throw new IllegalArgumentException("Number of sockets must be between 1 to 5");
        this.strength = strength;
        this.sockets = sockets;
    }

    public void setStrength(double newStrength) {
        strength = newStrength;
    }

    public double getStrength() {
        return this.strength;
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

    double getCostFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getCost())
                .sum();
    }

    double getStrengthFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getStrength())
                .sum();
    }

    abstract double accept(ItemVisitor visitor);

    protected long countSocketsOfColor(List<MagicSocket> sockets, MagicColor color) {
        return sockets.stream().filter(socket -> socket.getColor() == color).count();
    }

    abstract boolean anySocketOfWrongColor(List<MagicSocket> sockets);

}
