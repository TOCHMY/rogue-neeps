import java.util.List;

public class Shield extends Item{
    public Shield(double strength, List<MagicSocket> sockets) {
        super(strength, sockets);
    }

    @Override
    double use(ItemVisitor visitor) {
        return visitor.visit(this);
    }
}
