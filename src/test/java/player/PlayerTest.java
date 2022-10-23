package player;

import map.Map;
import npc.Pig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
//import org.testng.annotations.Test;
import player.Human;
import player.Ogre;
import player.Player;
import util.Position;

public class PlayerTest {

    private static int STARTING_XP = 100;

    @Test
    public void When_Concrete_Expect_Success(){
        new Human();
        new Ogre();
    }
    @Test
    public void When_Concrete_Expect_ExperienceExists(){
        Player p = new Ogre();
        assertEquals(STARTING_XP, p.getRemainingXp());
    }

    @Test
    public void When_Concrete_Expect_Lvl(){
        Player p = new Ogre();
        int lvl = p.getLvl();
        assertEquals(1, lvl);
    }

    @Test
    public void When_Concrete_Test_AddXp(){
        int XPGained = 10;

        Ogre p = new Ogre();
        int xpBefore = p.getRemainingXp();
        p.addXp(XPGained);
        int xpAfter = p.getRemainingXp();
        assertEquals(xpBefore, xpAfter+XPGained);

    }

    @Test
    public void When_Concrete_Test_LvlUp(){
        Player p = new Ogre();
        p.addXp(110);

        Assertions.assertEquals(2, p.getLvl());
        Assertions.assertEquals(190, p.getRemainingXp());
    }
    @Test
    public void When_Concrete_Test_Combat(){
        Player p = new Ogre();
        Pig pig = new Pig();
        pig.takeDmg(p, 20);
        Assertions.assertEquals(0, pig.getHP());
        Assertions.assertEquals( 80, p.getRemainingXp());
    }

    @Test
    public void WhenNoMapExpectErrorOnMove(){
        Player p = new Ogre();
        assertThrows(IllegalStateException.class, () ->  p.moveUp());
    }

    @Test
    public void WhenMapExpectCanMove(){
        Player player = new Ogre();
        Map map = new Map();
        map.setPlayer(player, new Position(0, 0));
        assertDoesNotThrow(() -> player.moveRight());
    }

}