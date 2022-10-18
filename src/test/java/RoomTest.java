import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void When_roomIsCreated_Expect_getNameSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals("testName", room.getName());
    }

    @Test
    void When_roomIsCreated_Expect_getWidthSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals(5, room.getWidth());
    }

    @Test
    void When_roomIsCreated_Expect_getHeightSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals(5, room.getHeight());
    }

    @Test
    void When_roomIsCreated_Expect_getStartingTileSuccess(){
        Map map = new Map();
        Tile roomStartingTile = new Tile(1,1);
        Room room = new Room("testName", 5,5,roomStartingTile, map);
        assertEquals(roomStartingTile, room.getStartingTile());
    }



}