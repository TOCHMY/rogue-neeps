package player;

import item.items.Armor;
import item.items.Weapon;
import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import map.Map;
import map.Tile;
import npc.Pig;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static player.PlayerTest.PlayerMatcher.occupiesTile;

import org.junit.jupiter.api.Assertions;
import util.Direction;
import util.Position;

import java.util.List;

public class PlayerTest {

    private static final int STARTING_XP = 100;

    public class PlayerMatcher {

        public static TypeSafeMatcher<Player> occupiesTile(Tile tile) {
            return new TypeSafeMatcher<Player>() {
                @Override
                protected boolean matchesSafely(Player player) {

                    if (player.getPosition() == tile.getPosition() && tile.isOccupied()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                @Override
                public void describeTo(Description description) {
                    description.appendText("Player should occupy its Map's position");
                }
            };
        }
    }

    @Test
    public void WhenPlayerHasPositionExpectTileIsOccupied(){
        Player player = new Ogre();
        Map map = new Map();
        Position roomPos = new Position(1,1);

        player.setMap(map);
        map.getTile(roomPos).makeRoomTile();

        player.moveTo(roomPos);

        Tile playerTile = map.getTile(player.getPosition());
        assertThat(player, occupiesTile(playerTile));
    }

    @Test
    public void When_Concrete_Expect_ExperienceExists() {
        Player p = new Ogre();
        assertEquals(STARTING_XP, p.getRemainingXp());
    }

    @Test
    public void When_Concrete_Expect_Lvl() {
        Player p = new Ogre();
        int lvl = p.getLvl();
        assertEquals(1, lvl);
    }

    @Test
    public void When_Concrete_Test_AddXp() {
        int XPGained = 10;

        Ogre p = new Ogre();
        int xpBefore = p.getRemainingXp();
        p.addXp(XPGained);
        int xpAfter = p.getRemainingXp();
        assertEquals(xpBefore, xpAfter + XPGained);

    }

    @Test
    public void When_Concrete_Test_LvlUp() {
        Player p = new Ogre();
        p.addXp(110);

        Assertions.assertEquals(2, p.getLvl());
        Assertions.assertEquals(190, p.getRemainingXp());
    }

    @Test
    public void When_Concrete_Test_Combat() {
        Player p = new Ogre();
        Pig pig = new Pig();
        pig.takeDmg(p, 20);
        Assertions.assertEquals(0, pig.getHP());
        Assertions.assertEquals(90, p.getRemainingXp());
    }

    @Test
    public void WhenNoMapExpectErrorOnMove() {
        Player p = new Ogre();
        assertThrows(IllegalStateException.class, () -> p.move(Direction.UP));
    }

    @Test
    public void WhenMapButNoWalkableTileExpectCanMove() {
        Player player = new Ogre();
        Map map = new Map();
        player.setMap(map);
        assertThrows(IllegalStateException.class, () -> player.moveTo(new Position(0, 0)));
    }

    @Test
    void when_AttackWithWeapon_Expect_CorrectDamageOnPig() {
        Player player = new Ogre();
        Pig pig = new Pig();
        player.getItems().addRightHandItem(new Weapon(10, List.of(new MagicSocket(MagicColor.BLUE))));
        player.getItems().getRightHandItem().addStone(new GemStone(MagicColor.BLUE, 20, 2));

        player.attack(pig);

        double expectedPigStrength = 10 - 5 * 1.12;
        assertEquals(expectedPigStrength, pig.getHP(),0.01);
    }

    @Test
    void when_AttackWithWeaponTwice_Expect_WeakerAttack() {
        Player player = new Ogre();
        Pig pig1 = new Pig();
        Pig pig2 = new Pig();
        player.getItems().addRightHandItem(new Weapon(10, List.of(new MagicSocket(MagicColor.BLUE))));
        player.getItems().getRightHandItem().addStone(new GemStone(MagicColor.BLUE, 20, 2));

        player.attack(pig1);
        player.attack(pig2);

        double expectedPig2Strength = 10 - 5 * 1.09;
        assertEquals(expectedPig2Strength, pig2.getHP(),0.01);
    }

    @Test
    void when_PlayerHasIntelligenceItem_Expect_IncreasedIntelligence() {
        Player player = new Human();
        player.getItems().addArmor(new Armor(40, List.of(new MagicSocket(MagicColor.GREEN), new MagicSocket(MagicColor.RED))));
        player.getItems().getArmor().addStone(new GemStone(MagicColor.GREEN, 30, 0.2));

        assertEquals(17, player.getIntelligenceWithItems());
    }


}