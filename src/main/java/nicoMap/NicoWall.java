package nicoMap;

import player.Human;
import player.Player;

public class NicoWall extends NicoTile{
    Player player;


    NicoWall(int row, int col) {
        super(row, col);
    }

    @Override
    boolean isOccupied() {
        if(player != null){
            return true;
        }
        return false;
    }

    @Override
    void setPlayer(Player p) {

        if(p instanceof Human){
            this.player = p;

        }

    }
    @Override
    void removePlayer() {
        player = null;
    }

    @Override
    String getSymbol() {
        if(player != null){
            return "P";
        }
        return "X";
    }

    @Override
    boolean isWalkable() {
        return false;
    }

    @Override
    boolean isClimbable() {
        return true;
    }
}
