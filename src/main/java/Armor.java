import java.util.List;

public class Armor extends Item {
    private final double DEFAULT_COST_OF_USE = 0.5;

    public Armor(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double defend() {
        return super.defendImplementation();
    }
}
