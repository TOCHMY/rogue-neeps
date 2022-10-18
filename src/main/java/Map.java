import java.util.ArrayList;

public class Map {
    private final Tile[][] perimeterArray = new Tile[40][100];
    private Tile tileWithPlayerOn;
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Tunnel> tunnelList = new ArrayList<>();

    Map(){
        fillMapWithTiles();
    }

    public void initiateDungeon(Tile playerStartingTile) {
       // spawnFriendlyNpcs();
        updatePlayerPosition(playerStartingTile); // byt denna
        //setEnemyNpcPositions();
        //setFriendlyNpcPositions();

    }

    public void addRoom(Room room) {
        makeTilesBelongToRoom(room);
        roomList.add(room);

    }





    public void updatePlayerPosition(Tile tileWithPlayerOn) {
        tileWithPlayerOn.setPlayerOnTile();
        int row = tileWithPlayerOn.getRow();
        int col = tileWithPlayerOn.getColumn();
        this.tileWithPlayerOn = tileWithPlayerOn;
        perimeterArray[row][col].setPlayerOnTile();
    }

    public void updatePlayerPosition(String direction) {

    }


    public Tile getPlayerPosition() {
        return tileWithPlayerOn;
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
                perimeterArray[i][col+1].makeVerticalWallTile();
                perimeterArray[i][col-1].makeVerticalWallTile();
            }
        }
    }

    private void makeHorizontalTunnelWalls(Tunnel tunnel) {
        int tunnelStartCol = tunnel.getStartTile().getColumn();
        int tunnelEndCol = tunnel.getEndingTile().getColumn();
        int row = tunnel.getStartTile().getRow();
        for (int i = tunnelStartCol; i < tunnelEndCol; i++) {
            if(i != tunnelStartCol && i != tunnelEndCol){
                perimeterArray[row-1][i].makeHorizontalWallTile();
                perimeterArray[row+1][i].makeHorizontalWallTile();
            }
        }
    }



    private void makeTilesInTunnelToTunnelTiles(Tunnel tunnel) {
        Tile start = tunnel.getStartTile();
        Tile end = tunnel.getEndingTile();
        if(tunnel.isVerticalTunnel()){
            for (int i = start.getRow(); i < end.getRow()+1; i++) {
                perimeterArray[i][start.getColumn()].makeTunnelTile(tunnel);
                tunnel.addTile(perimeterArray[i][start.getColumn()]);
            }
        } else {
            // en horisontell tunnel
            for (int i = start.getColumn(); i < end.getColumn()+1; i++) {
                perimeterArray[start.getRow()][i].makeTunnelTile(tunnel);
                tunnel.addTile(perimeterArray[start.getRow()][i]);
            }
        }
    }


    private void fillMapWithTiles(){
        for (int row = 0; row < perimeterArray.length; row++) {
            for (int col = 0; col < perimeterArray[row].length; col++) {
                Tile tile = new Tile(row, col);
                perimeterArray[row][col] = new Tile(row,col);
            }
        }


    }


    public void printTilesInPerimeterArray(){
        for (int row = 0; row < perimeterArray.length; row++) {
            for (int col = 0; col < perimeterArray[row].length; col++) {
                System.out.println(perimeterArray[row][col].toString());
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
                    perimeterArray[j][i].makeRoomTile(room);
                    room.addTileToRoom(perimeterArray[j][i]);
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
                    perimeterArray[j][i].makeVerticalWallTile();
                }
                // om det är sista raden nedåt
            } else if(i == endOfCol){
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    perimeterArray[j][i].makeVerticalWallTile();
                }
            } else{
                // annars gå igenom raden nedåt.
                for (int j = startOfRow - 1; j < endOfRow + 1; j++) {
                    // om det är den första eller sista platsen i raden, gör den till walltile horizontal
                    if(j == startOfRow - 1|| j == endOfRow){
                        perimeterArray[j][i].makeHorizontalWallTile();
                    }
                }

            }


        }

    }


    public Tile[][] getPerimeterArray() {
        return perimeterArray;
    }


    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void printDungeon(String roomTilesOnOrOff, String backroundOnOrOff, String numbered) {
        for (int col = 0; col < perimeterArray.length; col++) {
            for (int row = 0; row < perimeterArray[col].length-1; row++) {
                 if(perimeterArray[col][row].isRoomTile()){
                    Room room = perimeterArray[col][row].getRoom();
                     if(perimeterArray[col][row].hasPlayer()){
                         System.out.print("P");
                     }else if(perimeterArray[col][row].hasEnemyNPC()) {
                         if(perimeterArray[col][row].getHostileNPC().isMeleeEnemy()){
                             System.out.print("M");
                         } else {
                             System.out.print("R");
                         }
                     } else if(perimeterArray[col][row].hasFriendlyNpc){
                         System.out.print("F");
                     } else {
                         printRoomTiles(room, roomTilesOnOrOff);
                     }

                } else if(perimeterArray[col][row].isVerticalWallTile()){
                     System.out.print("|");
                 }else if(perimeterArray[col][row].isHorizontalWallTile()){
                     System.out.print("=");
                 } else if(perimeterArray[col][row].isTunnelTile()) {
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
