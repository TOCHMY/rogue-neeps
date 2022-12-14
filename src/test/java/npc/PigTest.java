package npc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Human;


public class PigTest {

    //Using this class to test EnemyNPC

    Pig pig;

    @BeforeEach
    void setup() {
        pig = new Pig();
    }

    @Test
    public void testKillable() {
        assertTrue(pig.getHP() > 0);
        pig.die();
        assertEquals(0, pig.getHP());
    }

    @Test
    public void testXp() {
        assertNotEquals(0, pig.getXP());
    }

    @Test
    void pigHasTenHitPointsWhenCreated() {
        assertEquals(10, pig.getHP());
    }

    @Test
    void takeFiveDamage_pigStillAlive() {
        Human player = new Human();
        pig.takeDmg(player, 5);
        assertEquals(5, pig.getHP());
    }

    @Test
    void takeTenDamage_pigHitPointsIsZero() {
        Human player = new Human();
        pig.takeDmg(player, 10);
        assertEquals(0, pig.getHP());
    }

    @Test
    void takeElevenDamage_pigHitPointsIsZero() {
        Human player = new Human();
        pig.takeDmg(player, 11);
        assertEquals(0, pig.getHP());

    }

    @Test
    void takeTenDamage_pigIsDead() {
        Human player = new Human();
        pig.takeDmg(player, 10);
        assertTrue(pig.isDead());
    }

    @Test
    void pigDies_playerReceivesCorrectAmountOfExperiencePoints() {
        Human player = new Human();
        pig.takeDmg(player, 10);
        assertEquals(10, player.getCurrentXp());
    }



}
