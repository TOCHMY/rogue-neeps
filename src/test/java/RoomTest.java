import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void When_roomIsCreated_Expect_getNameSuccess(){
        Room room = new Room("testName", 5,5,new Tile(1,1));
        assertEquals("testName", room.getName());
    }

    @Test
    void When_roomIsCreated_Expect_getWidthSuccess(){
        Room room = new Room("testName", 5,5,new Tile(1,1));
        assertEquals(5, room.getWidth());
    }

    @Test
    void When_roomIsCreated_Expect_getHeightSuccess(){
        Room room = new Room("testName", 5,5,new Tile(1,1));
        assertEquals(5, room.getHeight());
    }

    @Test
    void When_roomIsCreated_Expect_getStartingTileSuccess(){
        Tile roomStartingTile = new Tile(1,1);
        Room room = new Room("testName", 5,5,roomStartingTile);
        assertEquals(roomStartingTile, room.getStartingTile());
    }



}