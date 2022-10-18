import java.util.ArrayList;

public class Map {
    private final Tile[][] perimeterArray = new Tile[40][100];
    private Tile tileWithPlayerOn;
    private ArrayList<Tile> enemyNpcPositionsArray;
    private ArrayList<Tile> friendlyNpcPositionsArray;
    private ArrayList<Room> roomList;
    private ArrayList<Tunnel> tunnelList;

    Player player;

    Map(){
        fillMapWithTiles();
    }

    public void setPlayer(Player p){
        player = p;
        p.setMap(this);
    }

    public void addPlayer(Player p){
        setPlayer(p);
        /*
        Vill kunna sätta ut playerTile efter instansiering,
        det gör också testarna mer intressanta, tex ska det kanske inte gå att
        sätta ut gubben i en vägg eller utanför ett rum.

        setPlayerPosition([0][5]); // setPlayerPosition kanske inte ska ta in en Tile eftersom
        #1: vad är en tile/ hur vet man vad nästa tile är
        #2: den bör placeras på en tile (position)
        */
    }
    public void updatePlayerPosition(Direction d){

        if(d == Direction.UP){
            //Player tile = [++row][col]
        }
        if(d == Direction.DOWN){

        }
        if(d == Direction.LEFT){

        }
        if(d == Direction.RIGHT){

        }
    }

    public void initiateDungeon(Tile playerStartingTile) {
        createRooms();
        createTunnelsBetweenRooms();
        spawnEnemyNpcs();
        spawnFriendlyNpcs();
        // setPlayerPosition(playerStartingTile); // byt denna
        //setEnemyNpcPositions();
        //setFriendlyNpcPositions();

    }

    private void spawnFriendlyNpcs() {
        friendlyNpcPositionsArray = new ArrayList<>();

        // Friendly 1 Room A
        perimeterArray[9][8].setFriendlyNpcOnTile();
        friendlyNpcPositionsArray.add(perimeterArray[9][8]);

        // Friendly 2 Room A
        perimeterArray[9][10].setFriendlyNpcOnTile();
        friendlyNpcPositionsArray.add(perimeterArray[9][10]);

        // Friendly 3 Room B
        perimeterArray[4][80].setFriendlyNpcOnTile();
        friendlyNpcPositionsArray.add(perimeterArray[4][80]);

        // Friendly 4 Room E
        perimeterArray[37][8].setFriendlyNpcOnTile();
        friendlyNpcPositionsArray.add(perimeterArray[37][8]);



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
        int row = tileWithPlayerOn.getRow();
        int col = tileWithPlayerOn.getColumn();
        this.tileWithPlayerOn = tileWithPlayerOn;
        perimeterArray[row][col].setPlayerOnTile();
    }

    public Tile getPlayerPosition() {
        return tileWithPlayerOn;
    }

    private void createTunnelsBetweenRooms() {
        tunnelList = new ArrayList<>();

        Tunnel tunnel1 = makeTunnelBetweenAandC();
        makeTilesBelongToTunnel(tunnel1);
        tunnelList.add(tunnel1);

        Tunnel tunnel2 = makeTunnelBetweenBandD();
        makeTilesBelongToTunnel(tunnel2);
        tunnelList.add(tunnel2);

        Tunnel tunnel3 = makeTunnelBetweenDandF();
        makeTilesBelongToTunnel(tunnel3);
        tunnelList.add(tunnel3);

        Tunnel tunnel4 = makeTunnelBetweenEandF();
        makeTilesBelongToTunnel(tunnel4);
        tunnelList.add(tunnel4);

        Tunnel tunnel5 = makeTunnelBetweenCandD();
        makeTilesBelongToTunnel(tunnel5);
        tunnelList.add(tunnel5);
    }

    private Tunnel makeTunnelBetweenCandD() {
        Tile tunnelStart = perimeterArray[17][49];
        Tile tunnelEnd = perimeterArray[17][59];
        Tunnel tunnel = new Tunnel("C", "D",tunnelStart,tunnelEnd);
        return tunnel;
    }


    private Tunnel makeTunnelBetweenAandC() {
        Tile tunnelStart = perimeterArray[10][31];
        Tile tunnelEnd = perimeterArray[13][31];
        Tunnel tunnel = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        return tunnel;
    }

    private Tunnel makeTunnelBetweenBandD() {
        Tile tunnelStart = perimeterArray[10][72];
        Tile tunnelEnd = perimeterArray[13][72];
        Tunnel tunnel = new Tunnel("B", "D",tunnelStart,tunnelEnd);
        return tunnel;
    }

    private Tunnel makeTunnelBetweenDandF() {
        Tile tunnelStart = perimeterArray[23][72];
        Tile tunnelEnd = perimeterArray[26][72];
        Tunnel tunnel = new Tunnel("D", "F",tunnelStart,tunnelEnd);
        return tunnel;
    }

    private Tunnel makeTunnelBetweenEandF() {
        Tile tunnelStart = perimeterArray[29][32];
        Tile tunnelEnd = perimeterArray[29][54];
        Tunnel tunnel = new Tunnel("E", "F",tunnelStart,tunnelEnd);
        return tunnel;
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


    private void createRooms() {
        roomList = new ArrayList<>();

        Room roomA = new Room("A", 27,8, new Tile(2, 7));
        makeTilesBelongToRoom(roomA);
        roomList.add(roomA);

        Room roomB = new Room("B", 31,8, new Tile(2, 56));
        makeTilesBelongToRoom(roomB);
        roomList.add(roomB);

        Room roomC = new Room("C", 23,6, new Tile(14, 26));
        makeTilesBelongToRoom(roomC);
        roomList.add(roomC);

        Room roomE = new Room("E", 25,14, new Tile(24, 7));
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

        int startOfCol = sT.getColumn();
        int endOfCol = sT.getColumn() + width;

        int startOfRow = sT.getRow();
        int endOfRow = sT.getRow() + height;
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
                        System.out.print("H");
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

    public ArrayList<Tunnel> getTunnelList() {
        return tunnelList;
    }
}
