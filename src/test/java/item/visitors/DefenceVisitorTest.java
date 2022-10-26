package item.visitors;

import item.items.Armor;
import item.items.Shield;
import item.items.Weapon;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.DefenceVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefenceVisitorTest {

    DefenceVisitor DEFENCE_VISITOR;

    Weapon WEAPON;

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
        WEAPON = new Weapon(50, attackDefenceSockets);
    }

    @Test
    void testDefenceVisitorVisitWeaponReturnsHalf() {
        assertEquals(25, DEFENCE_VISITOR.visit(WEAPON));
    }


}
