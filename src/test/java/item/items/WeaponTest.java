package item.items;

import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import item.visitors.AttackVisitor;
import item.visitors.DefenceVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;
    AttackVisitor ATTACK_VISITOR;
    DefenceVisitor DEFENCE_VISITOR;

    @BeforeEach
    void setup() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));

        WEAPON_WITH_THREE_SOCKETS = new Weapon(50, sockets);
        ATTACK_VISITOR = new AttackVisitor();
        DEFENCE_VISITOR = new DefenceVisitor();
    }

    @Test
    public void testDefenceOnceWithoutStones() {
        assertEquals(25, WEAPON_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @Test
    void testDefenceTwiceWithoutStones() {
        WEAPON_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR);
        assertEquals(24.75, WEAPON_WITH_THREE_SOCKETS.accept(DEFENCE_VISITOR));
        assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @ParameterizedTest
    @EnumSource(value = MagicColor.class, names = {"GREEN", "PURPLE"})
    void testCreateWeaponWithWrongSocket(MagicColor color) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, List.of(new MagicSocket(color)));
        });
    }

    //("TestID AT1")
    @Test
    void testAcceptAttackVisitor5MixedStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 2));
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));

        assertEquals(60, weapon.accept(ATTACK_VISITOR));
        assertEquals(46.5, weapon.getBaseStrength());
    }

    //("TestID AT2")
    @Test
    void testAttackNoSockets() {
        Weapon weapon = new Weapon(50, List.of(
                new MagicSocket(MagicColor.BLUE)));
        assertEquals(50, weapon.accept(ATTACK_VISITOR));
        assertEquals(49.5, weapon.getBaseStrength());
    }

    //("TestID AT3")
    @Test
    void testAttackSocketsNegativeBaseStrength() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(1, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 5, 1));
        weapon.accept(ATTACK_VISITOR);

        assertEquals(-1.725, weapon.accept(ATTACK_VISITOR), 0.05);
        assertEquals(-4, weapon.getBaseStrength());
    }

    //("TestID AT4")
    @Test
    void testAcceptAttackVisitorOnlyBlueStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));

        assertEquals(65, weapon.accept(ATTACK_VISITOR));
        assertEquals(46.5, weapon.getBaseStrength());
    }

    //("TestID AT5")
    @Test
    void testAcceptAttackVisitorOnlyRedStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));

        assertEquals(50, weapon.accept(ATTACK_VISITOR));
        assertEquals(49.5, weapon.getBaseStrength());
    }

    //("TestID AT6, DT6")
    @Test
    void testAcceptNullVisitor() {
        assertThrows(NullPointerException.class, () -> WEAPON_WITH_THREE_SOCKETS.accept(null));
    }

    //("TestID DT1")
    @Test
    void testDefenceWithMixedSockets() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));

        assertEquals(27.5, weapon.accept(DEFENCE_VISITOR));
        assertEquals(48.5, weapon.getBaseStrength());
    }

    //("TestID DT2")
    @Test
    void testDefenceNegativeStrengthOrderedSocketsDifferentStrengthAndCost() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(1, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 5, 1));
        weapon.addStone(new GemStone(MagicColor.RED, 10, 2));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.accept(DEFENCE_VISITOR);

        assertEquals(-1.4375, weapon.accept(DEFENCE_VISITOR),0.05);
        assertEquals(-6, weapon.getBaseStrength(),0.05);
    }

    //("TestID DT3")
    @Test
    void testDefenceBlueStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.BLUE),
                new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));
        weapon.addStone(new GemStone(MagicColor.BLUE, 10, 1));

        assertEquals(25, weapon.accept(DEFENCE_VISITOR),0.05);
        assertEquals(49.5, weapon.getBaseStrength(),0.05);
    }

    //("TestID DT4")
    @Test
    void testDefenceRedStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));

        assertEquals(30, weapon.accept(DEFENCE_VISITOR),0.05);
        assertEquals(47.5, weapon.getBaseStrength(),0.05);
    }

    //("TestID DT5")
    @Test
    void testDefenceEmptySockets() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);

        assertEquals(25, weapon.accept(DEFENCE_VISITOR),0.05);
        assertEquals(49.5, weapon.getBaseStrength(),0.05);
    }


}
