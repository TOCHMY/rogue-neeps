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

    public Tunnel(String fromRoom, String toRoom, Tile tunnelStart, Tile tunnelEnd) {
        if(tunnelStart.isWallTile() && tunnelEnd.isWallTile()){
            this.fromRoom = fromRoom;
            this.toRoom = toRoom;
            this.tunnelStart = tunnelStart;
            this.tunnelEnd = tunnelEnd;
            if(tunnelStart.isHorizontalWallTile()){
                verticalTunnel = true;
                length = tunnelEnd.getPosition().row()+1 - tunnelStart.getPosition().row();
            } else {
                horizontalTunnel = true;
                length = tunnelEnd.getPosition().col()+1 - tunnelStart.getPosition().col();
            }
        } else{
            throw new IllegalArgumentException("A tunnel must be initiated from a walltile & end on a walltile");
        }

    }

    public boolean isVerticalTunnel() {
        return verticalTunnel;
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

    public int getLength() {
       return length;
    }

    public void addTile(Tile tile) {
        tileList.add(tile);
    }

    public List<Tile> getTileList() {
        return tileList;
    }
}
