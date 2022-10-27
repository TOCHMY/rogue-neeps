package item.stonesystem;

import org.junit.jupiter.api.Test;
import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;

import static org.junit.jupiter.api.Assertions.*;

public class GemStoneTest {

    @Test
    public void testCreateGemstoneCorrectColor(){
        GemStone gemStone = new GemStone(MagicColor.BLUE,5,2);

        assertEquals(MagicColor.BLUE, gemStone.color());
    }
    @Test
    public void testCreateGemstoneCorrectStrength(){
        GemStone gemStone = new GemStone(MagicColor.BLUE,5,2);

        assertEquals(5, gemStone.strength());
    }
    @Test
    public void testCreateGemstoneCorrectCost(){
        GemStone gemStone = new GemStone(MagicColor.BLUE,5,2);

        assertEquals(2, gemStone.cost());
    }

    @Test
    public void testCreateGemstoneWithLowestPossibleValues(){
        assertDoesNotThrow(() -> new GemStone(MagicColor.RED,1,0) );
    }

    @Test
    public void testCreateGemstoneWithHighestPossibleValues(){
        assertDoesNotThrow(() -> new GemStone(MagicColor.RED,30,30) );
    }

    @Test
    public void testTooLowStrength(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 0, 25));
    }

    @Test
    public void testTooHighStrength(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 31, 10));
    }

    @Test
    public void testTooLowCost(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, -0.1));
    }

    @Test
    public void testTooHighCost(){
        assertThrows(IllegalArgumentException.class, () ->
                new GemStone(MagicColor.BLUE, 10, 31));
    }





}
