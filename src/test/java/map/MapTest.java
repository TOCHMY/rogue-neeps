package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import org.junit.jupiter.api.Test;
import player.Ogre;
import player.Player;
import util.Direction;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    final Position playerStartingPosition = new Position(5,19);

    @Test
    void When_newMapCreated_Expect_heightAndWidthOfMapCorrect() {
        int expectedHeight = 40;
        int expectedWidth = 100;
        Map map = new Map();

        int width = map.getMap()[0].length;
        int height = map.getMap().length;
        assertEquals(expectedHeight, height);
        assertEquals(expectedWidth, width);
    }

    @Test
    void When_newMapCreated_Expect_firstTileCorrectPosition(){
        Map map = new Map();
        Tile firstTile = map.getMap()[0][0];
        assertEquals(0, firstTile.getPosition().row());
        assertEquals(0, firstTile.getPosition().col());
    }

    @Test
    void When_newMapCreated_Expect_lastTileCorrectPosition(){
        Map map = new Map();
        Tile lastTile = map.getMap()[39][99];
        assertEquals(39, lastTile.getPosition().row());
        assertEquals(99, lastTile.getPosition().col());
    }

    @Test
    void When_newMapCreated_Expect_playerToBeNull(){
        Map map = new Map();
        assertNull(map.getPlayer());
    }

    @Test
    void When_newMapCreated_And_PlayerIsSet_Expect_correctPlayerPositionOnMap(){
        Map map = new Map();
        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());

    }

    @Test
    void When_playerMoveUp_Expect_playerPositionCorrectOnMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(new Position(2, 7)), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.UP);

        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);

        player.move(Direction.UP);
        assertEquals(expectedPosition, map.getPlayerPosition());
    }

    @Test
    void When_playerMoveDown_Expect_playerPositionCorrectOnMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(new Position(2, 7)), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.DOWN);
        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);
        player.move(Direction.DOWN);
        assertEquals(expectedPosition , map.getPlayerPosition());
    }

    @Test
    void When_playerMoveRight_Expect_playerPositionCorrectOnMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(new Position(2,7)), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.RIGHT);
        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.move(Direction.RIGHT);
        assertEquals(expectedPosition , map.getPlayerPosition());
    }

    @Test
    void When_playerMoveLeft_Expect_playerPositionCorrectOnMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(new Position(2,7)), map);
        map.addRoom(roomA);

        Position expectedPosition = playerStartingPosition.newPosition(Direction.LEFT);
        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());
        player.move(Direction.LEFT);
        assertEquals(expectedPosition , map.getPlayerPosition());
    }


    @Test
    void When_sixRoomsAreCreated_Expect_expectRoomlistSizeSix(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomB = new Room("B", 31,8, new Tile(new Position(2,56)), map);
        map.addRoom(roomB);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);
        Room roomD = new Room("D", 23,9, new Tile(new Position(14, 60)), map);
        map.addRoom(roomD);
        Room roomE = new Room("E", 25,14, new Tile(new Position(24, 7)), map);
        map.addRoom(roomE);
        Room roomF = new Room("F", 40,9, new Tile(new Position(27,55)), map);
        map.addRoom(roomF);

        assertEquals(6,map.getRooms().size());

    }

    @Test
    void When_sixRoomsAreCreated_And_fiveTunnelsAreCreated_expectTunnelListSizeFive(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomB = new Room("B", 31,8, new Tile(new Position(2,56)), map);
        map.addRoom(roomB);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);
        Room roomD = new Room("D", 23,9, new Tile(new Position(14, 60)), map);
        map.addRoom(roomD);
        Room roomE = new Room("E", 25,14, new Tile(new Position(24, 7)), map);
        map.addRoom(roomE);
        Room roomF = new Room("F", 40,9, new Tile(new Position(27,55)), map);
        map.addRoom(roomF);

        // map.Tunnel A till C
        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        // map.Tunnel B till D
        tunnelStart = map.getMap()[10][72];
        tunnelEnd = map.getMap()[13][72];
        Tunnel tunnel2 = new Tunnel("B", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel2);

        // map.Tunnel D till F
        tunnelStart = map.getMap()[23][72];
        tunnelEnd = map.getMap()[26][72];
        Tunnel tunnel3 = new Tunnel("D", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel3);

        // map.Tunnel C till D
        tunnelStart = map.getMap()[17][49];
        tunnelEnd = map.getMap()[17][59];
        Tunnel tunnel4 = new Tunnel("C", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel4);

        // map.Tunnel E till F
        tunnelStart = map.getMap()[29][32];
        tunnelEnd = map.getMap()[29][54];
        Tunnel tunnel5 = new Tunnel("E", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel5);

        assertEquals(5,map.getTunnels().size());

    }








    @Test
    void createMapVersion1_and_printMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomB = new Room("B", 31,8, new Tile(new Position(2,56)), map);
        map.addRoom(roomB);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);
        Room roomD = new Room("D", 23,9, new Tile(new Position(14, 60)), map);
        map.addRoom(roomD);
        Room roomE = new Room("E", 25,14, new Tile(new Position(24, 7)), map);
        map.addRoom(roomE);
        Room roomF = new Room("F", 40,9, new Tile(new Position(27,55)), map);
        map.addRoom(roomF);

        roomE.addSwampTile(new Position(24,13));
        roomE.addSwampTile(new Position(24,14));
        roomE.addSwampTile(new Position(24,15));

        roomE.addSwampTile(new Position(25,13));
        roomE.addSwampTile(new Position(25,14));
        roomE.addSwampTile(new Position(25,15));


        roomE.addWaterTile(new Position(28,17));
        roomE.addWaterTile(new Position(28,18));
        roomE.addWaterTile(new Position(28,19));
        roomE.addWaterTile(new Position(28,20));
        roomE.addWaterTile(new Position(28,21));
        roomE.addWaterTile(new Position(28,22));
        roomE.addWaterTile(new Position(28,23));

        roomE.addWaterTile(new Position(29,17));
        roomE.addWaterTile(new Position(29,18));
        roomE.addWaterTile(new Position(29,19));
        roomE.addWaterTile(new Position(29,20));
        roomE.addWaterTile(new Position(29,21));
        roomE.addWaterTile(new Position(29,22));
        roomE.addWaterTile(new Position(29,23));

        roomE.addWaterTile(new Position(30,17));
        roomE.addWaterTile(new Position(30,18));
        roomE.addWaterTile(new Position(30,19));
        roomE.addWaterTile(new Position(30,20));
        roomE.addWaterTile(new Position(30,21));
        roomE.addWaterTile(new Position(30,22));
        roomE.addWaterTile(new Position(30,23));

        roomE.addWaterTile(new Position(31,17));
        roomE.addWaterTile(new Position(31,18));
        roomE.addWaterTile(new Position(31,19));
        roomE.addWaterTile(new Position(31,20));
        roomE.addWaterTile(new Position(31,21));
        roomE.addWaterTile(new Position(31,22));
        roomE.addWaterTile(new Position(31,23));


        // Enemy 1 map.Room A new version
        EnemyNPC npc1 = new EnemyNPC("hostile1melee",1,true); // nuvarande
        Position npcPos = new Position(3,15);
        roomA.addHostileNpc(npc1, npcPos);

        // HostileNPC 2 map.Room A NEW
        EnemyNPC npc2 = new EnemyNPC("hostile2melee",1,true); // nuvarande
        npcPos = new Position(4,28);
        roomA.addHostileNpc(npc2, npcPos);

        // HostileNPC 3 map.Room B NEW
        EnemyNPC npc3 = new EnemyNPC("hostile3melee",1,true); // nuvarande
        npcPos = new Position(4,65);
        roomB.addHostileNpc(npc3,npcPos);

        // HostileNPC 4 map.Room B NEW
        EnemyNPC npc4 = new EnemyNPC("hostile4NotMelee",1,false); // nuvarande
        npcPos = new Position(7,66);
        roomB.addHostileNpc(npc4, npcPos);

        // HostileNPC 5 map.Room B NEW
        EnemyNPC npc5 = new EnemyNPC("hostile5NotMelee",1,false); // nuvarande
        npcPos = new Position(9,70);
        roomB.addHostileNpc(npc5, npcPos);

        // HostileNPC 6 map.Room D NEW
        EnemyNPC npc6 = new EnemyNPC("hostile6NotMelee",1,false); // nuvarande
        npcPos = new Position(16,70);
        roomD.addHostileNpc(npc6, npcPos);

        // HostileNPC 7 map.Room F NEW
        EnemyNPC npc7 = new EnemyNPC("hostile7NotMelee",1,false); // nuvarande
        npcPos = new Position(30,75);
        roomF.addHostileNpc(npc7, npcPos);

        // HostileNPC 8 map.Room E NEW
        EnemyNPC npc8 = new EnemyNPC("hostile8NotMelee",1,false); // nuvarande
        npcPos = new Position(34,70);
        roomF.addHostileNpc(npc8, npcPos);

        // HostileNPC 9 map.Room D NEW
        EnemyNPC npc9 = new EnemyNPC("hostile9NotMelee",1,false); // nuvarande
        npcPos = new Position(19,70);
        roomD.addHostileNpc(npc9, npcPos);

        // HostileNPC 10 map.Room E NEW
        EnemyNPC npc10 = new EnemyNPC("hostile10melee",1,true); // nuvarande
        npcPos = new Position(34,19);
        roomE.addHostileNpc(npc10, npcPos);

        // HostileNPC 11 map.Room E NEW
        EnemyNPC npc11 = new EnemyNPC("hostile11melee",1,true); // nuvarande
        npcPos = new Position(27,15);
        roomE.addHostileNpc(npc11, npcPos);

        // HostileNPC 12 map.Room E NEW
        EnemyNPC npc12 = new EnemyNPC("hostile12melee",1,true); // nuvarande
        npcPos = new Position(25,13);
        roomE.addHostileNpc(npc12, npcPos);

        // HostileNPC 13 map.Room D NEW
        EnemyNPC npc13 = new EnemyNPC("hostile13melee",1,true); // nuvarande
        npcPos = new Position(15,30);
        roomC.addHostileNpc(npc13, npcPos);

        // HostileNPC 13 map.Room D NEW
        EnemyNPC npc14 = new EnemyNPC("hostile14melee",1,true); // nuvarande
        npcPos = new Position(17,44);
        roomC.addHostileNpc(npc14, npcPos);

        // Friendly 1 map.Room A NEW
        FriendlyNPC npc15 = new FriendlyNPC("Friendly1"); // nuvarande
        npcPos = new Position(9,8);
        roomA.addFriendlyNpc(npc15,npcPos);

        // Friendly 2 map.Room A NEW
        FriendlyNPC npc16 = new FriendlyNPC("Friendly2"); // nuvarande
        npcPos = new Position(9,10);
        roomA.addFriendlyNpc(npc16,npcPos);

        FriendlyNPC npc17 = new FriendlyNPC("Friendly3"); // nuvarande
        npcPos = new Position(4,80);
        roomB.addFriendlyNpc(npc17,npcPos);


        // map.Tunnel A till C
        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        // map.Tunnel B till D
        tunnelStart = map.getMap()[10][72];
        tunnelEnd = map.getMap()[13][72];
        Tunnel tunnel2 = new Tunnel("B", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel2);

        // map.Tunnel D till F
        tunnelStart = map.getMap()[23][72];
        tunnelEnd = map.getMap()[26][72];
        Tunnel tunnel3 = new Tunnel("D", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel3);

        // map.Tunnel C till D
        tunnelStart = map.getMap()[17][49];
        tunnelEnd = map.getMap()[17][59];
        Tunnel tunnel4 = new Tunnel("C", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel4);

        // map.Tunnel E till F
        tunnelStart = map.getMap()[29][32];
        tunnelEnd = map.getMap()[29][54];
        Tunnel tunnel5 = new Tunnel("E", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel5);

        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);

        map.printDungeon("off","on","off");
        player.move(Direction.DOWN);
        map.printDungeon("off","on","off");
        player.move(Direction.RIGHT);
        map.printDungeon("off","on","off");
    }

}





