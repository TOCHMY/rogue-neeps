

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void When_NewTileIsCreated_Expect_tileGetXandYCorrectFormat(){
        Tile testtile = new Tile(39,99);
        Assertions.assertEquals(39, testtile.getRow());
        Assertions.assertEquals(99, testtile.getColumn());
    }

    @Test
    void makeRoomTileWorks(){
        Tile tile = new Tile(0,0);
        Assertions.assertEquals(false, tile.isRoomTile());
        tile.makeRoomTile(new Room("test", 5,5,tile));
        Assertions.assertEquals(true, tile.isRoomTile());

    }

    @Test
    void When_NewTileIsCreated_Expect_tileIsWalkableFalse(){
        Tile tile = new Tile(0,0);
        Assertions.assertEquals(false, tile.isWalkable());
    }

    @Test
    void When_NewTileIsCreated_Expect_isRoomTileFalse(){
        Tile tile = new Tile(0,0);
        Assertions.assertEquals(false, tile.isRoomTile());
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
        Assertions.assertEquals(true, tile.isWalkable());
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