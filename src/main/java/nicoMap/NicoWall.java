package nicoMap;

import player.Human;
import player.Player;

public class NicoWall extends NicoTile{
    Player player;


    NicoWall(int row, int col) {
        super(row, col);
    }
    @Override
    protected boolean isOccupied() {
        if(player != null){
            return true;
        }
        return false;
    }

    @Override
    public void setPlayer(Player p) {

        if(p instanceof Human){
            this.player = p;
        }

    }



    @Override
    public void removePlayer() {
        player = null;
    }

    @Override
    String getSymbol() {
        return "X";
    }

}
