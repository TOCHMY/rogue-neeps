import java.util.List;

public class MagicBag extends Item {
    public MagicBag(double strength, List<MagicSocket> sockets) {
        super(strength, sockets);
    }

    @Override
    double use(ItemVisitor visitor) {
        return 0;
    }
}
