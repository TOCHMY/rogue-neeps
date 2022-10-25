package nicoMap;

import player.Player;

public class NicoFloor extends NicoTile{
    Player player;
    NicoFloor(int row, int col) {
        super(row, col);
    }


    void setPlayer(Player p) {
        if(!isOccupied()){
            player = p;
        }
    }

    void removePlayer() {
        player = null;
    }

    protected boolean isOccupied(){
        if(player != null){
            return true;
        }
        return false;
    }

    @Override
    String getSymbol() {
        if(player != null){
            return "P";
        }
        return " ";
    }

}
