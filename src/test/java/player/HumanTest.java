package player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanTest {

    @Test
    public void Human_BaseStats() {
        Human player = new Human();
        Stats playerStats = player.stats;

        assertEquals(1, playerStats.getStrength());
        assertEquals(1, playerStats.getDexterity());
        assertEquals(5, playerStats.getIntelligence());

    }
}
