import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void When_NewTileIsCreated_Expect_tileGetXandYCorrectFormat(){
        Tile testtile = new Tile(39,99);
        assertEquals(40, testtile.getX());
        assertEquals(100, testtile.getY());
        System.out.println("testile är: "+ " x:" +testtile.getX() + " y: " + testtile.getY());
    }

    @Test
    void makeRoomTileWorks(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isRoomTile());
        tile.makeRoomTile(new Room("test", 5,5,tile));
        assertEquals(true, tile.isRoomTile());

    }

    @Test
    void When_NewTileIsCreated_Expect_tileIsWalkableFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isWalkable());
    }

    @Test
    void When_NewTileIsCreated_Expect_isRoomTileFalse(){
        Tile tile = new Tile(0,0);
        assertEquals(false, tile.isRoomTile());
    }

    @Test
    void When_NewTileIsCreated_Expect_getRoomNull(){
        Tile tile = new Tile(0,0);
        assertNull(tile.getRoom());
    }


    @Test
    void When_tileIsSetToRoomTile_Expect_tileIsWalkableTrue(){
        Tile tile = new Tile(0,0);
        tile.makeWalkable();
        assertEquals(true, tile.isWalkable());
    }

    @Test
    void When_tileIsSetToRoomTile_Expect_isRoomTileTrue(){
        Tile tile = new Tile(0,0);
        tile.makeWalkable();
        assertNull( tile.getRoom());
    }

    @Test
    void When_tileIsSetToRoomTile_Expect_getRoomNotNull(){
        Tile tile = new Tile(0,0);
        tile.makeRoomTile(new Room("test", 5,5,tile));
        assertNotNull(tile.getRoom());
    }













}