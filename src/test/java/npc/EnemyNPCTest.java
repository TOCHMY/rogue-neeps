package npc;

import map.Map;
import org.junit.jupiter.api.Test;
import util.Direction;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyNPCTest {

    //Using this class to test abstract NPC class

    @Test
    void testEnemyNPCDie_isDead() {
        EnemyNPC npc = new EnemyNPC("Pig", 1, true);
        assertFalse(npc.isDead());
        npc.die();
        assertTrue(npc.isDead());
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
