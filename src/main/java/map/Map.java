package map;

import player.Player;
import util.Direction;
import util.Position;

import java.util.ArrayList;
import java.util.Objects;

public class Map {
    private final Tile[][] map2dArray = new Tile[40][100];
    private final ArrayList<Room> roomList = new ArrayList<>();
    private final ArrayList<Tunnel> tunnelList = new ArrayList<>();
    private Player player;


    public Map(){
        fillMapWithTiles();
    }

    private void fillMapWithTiles(){
        for (int row = 0; row < map2dArray.length; row++) {
            for (int col = 0; col < map2dArray[row].length; col++) {
                map2dArray[row][col] = new Tile(row,col);
            }
        }
    }


    public void setPlayer(Player p, Position pos){
        player = p;
        p.setMap(this);
        player.setPosition(pos);
        map2dArray[pos.row()][pos.col()].setPlayerOnTile(p);
    }

    public Player getPlayer(){
        return player;
    }



    public void updatePlayerPosition(Direction direction, Player player) {
        Position newPos = player.getPosition().newPosition(direction);
        if(map2dArray[newPos.row()][newPos.col()].isWalkable()){
            map2dArray[player.getPosition().row()][player.getPosition().col()].removePlayerFromTile();
            setPlayer(player, newPos);
        }
    }

    public Position getPlayerPosition() {
        return player.getPosition();
    }

    public void addRoom(Room room) {
        makeTilesBelongToRoom(room);
        roomList.add(room);

    }

    public void addTunnel(Tunnel tunnel){
        constructTunnelAndTunnelWalls(tunnel);
        tunnelList.add(tunnel);

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
        int tunnelStartRow = tunnel.getStartTile().getRow();
        int tunnelEndRow = tunnel.getEndingTile().getRow();
        int col = tunnel.getStartTile().getColumn();
        for (int i = tunnelStartRow; i <= tunnelEndRow; i++) {
            if(i != tunnelStartRow && i != tunnelEndRow){
                map2dArray[i][col+1].makeVerticalWallTile();
                map2dArray[i][col-1].makeVerticalWallTile();
            }
        }
    }

    private void makeHorizontalTunnelWalls(Tunnel tunnel) {
        int tunnelStartCol = tunnel.getStartTile().getColumn();
        int tunnelEndCol = tunnel.getEndingTile().getColumn();
        int row = tunnel.getStartTile().getRow();
        for (int i = tunnelStartCol; i <= tunnelEndCol; i++) {
            if(i != tunnelStartCol && i != tunnelEndCol){
                map2dArray[row-1][i].makeHorizontalWallTile();
                map2dArray[row+1][i].makeHorizontalWallTile();
            }
        }
    }

    private void makeTunnelTiles(Tunnel tunnel) {
        Tile start = tunnel.getStartTile();
        Tile end = tunnel.getEndingTile();
        if(tunnel.isVerticalTunnel()){
            for (int i = start.getRow(); i < end.getRow()+1; i++) {
                map2dArray[i][start.getColumn()].makeTunnelTile(tunnel);
                tunnel.addTile(map2dArray[i][start.getColumn()]);
            }
        } else {
            // en horisontell tunnel
            for (int i = start.getColumn(); i < end.getColumn()+1; i++) {
                map2dArray[start.getRow()][i].makeTunnelTile(tunnel);
                tunnel.addTile(map2dArray[start.getRow()][i]);
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

        int startOfCol = sT.getColumn();
        int endOfCol = sT.getColumn() + width;

        int startOfRow = sT.getRow();
        int endOfRow = sT.getRow() + height;
        for (int i = startOfCol; i < endOfCol; i++) {
                for (int j = startOfRow; j < endOfRow; j++) {
                    map2dArray[j][i].makeRoomTile(room);
                    room.addTileToRoom(map2dArray[j][i]);
                }
        }
    }

    private void createWallsAroundRoom(Room room) {
        int height = room.getHeight();
        int width = room.getWidth();
        Tile sT = room.getStartingTile();

        int startOfCol = sT.getColumn();
        int endOfCol = sT.getColumn() + width;

        int startOfRow = sT.getRow();
        int endOfRow = sT.getRow() + height;
        for (int i = startOfCol - 1; i < endOfCol + 1; i++) {
            // om det är första raden nedåt
            if(i == startOfCol - 1){
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    map2dArray[j][i].makeVerticalWallTile();
                }
                // om det är sista raden nedåt
            } else if(i == endOfCol){
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    map2dArray[j][i].makeVerticalWallTile();
                }
            } else{
                map2dArray[startOfRow-1][i].makeHorizontalWallTile();
                map2dArray[endOfRow][i].makeHorizontalWallTile();

                /*// annars gå igenom raden nedåt.
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    // om det är den första eller sista platsen i raden, gör den till walltile horizontal
                    if(j == startOfRow - 1|| j == endOfRow){
                        map2dArray[j][i].makeHorizontalWallTile();
                    }
                }*/
            }
        }
    }


