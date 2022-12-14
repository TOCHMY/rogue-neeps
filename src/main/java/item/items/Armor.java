package item.items;

import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.ItemVisitor;

import java.util.List;

public class Armor extends Item {

    public Armor(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
        if(anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Defence items can only have red or green sockets");
        }
    }

    @Override
    public double accept(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    protected boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size()
                - countSocketsOfColor(sockets, MagicColor.RED)
                - countSocketsOfColor(sockets, MagicColor.GREEN) > 0;
    }

}
