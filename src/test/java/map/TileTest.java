package map;

import map.Map;
import map.Room;
import map.Tile;
import npc.Albatross;
import npc.EnemyNPC;
import org.junit.jupiter.api.Test;
import util.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void When_NewTileIsCreated_Expect_tileGetXandYCorrectFormat(){
        Tile testtile = new Tile(new Position(39,99));
        assertEquals(39, testtile.getRow());
        assertEquals(99, testtile.getColumn());
    }

    @Test
    void When_TileIsSetToRoomTile_Expect_isRoomTileTrue(){
        Map map = new Map();
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isRoomTile());
        tile.makeRoomTile(new Room("test", 5,5,tile, map));
        assertEquals(true, tile.isRoomTile());

    }

    @Test
    void When_twoTilesWithSameRowAndColAreCompared_Expect_equalsToBeTrue(){
        Map map = new Map();
        Tile tile1 = new Tile(0,0);
        Tile tile2 = new Tile(0,0);
        assertEquals(true, tile1.equals(tile2));
    }

    @Test
    void When_TileIsSetToVerticalWallTile_Expect_isVerticalWallTileTrue(){
        Tile tile = new Tile(0,0);
        tile.makeVerticalWallTile();
        assertEquals(true, tile.isVerticalWallTile());
    }



    @Test
    void When_TileIsSetToHorizontalWallTile_Expect_isHorizontalWallTileTrue(){
        Tile tile = new Tile(0,0);
        tile.makeHorizontalWallTile();
        assertEquals(true, tile.isHorizontalWallTile());
    }

    @Test
    void When_TileIsSetToWaterTile_Expect_isWaterTileToBeTrue(){
        Tile tile = new Tile(0,0);
        tile.makeWaterTile();
        assertEquals(true, tile.isWaterTile());
    }

    @Test
    void When_TileIsSetToSwampTile_Expect_isSwampTileToBeTrue(){
        Tile tile = new Tile(0,0);
        tile.makeSwampTile();
        assertEquals(true, tile.isSwampTile());
    }

    @Test
    void When_TileIsSetToHorizontalWallTile_Expect_isWallToBeTrue(){
        Tile tile = new Tile(0,0);
        tile.makeHorizontalWallTile();
        assertEquals(true, tile.isWallTile());
    }

    @Test
    void When_TileIsSetToVerticalWallTile_Expect_isWallToBeTrue(){
        Tile tile = new Tile(0,0);
        tile.makeVerticalWallTile();
        assertEquals(true, tile.isWallTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isRoomTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isRoomTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isWaterTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isWaterTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isSwampTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isSwampTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isHorizontalWallTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isHorizontalWallTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isVerticalWallTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isVerticalWallTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_isWallTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isWallTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_getRoomNull(){
        Tile tile = new Tile(0,0);
        assertNull(tile.getRoom());
    }

    @Test
    void When_NewRoomIsCreated_Expect_firstRoomTileToBelongToRoom(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        assertEquals("A",map.getMap()[2][7].getRoom().getName());
    }

    @Test
    void When_NewTunnelIsCreated_Expect_tilesInTunnelToBeTunnelTiles(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        assertEquals(true,map.getMap()[10][31].isTunnelTile());
        assertEquals(true,map.getMap()[11][31].isTunnelTile());
        assertEquals(true,map.getMap()[12][31].isTunnelTile());
        assertEquals(true,map.getMap()[13][31].isTunnelTile());
    }

    @Test
    void When_NewTunnelIsCreated_Expect_tileInTunnelReturnCorrectGetTunnelValue(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel);

        assertEquals(tunnel, map.getMap()[10][31].getTunnel());
    }

    @Test
    void When_TileIsSetToRoomTile_Expect_getRoomNotNull(){
        Map map = new Map();
        Tile startTile = new Tile(0,0);
        startTile.makeRoomTile(new Room("test", 5,5,startTile, map));
        assertNotNull(startTile.getRoom());
    }

    @Test
    void When_RoomTileIsPrinted_Expect_CorrectSymbol(){
        Map map = new Map();
        Tile startTile = new Tile(0,0);
        startTile.makeRoomTile(new Room("test", 5,5,startTile, map));
        assertEquals(" ", startTile.symbolPrint());
    }

    @Test
    void When_TunnelTileIsPrinted_Expect_CorrectSymbol(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel);

        assertEquals(" ", tunnelStart.symbolPrint());
    }

    @Test
    void When_HorizontalWallTileIsPrinted_Expect_CorrectSymbol(){
        Tile startTile = new Tile(0,0);
        startTile.makeHorizontalWallTile();
        assertEquals("=", startTile.symbolPrint());
    }

    @Test
    void When_VerticalWallTileIsPrinted_Expect_CorrectSymbol(){
        Tile startTile = new Tile(0,0);
        startTile.makeVerticalWallTile();
        assertEquals("|", startTile.symbolPrint());
    }

    @Test
    void When_WaterTileIsPrinted_Expect_CorrectSymbol(){
        Map map = new Map();
        Tile startTile = new Tile(0,0);
        startTile.makeWaterTile();
        assertEquals("~", startTile.symbolPrint());
    }

    @Test
    void When_SwampTileIsPrinted_Expect_CorrectSymbol(){
        Map map = new Map();
        Tile startTile = new Tile(0,0);
        startTile.makeSwampTile();
        assertEquals("#", startTile.symbolPrint());
    }

    @Test
    void When_newTileIsCreated_Exepct_isOccupiedTrue(){
        // Enemy 1 map.Room A new version

        Tile tile = new Tile(0,0);
        tile.setOccupied(true);

        assertEquals(true, tile.isOccupied());
    }

    @Test
    void When_NPCisPlaced_Exepct_NPCTileToBeOccupied(){
        // Enemy 1 map.Room A new version

        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);

        EnemyNPC npc1 = new Albatross(); // nuvarande

        npc1.setMap(map);
        Position npcPos = new Position(3,15);
        roomA.addHostileNpc(npc1, npcPos);

       assertEquals(true, map.getMap()[3][15].isOccupied());
    }














}