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
    private final List<Tile> swampTileList = new ArrayList<>();
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

    public void addHostileNpc(EnemyNPC npc, Position requestedPositionForNpc){
        for(NPC npcloop : map.npcs){
            if(npcloop.equals(npc)){
                Tile requestedTileForNpc = map.getMap()[requestedPositionForNpc.row()][requestedPositionForNpc.col()];
                if(!roomTilesList.contains(requestedTileForNpc)){
                    throw new IllegalArgumentException("tile is not a room tile");
                }
                if(requestedTileForNpc.isOccupied()){
                    throw new IllegalArgumentException("tile is already occupied");
                }
                npc.moveTo(requestedPositionForNpc);
                hostileNpcList.add(npc);
            }
        }
    }

    public List<NPC> getHostileNpcList(){
        return hostileNpcList;
    }

    public void addFriendlyNpc(FriendlyNPC npc, Position requestedPositionForNpc){
        for(NPC npcloop : map.npcs){
            if(npcloop.equals(npc)) {
                Tile requestedNpcTile = map.getMap()[requestedPositionForNpc.row()][requestedPositionForNpc.col()];
                if (!roomTilesList.contains(requestedNpcTile)) {
                    throw new IllegalArgumentException("tile is not a room tile");
                }
                if (requestedNpcTile.isOccupied()) {
                    throw new IllegalArgumentException("tile is already occupied");
                }
                npc.moveTo(requestedPositionForNpc);
                friendlyNpcList.add(npc);
            }
        }
    }

    public void addWaterTile(Position requestedWaterPosition){
        Tile requestedWaterTile = map.getMap()[requestedWaterPosition.row()][requestedWaterPosition.col()];
        if(!roomTilesList.contains(requestedWaterTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedWaterTile.isWaterTile() || requestedWaterTile.isSwampTile()){
            throw new IllegalArgumentException("tile is already a swamp or a waterTile");
        }

        requestedWaterTile.makeWaterTile();
        waterTileList.add(requestedWaterTile);
    }

    public void addSwampTile(Position requestedSwampPosition){
        Tile requestedSwampTile = map.getMap()[requestedSwampPosition.row()][requestedSwampPosition.col()];
        if(!roomTilesList.contains(requestedSwampTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(requestedSwampTile.isWaterTile() || requestedSwampTile.isSwampTile()){
            throw new IllegalArgumentException("tile is already a swamp or a waterTile");
        }
        requestedSwampTile.makeSwampTile();
        swampTileList.add(requestedSwampTile);
    }

    public List<NPC> getFriendlyNpcs(){
        return friendlyNpcList;
    }

    public List<Tile> getRoomTilesList() {
        return roomTilesList;
    }

    public void addTileToRoom(Tile tile) {
        roomTilesList.add(tile);
    }
}
