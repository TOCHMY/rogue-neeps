import java.util.ArrayList;
import java.util.List;

public class Tunnel {
    private String fromRoom;
    private String toRoom;
    private Tile tunnelStart;
    private Tile tunnelEnd;
    private List<Tile> tileList = new ArrayList<>();
    private int length;
    private boolean horizontalTunnel;
    private boolean verticalTunnel;



    public Tunnel(String fromRoom, String toRoom, Tile tunnelStart, Tile tunnelEnd) throws IllegalArgumentException{
        if(tunnelStart.isWallTile() && tunnelEnd.isWallTile()){
            this.fromRoom = fromRoom;
            this.toRoom = toRoom;
            this.tunnelStart = tunnelStart;
            this.tunnelEnd = tunnelEnd;
            if(tunnelStart.isHorizontalWallTile()){
                verticalTunnel = true;
                length = tunnelEnd.getRow() - tunnelStart.getRow();
            } else {
                horizontalTunnel = true;
                length = tunnelEnd.getColumn() - tunnelEnd.getColumn();
            }
        } else{
            throw new IllegalArgumentException("A tunnel must be initiated from a walltile");
        }

    }


    public Tile getStartTile() {
        return tunnelStart;
    }

    public Tile getEndingTile(){
        return tunnelEnd;
    }

    public String getFromRoom() {
        return fromRoom;
    }

    public String getToRoom() {
        return toRoom;
    }

    public boolean isHorizontalTunnel() {
        return horizontalTunnel;
    }

    public boolean isVerticalTunnel() {
        return verticalTunnel;
    }

    public int getLength() {
        if(verticalTunnel){
            return 1 + (tunnelEnd.getRow() - tunnelStart.getRow());
        } else {
            return 1 + (tunnelEnd.getColumn() - tunnelStart.getColumn());
        }
    }

    public void addTile(Tile tile) {
        tileList.add(tile);
    }

    public List<Tile> getTileList() {
        return tileList;
    }
}
