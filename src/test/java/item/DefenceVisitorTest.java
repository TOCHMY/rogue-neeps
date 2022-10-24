package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefenceVisitorTest {

    DefenceVisitor DEFENCE_VISITOR;
    Shield SHIELD;
    Armor ARMOR;
    Weapon WEAPON;
    MagicBag MAGIC_BAG;

    @BeforeEach
    void setup() {
        DEFENCE_VISITOR = new DefenceVisitor();
        List<MagicSocket> defenceSockets = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        List<MagicSocket> attackDefenceSockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));
        ARMOR = new Armor(50, defenceSockets);
        WEAPON = new Weapon(50, attackDefenceSockets);
        SHIELD = new Shield(50, defenceSockets);
        MAGIC_BAG = new MagicBag(50, List.of(new MagicSocket(MagicColor.PURPLE)));
    }

    @Test
    void testDefenceVisitorVisitWeaponReturnsHalf() {
        assertEquals(25, DEFENCE_VISITOR.visit(WEAPON));
    }

    @Test
    void testDefenceVisitorVisitMagicBagReturnsZero() {
        assertEquals(0.0, DEFENCE_VISITOR.visit(MAGIC_BAG));
    }
}
