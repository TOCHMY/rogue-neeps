import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


public class PigTest {

    @Test
    public void TestKillable(){
        Pig pig = new Pig();
        assertTrue(pig.getHP() > 0);
        pig.die();
        assertEquals(0, pig.getHP());
    }

    @Test
    public void TestXp(){
        Pig pig = new Pig();
        assertNotEquals(0, pig.getXP());
    }


    @Test
    public void TestStartPosition(){
        Pig pig = new Pig();
        assertEquals(0, pig.getCurrentX());
        assertEquals(0, pig.getCurrentY());
    }

    @Test
    public void TestCustomStartPos(){
        Pig pig = new Pig(20, 20);
        assertEquals(20, pig.getCurrentX());
        assertEquals(20, pig.getCurrentY());
    }
    @Test
    public void TestPigMovement(){
        Pig pig = new Pig();
        pig.moveUp();
        assertEquals(10, pig.getCurrentY());
    }


}
