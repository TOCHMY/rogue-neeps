import java.util.List;

public class MagicBag extends Item {
    public MagicBag(double strength, List<MagicSocket> sockets) {
        super(strength, sockets);
        if(anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Magic items can only have purple sockets");
        }
    }

    @Override
    double accept(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets,MagicColor.PURPLE) > 0;
    }
}
