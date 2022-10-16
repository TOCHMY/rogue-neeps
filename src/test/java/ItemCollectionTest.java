import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCollectionTest {

    private ItemCollection ITEM_COLLECTION;
    private List<MagicSocket> DEFENCE_SOCKETS;

    @BeforeEach
    void setup() {
        ITEM_COLLECTION = new ItemCollection();
        DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
    }

    @Test
    void testInitializeItemCollection() {
        ItemCollection items = new ItemCollection();
        assertNotNull(items);
        System.out.println(items.getHandItems());
        assertNull(items.getHandItems().get(0));
        assertNull(items.getHandItems().get(1));
    }

    @Test
    void testAddArmor() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        assertEquals(armor, ITEM_COLLECTION.getArmor());
    }

    @Test
    void testAddTooManyArmors() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);
        assertThrows(IllegalStateException.class, () -> {
            ITEM_COLLECTION.addArmor(armor);
        });
    }

    @Test
    void testRemoveArmor() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);
        assertEquals(armor, ITEM_COLLECTION.removeArmor());
        assertNull(ITEM_COLLECTION.getArmor());
    }
}
