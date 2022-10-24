package item.weapon;

import item.GemStone;
import item.MagicColor;
import item.MagicSocket;
import item.Weapon;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import item.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;
    AttackVisitor ATTACK_VISITOR;
    DefenceVisitor defenceVisitor;


    @BeforeEach
    void setup() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));

        WEAPON_WITH_THREE_SOCKETS = new Weapon(50, sockets);
        GREEN_SOCKET = List.of
                (new MagicSocket(MagicColor.GREEN));
        PURPLE_SOCKET = List.of
                (new MagicSocket(MagicColor.PURPLE));
        ATTACK_VISITOR = new AttackVisitor();
        defenceVisitor = new DefenceVisitor();


    }


    @Test
    public void testDefenceOnceWithoutStones() {
        assertEquals(25, WEAPON_WITH_THREE_SOCKETS.accept(defenceVisitor));
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @Test
    void testDefenceTwiceWithoutStones() {
        WEAPON_WITH_THREE_SOCKETS.accept(defenceVisitor);
        assertEquals(24.75, WEAPON_WITH_THREE_SOCKETS.accept(defenceVisitor));
        assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @ParameterizedTest
    @EnumSource(value = MagicColor.class, names = {"GREEN", "PURPLE"})
    void testCreateWeaponWithWrongSocket(MagicColor color) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, List.of(new MagicSocket(color)));
        });
    }

    @Test
    @Description("TestID T1")
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

    @Test
    @Description("TestID T2")
    void testAttackNoSockets() {
        Weapon weapon = new Weapon(50, List.of(
                new MagicSocket(MagicColor.BLUE)));
        assertEquals(50, weapon.accept(ATTACK_VISITOR));
        assertEquals(49.5, weapon.getBaseStrength());
    }

    @Test
    @Description("TestID T3")
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

    @Test
    @Description("TestID T4")
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

    @Test
    @Description("TestID T5")
    void testAcceptAttackVisitorOnlyRedStones() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.RED, 10, 1));

        assertEquals(50, weapon.accept(ATTACK_VISITOR));
        assertEquals(49.5, weapon.getBaseStrength());
    }

    @Test
    @Description("TestID T6")
    void testAcceptNullVisitor() {
        assertThrows(NullPointerException.class, () -> WEAPON_WITH_THREE_SOCKETS.accept(null));
    }
}
