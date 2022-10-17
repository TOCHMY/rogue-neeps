

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {
    Map map = new Map();
    final Tile playerStartingTile = new Tile(10, 10);

    @Test
    void testX() {
        assertEquals(10, playerStartingTile.getX());
    }


    @Test
    void testFireballRange() {

       /* Fireball fireball = new Fireball();
        Player p = new Player();
        Tile targetTile = new Tile(10, 16);
        int playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(targetTile, fireball.cast(playerStartingTile, playerFacingDirection));*/
    }
}
