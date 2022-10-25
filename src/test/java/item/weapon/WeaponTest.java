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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;
    AttackVisitor ATTACK_VISITOR;
    DefenceVisitor DEFENCE_VISITOR;


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

    @Test
    @Description("TestID AT1")
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
    @Description("TestID AT2")
    void testAttackNoSockets() {
        Weapon weapon = new Weapon(50, List.of(
                new MagicSocket(MagicColor.BLUE)));
        assertEquals(50, weapon.accept(ATTACK_VISITOR));
        assertEquals(49.5, weapon.getBaseStrength());
    }

    @Test
    @Description("TestID AT3")
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
    @Description("TestID AT4")
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
    @Description("TestID AT5")
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
    @Description("TestID AT6, DT6")
    void testAcceptNullVisitor() {
        assertThrows(NullPointerException.class, () -> WEAPON_WITH_THREE_SOCKETS.accept(null));
    }

    /*
    DT1	BS=50, Sockets med stenar BLÅ10RÖD10BLÅ10
    DT2	BS=-2,5, Sockets med stenar RÖD5RÖD10(COST=2)BLÅ10
    DT3	BS=50, Sockets med stenar BLÅ10BLÅ10
    DT4	BS=50, Sockets med stenar RÖD10RÖD10
    DT5	BS=50, TVÅ RÖDA TOMMA SOCKETS
     */

    @Test
    @Description("TestID DT1")
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

    @Test
    @Description("TestID DT2")
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

    @Test
    @Description("TestID DT3")
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

    @Test
    @Description("TestID DT4")
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

    @Test
    @Description("TestID DT5")
    void testDefenceEmptySockets() {
        List<MagicSocket> sockets = List.of(
                new MagicSocket(MagicColor.RED),
                new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, sockets);

        assertEquals(25, weapon.accept(DEFENCE_VISITOR),0.05);
        assertEquals(49.5, weapon.getBaseStrength(),0.05);
    }


}
