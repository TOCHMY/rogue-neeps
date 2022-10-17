import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    final int expectedHeight = 40;
    final int expectedWidth = 100;
    final Tile playerStartingTile = new Tile(5,19);


    @Test
    void When_DungeonIsNotInitiated_Expect_heightAndWidthOfPerimeterArrayCorrect() {
        Map map = new Map();
        int width = map.getPerimeterArray()[0].length;
        int height = map.getPerimeterArray().length;
        assertEquals(expectedHeight, height);
        assertEquals(expectedWidth, width);
    }



    @Test
    void When_DungeonIsNotInitiated_And_MapSizeIs40x100_Expect_firstTile0X0Y(){
        Map map = new Map();
        Tile firstTile = map.getPerimeterArray()[0][0];
        assertEquals(0, firstTile.getRow());
        assertEquals(0, firstTile.getColumn());
    }



    @Test
    void When_DungeonIsNotInitiated_And_MapSizeIs40x100_Expect_lastTile39X99Y(){
        Map map = new Map();
        int width = map.getPerimeterArray()[0].length;
        int height = map.getPerimeterArray().length;
        Tile lastTile = map.getPerimeterArray()[height-1][width-1];
        assertEquals(39, lastTile.getRow());
        assertEquals(99, lastTile.getColumn());
    }


    @Test
    void When_DungeonIsNotInitiated_Expect_perimeterLengthToBe40(){
        Map map = new Map();
        assertEquals(40, map.getPerimeterArray().length);
    }

    @Test
    void When_DungeonIsNotInitiated_Expect_perimeterWidthToBe100(){
        Map map = new Map();
        assertEquals(100, map.getPerimeterArray()[0].length);
    }

    @Test
    void When_DungeonIsNotInitiated_Expect_playerPositionToBeNull(){
        Map map = new Map();
        assertNull(map.getPlayerPosition());
    }

    @Test
    void When_DungeonIsNotInitiated_Expect_EnemyNpcPositionsListToBeNull(){
        Map map = new Map();
        assertNull(map.getEnemyNpcPositionsArray());
    }

    @Test
    void When_DungeonIsNotInitiated_Expect_FriendlyNpcPositionsListToBeNull(){
        Map map = new Map();
        assertNull(map.getFriendlyNpcPositionsArray());
    }

    @Test
    void When_DungeonIsInitiated_Expect_playerPositionNotNull(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertNotNull(map.getPlayerPosition());
    }

    @Test
    void When_DungeonIsIniated_Expect_playerStartingPositionCorrect(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertEquals(5,playerStartingTile.getRow());
        assertEquals(19,playerStartingTile.getColumn());
    }

    @Test
    void When_DungeonIsInitiated_Expect_EnemyNpcPositionsListNotNull(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertNotNull(map.getEnemyNpcPositionsArray());
    }

    @Test
    void When_DungeonIsInitiated_Expect_FriendlyNpcPositionsListNotNull(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertNotNull(map.getFriendlyNpcPositionsArray());
    }

    @Test
    void When_DungeonIsInitiated_Expect_RoomListSizeIsSix(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertEquals(6, map.getRoomList().size());
    }

    @Test
    void When_EnemyNpcAreSpawned_Expect_EnemyNpcArraySizeToBe14(){
        Map map = new Map();
        map.spawnEnemyNpcs();
        assertEquals(14, map.getEnemyNpcPositionsArray().size());
    }

    @Test
    void When_DungeonIsInitiated_Expect_WallTilesInRightLocation(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tile roomAtopLeftVerticalWallTile = map.getPerimeterArray()[1][6];
        Tile roomAtopLeftHorizontalWallTile = map.getPerimeterArray()[1][7];
        assertEquals(true, roomAtopLeftVerticalWallTile.isVerticalWallTile());
        assertEquals(true, roomAtopLeftHorizontalWallTile.isHorizontalWallTile());

    }
    @Test
    void When_DungeonIsInitiated_Expect_VerticalWallTileNotWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tile knownVerticalWalltile = map.getPerimeterArray()[1][7];
        assertEquals(false, knownVerticalWalltile.isWalkable());
    }

    @Test
    void When_DungeonIsInitiated_Expect_HorizontalWallTileNotWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tile knownHorizontalWalltile = map.getPerimeterArray()[1][8];
        assertEquals(false, knownHorizontalWalltile.isWalkable());
    }

    @Test
    void When_DungeonIsInitiated_Expect_roomTileToBeWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tile knownRoomTile = map.getPerimeterArray()[2][8];
        assertEquals(true, knownRoomTile.isWalkable());
    }

    @Test
    void When_DungeonIsInitiated_Expect_enemyNpcInRightLocation(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tile knownEnemyNpcTile = map.getPerimeterArray()[3][15];
        assertEquals(3, knownEnemyNpcTile.getRow());
        assertEquals(15, knownEnemyNpcTile.getColumn());
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelStartWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel aToC = map.getTunnelList().get(0);
        Tile startTile = aToC.getStartTile();

        assertEquals(true, startTile.isWalkable());
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelEndWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel tunnelAtoD = map.getTunnelList().get(0);
        Tile endTile = tunnelAtoD.getEndingTile();

        assertEquals(true, endTile.isWalkable());
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelTraversable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel tunnelAtoD = map.getTunnelList().get(0);
        Tile startTile = tunnelAtoD.getStartTile();
        int col = startTile.getColumn();
        int row = startTile.getRow();
        System.out.println("Tunnellängd: " + tunnelAtoD.getLength());

        // tunneln A till C är 4 lång
        Tile tunnelTile1 = tunnelAtoD.getStartTile();
        Tile tunnelTile2 = map.getPerimeterArray()[row+1][col];
        Tile tunnelTile3 = map.getPerimeterArray()[row+2][col];
        Tile tunnelTile4 = tunnelAtoD.getEndingTile();


        assertEquals(true, tunnelTile1.isWalkable());
        assertEquals(true, tunnelTile2.isWalkable());
        assertEquals(true, tunnelTile3.isWalkable());
        assertEquals(true, tunnelTile4.isWalkable());

        // kolla så att startTile är rätt
        assertEquals(tunnelAtoD.getStartTile(), map.getPerimeterArray()[row][col]);
        assertEquals(tunnelAtoD.getEndingTile(), map.getPerimeterArray()[row+3][col]);


    }

    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelStartWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel tunnelEtoF = map.getTunnelList().get(3);
        Tile startTile = tunnelEtoF.getStartTile();

        assertEquals(true, startTile.isWalkable());
    }

    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelEndWalkable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel tunnelEtoF = map.getTunnelList().get(3);
        Tile endTile = tunnelEtoF.getEndingTile();

        assertEquals(true, endTile.isWalkable());
    }

    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelTraversable(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        Tunnel tunnelCtoD = map.getTunnelList().get(4);
        Tile startTile = tunnelCtoD.getStartTile();
        int col = startTile.getColumn();
        int row = startTile.getRow();
        System.out.println("Tunnellängd: " + tunnelCtoD.getLength());

        // tunneln C till D är 11 lång
        Tile tunnelTile1 = tunnelCtoD.getStartTile();
        Tile tunnelTile2 = map.getPerimeterArray()[row][col+1];
        Tile tunnelTile3 = map.getPerimeterArray()[row][col+2];
        Tile tunnelTile4 = map.getPerimeterArray()[row][col+3];
        Tile tunnelTile5 = map.getPerimeterArray()[row][col+4];
        Tile tunnelTile6 = map.getPerimeterArray()[row][col+5];
        Tile tunnelTile7 = map.getPerimeterArray()[row][col+6];
        Tile tunnelTile8 = map.getPerimeterArray()[row][col+7];
        Tile tunnelTile9 = map.getPerimeterArray()[row][col+8];
        Tile tunnelTile10 = map.getPerimeterArray()[row][col+9];
        Tile tunnelTile11 = tunnelCtoD.getEndingTile();

        assertEquals(true, tunnelTile1.isWalkable());
        assertEquals(true, tunnelTile2.isWalkable());
        assertEquals(true, tunnelTile3.isWalkable());
        assertEquals(true, tunnelTile4.isWalkable());
        assertEquals(true, tunnelTile5.isWalkable());
        assertEquals(true, tunnelTile6.isWalkable());
        assertEquals(true, tunnelTile7.isWalkable());
        assertEquals(true, tunnelTile8.isWalkable());
        assertEquals(true, tunnelTile9.isWalkable());
        assertEquals(true, tunnelTile10.isWalkable());
        assertEquals(true, tunnelTile11.isWalkable());

        // kolla så start och endtile är rätt
        assertEquals(tunnelCtoD.getStartTile(), map.getPerimeterArray()[row][col]);
        assertEquals(tunnelCtoD.getEndingTile(), map.getPerimeterArray()[row][col+10]);
    }






    /** Nedan är metoder för att printa kartan i olika former, samt metod för att printa info om rum **/

    /** printar bakgrundskartan, så kallad perimeterArray. Denna innehåller inte rum. För rum måste Map.iniateDungeon() köras **/
    @Test
    void printPerimieterArray_When_DungeonNotInitiated(){
        Map map = new Map();
        for (int i = 0; i < map.getPerimeterArray().length-1; i++) {
            for (int j = 0; j < map.getPerimeterArray()[i].length-1; j++) {
                System.out.print("-");
            }
            System.out.println("");
        }
    };

    /** printar kartan när dungeon är spawnad. Rum, väggar, npcs, players existerar är.
      välj att sätta on or off för roomtiles och backgroundtiles när printDungeon() kallas på **/
    @Test
    void printDungeon_When_DungenonIsInitiated(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        map.printDungeon("off","on","off");

    }

    /** printar info om de rum som skapats **/
    @Test
    void printAllRoomsInRoomListTest(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        map.printRooms();
    }

    /** printar info om varje tile som existerar på kartan, när dungeon INTE ÄR spawnad **/
    @Test
    void When_DungeonNotIniated_printAllTilesInPerimeterArray(){
        Map map = new Map();
        map.printTilesInPerimeterArray();
    }

    /** printar info om varje tile som existerar på kartan, dungeon ÄR spawnad **/
    @Test
    void When_DungeonIsIniated_printAllTilesInPerimeterArray(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        map.printTilesInPerimeterArray();
    }





}





