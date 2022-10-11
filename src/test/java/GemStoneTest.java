import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GemStoneTest {

    @Test
    public void createGemstoneWithColorBoostAndCost(){
        GemStone gemStone = new GemStone(MagicColor.BLUE,5,2);
        assertEquals(MagicColor.BLUE, gemStone.getColor());
        assertEquals(5, gemStone.getStrength());
        assertEquals(2, gemStone.getCost());
    }





}
