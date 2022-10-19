import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;

    @BeforeEach
    void createWeapon(){
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));

        WEAPON_WITH_THREE_SOCKETS = new Weapon(50, sockets);

    }

    @Test
    public void testItemCreatedWithParameters() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE));
        Weapon weapon = new Weapon(50, magicSockets);
        assertNotNull(weapon);
        assertEquals(50, weapon.getStrength());
        assertEquals(magicSockets, weapon.getSockets());
    }

    @Test
    public void testItemCreatedWithMultipleSockets() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED));
        Weapon weapon = new Weapon(50, magicSockets);
        assertEquals(50, weapon.getStrength());
    }

    @Test
    public void testItemCreationTooManySockets() {
        List<MagicSocket> magicSockets = List.of(new MagicSocket(MagicColor.BLUE), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED), new MagicSocket(MagicColor.RED));
        assertThrows(IllegalArgumentException.class, () -> new Weapon(50, magicSockets));
    }

    @Test
    public void testItemCreationWithNoSockets() {
        List<MagicSocket> magicSockets = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Weapon(50, magicSockets));
    }

    @Test
    public void testCreateItemWithIllegalStrength() {
        assertThrows(IllegalArgumentException.class, () -> new Weapon(101, List.of(new MagicSocket(MagicColor.BLUE))));
    }

    @Test
    public void testAddGemStoneToItem() {

        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 5, 5));
        assertEquals(1, WEAPON_WITH_THREE_SOCKETS.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.BLUE)).count());

        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 5, 5));
        assertEquals(2, WEAPON_WITH_THREE_SOCKETS.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.BLUE)).count());

        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 5, 5));
        assertEquals(1, WEAPON_WITH_THREE_SOCKETS.getSockets().stream()
                .filter(magicSocket -> magicSocket.getGemStone() != null)
                .filter(magicSocket -> magicSocket.getGemStone().getColor().equals(MagicColor.RED)).count());
    }

    @Test
    public void testAddGemStoneOfWrongColor() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE));

        Weapon weapon = new Weapon(50, sockets);

        assertThrows(IllegalArgumentException.class, () -> weapon.addStone(new GemStone(MagicColor.RED, 5, 5)));
    }

    @Test
    public void testAddTooManyGemStonesOfSameColor() {
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE));

        Weapon weapon = new Weapon(50, sockets);
        weapon.addStone(new GemStone(MagicColor.BLUE, 5, 5));

        assertThrows(IllegalArgumentException.class, () -> weapon.addStone(new GemStone(MagicColor.BLUE, 5, 5)));
    }
}
