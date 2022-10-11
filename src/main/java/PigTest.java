import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


public class PigTest {

    @Test
    public void TestPigKillable(){

    }

    @Test
    public void TestPigMovement(){
        Pig pig = new Pig();
        pig.moveUp();
        assertEquals(10, pig.getCurrentY());
    }
}
