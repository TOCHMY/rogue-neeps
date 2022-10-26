package player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OgreTest {
    @Test
    public void Ogre_BaseStats() {
        Ogre player = new Ogre();
        Stats playerStats = player.stats;

        assertEquals(5, playerStats.getStrength());
        assertEquals(1, playerStats.getDexterity());
        assertEquals(1, playerStats.getIntelligence());
    }
}
