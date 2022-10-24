package item;

import java.util.List;

public class Armor extends Item {

    public Armor(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
        if(anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Defence items can only have red sockets");
        }
    }

    @Override
    public double accept(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets, MagicColor.RED) > 0;
    }

}
