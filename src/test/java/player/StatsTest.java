package player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatsTest {

    @Test
    public void TestConstructorValues(){
        Stats stats = new Stats(1,2,3);

        assertEquals(1, stats.getStrength());
        assertEquals(2, stats.getDexterity());
        assertEquals(3, stats.getIntelligence());
    }

    @Test
    public void TestAddStrength(){
        Stats stats = new Stats(1,2,3);
        stats.addStrength(2);
        assertEquals(3, stats.getStrength());

    }

    @Test
    public void TestAddIntelligence(){
        Stats stats = new Stats(1,2,3);
        stats.addIntelligence(1);
        assertEquals(4, stats.getIntelligence());
    }

    @Test
    public void TestAddDexterity(){
        Stats stats = new Stats(1,2,3);
        stats.addDexterity(1);
        assertEquals(3, stats.getDexterity());
    }


}
