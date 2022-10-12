import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagicSocketTest {

    @Test
    public void testCreateSocketWithValidColor(){
        MagicSocket socket = new MagicSocket(MagicColor.BLUE);
        assertEquals(MagicColor.BLUE, socket.getColor());
    }

    @Test
    public void testAddGemstoneOfCorrectColor(){
        GemStone gemStone = new GemStone(MagicColor.BLUE, 5 ,5);
        MagicSocket socket = new MagicSocket(MagicColor.BLUE);
        socket.addStone(gemStone);
        assertEquals(gemStone, socket.getGemStone());
    }



}
