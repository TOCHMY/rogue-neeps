package item.items;

import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.DefenceVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShieldTest {

    Shield SHIELD;
    DefenceVisitor DEFENCEVISITOR;
    List<MagicSocket> TWO_DEFENCE_SOCKETS;

    @BeforeEach
    public void createSockets() {
        TWO_DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        SHIELD = new Shield(50, TWO_DEFENCE_SOCKETS);
        DEFENCEVISITOR = new DefenceVisitor();
    }

    @Test
    public void testCreateShieldCorrectStrength() {
        Shield shield = new Shield(50, TWO_DEFENCE_SOCKETS);

        assertEquals(50, shield.getBaseStrength());
    }

    @Test
    public void testCreateShieldCorrectSockets() {
        Shield shield = new Shield(50, TWO_DEFENCE_SOCKETS);

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
    void testDefendWithoutStonesCorrectStrength() {
        assertEquals(50, SHIELD.accept(DEFENCEVISITOR));
    }

    @Test
    void testDefendWithoutStonesCorrectCost() {
        SHIELD.accept(DEFENCEVISITOR);

        assertEquals(49.5, SHIELD.getBaseStrength());
    }

    @Test
    void testDefendOnceWithStonesCorrectStrength() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));

        assertEquals(60, SHIELD.accept(DEFENCEVISITOR));
    }

    @Test
    void testDefendOnceWithStonesCorrectCost() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));

        SHIELD.accept(DEFENCEVISITOR);

        assertEquals(47.5, SHIELD.getBaseStrength());
    }

    @Test
    void testDefendTwiceWithStonesCorrectStrength() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));
        SHIELD.accept(DEFENCEVISITOR);
        SHIELD.addStone(new GemStone(MagicColor.RED, 30, 20));

        assertEquals(71.25, SHIELD.accept(DEFENCEVISITOR));
    }


    @Test
    void testDefendTwiceWithStonesCorrectCost() {
        SHIELD.addStone(new GemStone(MagicColor.RED, 20, 2));
        SHIELD.accept(DEFENCEVISITOR);
        SHIELD.addStone(new GemStone(MagicColor.RED, 30, 20));

        SHIELD.accept(DEFENCEVISITOR);

        assertEquals(25, SHIELD.getBaseStrength());
    }
}
