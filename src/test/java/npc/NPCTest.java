package npc;

import map.Map;
import map.Tile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Ogre;
import player.Player;
import util.Direction;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class NPCTest {

    Map map;
    NPC npc;

    @BeforeEach
    public void setUpNPC(){
        npc = new Pig();
        map = new Map();
    }



    @Test
    void setMapExpectNotNull() {
        NPC enemy = new Pig();
        Map m = new Map();
        enemy.setMap(m);
        assertNotNull(enemy.getMap());
    }


    @Test
    public void WhenNoMapAndMoveToExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            npc.moveTo(new Position(10, 10));
        });
    }

    @Test
    public void WhenNoMapAndMoveWithDirectionExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            npc.move(Direction.UP);
        });
    }

    @Test
    public void WhenNoMapAndMoveWithNoPositionExpectThrows(){
        assertThrows(IllegalStateException.class, () -> {
            npc.moveTo(new Position(2,5));
        });
    }

    @Test
    public void WhenNoMapAndMoveWithNoEarlierPositionExpectThrows(){
        npc.setPosition(new Position(20,20));
        assertThrows(IllegalStateException.class, () -> {
            npc.moveTo(new Position(2,5));
        });
    }
    @Test
    void WhenNoPositionExpectThrows() {
        assertThrows(IllegalStateException.class, () -> {
            npc.getPosition();
        });

    }

    @Test
    void WhenPositionExpectSuccess() {
        npc.setPosition(new Position(1,1));
        assertDoesNotThrow(() -> {
            npc.getPosition();
        });

    }

    @Test
    void TestSetPositionExpectCorrectPosition() {
        npc.setPosition(new Position(1,1));
        assertEquals(new Position(1,1), npc.getPosition());
    }

    @Test
    public void WhenMapAndRoomExpectMovement(){
        npc.setMap(map);
        Position roomPos = new Position(1,1);
        Position roomPos2 = new Position(1,2);

        map.getTile(roomPos).makeRoomTile();
        map.getTile(roomPos2).makeRoomTile();

        npc.moveTo(roomPos);
        npc.move(Direction.RIGHT);

        assertEquals(roomPos2, npc.getPosition());

    }
    @Test
    public void WhenMapAndRoomAndOccupiedExpectNoMovement(){
        npc.setMap(map);
        Position roomPos = new Position(1,1);
        Position roomPos2 = new Position(1,2);

        map.getTile(roomPos).makeRoomTile();
        map.getTile(roomPos2).makeRoomTile();

        Player player = new Ogre();
        player.setMap(map);

        player.moveTo(roomPos2);

        npc.moveTo(roomPos);
        npc.move(Direction.RIGHT);

        assertEquals(roomPos, npc.getPosition());

    }

    @Test void WhenPositionMovePosition(){
        npc.setMap(map);
        Position roomPos = new Position(1,1);
        Position roomPos2 = new Position(1,2);

        map.getTile(roomPos).makeRoomTile();
        map.getTile(roomPos2).makeRoomTile();

        npc.moveTo(roomPos);
        npc.moveTo(roomPos2);

        assertEquals(roomPos2, npc.getPosition());

    }

    @Test
    void WhenDefaultExpectPlayerFacingUp() {
        assertEquals(Direction.UP, npc.getFacingDirection());
    }

    @Test
    void getName() {
        assertEquals("Pig", npc.getName());
    }
}