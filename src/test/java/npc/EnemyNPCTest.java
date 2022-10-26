package npc;

import map.Map;
import org.junit.jupiter.api.Test;
import player.Human;
import util.Direction;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyNPCTest {

    //Using this class to test abstract NPC class

    @Test
    void testEnemyNPC_createNewPig() {
        Pig pig = new Pig();
        assertEquals("Pig", pig.getName());
    }

    @Test
    void testEnemyNPC_createNewAlbatross() {
        Albatross albatross = new Albatross();
        assertEquals("Albatross", albatross.getName());
    }

    @Test
    void testEnemyNPCDie_isDead() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true, 10, 10);
        assertFalse(npc.isDead());
        npc.die();
        assertTrue(npc.isDead());
    }

    @Test
    void testEnemyNPC_returnsCorrectXP() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true, 10, 10);
        assertEquals(10, npc.getXP());
    }

    @Test
    void testEnemyNPC_returnsCorrectHP() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true, 10, 10);
        assertEquals(10, npc.getHP());
    }

    @Test
    void testEnemyNPC_takesCorrectAmountOfDamage() {
        Human player = new Human();
        EnemyNPC npc = new EnemyNPC("Pig", 1, true, 10, 10);
        npc.takeDmg(player, 5);
        assertEquals(5, npc.getHP());
    }

    @Test
    void testEnemyNPC_isMeleeEnemy() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true);
        assertTrue(npc.isMeleeEnemy());
    }

    @Test
    void testEnemyNPC_toStringFormat() {
        EnemyNPC npc = new EnemyNPC("Spöket Laban", 2, true);
        assertEquals("EnemyNPC:\nName: Spöket Laban\nLevel: 2\nHP: 20.0", npc.toString());
    }

    @Test
    void testMoveFromAbstractClassNPC_noMapThrowsException() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true);
        assertThrows(IllegalStateException.class, ()
                -> npc.move(Direction.UP));
    }
}
