import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    @Test
    public void testWeaponCreatedWithParameters() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(50, magicSockets);
        assertNotNull(weapon);
        assertEquals(50, weapon.getStrength());
        assertEquals(magicSockets, weapon.getSockets());
    }

    @Test
    public void testWeaponCreatedWithMultipleSockets() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, magicSockets);
        assertEquals(50, weapon.getStrength());
    }

    @Test
    public void testWeaponCreationTooManySockets() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED));
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, magicSockets);
        });
    }

    @Test
    public void testWeaponCreationWithNoSockets() {
        List<MagicSocket> magicSockets = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(50, magicSockets);
        });
    }

    @Test
    public void testCreateWeaponWithIllegalStrength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weapon(101, List.of(new MagicSocket(MagicColor.BLUE)));
        });
    }

    @Test
    public void testAddGemStoneToWeapon() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.GREEN));

        Weapon weapon = new Weapon(50, sockets);

        weapon.addStone(new GemStone(MagicColor.BLUE, 5, 5));
        assertEquals(1, weapon.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.BLUE)).count());

        weapon.addStone(new GemStone(MagicColor.BLUE, 5, 5));
        assertEquals(2, weapon.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.BLUE)).count());

        weapon.addStone(new GemStone(MagicColor.RED, 5, 5));
        assertEquals(1, weapon.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.RED)).count());
    }

    @Test
    public void testAddGemStoneOfWrongColor() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE));

        Weapon weapon = new Weapon(50, sockets);

        assertThrows(IllegalArgumentException.class, () -> {
            weapon.addStone(new GemStone(MagicColor.RED, 5, 5));
        });


    }


}
