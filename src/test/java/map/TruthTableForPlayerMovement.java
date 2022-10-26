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
    EnemyNPC enemy;
    FriendlyNPC friendly;
    Player ogre;
    Tile room;
    Tile tunnel;
    Tile water;
    Tile swamp;

    @BeforeEach
    public void initEach(){
        enemy = new Pig();
        friendly = new FriendlyNPC("Friendly NPC");
        human = new Human();
        ogre = new Ogre();

        room = new Tile(0,0);
        room.makeRoomTile();

        tunnel = new Tile(0,0);
        tunnel.makeTunnelTile();

        water = new Tile(0,0);
        water.makeWaterTile();

        swamp = new Tile(0,0);
        swamp.makeSwampTile();

    }

    @Test
    // Testcase ID 1
    public void Human_StrBelow10_RoomTile_Expect_True(){
        assertTrue(human.canMove(room));
    }
    @Test
    // Testcase ID 2
    public void Human_StrAbove10Below20_RoomTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(room));
    }
    @Test
    // Testcase ID 3
    public void Human_StrAbove20_RoomTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(room));
    }
    @Test
    // Testcase ID 4
    public void Human_StrBelow10_TunnelTile_Expect_True(){
        assertTrue(human.canMove(tunnel));
    }
    @Test
    // Testcase ID 5
    public void Human_StrAbove10Below20_TunnelTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(tunnel));
    }
    @Test
    // Testcase ID 6
    public void Human_StrAbove20_TunnelTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(tunnel));
    }

    @Test
    // Testcase ID 7
    public void Human_StrBelow10_SwampTile_Expect_False(){
        assertFalse(human.canMove(swamp));
    }

    @Test
    // Testcase ID 8
    public void Human_StrAbove10Below20_SwampTile_Expect_False(){
        human.getStats().addStrength(10);
        assertFalse(human.canMove(swamp));
    }

    @Test
    // Testcase ID 9
    public void Human_StrAbove20_SwampTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(swamp));
    }

    @Test
    // Testcase ID 10
    public void Human_StrBelow10_WaterTile_Expect_False(){
        assertFalse(human.canMove(water));
    }

    @Test
    // Testcase ID 11
    public void Human_StrAbove10Below20_WaterTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(water));
    }

    @Test
    // Testcase ID 12
    public void Human_StrAbove20_WaterTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(water));
    }

    @Test
    // Testcase ID 13
    public void Ogre_StrBelow10_RoomTile_Expect_True(){
        assertTrue(ogre.canMove(room));
    }

    @Test
    // Testcase ID 14
    public void Ogre_StrAbove10_Below20_RoomTile_Expect_True(){
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(room));
    }

    @Test
    // Testcase ID 15
    public void Ogre_StrAbove20_RoomTile_Expect_True(){
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(room));
    }

    @Test
    // Testcase ID 16
    public void Ogre_StrBelow10_TunnelTile_Expect_True(){
        assertTrue(ogre.canMove(tunnel));
    }

    @Test
    // Testcase ID 17
    public void Ogre_StrAbove10_Below20_TunnelTile_Expect_True(){
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(tunnel));
    }

    @Test
    // Testcase ID 18
    public void Ogre_StrAbove20_TunnelTile_Expect_True(){
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(tunnel));
    }

    @Test
    // Testcase ID 19
    public void Ogre_StrBelow10_SwampTile_Expect_False(){
        assertFalse(ogre.canMove(swamp));
    }

    @Test
    // Testcase ID 20
    public void Ogre_StrAbove10_Below20_SwampTile_Expect_True(){
        ogre.getStats().addStrength(10);
        assertTrue(ogre.canMove(swamp));
    }

    @Test
    // Testcase ID 21
    public void Ogre_StrAbove20_SwampTile_Expect_True(){
        ogre.getStats().addStrength(21);
        assertTrue(ogre.canMove(swamp));
    }

    @Test
    // Testcase ID 22
    public void Ogre_StrBelow10_WaterTile_Expect_False(){
        assertFalse(ogre.canMove(water));
    }

    @Test
    // Testcase ID 23
    public void Ogre_StrAbove10_Below20_WaterTile_Expect_False(){
        ogre.getStats().addStrength(10);
        assertFalse(ogre.canMove(water));
    }

    @Test
    // Testcase ID 24
    public void Ogre_StrAbove20_WaterTile_Expect_False(){
        ogre.getStats().addStrength(21);
        assertFalse(ogre.canMove(water));
    }




    @Test
    // Testcase ID 25
    public void NPC_Friendly_RoomTile_Expect_True(){
        assertTrue(friendly.canMove(room));
    }

    @Test
    // Testcase ID 26
    public void NPC_Friendly_TunnelTile_Expect_False(){
        assertFalse(friendly.canMove(tunnel));
    }

    @Test
    // Testcase ID 27
    public void NPC_Friendly_SwampTile_Expect_False(){
        assertFalse(friendly.canMove(swamp));
    }

    @Test
    // Testcase ID 28
    public void NPC_Friendly_WaterTile_Expect_False(){

        assertFalse(friendly.canMove(water));
    }

    @Test
    // Testcase ID 29
    public void NPC_Enemy_RoomTile_Expect_True(){
        assertTrue(enemy.canMove(room));
    }

    @Test
    // Testcase ID 30
    public void NPC_Enemy_TunnelTile_Expect_False(){
        assertFalse(enemy.canMove(tunnel));
    }

    @Test
    // Testcase ID 31
    public void NPC_Enemy_SwampTile_Expect_False(){
;
        assertFalse(enemy.canMove(swamp));
    }

    @Test
    // Testcase ID 32
    public void NPC_Enemy_WaterTile_Expect_False(){
        assertFalse(enemy.canMove(water));
    }
}
