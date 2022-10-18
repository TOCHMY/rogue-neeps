import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private int width;
    private int height;
    private Tile startingTile;
    private List<NPC> friendlyNpcList = new ArrayList<>();
    private List<NPC> hostileNpcList = new ArrayList<>();
    private Map map;
    private List<Tile> roomTilesList = new ArrayList<>();


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
        Tile t = map.getPerimeterArray()[npcTile.getRow()][npcTile.getColumn()];

        if(!roomTilesList.contains(npcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        };
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
        Tile t = map.getPerimeterArray()[npcTile.getRow()][npcTile.getColumn()];
        if(!roomTilesList.contains(npcTile)){
            throw new IllegalArgumentException("tile is not a room tile");
        };
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
        return "Room{" +
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
