package map;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.NPC;
import npc.Pig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import player.Human;
import player.Ogre;
import player.Player;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TruthTableForPlayerMovement {
    Player human;
    Map map;
    Room roomA;
    Position tileToTest;
    Position tunnelPosition;
    EnemyNPC enemy;
    FriendlyNPC friendly;
    Player ogre;

    @BeforeEach
    public void initEach(){
        enemy = new Pig();
        friendly = new FriendlyNPC("Friendly NPC");

        human = new Human();
        ogre = new Ogre();
        map = new Map();
        human.setMap(map);
        roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);

        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        tunnelPosition = tunnelStart.getPosition();

        tileToTest = new Position(3, 9);
    }

    @Test
    // Testcase ID 1
    public void Human_StrBelow10_FloorTile_Expect_True(){
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    // Testcase ID 2
    public void Human_StrAbove10Below20_FloorTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    // Testcase ID 3
    public void Human_StrAbove20_FloorTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    // Testcase ID 4
    public void Human_StrBelow10_TunnelTile_Expect_True(){
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }
    @Test
    // Testcase ID 5
    public void Human_StrAbove10Below20_TunnelTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }
    @Test
    // Testcase ID 6
    public void Human_StrAbove20_TunnelTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 7
    public void Human_StrBelow10_SwampTile_Expect_False(){
        roomA.addSwampTile(tileToTest);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 8
    public void Human_StrAbove10Below20_SwampTile_Expect_False(){
        roomA.addSwampTile(tileToTest);
        human.getStats().addStrength(10);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 9
    public void Human_StrAbove20_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 10
    public void Human_StrBelow10_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 11
    public void Human_StrAbove10Below20_WaterTile_Expect_True(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 12
    public void Human_StrAbove20_WaterTile_Expect_True(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 13
    public void Ogre_StrBelow10_RoomTile_Expect_True(){
        assertTrue(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 14
    public void Ogre_StrAbove10_Below20_RoomTile_Expect_True(){
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 15
    public void Ogre_StrAbove20_RoomTile_Expect_True(){
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 16
    public void Ogre_StrBelow10_TunnelTile_Expect_True(){
        assertTrue(ogre.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 17
    public void Ogre_StrAbove10_Below20_TunnelTile_Expect_True(){
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 18
    public void Ogre_StrAbove20_TunnelTile_Expect_True(){
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 19
    public void Ogre_StrBelow10_SwampTile_Expect_False(){
        roomA.addSwampTile(tileToTest);
        assertFalse(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 20
    public void Ogre_StrAbove10_Below20_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 21
    public void Ogre_StrAbove20_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 22
    public void Ogre_StrBelow10_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 23
    public void Ogre_StrAbove10_Below20_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        ogre.getStats().addStrength(10);
        assertFalse(ogre.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 24
    public void Ogre_StrAbove20_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        ogre.getStats().addStrength(21);
        assertFalse(ogre.canMove(map.getTile(tileToTest)));
    }




    @Test
    // Testcase ID 25
    public void NPC_Friendly_RoomTile_Expect_True(){
        assertTrue(friendly.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 26
    public void NPC_Friendly_TunnelTile_Expect_False(){
        assertFalse(friendly.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 27
    public void NPC_Friendly_SwampTile_Expect_False(){
        roomA.addSwampTile(tileToTest);
        assertFalse(friendly.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 28
    public void NPC_Friendly_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(friendly.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 29
    public void NPC_Enemy_RoomTile_Expect_True(){
        assertTrue(enemy.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 30
    public void NPC_Enemy_TunnelTile_Expect_False(){
        assertFalse(enemy.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    // Testcase ID 31
    public void NPC_Enemy_SwampTile_Expect_False(){
        roomA.addSwampTile(tileToTest);
        assertFalse(enemy.canMove(map.getTile(tileToTest)));
    }

    @Test
    // Testcase ID 32
    public void NPC_Enemy_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(enemy.canMove(map.getTile(tileToTest)));
    }
}
