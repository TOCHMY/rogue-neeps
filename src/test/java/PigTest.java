

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PigTest {

    @Test
    public void TestKillable(){
        Pig pig = new Pig();
        assertTrue(pig.getHP() > 0);
        pig.die();
        Assertions.assertEquals(0, pig.getHP());
    }

    @Test
    public void TestXp(){
        Pig pig = new Pig();
        Assertions.assertNotEquals(0, pig.getXP());
    }

}
