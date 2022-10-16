import java.util.List;

public abstract class Item {

    private final double DEFAULT_COST_OF_USE = 0.5;
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

    void setStrength(double newStrength){
        strength = newStrength;
    }

    double getStrength() {
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
                .filter(socket -> socket.getGemStone()!= null &&socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getCost())
                .sum();
    }

    double getStrengthFromStonesOfColor(MagicColor color) {
        return getSockets().stream()
                .filter(socket -> socket.getGemStone()!= null &&socket.getGemStone().getColor().equals(color))
                .mapToDouble(socket -> socket.getGemStone().getStrength())
                .sum();
    }

    abstract double use(ItemVisitor visitor);

    protected double attackImplementation(){
        double power = getStrength();
        double attackPowerFromStones = getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = getCostFromStonesOfColor(MagicColor.BLUE);

        setStrength(getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * power * (1 + (attackPowerFromStones / 100.0)))/100.0;
    }

    protected double defendImplementation() {
        double power = getStrength();
        double defencePowerFromStones = getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = getCostFromStonesOfColor(MagicColor.RED);

        setStrength(getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100*power * (1 + (defencePowerFromStones / 100)))/100.0;
    }
}
