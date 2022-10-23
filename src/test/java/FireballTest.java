
import map.Tile;

public class FireballTest {
    static final Tile playerStartingTile = new Tile(8, 10);
    static final Tile playerStartingTileCloseToWall = new Tile(3, 8);

   /* @Test
    void testFireballRange() {
        map.Map map = new map.Map();
        map.initiateDungeon(playerStartingTile);
        map.printDungeon("off", "off", "on");
        Fireball fireball = new Fireball();
        player.Player p = new player.Player();
        map.Tile targetTile = map.getPerimeterArray()[4][10];
        util.FacingDirection playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(targetTile.getRow(), fireball.cast(playerStartingTile, playerFacingDirection).getRow());
        assertEquals(targetTile.getColumn(), fireball.cast(playerStartingTile, playerFacingDirection).getColumn());
    }*/

    /*//Testa att fireball kolliderar med vägg
    @Test
    void testCastFireball_fireballCollideWithWall() {
        map.Map map = new map.Map();
        map.initiateDungeon(playerStartingTileCloseToWall);
        map.printDungeon("off", "off", "on");
        Fireball fireball = new Fireball();

        player.Player p = new player.Player();
        util.FacingDirection playerFacingDirection = p.getPlayerFacingDirection();
        assertEquals(1, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getRow());
        assertEquals(8, fireball.cast(playerStartingTileCloseToWall, playerFacingDirection).getColumn());
    }*/
}
