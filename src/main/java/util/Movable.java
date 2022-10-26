package util;

import map.Tile;

public interface Movable {
    void move(Direction d);
    void move(Tile target, Tile current);
    void move(Tile target);
    void moveTo(Position pos);
}
