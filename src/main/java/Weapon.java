import java.util.List;

public class Weapon extends Item {

    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
        if(anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Attack-Defence items can only have blue and red stones");
        }
    }

    @Override
    double use(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets, MagicColor.BLUE) - countSocketsOfColor(sockets,MagicColor.RED) > 0;
    }
}
