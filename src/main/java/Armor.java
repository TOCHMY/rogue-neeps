import java.util.List;

public class Armor extends Item {

    public Armor(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double defend() {
        return super.defendImplementation();
    }
}
