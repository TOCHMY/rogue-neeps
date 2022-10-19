import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShieldTest {

    List<MagicSocket> TWO_DEFENCE_SOCKETS;
    List<MagicSocket> BLUE_SOCKET;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;


    @BeforeEach
    public void createSockets(){
        TWO_DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        BLUE_SOCKET =  List.of
                (new MagicSocket(MagicColor.BLUE));
        GREEN_SOCKET =  List.of
                (new MagicSocket(MagicColor.GREEN));
        PURPLE_SOCKET =  List.of
                (new MagicSocket(MagicColor.PURPLE));
    }

    @Test
    public void testCreateShield(){
        Shield shield = new Shield(50, TWO_DEFENCE_SOCKETS);
        assertEquals(50, shield.getStrength());
        assertEquals(TWO_DEFENCE_SOCKETS, shield.getSockets());
    }

    @Test
    void testCreateShieldWithWrongStones() {
        assertThrows(IllegalArgumentException.class, ()->{
            new Shield(50, BLUE_SOCKET);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            new Shield(50, GREEN_SOCKET);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            new Shield(50, PURPLE_SOCKET);
        });
    }
}
