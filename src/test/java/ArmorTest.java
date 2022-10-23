
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArmorTest {

    List<MagicSocket> THREE_SOCKETS;
    Armor ARMOR_WITH_THREE_SOCKETS;
    DefenceVisitor DEFENCEVISITOR;
    List<MagicSocket> BLUE_SOCKET;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;

    @BeforeEach
    public void createSockets() {
        THREE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        ARMOR_WITH_THREE_SOCKETS = new Armor(50, THREE_SOCKETS);
        DEFENCEVISITOR = new DefenceVisitor();
        BLUE_SOCKET = List.of
                (new MagicSocket(MagicColor.BLUE));
        GREEN_SOCKET = List.of
                (new MagicSocket(MagicColor.GREEN));
        PURPLE_SOCKET = List.of
                (new MagicSocket(MagicColor.PURPLE));
    }

    @Test
    public void testCreateArmor() {
        Armor armor = new Armor(50, THREE_SOCKETS);
        assertEquals(50, armor.getStrength());
        assertEquals(THREE_SOCKETS, armor.getSockets());
    }

    @Test
    void testDefendWithoutStones() {
        assertEquals(50, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(49.5, ARMOR_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testDefendOnceWithStones() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 20, 2));
        assertEquals(60, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(47.5, ARMOR_WITH_THREE_SOCKETS.getStrength());
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(25, ARMOR_WITH_THREE_SOCKETS.getStrength());
    }
    @Test
    void testDefendTwiceWithStones() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 20, 2));
        ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR);
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(25, ARMOR_WITH_THREE_SOCKETS.getStrength());
    }

    @ParameterizedTest
    @EnumSource(value = MagicColor.class, names = {"GREEN", "PURPLE", "BLUE"})
    void testCreateArmorWithWrongSocket(MagicColor color) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Armor(50, List.of(new MagicSocket(color)));
        });
    }

}
