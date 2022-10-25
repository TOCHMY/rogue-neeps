package nicoMap;

import player.Player;

public class Swamp extends NicoTile{
    Player player;

    public Swamp(int row, int col) {
        super(row, col);
    }

    @Override
    protected boolean isOccupied() {
        if(player != null){
            return true;
        }
        return false;    }


    @Override
    void setPlayer(Player p) {
        player = p;
    }

    @Override
    void removePlayer() {
        player = null;
    }

    @Override
    String getSymbol() {
        return null;
    }

}
