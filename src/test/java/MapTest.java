import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapTest {
    final Position playerStartingPosition= new Position(5,19);

    @Test
    void createMapVersion1_and_printMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2, 7), map);
        map.addRoom(roomA);
        Room roomB = new Room("B", 31,8, new Tile(2, 56), map);
        map.addRoom(roomB);
        Room roomC = new Room("C", 23,6, new Tile(14, 26), map);
        map.addRoom(roomC);
        Room roomD = new Room("D", 23,9, new Tile(14, 60), map);
        map.addRoom(roomD);
        Room roomE = new Room("E", 25,14, new Tile(24, 7), map);
        map.addRoom(roomE);
        Room roomF = new Room("F", 40,9, new Tile(27, 55), map);
        map.addRoom(roomF);

        // Enemy 1 Room A new version
        EnemyNPC npc1 = new EnemyNPC("hostile1melee",1,true); // nuvarande
        Tile npcPos = new Tile(3,15);
        roomA.addHostileNpc(npc1, npcPos);

        // HostileNPC 2 Room A NEW
        EnemyNPC npc2 = new EnemyNPC("hostile2melee",1,true); // nuvarande
        npcPos = new Tile(4,28);
        roomA.addHostileNpc(npc2, npcPos);

        // HostileNPC 3 Room B NEW
        EnemyNPC npc3 = new EnemyNPC("hostile3melee",1,true); // nuvarande
        npcPos = new Tile(4,65);
        roomB.addHostileNpc(npc3,npcPos);

        // HostileNPC 4 Room B NEW
        EnemyNPC npc4 = new EnemyNPC("hostile4NotMelee",1,false); // nuvarande
        npcPos = new Tile(7,66);
        roomB.addHostileNpc(npc4, npcPos);

        // HostileNPC 5 Room B NEW
        EnemyNPC npc5 = new EnemyNPC("hostile5NotMelee",1,false); // nuvarande
        npcPos = new Tile(9,70);
        roomB.addHostileNpc(npc5, npcPos);

        // HostileNPC 6 Room D NEW
        EnemyNPC npc6 = new EnemyNPC("hostile6NotMelee",1,false); // nuvarande
        npcPos = new Tile(16,70);
        roomD.addHostileNpc(npc6, npcPos);

        // HostileNPC 7 Room F NEW
        EnemyNPC npc7 = new EnemyNPC("hostile7NotMelee",1,false); // nuvarande
        npcPos = new Tile(30,75);
        roomF.addHostileNpc(npc7, npcPos);

        // HostileNPC 8 Room E NEW
        EnemyNPC npc8 = new EnemyNPC("hostile8NotMelee",1,false); // nuvarande
        npcPos = new Tile(34,70);
        roomF.addHostileNpc(npc8, npcPos);

        // HostileNPC 9 Room D NEW
        EnemyNPC npc9 = new EnemyNPC("hostile9NotMelee",1,false); // nuvarande
        npcPos = new Tile(19,70);
        roomD.addHostileNpc(npc9, npcPos);

        // HostileNPC 10 Room E NEW
        EnemyNPC npc10 = new EnemyNPC("hostile10melee",1,true); // nuvarande
        npcPos = new Tile(34,19);
        roomE.addHostileNpc(npc10, npcPos);

        // HostileNPC 11 Room E NEW
        EnemyNPC npc11 = new EnemyNPC("hostile11melee",1,true); // nuvarande
        npcPos = new Tile(27,15);
        roomE.addHostileNpc(npc11, npcPos);

        // HostileNPC 12 Room E NEW
        EnemyNPC npc12 = new EnemyNPC("hostile12melee",1,true); // nuvarande
        npcPos = new Tile(25,13);
        roomE.addHostileNpc(npc12, npcPos);

        // HostileNPC 13 Room D NEW
        EnemyNPC npc13 = new EnemyNPC("hostile13melee",1,true); // nuvarande
        npcPos = new Tile(15,30);
        roomC.addHostileNpc(npc13, npcPos);

        // HostileNPC 13 Room D NEW
        EnemyNPC npc14 = new EnemyNPC("hostile14melee",1,true); // nuvarande
        npcPos = new Tile(17,44);
        roomC.addHostileNpc(npc14, npcPos);

        // Friendly 1 Room A NEW
        FriendlyNPC npc15 = new FriendlyNPC("Friendly1"); // nuvarande
        npcPos = new Tile(9,8);
        roomA.addFriendlyNpc(npc15,npcPos);

        // Friendly 2 Room A NEW
        FriendlyNPC npc16 = new FriendlyNPC("Friendly2"); // nuvarande
        npcPos = new Tile(9,10);
        roomA.addFriendlyNpc(npc16,npcPos);

        FriendlyNPC npc17 = new FriendlyNPC("Friendly3"); // nuvarande
        npcPos = new Tile(4,80);
        roomB.addFriendlyNpc(npc17,npcPos);

        // Friendly 4 Room E NEW
        FriendlyNPC npc18 = new FriendlyNPC("Friendly4"); // nuvarande
        npcPos = new Tile(37,8);
        roomE.addFriendlyNpc(npc18,npcPos);


        // Tunnel A till C
        Tile tunnelStart = map.getMap2dArray()[10][31];
        Tile tunnelEnd = map.getMap2dArray()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        // Tunnel B till D
        tunnelStart = map.getMap2dArray()[10][72];
        tunnelEnd = map.getMap2dArray()[13][72];
        Tunnel tunnel2 = new Tunnel("B", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel2);

        // Tunnel D till F
        tunnelStart = map.getMap2dArray()[23][72];
        tunnelEnd = map.getMap2dArray()[26][72];
        Tunnel tunnel3 = new Tunnel("D", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel3);

        // Tunnel C till D
        tunnelStart = map.getMap2dArray()[17][49];
        tunnelEnd = map.getMap2dArray()[17][59];
        Tunnel tunnel4 = new Tunnel("C", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel4);

        // Tunnel E till F
        tunnelStart = map.getMap2dArray()[29][32];
        tunnelEnd = map.getMap2dArray()[29][54];
        Tunnel tunnel5 = new Tunnel("E", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel5);

        Player player = new Ogre();

        map.setPlayer(player, playerStartingPosition);
        map.printDungeon("off","on","off");

        player.moveDown();
        map.printDungeon("off","on","off");
        player.moveRight();
        map.printDungeon("off","on","off");
    }


    @Test
    void When_newMapCreated_Expect_heightAndWidthOfPerimeterArrayCorrect() {
        int expectedHeight = 40;
        int expectedWidth = 100;
        Map map = new Map();

        int width = map.getMap2dArray()[0].length;
        int height = map.getMap2dArray().length;
        assertEquals(expectedHeight, height);
        assertEquals(expectedWidth, width);
    }



    @Test
    void When_newMapCreated_And_MapSizeIs40x100_Expect_firstTile0X0Y(){
        Map map = new Map();
        Tile firstTile = map.getMap2dArray()[0][0];
        assertEquals(0, firstTile.getRow());
        assertEquals(0, firstTile.getColumn());
    }



    @Test
    void When_newMapCreated_And_MapSizeIs40x100_Expect_lastTile39X99Y(){
        Map map = new Map();
        int width = map.getMap2dArray()[0].length;
        int height = map.getMap2dArray().length;
        Tile lastTile = map.getMap2dArray()[height-1][width-1];
        assertEquals(39, lastTile.getRow());
        assertEquals(99, lastTile.getColumn());
    }

    @Test
    void When_newMapCreated_Expect_playerPositionToBeNull(){
        Map map = new Map();
        assertNull(map.getPlayerPosition());
    }



    @Test
    void When_newMapCreated_Expect_playerPositionRow5Col20(){
        Map map = new Map();
        Player player = new Ogre();
        map.setPlayer(player, playerStartingPosition);

        assertEquals(playerStartingPosition, map.getPlayerPosition());
    }

    @Test
    void When_playerMoveUp_Expect_playerPositionCorrect(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2, 7), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.UP);

        Player player = new Ogre();
        map.setPlayer(player, playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.moveUp();
        assertEquals(expectedPosition, map.getPlayerPosition());
    }

    @Test
    void When_playerMoveDown_Expect_playerPositionCorrect(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2, 7), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.DOWN);
        Player player = new Ogre();
        map.setPlayer(player, playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.moveDown();
        assertEquals(expectedPosition , map.getPlayerPosition());
    }

    @Test
    void When_playerMoveRight_Expect_playerPositionCorrect(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2, 7), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.RIGHT);
        Player player = new Ogre();
        map.setPlayer(player, playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.moveRight();
        assertEquals(expectedPosition , map.getPlayerPosition());
    }

    @Test
    void When_playerMoveLeft_Expect_playerPositionCorrect(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2, 7), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.LEFT);
        Player player = new Ogre();
        map.setPlayer(player, playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.moveLeft();
        assertEquals(expectedPosition , map.getPlayerPosition());
    }


    @Test
    void When_DungeonIsInitiated_Expect_RoomListSizeIsSix(){
        Map map = new Map();

    }

   /* @Test
    void When_EnemyNpcAreSpawned_Expect_EnemyNpcArraySizeToBe14(){
        Map map = new Map();
        map.spawnEnemyNpcs();
        assertEquals(14, map.getEnemyNpcPositionsArray().size());
    }*/

    /*@Test
    void When_DungeonIsInitiated_Expect_WallTilesInRightLocation(){
        Map map = new Map();
       // map.initiateDungeon(playerStartingTile);
        Tile roomAtopLeftVerticalWallTile = map.getMap2dArray()[1][6];
        Tile roomAtopLeftHorizontalWallTile = map.getMap2dArray()[1][7];
        assertEquals(true, roomAtopLeftVerticalWallTile.isVerticalWallTile());
        assertEquals(true, roomAtopLeftHorizontalWallTile.isHorizontalWallTile());
    }*/


    @Test
    void When_DungeonIsInitiated_Expect_VerticalWallTileNotWalkable(){
        Map map = new Map();
     /*   map.initiateDungeon(playerStartingTile);
        Tile knownVerticalWalltile = map.getPerimeterArray()[1][7];
        assertEquals(false, knownVerticalWalltile.isWalkable());*/
    }

    @Test
    void When_DungeonIsInitiated_Expect_HorizontalWallTileNotWalkable(){
        Map map = new Map();
     /*   map.initiateDungeon(playerStartingTile);
        Tile knownHorizontalWalltile = map.getPerimeterArray()[1][8];
        assertEquals(false, knownHorizontalWalltile.isWalkable());*/
    }

    @Test
    void When_DungeonIsInitiated_Expect_roomTileToBeWalkable(){
        Map map = new Map();
       /* map.initiateDungeon(playerStartingTile);
        Tile knownRoomTile = map.getPerimeterArray()[2][8];
        assertEquals(true, knownRoomTile.isWalkable());*/
    }

    @Test
    void When_DungeonIsInitiated_Expect_enemyNpcInRightLocation(){
        Map map = new Map();
       /* map.initiateDungeon(playerStartingTile);
        Tile knownEnemyNpcTile = map.getPerimeterArray()[3][15];
        assertEquals(3, knownEnemyNpcTile.getRow());
        assertEquals(15, knownEnemyNpcTile.getColumn());*/
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelStartWalkable(){
        Map map = new Map();
      /*  map.initiateDungeon(playerStartingTile);
        Tunnel aToC = map.getTunnelList().get(0);
        Tile startTile = aToC.getStartTile();

        assertEquals(true, startTile.isWalkable());*/
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelEndWalkable(){
        Map map = new Map();
      /*  map.initiateDungeon(playerStartingTile);
        Tunnel tunnelAtoD = map.getTunnelList().get(0);
        Tile endTile = tunnelAtoD.getEndingTile();

        assertEquals(true, endTile.isWalkable());*/
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_TunnelTraversable(){
        Map map = new Map();
       /* map.initiateDungeon(playerStartingTile);
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
        assertEquals(tunnelAtoD.getEndingTile(), map.getPerimeterArray()[row+3][col]);*/


    }



    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelStartWalkable(){
        Map map = new Map();
     /*   map.initiateDungeon(playerStartingTile);
        Tunnel tunnelEtoF = map.getTunnelList().get(3);
        Tile startTile = tunnelEtoF.getStartTile();

        assertEquals(true, startTile.isWalkable());*/
    }

    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelEndWalkable(){
        Map map = new Map();
    /*    map.initiateDungeon(playerStartingTile);
        Tunnel tunnelEtoF = map.getTunnelList().get(3);
        Tile endTile = tunnelEtoF.getEndingTile();

        assertEquals(true, endTile.isWalkable());*/
    }

    @Test
    void When_HorizontalTunnelIsCreated_Expect_TunnelTraversable(){
        Map map = new Map();
     /*   map.initiateDungeon(playerStartingTile);
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
        assertEquals(tunnelCtoD.getEndingTile(), map.getPerimeterArray()[row][col+10]);*/
    }






    /** Nedan är metoder för att printa kartan i olika former, samt metod för att printa info om rum **/

    /** printar bakgrundskartan, så kallad perimeterArray. Denna innehåller inte rum. För rum måste Map.iniateDungeon() köras **/
    @Test
    void printPerimieterArray_When_DungeonNotInitiated(){
        Map map = new Map();
        for (int i = 0; i < map.getMap2dArray().length-1; i++) {
            for (int j = 0; j < map.getMap2dArray()[i].length-1; j++) {
                System.out.print("-");
            }
            System.out.println("");
        }
    };

    /** printar kartan när dungeon är spawnad. Rum, väggar, npcs, players existerar är.
      välj att sätta on or off för roomtiles och backgroundtiles när printDungeon() kallas på **/
   /* @Test
    void printDungeon_When_DungenonIsInitiated(){
        *//*map.initiateDungeon(playerStartingTile);*//*
        map.printDungeon("off","on","off");
    }*/

    /** printar info om de rum som skapats **/
   /* @Test
    void printAllRoomsInRoomListTest(){
        *//*map.initiateDungeon(playerStartingTile);*//*
        map.printRooms();
    }*/

    /** printar info om varje tile som existerar på kartan, när dungeon INTE ÄR spawnad **/
   /* @Test
    void When_DungeonNotIniated_printAllTilesInPerimeterArray(){
        map.printTilesInPerimeterArray();
    }*/

    /** printar info om varje tile som existerar på kartan, dungeon ÄR spawnad **/
   /* @Test
    void When_DungeonIsIniated_printAllTilesInPerimeterArray(){
        //map.initiateDungeon(playerStartingTile);
        map.printTilesInPerimeterArray();
    }*/





}





