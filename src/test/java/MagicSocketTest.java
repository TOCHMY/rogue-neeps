import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagicSocketTest {

    @Test
    public void testCreateSocketWithValidColor(){
        MagicSocket socket = new MagicSocket(MagicColor.BLUE);
        assertEquals(MagicColor.BLUE, socket.getColor());
    }

}
