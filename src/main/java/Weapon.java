import java.util.List;

public class Weapon extends Item {
    private final double DEFAULT_COST_OF_USE = 0.5;

    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double attack(){
       return super.attackImplementation();
    }

    public double defend() {
        return super.defendImplementation();
    }
}
