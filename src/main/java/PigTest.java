import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


public class PigTest {

    @Test
    public void TestgKillable(){
        Pig pig = new Pig();
        pig.die();
        assertEquals(0, pig.getHealth());
    }

    @Test
    public void TestXp(){
        Pig pig = new Pig();
        assertNotEquals(0, pig.getXp());
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
