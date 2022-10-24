package item;

import java.util.Collections;
import java.util.List;

public abstract class Item {

    private static final double MAX_BASE_STRENGTH = 100;
    private static final double MIN_BASE_STRENGTH = 1;
    private static final double MAX_AMOUNT_OF_SOCKETS = 5;
    private double baseStrength;
    private final List<MagicSocket> sockets;

    public Item(double baseStrength, List<MagicSocket> sockets) {
        if (baseStrength < MIN_BASE_STRENGTH || baseStrength > MAX_BASE_STRENGTH)
            throw new IllegalArgumentException("Initial base strength must be between 1 to 100");
        if (sockets == null || sockets.isEmpty() || sockets.size() > MAX_AMOUNT_OF_SOCKETS)
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
        return Collections.unmodifiableList(sockets);
    }

    public void addStone(GemStone gemStone) {
        sockets.stream()
                .filter(socket -> socket.getGemStone() == null && socket.getColor() == gemStone.color())
                .findFirst()
                .ifPresentOrElse(
                        socket -> socket.addStone(gemStone),
                        () -> {
                            throw new IllegalArgumentException("There is no empty socket of the right color");
                        });
    }

    protected double getCostFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().color().equals(color))
                .mapToDouble(socket -> socket.getGemStone().cost())
                .sum();
    }

    protected double getStrengthFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone() != null && socket.getGemStone().color().equals(color))
                .mapToDouble(socket -> socket.getGemStone().strength())
                .sum();
    }

    protected long countSocketsOfColor(List<MagicSocket> sockets, MagicColor color) {
        return sockets.stream().filter(socket -> socket.getColor() == color).count();
    }
    abstract boolean anySocketOfWrongColor(List<MagicSocket> sockets);
}
