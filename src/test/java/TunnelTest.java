import org.junit.jupiter.api.Test;

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
    void When_TunnelIsCreated_Expect_startingEndingTileSet(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(tunnelStart, tunnel.getStartTile());
    }

    @Test
    void When_tunnelIsCreated_Expect_tunnelTilesListNotEmpty(){
        Tile tunnelStart = new Tile(10, 31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13, 31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);
        assertEquals(tunnelStart, tunnel.getStartTile());
    }

    @Test
    void When_tunnelIsCreated_Expect_IllegalArgument_If_StartingTileIsNotWallTile() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Tile tunnelStart = new Tile(5, 31);
                    Tile tunnelEnd = new Tile(13, 31);
                    tunnelEnd.makeHorizontalWallTile();
                    Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);} );
    }

    @Test
    void When_tunnelIsCreated_Expect_IllegalArgument_If_endingTileIsNotWallTile() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Tile tunnelStart = new Tile(10, 31);
                    tunnelStart.makeHorizontalWallTile();
                    Tile tunnelEnd = new Tile(13, 31);
                    Tunnel tunnel = new Tunnel("A", "C", tunnelStart, tunnelEnd);} );
    }

    @Test
    void When_tunnelIsCreated_Expect_startingTileToBeAWallTile(){
        Tile tunnelStart = new Tile(10,31);
        tunnelStart.makeHorizontalWallTile();
        Tile tunnelEnd = new Tile(13,31);
        tunnelEnd.makeHorizontalWallTile();
        Tunnel tunnel = new Tunnel("A","C", tunnelStart, tunnelEnd);
        Tile wallTile = new Tile(0,0);
        wallTile.makeVerticalWallTile();
        assertEquals(wallTile.isWallTile(), tunnel.getStartTile().isWallTile());
    }



}