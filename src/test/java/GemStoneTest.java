import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GemStoneTest {

    @Test
    public void testCreateGemstoneWithColorStrengthAndCost(){
        GemStone gemStone = new GemStone(MagicColor.BLUE,5,2);
        assertEquals(MagicColor.BLUE, gemStone.getColor());
        assertEquals(5, gemStone.getStrength());
        assertEquals(2, gemStone.getCost());
    }

    @Test
    public void testInvalidStrength(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 0, 10));
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 11, 10));
    }

    @Test
    public void testInvalidCost(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, 0));

        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, 11));

    }





}
