package item.visitors;

import item.items.Shield;
import item.items.Weapon;
import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntelligenceVisitorTest {

    @Test
    void testIntelligenceVisitorOnAttackItemStrength() {
        IntelligenceVisitor intelligenceVisitor = new IntelligenceVisitor();

        Weapon weapon = new Weapon(50, List.of(new MagicSocket(MagicColor.BLUE)));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10,10));

        assertEquals(0, intelligenceVisitor.visit(weapon));
    }

    @Test
    void testIntelligenceVisitorOnDefenceItemStrength() {
        IntelligenceVisitor intelligenceVisitor = new IntelligenceVisitor();

        Shield shield = new Shield(50, List.of(new MagicSocket(MagicColor.RED)));
        shield.addStone(new GemStone(MagicColor.RED, 10,10));

        assertEquals(0, intelligenceVisitor.visit(shield));
    }

    @Test
    void testIntelligenceVisitorOnAttackItemCost() {
        IntelligenceVisitor intelligenceVisitor = new IntelligenceVisitor();
        Weapon weapon = new Weapon(50, List.of(new MagicSocket(MagicColor.BLUE)));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10,10));

        intelligenceVisitor.visit(weapon);

        assertEquals(50, weapon.getBaseStrength());
    }

    @Test
    void testIntelligenceVisitorOnDefenceItemCost() {
        IntelligenceVisitor intelligenceVisitor = new IntelligenceVisitor();
        Shield shield = new Shield(50, List.of(new MagicSocket(MagicColor.RED)));
        shield.addStone(new GemStone(MagicColor.RED, 10,10));

        intelligenceVisitor.visit(shield);

        assertEquals(50, shield.getBaseStrength());
    }




}
