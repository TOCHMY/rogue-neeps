package map;

import npc.Albatross;
import org.hamcrest.Description;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import util.Position;


import static map.TunnelMatcher.hasFromRoom;
import static org.hamcrest.MatcherAssert.assertThat;
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
                    new Tunnel("A", "C", tunnelStart, tunnelEnd);} );
    }

    @Test
    void When_TunnelIsCreated_Expect_IllegalArgument_If_endingTileIsNotWallTile() {
        Tile tunnelStart = new Tile(1, 1);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(4, 1);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Tunnel("A", "C", tunnelStart, tunnelEnd);});
    }

    @Test
    void When_newTunnelIsCreated_Expect_tunnelHasFromRoom(){
        Map map = new Map();
        Room roomA = new Room("A",4,4, new Tile(4,4), map);
        Room roomB = new Room("B",4,4, new Tile(12,4), map);
        map.addRoom(roomA);
        map.addRoom(roomB);

        Tile tunnelStart = map.getMap()[8][5];
        Tile tunnelEnd = map.getMap()[11][5];

        Tunnel tunnel = new Tunnel("A", "B", tunnelStart, tunnelEnd);
        assertThat(tunnel, hasFromRoom());
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