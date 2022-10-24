package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.NPC;

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

    public void addHostileNpc(EnemyNPC npc, Tile npcTile) throws IllegalArgumentException{
        Tile t = map.getMap2dArray()[npcTile.getRow()][npcTile.getColumn()];

        if(!roomTilesList.contains(npcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(t.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        t.setEnemyNpcOnTile(npc);
        hostileNpcList.add(npc);
    }

    public List<NPC> getHostileNpcList(){
        return hostileNpcList;
    }

    public void addFriendlyNpc(FriendlyNPC npc, Tile npcTile)throws IllegalArgumentException{
        Tile t = map.getMap2dArray()[npcTile.getRow()][npcTile.getColumn()];
        if(!roomTilesList.contains(npcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        }
        if(t.isOccupied()){
            throw new IllegalArgumentException("tile is already occupied");
        }
        t.setFriendlyNpcOnTile(npc);
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
