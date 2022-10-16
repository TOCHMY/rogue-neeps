import java.util.List;

public class Armor extends Item {

    public Armor(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    @Override
    double use(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    public double defend() {
        return super.defendImplementation();
    }
}
