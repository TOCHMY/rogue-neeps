package map;

import util.Position;

public class VerticalWall extends Tile2{

    VerticalWall(Position p) {
        super(p);
    }

    @Override
    String getSymbol() {
        return "|";
    }
}
