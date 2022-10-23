package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import player.Player;

import java.util.Objects;

public class Tile {
    private boolean walkable = false;
    private boolean roomTile = false;
    private boolean tunnelTile = false;
    private boolean wallTile = false;
    private boolean horizontalWall = false;
    private boolean verticalWall = false;
    private boolean hasPlayer = false;
    private boolean hasEnemyNPC = false;
    private EnemyNPC hostileNpc;
    private FriendlyNPC friendlyNpc;
    boolean hasFriendlyNpc = false;
    Player player;
    private Room room;
    private Tunnel tunnel;
    private int row;
    private int column;


    // +1 för att börja räkna spelplanen från X1Y1 istället för X0Y0.
    public Tile(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void makeRoomTile(Room room){
        makeWalkable();
        roomTile = true;
        this.room = room;
    }

    public void makeWalkable() {
        walkable = true;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isRoomTile(){
        return roomTile;
    }

    public Room getRoom() {
        return room;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPlayerOnTile(Player player) {
        this.player = player;
        hasPlayer = true;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }


    public void setEnemyNpcOnTile(EnemyNPC hostile) {
        this.hostileNpc = hostile;
        hasEnemyNPC = true;
        walkable = false;
        //enemyNPC = null;
    }

    public boolean hasEnemyNPC() {
        return hasEnemyNPC;
    }

    public void makeVerticalWallTile() {
        wallTile = true;
        verticalWall = true;
        walkable = false;
    }

    public void makeHorizontalWallTile() {
        wallTile = true;
        horizontalWall = true;
        walkable = false;
    }

    public boolean isVerticalWallTile() {
        return verticalWall;
    }

    public boolean isHorizontalWallTile() {
        return horizontalWall;
    }

    public void makeTunnelTile(Tunnel tunnel) {
        this.tunnel = tunnel;
        tunnelTile = true;
        wallTile = false;
        verticalWall = false;
        horizontalWall = false;
        roomTile = false;
        makeWalkable();
    }

    public boolean isWallTile(){
        return wallTile;
    }

    @Override
    public String toString() {
        return "map.Tile{" +
                "row:" + row +
                ", column:" + column +
                ", walkable=" + walkable +
                ", roomTile=" + roomTile +
                ", hasPlayer=" + hasPlayer +
                ", hasEnemyNPC=" + hasEnemyNPC +
                ", horizontalWall=" + horizontalWall +
                ", verticalWall=" + verticalWall +
                ", room=" + room +
                '}';
    }

    public boolean isTunnelTile() {
        return tunnelTile;
    }

    public void setFriendlyNpcOnTile(FriendlyNPC friendly) {
        this.friendlyNpc = friendly;
        hasFriendlyNpc = true;
        walkable = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile otherTile = (Tile) o;
        return this.row == otherTile.row && this.column == otherTile.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public boolean isOccupied() {
        if(hasFriendlyNpc || hasPlayer || hasEnemyNPC){
            return true;
        }
        return false;
    }

    public EnemyNPC getHostileNPC() {
        return hostileNpc;
    }

    public void removePlayerFromTile() {
            hasPlayer = false;
            player = null;
    }

    // använd denna när vi har en klass för enemyNPC
    /*public void setEnemyNpcOnTile(Enemy enemyNPC) {
        hasEnemyNPC = true;
        this.enemyNPC = enemyNPC;
    }*/
}
