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

        if(tile.isTunnelTile() || tile.isRoomTile()){
            return true;
        }
        else if(tile.isSwampTile()){
            return stats.getStrength() >= 20;
        }
        else if(tile.isWaterTile()){
            return stats.getStrength() >= 10;
        }
        return false;
    }

}
