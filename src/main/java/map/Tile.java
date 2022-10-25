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
    private boolean hasFriendlyNpc = false;
    private Player player;
    private Room room;
    private Tunnel tunnel;
    private final int row;
    private final int column;


    // +1 för att börja räkna spelplanen från X1Y1 istället för X0Y0.
    public Tile(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void makeRoomTile(Room room){
        makeWalkable();
        roomTile = true;
        wallTile = false;
        horizontalWall = false;
        verticalWall = false;
        this.room = room;
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

    public void makeWalkable() {
        walkable = true;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isRoomTile(){
        return roomTile;
    }

    public boolean isOccupied() {
        return hasFriendlyNpc || hasPlayer || hasEnemyNPC;
    }

    public boolean hasEnemyNPC() {
        return hasEnemyNPC;
    }

    public boolean hasFriendlyNpc(){
        return hasFriendlyNpc;
    }

    public boolean hasPlayer() {
        return hasPlayer;
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
        walkable = false;
    }

    public void removePlayerFromTile() {
        hasPlayer = false;
        player = null;
    }

    public void setEnemyNpcOnTile(EnemyNPC hostile) {
        this.hostileNpc = hostile;
        hasEnemyNPC = true;
        walkable = false;
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

    private Tunnel getTunnel(){
        return tunnel;
    }

    public boolean isWallTile(){
        return wallTile;
    }


    public boolean isTunnelTile() {
        return tunnelTile;
    }

    public void setFriendlyNpcOnTile(FriendlyNPC friendly) {
        this.friendlyNpc = friendly;
        hasFriendlyNpc = true;
        walkable = false;
    }

    public FriendlyNPC getFriendlyNpc(){
        return friendlyNpc;
    }
    
    public boolean hasFriendlyNPC(){
        return hasFriendlyNpc;
    }


    public EnemyNPC getHostileNPC() {
        return hostileNpc;
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
}
