package nicoMap;

import player.Player;

public class NicoFloor extends NicoTile{
    Player player;
    NicoFloor(int row, int col) {
        super(row, col);
    }

    @Override
    void setPlayer(Player p) {
        if(isWalkable()){
            player = p;
        }
    }

    @Override
    void removePlayer() {
        player = null;
    }

    @Override
    public boolean isOccupied(){
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

    @Override
    boolean isWalkable() {
        return true;
    }

    @Override
    boolean isClimbable() {
        return false;
    }
}
