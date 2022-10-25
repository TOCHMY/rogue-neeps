package player;

import item.items.Shield;
import item.items.Weapon;
import map.Tile;
import java.util.List;

public class Ogre extends Player{
    public Ogre() {
        super(5, 1, 1, 100);
    }

    @Override
    public boolean canMove(Tile tile) {
        return tile.isRoomTile() || tile.isTunnelTile();
    }

    @Override
    void equip(Weapon weapon){

    }
    @Override
    void equip(Shield shield) {

    }
    @Override
    List<Weapon> canEquip() {
        return null;
    }
}

