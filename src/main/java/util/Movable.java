package util;

import map.Tile;

public interface Movable {
    void move(Direction d);
    void move(Tile target, Tile current);
    void moveTo(Position pos);
}
