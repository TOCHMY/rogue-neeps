package item.weapon;

import item.Item;
import item.ItemVisitor;
import item.magic.MagicColor;
import item.magic.MagicSocket;

import java.util.List;

public class Weapon extends Item {

    public Weapon(double strength, List<MagicSocket> sockets) {
        super(strength, sockets);
        if (anySocketOfWrongColor(sockets)) {
            throw new IllegalArgumentException("Attack-Defence items can only have blue and red stones");
        }
    }

    @Override
    public double accept(ItemVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean anySocketOfWrongColor(List<MagicSocket> sockets) {
        return sockets.size() - countSocketsOfColor(sockets, MagicColor.BLUE) - countSocketsOfColor(sockets, MagicColor.RED) > 0;
    }
}
