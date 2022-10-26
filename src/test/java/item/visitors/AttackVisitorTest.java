package item.visitors;

import item.items.Armor;
import item.items.Shield;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackVisitorTest {

    AttackVisitor ATTACK_VISITOR;
    Shield SHIELD;
    Armor ARMOR;

    @BeforeEach
    void setup() {
        ATTACK_VISITOR = new AttackVisitor();
        List<MagicSocket> defenceSockets = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        ARMOR = new Armor(50, defenceSockets);
        SHIELD = new Shield(50, defenceSockets);

    }

    @Test
    void testAttackVisitorVisitArmorReturnsZero() {
        assertEquals(0.0, ATTACK_VISITOR.visit(ARMOR));
    }

    @Test
    void testAttackVisitorVisitShieldReturnsZero() {
        assertEquals(0.0, ATTACK_VISITOR.visit(SHIELD));
    }

}
