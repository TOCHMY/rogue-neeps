package map;

import npc.NPC;
import player.Player;
import util.Direction;
import util.Movable;
import util.Position;

import java.util.ArrayList;
import java.util.Objects;

public class Map {
    public static Position STARTING_POS = new Position(1,1);
    private final Tile[][] map = new Tile[40][100];
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Tunnel> tunnels = new ArrayList<>();
    private Player player;
    private ArrayList<NPC> npcs;

    public Map(){
        fillMapWithTiles();
    }

    public void addNPC(NPC npc){
        npcs.add(npc);
    }
    public void setPlayer(Player p) {
        player = p;
    }
    public void movement(Movable entity){

    }
    public Tile getTile(Position pos){
        return map[pos.row()][pos.col()];
    }

    public Tile getTile(Direction direction){
        Position targetTile = player.getPosition().newPosition(direction);
        return map[targetTile.row()][targetTile.col()];
    }

    private void fillMapWithTiles(){
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = new Tile(new Position(row, col));
            }
        }
    }

    public Player getPlayer(){
        return player;
    }
    public Position getPlayerPosition() {
        return player.getPosition();
    }

    public void addRoom(Room room) {
        makeTilesBelongToRoom(room);
        rooms.add(room);

    }

    public void addTunnel(Tunnel tunnel){
        constructTunnelAndTunnelWalls(tunnel);
        tunnels.add(tunnel);

    }

    private void constructTunnelAndTunnelWalls(Tunnel tunnel) {
        makeTunnelTiles(tunnel);
        makeTunnelWalls(tunnel);
    }

    private void makeTunnelWalls(Tunnel tunnel) {
        if(tunnel.isVerticalTunnel()){
            makeVerticalTunnelWalls(tunnel);
        } else {
            makeHorizontalTunnelWalls(tunnel);
        }

    }

    private void makeVerticalTunnelWalls(Tunnel tunnel) {
        int tunnelStartRow = tunnel.getStartTile().getPosition().row();
        int tunnelEndRow = tunnel.getEndingTile().getPosition().row();
        int col = tunnel.getStartTile().getPosition().col();
        for (int i = tunnelStartRow; i <= tunnelEndRow; i++) {
            if(i != tunnelStartRow && i != tunnelEndRow){
                map[i][col+1].makeVerticalWallTile();
                map[i][col-1].makeVerticalWallTile();
            }
        }
    }

    private void makeHorizontalTunnelWalls(Tunnel tunnel) {
        int tunnelStartCol = tunnel.getStartTile().getPosition().col();
        int tunnelEndCol = tunnel.getEndingTile().getPosition().col();
        int row = tunnel.getStartTile().getPosition().row();
        for (int i = tunnelStartCol; i <= tunnelEndCol; i++) {
            if(i != tunnelStartCol && i != tunnelEndCol){
                map[row-1][i].makeHorizontalWallTile();
                map[row+1][i].makeHorizontalWallTile();
            }
        }
    }

    private void makeTunnelTiles(Tunnel tunnel) {
        Tile start = tunnel.getStartTile();
        Tile end = tunnel.getEndingTile();
        if(tunnel.isVerticalTunnel()){
            for (int i = start.getPosition().row(); i < end.getPosition().row()+1; i++) {
                map[i][start.getPosition().col()].makeTunnelTile(tunnel);
                tunnel.addTile(map[i][start.getPosition().col()]);
            }
        } else {
            // en horisontell tunnel
            for (int i = start.getPosition().col(); i < end.getPosition().col()+1; i++) {
                map[start.getPosition().row()][i].makeTunnelTile(tunnel);
                tunnel.addTile(map[start.getPosition().row()][i]);
            }
        }
    }

    private void makeTilesBelongToRoom(Room room) {
        createWallsAroundRoom(room);
        createRoomTilesInsideRoom(room);
    }

    private void createRoomTilesInsideRoom(Room room) {
        int height = room.getHeight();
        int width = room.getWidth();
        Tile sT = room.getStartingTile();

        int startOfCol = sT.getPosition().col();
        int endOfCol = sT.getPosition().col() + width;

        int startOfRow = sT.getPosition().row();
        int endOfRow = sT.getPosition().row() + height;
        for (int i = startOfCol; i < endOfCol; i++) {
                for (int j = startOfRow; j < endOfRow; j++) {
                    map[j][i].makeRoomTile(room);
                    room.addTileToRoom(map[j][i]);
                }
        }
    }

    private void createWallsAroundRoom(Room room) {
        int height = room.getHeight();
        int width = room.getWidth();
        Tile sT = room.getStartingTile();

        int startOfCol = sT.getPosition().col();
        int endOfCol = sT.getPosition().col() + width;

        int startOfRow = sT.getPosition().row();
        int endOfRow = sT.getPosition().row() + height;
        for (int i = startOfCol - 1; i < endOfCol + 1; i++) {
            // om det är första raden nedåt
            if(i == startOfCol - 1){
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    map[j][i].makeVerticalWallTile();
                }
                // om det är sista raden nedåt
            } else if(i == endOfCol){
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    map[j][i].makeVerticalWallTile();
                }
            } else{
                map[startOfRow-1][i].makeHorizontalWallTile();
                map[endOfRow][i].makeHorizontalWallTile();
            }
        }
    }


    public Tile[][] getMap() {
        return map;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Tunnel> getTunnels(){
        return tunnels;
    }


    public void printDungeon(String roomTilesOnOrOff, String backroundOnOrOff, String numbered) {
        Position playerPos = getPlayerPosition();

        for (int col = 0; col < map.length; col++) {
            for (int row = 0; row < map[col].length-1; row++) {

                if(playerPos.row() == col && playerPos.col() == row) {
                    System.out.print("P");
                } else {
                    System.out.print(map[col][row].symbolPrint());;
                }
            }

            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }


}
