package player;

import map.Tile;

public class Ogre extends Player{
    public Ogre() {
        super(5, 1, 1, 100);
    }

    @Override
    public boolean canMove(Tile tile) {
        if(tile.isTunnelTile() || tile.isRoomTile()){
            return true;
        }
        else if(tile.isSwampTile()){
            return stats.getStrength() >= 10;
        }
        return false;
    }

}

