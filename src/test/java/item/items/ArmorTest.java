package item.items;

import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.DefenceVisitor;
import item.visitors.IntelligenceVisitor;
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
    static final DefenceVisitor DEFENCE_VISITOR = new DefenceVisitor();
    static  final IntelligenceVisitor INTELLIGENCE_VISITOR = new IntelligenceVisitor();

    @BeforeEach
    public void createSockets() {
        THREE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.GREEN));
        ARMOR_WITH_THREE_SOCKETS = new Armor(50, THREE_SOCKETS);
    }

    @Test
    public void testCreateArmor() {
        Armor armor = new Armor(50, THREE_SOCKETS);
        assertEquals(50, armor.getBaseStrength());
        assertEquals(THREE_SOCKETS, armor.getSockets());
    }

    @Test
    void testDefendWithoutStones() {
        assertEquals(50, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(49.5, ARMOR_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @Test
    void testDefendOnceWithRedStones() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 20, 2));
        assertEquals(60, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(47.5, ARMOR_WITH_THREE_SOCKETS.getBaseStrength());
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(25, ARMOR_WITH_THREE_SOCKETS.getBaseStrength());
    }
    @Test
    void testDefendTwiceWithRedStones() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 20, 2));
        ARMOR_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR);
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(25, ARMOR_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @ParameterizedTest
    @EnumSource(value = MagicColor.class, names = {"PURPLE", "BLUE"})
    void testCreateArmorWithWrongSocket(MagicColor color) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Armor(50, List.of(new MagicSocket(color)));
        });
    }

    @Test
    void testIntelligenceWithoutStone() {
        assertEquals(0, ARMOR_WITH_THREE_SOCKETS.accept(INTELLIGENCE_VISITOR));
    }

    @Test
    void testGetIntelligenceFromGreenStone() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.GREEN, 10,0));
        assertEquals(5, ARMOR_WITH_THREE_SOCKETS.accept(INTELLIGENCE_VISITOR));
    }
}
