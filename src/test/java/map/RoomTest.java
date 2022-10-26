package map;

import npc.Albatross;
import npc.EnemyNPC;
import npc.FriendlyNPC;
import org.junit.jupiter.api.Test;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {


    @Test
    void When_RoomIsCreated_Expect_getNameSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals("testName", room.getName());
    }

    @Test
    void When_RoomIsCreated_Expect_getWidthSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals(5, room.getWidth());
    }

    @Test
    void When_RoomIsCreated_Expect_getHeightSuccess(){
        Map map = new Map();
        Room room = new Room("testName", 5,5,new Tile(1,1), map);
        assertEquals(5, room.getHeight());
    }

    @Test
    void When_RoomIsCreated_Expect_getStartingTileSuccess(){
        Map map = new Map();
        Tile roomStartingTile = new Tile(1,1);
        Room room = new Room("testName", 5,5,roomStartingTile, map);
        assertEquals(roomStartingTile, room.getStartingTile());
    }

    @Test
    void When_RoomIsCreated_And_OneFriendlyNpcIsPlaced_Expect_getFriendlyNPCsListSizeOne(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);

        FriendlyNPC friendlyNPC = new FriendlyNPC("Trainer");
        friendlyNPC.setMap(map);
        Position npcPos = new Position(5,10);
        roomA.addFriendlyNpc(friendlyNPC, npcPos);

        assertEquals(1, roomA.getFriendlyNpcs().size());
    }

    @Test
    void When_FriendlyNPCisPlacedOnOccupiedTile_Expect_addFriendlyNPCThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27, 8, new Tile(2, 7), map);
                    map.addRoom(roomA);
                    FriendlyNPC friendlyNPC1 = new FriendlyNPC("Trainer");
                    FriendlyNPC friendlyNPC2 = new FriendlyNPC("Trainer");
                    friendlyNPC1.setMap(map);
                    friendlyNPC2.setMap(map);
                    Position npcPos = new Position(3, 15);
                    roomA.addFriendlyNpc(friendlyNPC1, npcPos);
                    roomA.addFriendlyNpc(friendlyNPC2, npcPos);
                });
    }




    @Test
    void When_RoomIsCreated_And_OneHostileNpcIsPlaced_Expect_getHostileNPCsListSizeOne(){
        Map map = new Map();
        Room roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        EnemyNPC enemy = new Albatross();
        enemy.setMap(map);
        Position npcPos = new Position(3,15);
        roomA.addHostileNpc(enemy, npcPos);
        assertEquals(1, roomA.getHostileNpcList().size());
    }

    @Test
    void When_EnemyNPCisPlacedOnOccupiedTile_Expect_addHostileNPCThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    FriendlyNPC friendlyNPC1 = new FriendlyNPC("Trainer");
                    EnemyNPC enemy = new Albatross();
                    friendlyNPC1.setMap(map);
                    enemy.setMap(map);
                    Position npcPos = new Position(3, 15);
                    roomA.addFriendlyNpc(friendlyNPC1, npcPos);
                    roomA.addHostileNpc(enemy, npcPos);
                });
    }

    @Test
    void When_EnemyNPCisPlacedOnTileOutsideOfRoom_Expect_addHostileNPCThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    EnemyNPC enemy = new Albatross();
                    enemy.setMap(map);
                    Position npcPos = new Position(1, 6);
                    roomA.addHostileNpc(enemy, npcPos);
                });
    }

    @Test
    void When_FriendlyNPCisPlacedOnTileOutsideOfRoom_Expect_addFriendlyNPCThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    FriendlyNPC friendlyNPC = new FriendlyNPC("Trainer");
                    friendlyNPC.setMap(map);
                    Position npcPos = new Position(1, 6);
                    roomA.addFriendlyNpc(friendlyNPC, npcPos);
                });
    }

    @Test
    void When_WaterTileIsAddedToRoom_And_tileIsAlreadySwamp_Expect_illegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    roomA.addSwampTile(new Position(3,8));
                    roomA.addWaterTile(new Position(3,8));
                });
    }

    @Test
    void When_SwampTileIsAddedToRoom_And_tileIsAlreadyWater_Expect_illegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    roomA.addWaterTile(new Position(3,8));
                    roomA.addSwampTile(new Position(3,8));
                });
    }

    @Test
    void When_WaterTileIsAddedOutsideRoom_Expect_illegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    roomA.addWaterTile(new Position(1,6));
                });
    }

    @Test
    void When_SwampTileIsAddedOutsideRoom_Expect_illegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Map map = new Map();
                    Room roomA = new Room("A", 27,8, new Tile(2,7), map);
                    map.addRoom(roomA);
                    roomA.addSwampTile(new Position(1,6));
                });
    }

    @Test
    void When_RoomIsCreated_Expect_getHostileNPCsListSizeOne(){
        Map map = new Map();
        Room roomA = new Room("A", 10,10, new Tile(1,1), map);
        map.addRoom(roomA);
        assertEquals(100, roomA.getRoomTilesList().size());
    }







}