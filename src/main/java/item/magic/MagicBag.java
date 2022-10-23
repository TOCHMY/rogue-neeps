package item.magic;

import item.Item;
import item.ItemVisitor;

import java.util.List;

public class MagicBag extends Item {
    public MagicBag(double strength, List<MagicSocket> sockets) {
        super(strength, sockets);
        if(anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Magic items can only have purple sockets");
        }
    }

    @Override
    protected double accept(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    protected boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets,MagicColor.PURPLE) > 0;
    }
}
