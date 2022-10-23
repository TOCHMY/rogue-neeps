package item.magic;

import org.junit.jupiter.api.Test;
import item.magic.GemStone;
import item.magic.MagicColor;

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
    public void testCreateGemstoneWithBVAValues(){
        GemStone gemStone = new GemStone(MagicColor.RED,30,30);
        assertEquals(MagicColor.RED, gemStone.getColor());
        assertEquals(30, gemStone.getStrength());
        assertEquals(30, gemStone.getCost());
    }

    @Test
    public void testInvalidStrength(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 0, 25));
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 31, 10));
    }

    @Test
    public void testInvalidCost(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, 0));

        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, 31));

    }





}
