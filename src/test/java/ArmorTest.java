import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmorTest {


    List<MagicSocket> THREE_SOCKETS;

    @BeforeEach
    public void createSockets(){

        THREE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.PURPLE));
    }



    @Test
    public void testCreateArmor(){
        Armor armor = new Armor(50, THREE_SOCKETS);

        assertEquals(50, armor.getStrength());
        assertEquals(THREE_SOCKETS, armor.getSockets());
    }



}
