package map;

import util.Position;

public class HorizontalWall extends Tile2{
    HorizontalWall(Position p) {
        super(p);
    }

    @Override
    String getSymbol() {
        return "=";
    }
}
