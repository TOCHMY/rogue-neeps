package item.armor;

import item.armor.Armor;
import item.magic.GemStone;
import item.magic.MagicColor;
import item.magic.MagicSocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import item.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArmorTest {

    List<MagicSocket> THREE_SOCKETS;
    Armor ARMOR_WITH_THREE_SOCKETS;
    DefenceVisitor DEFENCEVISITOR;
    List<MagicSocket> BLUE_SOCKET;
    List<MagicSocket> GREEN_SOCKET;
    List<MagicSocket> PURPLE_SOCKET;

    @BeforeEach
    public void createSockets(){
        THREE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        ARMOR_WITH_THREE_SOCKETS = new Armor(50, THREE_SOCKETS);
        DEFENCEVISITOR = new DefenceVisitor();
        BLUE_SOCKET =  List.of
                (new MagicSocket(MagicColor.BLUE));
        GREEN_SOCKET =  List.of
                (new MagicSocket(MagicColor.GREEN));
        PURPLE_SOCKET =  List.of
                (new MagicSocket(MagicColor.PURPLE));
    }

    @Test
    public void testCreateArmor(){
        Armor armor = new Armor(50, THREE_SOCKETS);
        assertEquals(50, armor.getStrength());
        assertEquals(THREE_SOCKETS, armor.getSockets());
    }

    @Test
    void testDefendWithoutStones() {
        assertEquals(50, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(49.5, ARMOR_WITH_THREE_SOCKETS.getStrength());
    }
    @Test
    void testDefendWitStones() {
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 20, 2));
        assertEquals(60, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(47.5, ARMOR_WITH_THREE_SOCKETS.getStrength());
        ARMOR_WITH_THREE_SOCKETS.addStone(new GemStone(MagicColor.RED, 30, 20));
        assertEquals(71.25, ARMOR_WITH_THREE_SOCKETS.accept(DEFENCEVISITOR));
        assertEquals(25, ARMOR_WITH_THREE_SOCKETS.getStrength());
    }

    @Test
    void testCreateArmorWithWrongStones() {
        assertThrows(IllegalArgumentException.class, ()->{
            new Armor(50, BLUE_SOCKET);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            new Armor(50, GREEN_SOCKET);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            new Armor(50, PURPLE_SOCKET);
        });
    }

}
