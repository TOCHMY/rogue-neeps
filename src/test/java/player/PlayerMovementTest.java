package player;
import map.Map;
import map.Tile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import util.Direction;
import util.Position;

public class PlayerMovementTest {
    Player player;
    static Map map;

    @BeforeEach
    public void setUpPlayer(){
        player = new Ogre();
    }

    @BeforeAll
    public static void setUpMap(){
        map = new Map();
    }

    @Test
    public void WhenNoMapAndMoveToExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            player.moveTo(new Position(10, 10));
        });
    }

    @Test
    public void WhenNoMapAndMoveWithDirectionExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            player.move(Direction.UP);
        });
    }

    @Test
    public void WhenNoMapAndMoveWithNoPositionExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            player.moveTo(new Position(2,5));
        });
    }

    @Test
    public void WhenNoMapAndMoveWithNoEarlierPositionExpectThrows(){
        player.setPosition(new Position(20,20));
        assertThrows(IllegalStateException.class, () -> {
            player.moveTo(new Position(2,5));
        });
    }

    @Test
    public void TestFacingDirection(){
        Position roomPos = new Position(1,1);
        Position roomPos2 = new Position(1,2);
        player.setMap(map);
        map.getTile(roomPos).makeRoomTile();
        map.getTile(roomPos2).makeRoomTile();

        player.moveTo(roomPos);
        player.move(Direction.RIGHT);

        assertEquals(Direction.RIGHT, player.getPlayerFacingDirection());
    }

}
