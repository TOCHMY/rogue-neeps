import java.util.ArrayList;

public class Map {
    private final Tile[][] map2dArray = new Tile[40][100];
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Tunnel> tunnelList = new ArrayList<>();
    private Player player;
    private Position playerPosition;

    Map(){
        fillMapWithTiles();
    }

    private void bindPlayer(Player p){
        player = p;
        p.setMap(this);
    }

    public void setPlayer(Player p, Position pos){
        bindPlayer(p);
        playerPosition = pos;
        map2dArray[pos.row()][pos.col()].setPlayerOnTile(p);
    }



    public void updatePlayerPosition(Direction direction, Player player) {
        Position newPos = playerPosition.newPosition(direction);
        if(map2dArray[newPos.row()][newPos.col()].isWalkable()){
            map2dArray[playerPosition.row()][playerPosition.col()].removePlayerFromTile();
            player.map.setPlayer(player, newPos);
        }
    }



    public void addRoom(Room room) {
        makeTilesBelongToRoom(room);
        roomList.add(room);

    }



    public void updatePlayerPosition(String direction) {

    }


    public Position getPlayerPosition() {
        return playerPosition;
    }


    public void addTunnel(Tunnel tunnel){
        makeTilesBelongToTunnel(tunnel);
        tunnelList.add(tunnel);

    }


    private void makeTilesBelongToTunnel(Tunnel tunnel) {
        makeTilesInTunnelToTunnelTiles(tunnel);
        makeTilesAroundTunnelToTunnelWallTiles(tunnel);

    }

    private void makeTilesAroundTunnelToTunnelWallTiles(Tunnel tunnel) {
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
        for (int i = tunnelStartRow; i < tunnelEndRow; i++) {
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
        for (int i = tunnelStartCol; i < tunnelEndCol; i++) {
            if(i != tunnelStartCol && i != tunnelEndCol){
                map2dArray[row-1][i].makeHorizontalWallTile();
                map2dArray[row+1][i].makeHorizontalWallTile();
            }
        }
    }



    private void makeTilesInTunnelToTunnelTiles(Tunnel tunnel) {
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


    private void fillMapWithTiles(){
        for (int row = 0; row < map2dArray.length; row++) {
            for (int col = 0; col < map2dArray[row].length; col++) {
                Tile tile = new Tile(row, col);
                map2dArray[row][col] = new Tile(row,col);
            }
        }


    }


    public void printTilesInPerimeterArray(){
        for (int row = 0; row < map2dArray.length; row++) {
            for (int col = 0; col < map2dArray[row].length; col++) {
                System.out.println(map2dArray[row][col].toString());
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
                // annars gå igenom raden nedåt.
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    // om det är den första eller sista platsen i raden, gör den till walltile horizontal
                    if(j == startOfRow - 1|| j == endOfRow){
                        map2dArray[j][i].makeHorizontalWallTile();
                    }
                }

            }


        }

    }


    public Tile[][] getMap2dArray() {
        return map2dArray;
    }


    public ArrayList<Room> getRoomList() {
        return roomList;
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
                     } else if(map2dArray[col][row].hasFriendlyNpc){
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
                     if(backroundOnOrOff == "on"){
                         if(numbered == "on"){
                             if(row == 0){
                                 System.out.print(col);
                             }
                         }
                         System.out.print("-");
                     }
                     if(backroundOnOrOff == "off"){
                         if(numbered == "on"){
                             if(row == 0){
                                 System.out.print(col);
                             }
                         }
                         System.out.print(" ");


                     }


                }

            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private void printRoomTiles(Room room, String on) {
        if (on == "on") {
            if (room.getName() == "A") {
                System.out.print("A");
            }
            if (room.getName() == "B") {
                System.out.print("B");
            }
            if (room.getName() == "C") {
                System.out.print("C");
            }
            if (room.getName() == "D") {
                System.out.print("D");
            }
            if (room.getName() == "E") {
                System.out.print("E");
            }
            if (room.getName() == "F") {
                System.out.print("F");
            }
        }
        if(on == "off"){
            if (room.getName() == "A") {
                System.out.print(" ");
            }
            if (room.getName() == "B") {
                System.out.print(" ");
            }
            if (room.getName() == "C") {
                System.out.print(" ");
            }
            if (room.getName() == "D") {
                System.out.print(" ");
            }
            if (room.getName() == "E") {
                System.out.print(" ");
            }
            if (room.getName() == "F") {
                System.out.print(" ");
            }
        }
    }

    ;

    public void printRooms() {
        for (Room room : roomList) {
            System.out.println(room);
        }
    }

    public ArrayList<Tunnel> getTunnelList() {
        return tunnelList;
    }
}
