package player;


import item.items.Shield;
import item.items.Weapon;
import map.Tile;


import java.util.List;

public class Human extends Player{
    public Human() {
        super(1,1,5, 50);
    }

    @Override
    public boolean canMove(Tile tile) {
        return tile.isRoomTile() || tile.isTunnelTile();

    }
    @Override
    void equip(Weapon weapon) {
    }
    void equip(Shield shield) {
    }

    @Override
    List<Weapon> canEquip() {
        return null;
    }
}
