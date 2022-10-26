package npc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Human;
import util.Direction;


public class AlbatrossTest {

    //Using this class to test abstract NPC class

    Albatross albatross;

    @BeforeEach
    void setup() {
        albatross = new Albatross();
    }

    @Test
    public void testKillable() {
        assertTrue(albatross.getHP() > 0);
        albatross.die();
        assertEquals(0, albatross.getHP());
    }

    @Test
    public void testXp() {
        assertNotEquals(0, albatross.getXP());
    }

    @Test
    void albatrossHasTenHitPointsWhenCreated() {
        assertEquals(10, albatross.getHP());
    }

    @Test
    void takeFiveDamage_albatrossStillAlive() {
        Human player = new Human();
        albatross.takeDmg(player, 5);
        assertEquals(5, albatross.getHP());
    }

    @Test
    void takeTenDamage_albatrossHitPointsIsZero() {
        Human player = new Human();
        albatross.takeDmg(player, 10);
        assertEquals(0, albatross.getHP());
    }

    @Test
    void takeElevenDamage_albatrossHitPointsIsZero() {
        Human player = new Human();
        albatross.takeDmg(player, 11);
        assertEquals(0, albatross.getHP());

    }

    @Test
    void takeTenDamage_albatrossIsDead() {
        Human player = new Human();
        albatross.takeDmg(player, 10);
        assertTrue(albatross.isDead());
    }

    @Test
    void albatrossDies_playerReceivesCorrectAmountOfExperiencePoints() {
        Human player = new Human();
        albatross.takeDmg(player, 10);
        assertEquals(20, player.getCurrentXp());
    }

    @Test
    void testMoveFromAbstractClassNPC_noMapThrowsException() {
        assertThrows(IllegalStateException.class, ()
                -> albatross.move(Direction.UP));
    }

}
