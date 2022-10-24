package map;

import java.util.ArrayList;
import java.util.List;

public class Tunnel {
    private final String fromRoom;
    private final String toRoom;
    private final Tile tunnelStart;
    private final Tile tunnelEnd;
    private final List<Tile> tileList = new ArrayList<>();
    private final int length;
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
                length = tunnelEnd.getColumn() - tunnelStart.getColumn();
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
