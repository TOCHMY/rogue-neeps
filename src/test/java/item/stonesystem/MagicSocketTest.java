package item.stonesystem;

import org.junit.jupiter.api.Test;
import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MagicSocketTest {

    @Test
    public void testCreateSocketWithValidColor(){
        MagicSocket socket = new MagicSocket(MagicColor.BLUE);
        assertEquals(MagicColor.BLUE, socket.getColor());
    }

    @Test
    public void testAddGemstoneOfCorrectColor(){
        MagicSocket socket = new MagicSocket(MagicColor.BLUE);
        GemStone gemStone = new GemStone(MagicColor.BLUE, 5 ,5);
        socket.addStone(gemStone);
        assertEquals(gemStone, socket.getGemStone());
    }

    @Test
    public void testAddGemstoneOfIncorrectColor(){
        MagicSocket socket = new MagicSocket(MagicColor.RED);
        GemStone gemStone = new GemStone(MagicColor.BLUE, 5 ,5);
        assertThrows(IllegalArgumentException.class, () -> socket.addStone(gemStone));
    }



}
