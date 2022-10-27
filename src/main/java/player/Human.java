package player;

import map.Tile;

public class Human extends Player{
    public Human() {
        super(1,2,5, 50);
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
