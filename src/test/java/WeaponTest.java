

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(50, attackVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
        assertEquals(49.5, attackVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getStrength());

    }

    @Test
    void testAttackWithOneStone() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        assertEquals(54, attackVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(47.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testAttackWithTwoStones() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        assertEquals(58, attackVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(45.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    public void testDefenceWithoutStones() {
        assertEquals(25, defenceVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
        assertEquals(24.75, defenceVisitor.visit(WEAPON_WITH_THREE_SOCKETS));
        assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testCreateWeaponWithWrongStones() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, GREEN_SOCKET);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, PURPLE_SOCKET);
        });
    }


}
