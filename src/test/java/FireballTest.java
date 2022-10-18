import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {
    static final Tile playerStartingTile = new Tile(8, 10);
    static final Tile playerStartingTileCloseToWall = new Tile(3, 8);

    @Test
    void testFireballRange() {
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        map.printDungeon("off", "off", "on");
        Fireball fireball = new Fireball();
        Player p = new Player();
        Tile targetTile = map.getPerimeterArray()[4][10];
        FacingDirection playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(targetTile.getRow(), fireball.cast(playerStartingTile, playerFacingDirection).getRow());
        assertEquals(targetTile.getColumn(), fireball.cast(playerStartingTile, playerFacingDirection).getColumn());
    }

    //Testa att fireball kolliderar med vägg
    @Test
    void testCastFireball_fireballCollideWithWall() {
        Map map = new Map();
        map.initiateDungeon(playerStartingTileCloseToWall);
        map.printDungeon("off", "off", "on");
        Fireball fireball = new Fireball();

        Player p = new Player();
        FacingDirection playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(1, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getRow());
        assertEquals(8, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getColumn());
    }
}
