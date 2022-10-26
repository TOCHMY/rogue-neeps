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
    private final List<Tile> waterTileList = new ArrayList<>();
    private final List<Tile> roomTilesList = new ArrayList<>();
    private final Map map;


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

    public void addHostileNpc(NPC npc, Position requestedPositionForNpc) throws IllegalArgumentException{
        Tile requestedTileForNpc = map.getMap()[requestedPositionForNpc.row()][requestedPositionForNpc.col()];
        npc.move(requestedTileForNpc);

        if(!roomTilesList.contains(requestedTileForNpc)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedTileForNpc.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        hostileNpcList.add(npc);
    }

    public List<NPC> getHostileNpcList(){
        return hostileNpcList;
    }

    public void addFriendlyNpc(FriendlyNPC npc, Position requestedNpcPosition)throws IllegalArgumentException{
        Tile requestedNpcTile = map.getMap()[requestedNpcPosition.row()][requestedNpcPosition.col()];
        if(!roomTilesList.contains(requestedNpcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedNpcTile.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        friendlyNpcList.add(npc);

    }

    public void addWaterTile(Position requestedWaterPosition){
        Tile requesteWaterTile = map.getMap()[requestedWaterPosition.row()][requestedWaterPosition.col()];
        if(!roomTilesList.contains(requesteWaterTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requesteWaterTile.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        requesteWaterTile.makeWaterTile();
        waterTileList.add(requesteWaterTile);
    }

    public void addSwampTile(Position requestedSwampPosition){
        Tile requestedSwampTile = map.getMap()[requestedSwampPosition.row()][requestedSwampPosition.col()];
        if(!roomTilesList.contains(requestedSwampTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedSwampTile.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        requestedSwampTile.makeSwampTile();
        waterTileList.add(requestedSwampTile);
    }

    public List<NPC> getFriendlyNpc(){
        return friendlyNpcList;
    }

    public List<Tile> getRoomTilesList() {
        return roomTilesList;
    }

    public void addTileToRoom(Tile tile) {
        roomTilesList.add(tile);
    }
}
