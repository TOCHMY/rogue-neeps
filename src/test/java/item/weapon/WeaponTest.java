package item.weapon;

import item.magic.GemStone;
import item.magic.MagicColor;
import item.magic.MagicSocket;
import item.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import item.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;
    List<MagicSocket> RED_SOCKET;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;
    AttackVisitor attackVisitor;
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
        attackVisitor = new AttackVisitor();
        defenceVisitor = new DefenceVisitor();

    }


    @Test
    public void testAttacksWithoutStones() {
        assertEquals(50, WEAPON_WITH_THREE_SOCKETS.accept(attackVisitor));
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.accept(attackVisitor));
        assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());

    }

    @Test
    void testAttackWithOneStone() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        assertEquals(54, WEAPON_WITH_THREE_SOCKETS.accept(attackVisitor));
        assertEquals(47.5, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
    }

    @Test
    void testAttackWithTwoStones() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        assertEquals(58, WEAPON_WITH_THREE_SOCKETS.accept(attackVisitor));
        assertEquals(45.5, WEAPON_WITH_THREE_SOCKETS.getBaseStrength());
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


}
