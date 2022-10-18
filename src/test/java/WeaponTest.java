

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon WEAPON_WITH_THREE_SOCKETS;

    @BeforeEach
    void createWeapon(){
        List<MagicSocket> sockets = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.GREEN));

        WEAPON_WITH_THREE_SOCKETS = new Weapon(50, sockets);

    }


    @Test
    public void testAttacksWithoutStones(){
        Assertions.assertEquals(50, WEAPON_WITH_THREE_SOCKETS.attack());
        Assertions.assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
        Assertions.assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.attack());
        Assertions.assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testAttackWithOneStone() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        Assertions.assertEquals(54, WEAPON_WITH_THREE_SOCKETS.attack());
        Assertions.assertEquals(47.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testAttackWithTwoStones() {
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        WEAPON_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.BLUE, 8, 2));
        Assertions.assertEquals(58, WEAPON_WITH_THREE_SOCKETS.attack());
        Assertions.assertEquals(45.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    public void testDefenceWithoutStones(){
        Assertions.assertEquals(50, WEAPON_WITH_THREE_SOCKETS.defend());
        Assertions.assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.getStrength());
        Assertions.assertEquals(49.5, WEAPON_WITH_THREE_SOCKETS.defend());
        Assertions.assertEquals(49, WEAPON_WITH_THREE_SOCKETS.getStrength());
    }



}
