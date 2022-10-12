import java.util.List;

public abstract class Item {

    private static final double DEFAULT_COST_OF_USE = 0.5;
    private double strength;
    private List<MagicSocket> sockets;

    public Item(double strength, List<MagicSocket> sockets) {
        if (strength < 1 || strength > 100)
            throw new IllegalArgumentException("Strength must be between 1 to 100");
        if (sockets.isEmpty() || sockets.size() > 5)
            throw new IllegalArgumentException("Number of sockets must be between 1 to 5");
        this.strength = strength;
        this.sockets = sockets;
    }

    public double use(){
        double power = this.strength;
        this.strength -= DEFAULT_COST_OF_USE;
        return power;
    }


    double getStrength() {
        return this.strength;
    }

    public List<MagicSocket> getSockets() {
        return sockets;
    }

    void die() {
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
}
