package map;

import org.junit.jupiter.api.Test;
import util.Position;


import static org.junit.jupiter.api.Assertions.*;

class TunnelTest {

    @Test
    void When_TunnelIsCreated_Expect_startingTileSet(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(tunnelStart, tunnel.getStartTile());
    }

    @Test
    void When_TunnelIsCreated_Expect_TileArrayContainsCorrectTiles(){
        Map map = new Map();

        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];

        Tunnel tunnel = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel);

        assertEquals(tunnelStart, tunnel.getTileList().get(0));
        assertEquals(map.getMap()[11][31], tunnel.getTileList().get(1));
        assertEquals(map.getMap()[12][31], tunnel.getTileList().get(2));
        assertEquals(tunnelEnd, tunnel.getTileList().get(3));
    }


    @Test
    void When_TunnelIsCreated_Expect_EndingTileSet(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(tunnelEnd, tunnel.getEndingTile());
    }

    @Test
    void When_TunnelIsCreated_Expect_FromRoomCorrect(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals("A", tunnel.getFromRoom());
    }

    @Test
    void When_TunnelIsCreated_Expect_ExpectToRoomCorrect(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals("C", tunnel.getToRoom());
    }

    @Test
    void When_TunnelIsCreated_Expect_tunnelTilesListNotEmpty(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(tunnelStart, tunnel.getStartTile());
    }

    @Test
    void When_TunnelIsCreated_Expect_IllegalArgument_If_StartingTileIsNotWallTile() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Tile tunnelStart = new Tile(5, 31);
                    Tile tunnelEnd = new Tile(13, 31);
                    tunnelEnd.makeHorizontalWallTile();
                    Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);} );
    }

    @Test
    void When_TunnelIsCreated_Expect_IllegalArgument_If_endingTileIsNotWallTile() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Tile tunnelStart = new Tile(10, 31);
                    tunnelStart.makeHorizontalWallTile();
                    Tile tunnelEnd = new Tile(13, 31);
                    Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);} );
    }


    @Test
    void When_HorizontalTunnelIsCreated_Expect_LengthToBeCorrect(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(4, tunnel.getLength());
    }

    @Test
    void When_VerticalTunnelIsCreated_Expect_lengthToBeCorrect(){
        Tile tunnelStart = new Tile(10, 30);
        tunnelStart.makeVerticalWallTile();
        Tile tunnelEnd = new Tile(10, 35);
        tunnelEnd.makeVerticalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(6, tunnel.getLength());
    }




}