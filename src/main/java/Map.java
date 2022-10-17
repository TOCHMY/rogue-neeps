import java.util.ArrayList;

public class Map {
    private final Tile[][] perimeterArray = new Tile[40][100];
    private Tile tileWithPlayerOn;
    private ArrayList<Tile> enemyNpcPositionsArray;
    private ArrayList<Tile> friendlyNpcPositionsArray;
    private ArrayList<Room> roomList;

    Map(){
        fillMapWithTiles();
    }

    public void initiateDungeon(Tile playerStartingTile) {
        createRooms();
        spawnEnemyNpcs();
        spawnFriendlyNpcs();
        setPlayerPosition(playerStartingTile); // byt denna
        //setEnemyNpcPositions();
        //setFriendlyNpcPositions();
        createPathsBetweenRooms();

    }

    private void spawnFriendlyNpcs() {
        friendlyNpcPositionsArray = new ArrayList<>();
    }


    private void setFriendlyNpcPositions() {
        friendlyNpcPositionsArray = new ArrayList<>();
    }

    public ArrayList<Tile> getFriendlyNpcPositionsArray() {
        return friendlyNpcPositionsArray;
    }

    private void setEnemyNpcPositions() {
        enemyNpcPositionsArray = new ArrayList<>();
    }

    public ArrayList<Tile> getEnemyNpcPositionsArray() {
        return enemyNpcPositionsArray;
    }

    public void setPlayerPosition(Tile tileWithPlayerOn) {
        tileWithPlayerOn.setPlayerOnTile();
        int x = tileWithPlayerOn.getX();
        int y = tileWithPlayerOn.getY();
        this.tileWithPlayerOn = tileWithPlayerOn;
        perimeterArray[x][y].setPlayerOnTile();
    }

    public Tile getPlayerPosition() {
        return tileWithPlayerOn;
    }

    private static void createPathsBetweenRooms() {
    }

    private void createRooms() {
        roomList = new ArrayList<>();

        Room roomA = new Room("A", 23,8, new Tile(2, 7));
        makeTilesBelongToRoom(roomA);
        roomList.add(roomA);

        Room roomB = new Room("B", 31,8, new Tile(2, 56));
        makeTilesBelongToRoom(roomB);
        roomList.add(roomB);

        Room roomC = new Room("C", 23,6, new Tile(13, 26));
        makeTilesBelongToRoom(roomC);
        roomList.add(roomC);

        Room roomE = new Room("E", 25,14, new Tile(22, 7));
        makeTilesBelongToRoom(roomE);
        roomList.add(roomE);

        Room roomD = new Room("D", 23,9, new Tile(14, 60));
        makeTilesBelongToRoom(roomD);
        roomList.add(roomD);

        Room roomF = new Room("F", 40,9, new Tile(27, 55));
        makeTilesBelongToRoom(roomF);
        roomList.add(roomF);



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

        int startOfCol = sT.getY();
        int endOfCol = sT.getY() + width;

        int startOfRow = sT.getX();
        int endOfRow = sT.getX() + height;
        for (int i = startOfCol; i < endOfCol; i++) {
                for (int j = startOfRow; j < endOfRow; j++) {
                    perimeterArray[j][i].makeRoomTile(room);
                }
        }
    }




    private void createWallsAroundRoom(Room room) {
        int height = room.getHeight();
        int width = room.getWidth();
        Tile sT = room.getStartingTile();

        int startOfCol = sT.getY();
        int endOfCol = sT.getY() + width;

        int startOfRow = sT.getX();
        int endOfRow = sT.getX() + height;
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
                         System.out.print("H");
                     } else {
                         printRoomTiles(room, roomTilesOnOrOff);
                     }

                } else if(perimeterArray[col][row].isVerticalWallTile()){
                     System.out.print("|");
                 }else if(perimeterArray[col][row].isHorizontalWallTile()){
                     System.out.print("=");
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

    public void spawnEnemyNpcs() {
        enemyNpcPositionsArray = new ArrayList<>();

        // Enemy 1 Room A
        perimeterArray[3][15].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[3][15]);

        // Enemy 2 ROOM A
        perimeterArray[4][28].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[4][28]);


        // Enemy 3 ROOM B
        perimeterArray[4][65].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[4][65]);


        // Enemy 4 ROOM B
        perimeterArray[7][66].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[7][66]);

        // Enemy 5 ROOM F
        perimeterArray[9][70].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[9][70]);

        // Enemy 6 ROOM F
        perimeterArray[16][70].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[16][70]);


        // Enemy 7 ROOM E
        perimeterArray[30][75].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[30][75]);


        // Enemy 8 ROOM E
        perimeterArray[34][70].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[34][70]);


        // Enemy 9
        perimeterArray[19][70].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[19][70]);

        // Enemy 10
        perimeterArray[34][19].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[34][19]);

        // Enemy 11
        perimeterArray[27][15].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[27][15]);

        // Enemy 12
        perimeterArray[25][13].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[25][13]);


        // Enemy 13 för D
        perimeterArray[15][30].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[15][30]);

        // Enemy 14 för D
        perimeterArray[17][44].setEnemyNpcOnTile();
        enemyNpcPositionsArray.add(perimeterArray[17][44]);


    }
}
