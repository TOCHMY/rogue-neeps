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
        assertEquals(0, firstTile.getX());
        assertEquals(0, firstTile.getY());
    }



    @Test
    void When_DungeonIsNotInitiated_And_MapSizeIs40x100_Expect_lastTile39X99Y(){
        Map map = new Map();
        int width = map.getPerimeterArray()[0].length;
        int height = map.getPerimeterArray().length;
        Tile lastTile = map.getPerimeterArray()[height-1][width-1];
        assertEquals(39, lastTile.getX());
        assertEquals(99, lastTile.getY());
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
        assertEquals(5,playerStartingTile.getX());
        assertEquals(19,playerStartingTile.getY());
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
        assertEquals(3, knownEnemyNpcTile.getX());
        assertEquals(15, knownEnemyNpcTile.getY());
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





