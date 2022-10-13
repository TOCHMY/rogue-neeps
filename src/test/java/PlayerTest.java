import org.junit.Test;


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

        assertEquals(2, p.getLvl());
        assertEquals(190, p.getRemainingXp());
    }
    @Test
    public void TestKillEnemy(){
        Player p = new Player();
        Pig pig = new Pig();
        pig.takeDmg(p, 20);
        assertEquals(0, pig.getHP());
        assertEquals( 80, p.getRemainingXp());
    }
    @Test
    public void TestLvlUpByPig(){
        Player p = new Player();
        for(int i = 0; i < 5; i++){
            Pig pig = new Pig();
            pig.takeDmg(p, 10);
        }
        assertEquals(2, p.getLvl());
        assertEquals(200, p.getRemainingXp());
    }
    @Test
    public void TestPlayerMovement(){
        Player p = new Player();
        assertEquals(0, p.getCurrentX());
        assertEquals(0, p.getCurrentY());

        p.moveUp();

        assertEquals(5, p.getCurrentY());
    }
    @Test
    public void TestPlayerMovementWithDexterity(){
        Player p = new Player();

        p.addDexterity(10);


        p.moveUp();

        assertEquals(6, p.getCurrentY());
    }

}