package nicoMap;

import player.Player;
import util.Position;

public abstract class NicoTile {

    Position position;

    NicoTile(int row, int col) {
        position = new Position(row, col);
    }

    public Position getPosition() {
        return position;
    }

    abstract boolean isOccupied();
    abstract void setPlayer(Player p);
    abstract void removePlayer();
    abstract String getSymbol();

    abstract boolean isWalkable();
    abstract boolean isClimbable();

}
