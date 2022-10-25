package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import player.Player;
import util.Position;

import java.util.Objects;

public class Tile {

    private boolean roomTile = false;
    private boolean tunnelTile = false;
    private boolean wallTile = false;
    private boolean horizontalWall = false;
    private boolean verticalWall = false;
    private boolean swampTile = false;
    private boolean waterTile = false;
    private Room room;
    private Tunnel tunnel;
    private Position pos;
    private boolean occupied = false;


    // +1 för att börja räkna spelplanen från X1Y1 istället för X0Y0.
    public Tile(Position pos) {
        this.pos = pos;
    }

    public Tile(int row, int col) {
        this.pos = new Position(row, col);
    }

    public int getRow() {
        return pos.row();
    }

    public int getColumn() {
        return pos.col();
    }

    public void makeRoomTile(Room room) {
        roomTile = true;
        wallTile = false;
        horizontalWall = false;
        verticalWall = false;
        this.room = room;
    }

    public void makeVerticalWallTile() {
        wallTile = true;
        verticalWall = true;
    }

    public void makeHorizontalWallTile() {
        wallTile = true;
        horizontalWall = true;
    }

    public boolean isVerticalWallTile() {
        return verticalWall;
    }

    public boolean isHorizontalWallTile() {
        return horizontalWall;
    }

    public boolean isRoomTile() {
        return roomTile;
    }

    public boolean isOccupied() {
        return occupied;
    }


    public Room getRoom() {
        return room;
    }

    public Position getPosition() {
        return pos;
    }


    public void makeTunnelTile(Tunnel tunnel) {
        this.tunnel = tunnel;
        tunnelTile = true;
        wallTile = false;
        verticalWall = false;
        horizontalWall = false;
        roomTile = false;
    }

    private Tunnel getTunnel() {
        return tunnel;
    }

    public boolean isWallTile() {
        return wallTile;
    }


    public boolean isTunnelTile() {
        return tunnelTile;
    }

    public boolean isSwampTile() {
        return swampTile;
    }

    public boolean isWaterTile() {
        return waterTile;
    }

    public void makeWaterTile() {
        waterTile = true;
        roomTile = false;
        wallTile = false;
        verticalWall = false;
        horizontalWall = false;
        tunnelTile = false;
        swampTile = false;
    }

    public void makeSwampTile() {
        swampTile = true;
        roomTile = false;
        wallTile = false;
        verticalWall = false;
        horizontalWall = false;
        tunnelTile = false;
        waterTile = false;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile otherTile = (Tile) o;
        return this.pos.row() == otherTile.pos.row() && this.pos.row() == otherTile.pos.col();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }

    @Override
    public String toString() {
        if (isRoomTile()) {
            return " ";
        } else if (isHorizontalWallTile()) {
            return "=";
        } else if (isVerticalWallTile()) {
            return "|";
        } else if (isSwampTile()) {
            return "#";
        } else if (isWaterTile()) {
            return "~";
        }
        else return " ";
    }
}
