

import org.junit.jupiter.api.Assertions;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {

    @Test
    public void TestEmptyConstructor(){
        Player p = new Player();
    }

    @Test
    public void TestGetPlayerAttributes(){
        Player p = new Player();
        HashMap<Attributes, Integer> attrs = p.getAttributes();
        assertEquals(1, attrs.get(Attributes.STRENGTH));
        assertEquals(1, attrs.get(Attributes.DEXTERITY));
        assertEquals(1, attrs.get(Attributes.INTELLIGENCE));
    }

    @Test
    public void TestGetLvl(){
        Player p = new Player();
        int lvl = p.getLvl();
        assertEquals(1, lvl);
    }

    @Test
    public void TestGetRemainingXp(){
        Player p = new Player();
        int remaining = p.getRemainingXp();
        assertEquals(100, remaining);
    }

    @Test
    public void TestAddXp(){
        int XPGained = 10;

        Player p = new Player();
        int xpBefore = p.getRemainingXp();
        p.addXp(XPGained);
        int xpAfter = p.getRemainingXp();
        assertEquals(xpBefore, xpAfter+XPGained);

    }

    @Test
    public void TestLvlUp(){
        Player p = new Player();
        p.addXp(110);

        Assertions.assertEquals(2, p.getLvl());
        Assertions.assertEquals(190, p.getRemainingXp());
    }
    @Test
    public void TestKillEnemy(){
        Player p = new Player();
        Pig pig = new Pig();
        pig.takeDmg(p, 20);
        Assertions.assertEquals(0, pig.getHP());
        Assertions.assertEquals( 80, p.getRemainingXp());
    }
    @Test
    public void TestLvlUpByPig(){
        Player p = new Player();
        for(int i = 0; i < 5; i++){
            Pig pig = new Pig();
            pig.takeDmg(p, 10);
        }
        Assertions.assertEquals(2, p.getLvl());
        Assertions.assertEquals(200, p.getRemainingXp());
    }


}