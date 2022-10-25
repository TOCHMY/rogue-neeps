package map;

import util.Position;

public class Floor extends Tile2{
    Floor(Position p) {
        super(p);
    }

    @Override
    String getSymbol() {
        return "";
    }
}
