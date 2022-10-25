package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.NPC;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final int width;
    private final int height;
    private final Tile startingTile;
    private final List<NPC> friendlyNpcList = new ArrayList<>();
    private final List<NPC> hostileNpcList = new ArrayList<>();
    private final Map map;
    private final List<Tile> roomTilesList = new ArrayList<>();


    public Room(String name, int width, int height, Tile startingTile, Map map) {
        this.map = map;
        this.name = name;
        this.width = width;
        this.height = height;
        this.startingTile = startingTile;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public Tile getStartingTile() {
        return startingTile;
    }

    public void addHostileNpc(EnemyNPC npc, Position requestedPositionForNpc) throws IllegalArgumentException{
        Tile requestedTileForNpc = map.getMap2dArray()[requestedPositionForNpc.row()][requestedPositionForNpc.col()];

        if(!roomTilesList.contains(requestedTileForNpc)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedTileForNpc.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        requestedTileForNpc.setEnemyNpcOnTile(npc);
        hostileNpcList.add(npc);
    }

    public List<NPC> getHostileNpcList(){
        return hostileNpcList;
    }

    public void addFriendlyNpc(FriendlyNPC npc, Position requestedNpcPosition)throws IllegalArgumentException{
        Tile requestedNpcTile = map.getMap2dArray()[requestedNpcPosition.row()][requestedNpcPosition.col()];
        if(!roomTilesList.contains(requestedNpcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedNpcTile.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        requestedNpcTile.setFriendlyNpcOnTile(npc);
        friendlyNpcList.add(npc);

    }

    public List<NPC> getFriendlyNpc(){
        return friendlyNpcList;
    }

    @Override
    public String toString() {
        return "map.Room{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", startingTile=" + startingTile +
                '}';
    }

    public List<Tile> getRoomTilesList() {
        return roomTilesList;
    }

    public void addTileToRoom(Tile tile) {
        roomTilesList.add(tile);
    }
}
