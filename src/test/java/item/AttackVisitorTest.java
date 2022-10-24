package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackVisitorTest {

    AttackVisitor ATTACK_VISITOR;
    Shield SHIELD;
    Armor ARMOR;
    MagicBag MAGIC_BAG;

    @BeforeEach
    void setup() {
        ATTACK_VISITOR = new AttackVisitor();
        List<MagicSocket> defenceSockets = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        List<MagicSocket> attackDefenceSockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));
        ARMOR = new Armor(50, defenceSockets);
        SHIELD = new Shield(50, defenceSockets);
        MAGIC_BAG = new MagicBag(50, List.of(new MagicSocket(MagicColor.PURPLE)));

    }

    @Test
    void testAttackVisitorVisitArmorReturnsZero() {
        assertEquals(0.0, ATTACK_VISITOR.visit(ARMOR));
    }

    @Test
    void testAttackVisitorVisitShieldReturnsZero() {
        assertEquals(0.0, ATTACK_VISITOR.visit(SHIELD));
    }

    @Test
    void testAttackVisitorVisitMagicBagReturnsZero() {
        assertEquals(0.0, ATTACK_VISITOR.visit(MAGIC_BAG));
    }
}
