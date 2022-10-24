package item.weapon;

import item.GemStone;
import item.MagicColor;
import item.MagicSocket;
import item.Shield;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import item.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShieldTest {

    Shield SHIELD;
    DefenceVisitor DEFENCEVISITOR;
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
        SHIELD = new Shield(50, TWO_DEFENCE_SOCKETS);
        DEFENCEVISITOR = new DefenceVisitor();
    }

    @Test
    public void testCreateShield(){
        Shield shield = new Shield(50, TWO_DEFENCE_SOCKETS);
        assertEquals(50, shield.getBaseStrength());
        assertEquals(TWO_DEFENCE_SOCKETS, shield.getSockets());
    }

    @ParameterizedTest
    @EnumSource(value = MagicColor.class, names = {"GREEN", "PURPLE", "BLUE"})
    void testCreateArmorWithWrongSocket(MagicColor color) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Shield(50, List.of(new MagicSocket(color)));
        });
    }

    @Test
    void testDefendWithoutStones() {
        assertEquals(50, SHIELD.accept(DEFENCEVISITOR));
        assertEquals(49.5, SHIELD.getBaseStrength());
    }
    @Test
    void testDefendOnceWithStones() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));
        assertEquals(60, SHIELD.accept(DEFENCEVISITOR));
        assertEquals(47.5, SHIELD.getBaseStrength());
    }

    @Test
    void testDefendTwiceWithStones() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));
        SHIELD.accept(DEFENCEVISITOR);
        SHIELD.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, SHIELD.accept(DEFENCEVISITOR));
        assertEquals(25, SHIELD.getBaseStrength());
    }
}