    public Tile[][] getMap2dArray() {
        return map2dArray;
    }


    public void printDungeon(String roomTilesOnOrOff, String backroundOnOrOff, String numbered) {
        for (int col = 0; col < map2dArray.length; col++) {
            for (int row = 0; row < map2dArray[col].length-1; row++) {
                 if(map2dArray[col][row].isRoomTile()){
                    Room room = map2dArray[col][row].getRoom();
                     if(map2dArray[col][row].hasPlayer()){
                         System.out.print("P");
                     }else if(map2dArray[col][row].hasEnemyNPC()) {
                         if(map2dArray[col][row].getHostileNPC().isMeleeEnemy()){
                             System.out.print("M");
                         } else {
                             System.out.print("R");
                         }
                     } else if(map2dArray[col][row].hasFriendlyNPC()){
                         System.out.print("F");
                     } else {
                         printRoomTiles(room, roomTilesOnOrOff);
                     }

                } else if(map2dArray[col][row].isVerticalWallTile()){
                     System.out.print("|");
                 }else if(map2dArray[col][row].isHorizontalWallTile()){
                     System.out.print("=");
                 } else if(map2dArray[col][row].isTunnelTile()) {
                     System.out.print(" ");
                 } else {
                     if(Objects.equals(backroundOnOrOff, "on")){
                         if(Objects.equals(numbered, "on")){
                             if(row == 0){
                                 System.out.print(col);
                             }
                         }
                         System.out.print("-");
                     }
                     if(Objects.equals(backroundOnOrOff, "off")){
                         if(Objects.equals(numbered, "on")){
                             if(row == 0){
                                 System.out.print(col);
                             }
                         }
                         System.out.print(" ");


                     }


                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void printRoomTiles(Room room, String on) {
        if (Objects.equals(on, "on")) {
            if (Objects.equals(room.getName(), "A")) {
                System.out.print("A");
            }
            if (Objects.equals(room.getName(), "B")) {
                System.out.print("B");
            }
            if (Objects.equals(room.getName(), "C")) {
                System.out.print("C");
            }
            if (Objects.equals(room.getName(), "D")) {
                System.out.print("D");
            }
            if (Objects.equals(room.getName(), "E")) {
                System.out.print("E");
            }
            if (Objects.equals(room.getName(), "F")) {
                System.out.print("F");
            }
        }
        if(Objects.equals(on, "off")){
            if (Objects.equals(room.getName(), "A")) {
                System.out.print(" ");
            }
            if (Objects.equals(room.getName(), "B")) {
                System.out.print(" ");
            }
            if (Objects.equals(room.getName(), "C")) {
                System.out.print(" ");
            }
            if (Objects.equals(room.getName(), "D")) {
                System.out.print(" ");
            }
            if (Objects.equals(room.getName(), "E")) {
                System.out.print(" ");
            }
            if (Objects.equals(room.getName(), "F")) {
                System.out.print(" ");
            }
        }
    }

}
