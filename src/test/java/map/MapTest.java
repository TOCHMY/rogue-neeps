package map;

import org.junit.jupiter.api.Test;
import player.Ogre;
import player.Player;
import util.Direction;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    final Position playerStartingPosition = new Position(5,19);

    @Test
    void When_NewMapCreated_Expect_HeightAndWidthOfMapCorrect() {
        int expectedHeight = 40;
        int expectedWidth = 100;
        Map map = new Map();
        int width = map.getMap()[0].length;
        int height = map.getMap().length;
        assertEquals(expectedHeight, height);
        assertEquals(expectedWidth, width);
    }

    @Test
    void When_NewMapCreated_Expect_FirstTileCorrectPosition(){
        Map map = new Map();
        Tile firstTile = map.getMap()[0][0];
        assertEquals(0, firstTile.getPosition().row());
        assertEquals(0, firstTile.getPosition().col());
    }

    @Test
    void When_NewMapCreated_Expect_LastTileCorrectPosition(){
        Map map = new Map();
        Tile lastTile = map.getMap()[39][99];
        assertEquals(39, lastTile.getPosition().row());
        assertEquals(99, lastTile.getPosition().col());
    }

    @Test
    void When_NewMapCreated_Expect_PlayerToBeNull(){
        Map map = new Map();
        assertNull(map.getPlayer());
    }

    @Test
    void When_NewMapCreated_And_PlayerIsSet_Expect_CorrectPlayerPositionOnMap(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Player player = new Ogre();
        player.setMap(map);
        player.moveTo(playerStartingPosition);
        assertEquals(playerStartingPosition, map.getPlayerPosition());

    }

    @Test
    void When_PlayerMoveUp_Expect_PlayerPositionCorrectOnMap(){
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
    void When_PlayerMoveDown_Expect_PlayerPositionCorrectOnMap(){
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
    void When_playerMoveRight_Expect_PlayerPositionCorrectOnMap(){
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
    void When_playerMoveLeft_Expect_PlayerPositionCorrectOnMap(){
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
    void When_SixRoomsAreCreated_Expect_ExpectRoomlistSizeSix(){
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
    void When_SixRoomsAreCreated_And_fiveTunnelsAreCreated_expectTunnelListSizeFive(){
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

        // Tunnel A till C
        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        // Tunnel B till D
        tunnelStart = map.getMap()[10][72];
        tunnelEnd = map.getMap()[13][72];
        Tunnel tunnel2 = new Tunnel("B", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel2);

        // Tunnel D till F
        tunnelStart = map.getMap()[23][72];
        tunnelEnd = map.getMap()[26][72];
        Tunnel tunnel3 = new Tunnel("D", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel3);

        // Tunnel C till D
        tunnelStart = map.getMap()[17][49];
        tunnelEnd = map.getMap()[17][59];
        Tunnel tunnel4 = new Tunnel("C", "D",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel4);

        // Tunnel E till F
        tunnelStart = map.getMap()[29][32];
        tunnelEnd = map.getMap()[29][54];
        Tunnel tunnel5 = new Tunnel("E", "F",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel5);

        assertEquals(5,map.getTunnels().size());

    }
}





