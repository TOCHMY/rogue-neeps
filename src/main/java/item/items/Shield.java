package item.items;

import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.ItemVisitor;

import java.util.List;

public class Shield extends Item {

    public Shield(double strength, List<MagicSocket> sockets) {
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
    protected boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets, MagicColor.RED) > 0;
    }

}
