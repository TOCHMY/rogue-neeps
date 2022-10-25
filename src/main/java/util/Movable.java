package util;

import map.Tile;

public interface Movable {
    void move(Direction d);
    void move(Tile tile);
    void moveTo(Position pos);
}
