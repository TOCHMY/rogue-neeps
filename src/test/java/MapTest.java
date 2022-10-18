

import org.junit.jupiter.api.Assertions;
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
    void When_DungeonIsInitiated_Expect_RoomListSizeIsSix(){
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        assertEquals(6, map.getRoomList().size());
    }

   /* @Test
    void When_EnemyNpcAreSpawned_Expect_EnemyNpcArraySizeToBe14(){
        Map map = new Map();
        map.spawnEnemyNpcs();
        assertEquals(14, map.getEnemyNpcPositionsArray().size());
    }*/

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
     void mock_helakartan(){
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

         /* // Enemy 1 Room A old
         getPerimeterArray()[3][15].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[3][15]);*/


         // HostileNPC 2 Room A NEW
         EnemyNPC npc2 = new EnemyNPC("hostile2melee",1,true); // nuvarande
         npcPos = new Tile(4,28);
         roomA.addHostileNpc(npc2, npcPos);

         /*// HostileNPC 2 ROOM A old
         perimeterArray[4][28].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[4][28]);*/

         // HostileNPC 3 Room B NEW
         EnemyNPC npc3 = new EnemyNPC("hostile3melee",1,true); // nuvarande
         npcPos = new Tile(4,65);
         roomB.addHostileNpc(npc3,npcPos);

         /*// HostileNPC 3 ROOM B OLD
         perimeterArray[4][65].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[4][65]);*/

         // HostileNPC 4 Room B NEW
         EnemyNPC npc4 = new EnemyNPC("hostile4NotMelee",1,false); // nuvarande
         npcPos = new Tile(7,66);
         roomB.addHostileNpc(npc4, npcPos);

         /*// HostileNPC 4 ROOM B OLD
         perimeterArray[7][66].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[7][66]);*/

         // HostileNPC 5 Room B NEW
         EnemyNPC npc5 = new EnemyNPC("hostile5NotMelee",1,false); // nuvarande
         npcPos = new Tile(9,70);
         roomB.addHostileNpc(npc5, npcPos);

         /*// HostileNPC 5 ROOM B OLD
         perimeterArray[9][70].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[9][70]);*/

         // HostileNPC 6 Room D NEW
         EnemyNPC npc6 = new EnemyNPC("hostile6NotMelee",1,false); // nuvarande
         npcPos = new Tile(16,70);
         roomD.addHostileNpc(npc6, npcPos);

         /*// HostileNPC 6 ROOM D OLD
         perimeterArray[16][70].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[16][70]);*/

         // HostileNPC 7 Room F NEW
         EnemyNPC npc7 = new EnemyNPC("hostile7NotMelee",1,false); // nuvarande
         npcPos = new Tile(30,75);
         roomF.addHostileNpc(npc7, npcPos);


         /*// HostileNPC 7 ROOM E OLD
         perimeterArray[30][75].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[30][75]);*/

         // HostileNPC 8 Room E NEW
         EnemyNPC npc8 = new EnemyNPC("hostile8NotMelee",1,false); // nuvarande
         npcPos = new Tile(34,70);
         roomF.addHostileNpc(npc8, npcPos);


         /*// HostileNPC 8 ROOM E OLD
         perimeterArray[34][70].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[34][70]);*/

// hello test
         // HostileNPC 9 Room D NEW
         EnemyNPC npc9 = new EnemyNPC("hostile9NotMelee",1,false); // nuvarande
         npcPos = new Tile(19,70);
         roomD.addHostileNpc(npc9, npcPos);

        /* // HostileNPC 9 Room D OLD
         perimeterArray[19][70].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[19][70]);*/

         // HostileNPC 10 Room E NEW
         EnemyNPC npc10 = new EnemyNPC("hostile10melee",1,true); // nuvarande
         npcPos = new Tile(34,19);
         roomE.addHostileNpc(npc10, npcPos);

         /*// HostileNPC 10 Room E OLD
         perimeterArray[34][19].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[34][19]);*/

         // HostileNPC 11 Room E NEW
         EnemyNPC npc11 = new EnemyNPC("hostile11melee",1,true); // nuvarande
         npcPos = new Tile(27,15);
         roomE.addHostileNpc(npc11, npcPos);

         /*// HostileNPC 11 Room E OLD
         perimeterArray[27][15].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[27][15]);*/

         // HostileNPC 12 Room E NEW
         EnemyNPC npc12 = new EnemyNPC("hostile12melee",1,true); // nuvarande
         npcPos = new Tile(25,13);
         roomE.addHostileNpc(npc12, npcPos);

         /*// HostileNPC 12 Room E OLD
         perimeterArray[25][13].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[25][13]);*/

         // HostileNPC 13 Room D NEW
         EnemyNPC npc13 = new EnemyNPC("hostile13melee",1,true); // nuvarande
         npcPos = new Tile(15,30);
         roomC.addHostileNpc(npc13, npcPos);

         /*// HostileNPC 13 för D OLD
         perimeterArray[15][30].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[15][30]);*/

         // HostileNPC 13 Room D NEW
         EnemyNPC npc14 = new EnemyNPC("hostile14melee",1,true); // nuvarande
         npcPos = new Tile(17,44);
         roomC.addHostileNpc(npc14, npcPos);

         /*// Enemy 14 för D
         perimeterArray[17][44].setEnemyNpcOnTile();
         enemyNpcPositionsArray.add(perimeterArray[17][44]);*/

         // Friendly 1 Room A NEW
         FriendlyNPC npc15 = new FriendlyNPC("Friendly1"); // nuvarande
         npcPos = new Tile(9,8);
         roomA.addFriendlyNpc(npc15,npcPos);

         /*// Friendly 1 Room A OLD
         perimeterArray[9][8].setFriendlyNpcOnTile();
         friendlyNpcPositionsArray.add(perimeterArray[9][8]);*/

         // Friendly 2 Room A NEW
         FriendlyNPC npc16 = new FriendlyNPC("Friendly2"); // nuvarande
         npcPos = new Tile(9,10);
         roomA.addFriendlyNpc(npc16,npcPos);

         /*// Friendly 2 Room A OLD
         perimeterArray[9][10].setFriendlyNpcOnTile();
         friendlyNpcPositionsArray.add(perimeterArray[9][10]);*/

         FriendlyNPC npc17 = new FriendlyNPC("Friendly3"); // nuvarande
         npcPos = new Tile(4,80);
         roomB.addFriendlyNpc(npc17,npcPos);

        /* // Friendly 3 Room B OLD
         perimeterArray[4][80].setFriendlyNpcOnTile();
         friendlyNpcPositionsArray.add(perimeterArray[4][80]);*/


         // Friendly 4 Room E NEW
         FriendlyNPC npc18 = new FriendlyNPC("Friendly4"); // nuvarande
         npcPos = new Tile(37,8);
         roomE.addFriendlyNpc(npc18,npcPos);


         /*// Friendly 4 Room E OLD
         perimeterArray[37][8].setFriendlyNpcOnTile();
         friendlyNpcPositionsArray.add(perimeterArray[37][8]);*/


         Tile tunnelStart = map.getPerimeterArray()[10][31];
         Tile tunnelEnd = map.getPerimeterArray()[13][31];
         Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
         map.addTunnel(tunnel1);

         tunnelStart = map.getPerimeterArray()[10][72];
         tunnelEnd = map.getPerimeterArray()[13][72];
         Tunnel tunnel2 = new Tunnel("B", "D",tunnelStart,tunnelEnd);
         map.addTunnel(tunnel2);

         tunnelStart = map.getPerimeterArray()[23][72];
         tunnelEnd = map.getPerimeterArray()[26][72];
         Tunnel tunnel3 = new Tunnel("D", "F",tunnelStart,tunnelEnd);
         map.addTunnel(tunnel3);


         tunnelStart = map.getPerimeterArray()[17][49];
         tunnelEnd = map.getPerimeterArray()[17][59];
         Tunnel tunnel4 = new Tunnel("C", "D",tunnelStart,tunnelEnd);
         map.addTunnel(tunnel4);


         tunnelStart = map.getPerimeterArray()[29][32];
         tunnelEnd = map.getPerimeterArray()[29][54];
         Tunnel tunnel5 = new Tunnel("E", "F",tunnelStart,tunnelEnd);
         map.addTunnel(tunnel5);

         map.updatePlayerPosition(playerStartingTile);



        map.printDungeon("off","on","off");
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





