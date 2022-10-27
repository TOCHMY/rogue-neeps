package player;

import map.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HumanTest {

    @Test
    public void Human_BaseStats() {
        Human player = new Human();
        Stats playerStats = player.stats;

        assertEquals(1, playerStats.getStrength());
        assertEquals(1, playerStats.getDexterity());
        assertEquals(5, playerStats.getIntelligence());
    }

    @Test
    public void WhenDefaultTileExpectFalse(){
        Tile tile = new Tile(1,1);
        Human player = new Human();
        assertFalse( player.canMove(tile));

    }
}
