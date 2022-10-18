import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {

    static final Tile playerStartingTile = new Tile(8,10);
    static final Tile playerStartingTileCloseToWall = new Tile(3, 8);



    @Test
    void testX() {
        assertEquals(10, playerStartingTile.getX());
    }


    @Test
    void testFireballRange() {
        Map map = new Map();
        map.initiateDungeon(playerStartingTile);
        map.printDungeon("off", "off", "on");
        Fireball fireball = new Fireball();
        Player p = new Player();
        Tile targetTile = map.getPerimeterArray()[8][14];
        Tile wallTile = map.getPerimeterArray()[6][6];
        Tile knownTile = map.getPerimeterArray()[2][8];
        System.out.println(playerStartingTile.isWalkable());
        System.out.println(playerStartingTileCloseToWall.isWalkable());
        System.out.println(knownTile.isWalkable());
        System.out.println(wallTile.isVerticalWallTile());
        System.out.println(wallTile.isHorizontalWallTile());
        FacingDirection playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(targetTile.getX(), fireball.cast(playerStartingTile, playerFacingDirection).getX());
        assertEquals(targetTile.getY(), fireball.cast(playerStartingTile, playerFacingDirection).getY());
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
//        assertEquals(5, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getX());
        assertEquals(4, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getY());
    }
}
